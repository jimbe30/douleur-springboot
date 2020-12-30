package net.jimbe.douleur.protocoleDouleur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jimbe.douleur.protocoleDouleur.beans.PreconisationMedicament;
import net.jimbe.douleur.protocoleDouleur.beans.PreconisationOrdonnance;
import net.jimbe.douleur.protocoleDouleur.beans.PreconisationProduit;
import net.jimbe.douleur.protocoleDouleur.beans.ProtocoleDouleur;
import net.jimbe.douleur.protocoleDouleur.dao.NomenclatureRepository;
import net.jimbe.douleur.protocoleDouleur.dao.PreconisationRepository;
import net.jimbe.douleur.protocoleDouleur.entities.Compatibilite;
import net.jimbe.douleur.protocoleDouleur.entities.Nomenclature;
import net.jimbe.douleur.protocoleDouleur.entities.Preconisation;

@Service
public class ProtocoleDouleurService {

	@Autowired
	private NomenclatureRepository nomenclatureRepository;
	@Autowired
	private PreconisationRepository preconisationRepository;



	@Transactional
	public ProtocoleDouleur enregistrer(ProtocoleDouleur protocoleDouleur) {

		if (protocoleDouleur != null && protocoleDouleur.getIdDouleur() != null) {
			supprimer(protocoleDouleur.getIdDouleur());
		}

		Nomenclature nomenclature = convertirProtocoleEnNomenclature(protocoleDouleur);
		nomenclature = nomenclatureRepository.save(nomenclature);
		return convertirNomenclatureEnProtocole(nomenclature);
	}
	
	
	public boolean supprimer(Long idDouleur) {
		nomenclatureRepository.deleteById(idDouleur);
		return true;
	}

	protected Nomenclature getNomenclature(Long idDouleur) {

		Nomenclature result = null;
		if (idDouleur != null) {
			try {
				result = nomenclatureRepository.findById(idDouleur).get();
			} catch (NoSuchElementException e) {
				result = null;
			}
		}
		if (result == null) {
			result = new Nomenclature();
		}
		return result;
	}

	protected Nomenclature convertirProtocoleEnNomenclature(ProtocoleDouleur protocoleDouleur) {

		Nomenclature nomenclature = null;

		if (protocoleDouleur != null) {

			nomenclature = getNomenclature(protocoleDouleur.getIdDouleur());
			nomenclature.setIdParent(protocoleDouleur.getIdParent());
			nomenclature.setInfosGenerales(protocoleDouleur.getInfosGenerales());
			nomenclature.setRecommandations(protocoleDouleur.getRecommandations());
			nomenclature.setLibelle(protocoleDouleur.getLibelle());

			int numOrdonnance = 0;

			for (PreconisationOrdonnance preconisationOrdonnance : protocoleDouleur.getPrescriptions()) {

				numOrdonnance++;
				int numMedicament = 0;

				for (PreconisationMedicament preconisationMedicament : preconisationOrdonnance.getMedicamentsPreconises()) {

					numMedicament++;
					Preconisation preconisation = nomenclature.getPreconisation(preconisationMedicament.getId());
					preconisation.setIdDouleur(protocoleDouleur.getIdDouleur());
					preconisation.setNumOrdonnance(numOrdonnance);
					preconisation.setNumMedicament(numMedicament);
					preconisation.setDescription(preconisationMedicament.getDescription());
					preconisation.setDureeMin(preconisationMedicament.getDureeMin());
					preconisation.setDureeMax(preconisationMedicament.getDureeMax());
					preconisation.setFrequenceMin(preconisationMedicament.getFrequenceMin());
					preconisation.setFrequenceMax(preconisationMedicament.getFrequenceMax());
					preconisation.setFrequenceAutre(preconisationMedicament.getFrequencePrecision());
					preconisation.setQuantiteMin(preconisationMedicament.getQuantiteMin());
					preconisation.setQuantiteMax(preconisationMedicament.getQuantiteMax());					
					preconisation.setFormes(preconisationMedicament.getFormes().stream()
							.reduce((str1, str2) -> str1.concat("|").concat(str2)).get());

					for (PreconisationProduit preconisationProduit : preconisationMedicament.getProduits()) {

						Compatibilite compatibilite = preconisation.getCompatibilite(preconisationProduit.getId());
						compatibilite.setIdProduit(preconisationProduit.getIdProduit());
						compatibilite.setUniteDosage(preconisationProduit.getUniteDosage());
						compatibilite.setIdPreconisation(preconisationMedicament.getId());
						compatibilite.setDosages(preconisationProduit.getListeDosages().stream()
								.reduce((str1, str2) -> str1.concat("|").concat(str2)).get());
					}

				}
			}
		}

		return nomenclature;
	}

