package net.jimbe.douleur.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jimbe.douleur.beans.Prescription;
import net.jimbe.douleur.dao.PreconisationRepository;
import net.jimbe.douleur.entity.Preconisation;

@Service
public class ServiceDouleur {
	
	@Autowired
	PreconisationRepository preconisationRepository;
	
	
	public List<Prescription> getPrescriptionsProposees(int idDouleur) {
		List<Prescription> result = new ArrayList<Prescription>();
		List<Preconisation> preconisations = preconisationRepository.findByIdDouleur(idDouleur);
		if (preconisations != null) {
			preconisations.forEach(preconisation -> {
				if (result.isEmpty()) {
					Prescription prescription = new Prescription();
					prescription.addMedicamentPreconise(preconisation);
					result.add(prescription);
				} else {
					Prescription lastPrescription = result.get(result.size()-1);
					Preconisation lastMedicamentPreconise = lastPrescription.getLastMedicamentPreconise();
					if (lastMedicamentPreconise == null || preconisation.getNumOrdonnance() == lastMedicamentPreconise.getNumOrdonnance()) {
						lastPrescription.addMedicamentPreconise(preconisation);
					} else {
						Prescription prescription = new Prescription();
						prescription.addMedicamentPreconise(preconisation);
						result.add(prescription);
					}
				}
			});
		}
		return result;
	}


}
