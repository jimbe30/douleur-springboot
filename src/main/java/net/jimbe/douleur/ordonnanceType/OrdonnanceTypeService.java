package net.jimbe.douleur.ordonnanceType;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jimbe.douleur.ordonnanceType.beans.ReferentielMedicaments;
import net.jimbe.douleur.ordonnanceType.dao.OrdonnanceTypeRepository;
import net.jimbe.douleur.ordonnanceType.dao.ReferentielMedicamentsDAO;
import net.jimbe.douleur.ordonnanceType.entities.FormeMedicale;
import net.jimbe.douleur.ordonnanceType.entities.OrdonnanceType;
import net.jimbe.douleur.ordonnanceType.entities.Produit;
import net.jimbe.douleur.ordonnanceType.entities.UniteDosage;

@Service
public class OrdonnanceTypeService {

	@Autowired
	OrdonnanceTypeRepository ordonnanceTypeRepository;
	
	@Autowired
	ReferentielMedicamentsDAO referentielMedicamentsDAO;

	@Transactional
	public OrdonnanceType enregistrer(OrdonnanceType ordonnanceType) {

		if (ordonnanceType != null) {
			if (ordonnanceType.getId() != null) {
				supprimerOrdonnanceType(ordonnanceType.getId());
			}
			ordonnanceType.setId(null);
			ordonnanceType = ordonnanceTypeRepository.save(ordonnanceType);
			return ordonnanceTypeRepository.findById(ordonnanceType.getId()).get();
		}
		return null;
	}
	
	
	public List<OrdonnanceType> lister() {
		List<OrdonnanceType> all = ordonnanceTypeRepository.findAll();
		return all;
	}
	
	@Transactional
	public boolean supprimerOrdonnanceType(Long id) {
		ordonnanceTypeRepository.deleteById(id);
		return true;
	}
	
	public ReferentielMedicaments chargerReferentielMedicaments() {
		
		ReferentielMedicaments referentielMedicaments = new ReferentielMedicaments();
		
		List<Produit> listeProduits = referentielMedicamentsDAO.listerProduits();
		List<FormeMedicale> listeFormesMedicales = referentielMedicamentsDAO.listerFormesMedicales();
		List<UniteDosage> listeUnitesDosage = referentielMedicamentsDAO.listerUnitesDosage();
		
		referentielMedicaments.setFormesMedicamenteuses(listeFormesMedicales);
		referentielMedicaments.setListeProduits(listeProduits);
		referentielMedicaments.setListeUnitesDosage(listeUnitesDosage);
		
		return referentielMedicaments;
	}


}