	public ProtocoleDouleur chargerProtocole(Long idDouleur) {

		Nomenclature nomenclature = nomenclatureRepository.findById(idDouleur).get();
		return convertirNomenclatureEnProtocole(nomenclature);
	}

	protected ProtocoleDouleur convertirNomenclatureEnProtocole(Nomenclature nomenclature) {
		
		ProtocoleDouleur protocoleDouleur = new ProtocoleDouleur();
		
		if (nomenclature != null) {
			protocoleDouleur.setIdDouleur(nomenclature.getId());
			protocoleDouleur.setIdParent(nomenclature.getIdParent());
			protocoleDouleur.setInfosGenerales(nomenclature.getInfosGenerales());
			protocoleDouleur.setLibelle(nomenclature.getLibelle());
			protocoleDouleur.setRecommandations(nomenclature.getRecommandations());
			
			protocoleDouleur.setPrescriptions(getListePreconisationsOrdonnances(nomenclature.getId()));
		}		
		return protocoleDouleur;
	}

	protected List<PreconisationOrdonnance> getListePreconisationsOrdonnances(Long idDouleur) {
		List<PreconisationOrdonnance> listePreconisationsOrdonnances = new ArrayList<PreconisationOrdonnance>();
		List<Preconisation> preconisations = preconisationRepository.findByIdDouleur(idDouleur);
		if (preconisations != null) {
			preconisations.forEach(preconisation -> {				
				boolean memeOrdonnance = false;
				if (!listePreconisationsOrdonnances.isEmpty()) {
					PreconisationOrdonnance lastOrdonnance = listePreconisationsOrdonnances.get(listePreconisationsOrdonnances.size() - 1);
					PreconisationMedicament lastMedicamentPreconise = lastOrdonnance.getLastMedicamentPreconise();
					int lastNumOrdonnance = lastOrdonnance.getNumOrdonnance();
					if (lastMedicamentPreconise == null	|| preconisation.getNumOrdonnance() == lastNumOrdonnance) {
						lastOrdonnance.addMedicament(convertirPreconisationEnMedicament(preconisation));
						memeOrdonnance = true;
					}
				}
				if (!memeOrdonnance) {
					PreconisationOrdonnance preconisationOrdonnance = new PreconisationOrdonnance();
					listePreconisationsOrdonnances.add(preconisationOrdonnance);
					preconisationOrdonnance.setIdDouleur(idDouleur);
					preconisationOrdonnance.setNumOrdonnance(preconisation.getNumOrdonnance());
					PreconisationMedicament preconisationMedicament = convertirPreconisationEnMedicament(preconisation);
					preconisationOrdonnance.addMedicament(preconisationMedicament);
				} 
			});
		}
		return listePreconisationsOrdonnances;
	}

	protected PreconisationMedicament convertirPreconisationEnMedicament(Preconisation preconisation) {

		PreconisationMedicament preconisationMedicament = new PreconisationMedicament();
		if (preconisation != null) {
			preconisationMedicament.setDescription(preconisation.getDescription());
			preconisationMedicament.setDureeMax(preconisation.getDureeMax());
			preconisationMedicament.setDureeMin(preconisation.getDureeMin());
			preconisationMedicament.setFormes(Arrays.asList(preconisation.getFormes().split("\\|")));
			preconisationMedicament.setFrequenceMax(preconisation.getFrequenceMax());
			preconisationMedicament.setFrequenceMin(preconisation.getFrequenceMin());
			preconisationMedicament.setFrequencePrecision(preconisation.getFrequenceAutre());
			preconisationMedicament.setQuantiteMax(preconisation.getQuantiteMax());
			preconisationMedicament.setQuantiteMin(preconisation.getQuantiteMin());
			preconisationMedicament.setId(preconisation.getId());
			
			List<Compatibilite> compatibilites = preconisation.getCompatibilites();
			if (compatibilites != null) {
				
				compatibilites.forEach(compatibilite -> {
					PreconisationProduit preconisationProduit = convertirCompatibiliteEnProduit(compatibilite);
					preconisationMedicament.addPreconisationProduit(preconisationProduit);
				});				
			}
		}
		return preconisationMedicament;
	}

	protected PreconisationProduit convertirCompatibiliteEnProduit(Compatibilite compatibilite) {

		PreconisationProduit preconisationProduit = new PreconisationProduit();
		if (compatibilite != null) {
			preconisationProduit.setId(compatibilite.getId());
			preconisationProduit.setListeDosages(Arrays.asList(compatibilite.getDosages().split("\\|")));
			preconisationProduit.setUniteDosage(compatibilite.getUniteDosage());
			if (compatibilite.getProduit() != null) {
				preconisationProduit.setDescription(compatibilite.getProduit().getDesignation());
				preconisationProduit.setDesignation(compatibilite.getProduit().getDesignation());
				preconisationProduit.setIdProduit(compatibilite.getProduit().getId());
			}
		}
		return preconisationProduit;
	}

}
