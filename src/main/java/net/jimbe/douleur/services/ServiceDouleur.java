package net.jimbe.douleur.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jimbe.douleur.beans.PrescriptionPreconisee;
import net.jimbe.douleur.dao.PreconisationRepository;
import net.jimbe.douleur.entity.Preconisation;

@Service
public class ServiceDouleur {
	
	@Autowired
	PreconisationRepository preconisationRepository;
	
	
	public List<PrescriptionPreconisee> getPrescriptionsProposees(int idDouleur) {
		List<PrescriptionPreconisee> result = new ArrayList<PrescriptionPreconisee>();
		List<Preconisation> preconisations = preconisationRepository.findByIdDouleur(idDouleur);
		if (preconisations != null) {
			preconisations.forEach(preconisation -> {
				if (result.isEmpty()) {
					PrescriptionPreconisee prescriptionPreconisee = new PrescriptionPreconisee();
					prescriptionPreconisee.addMedicamentPreconise(preconisation);
					result.add(prescriptionPreconisee);
				} else {
					PrescriptionPreconisee lastPrescription = result.get(result.size()-1);
					Preconisation lastMedicamentPreconise = lastPrescription.getLastMedicamentPreconise();
					if (lastMedicamentPreconise == null || preconisation.getNumOrdonnance() == lastMedicamentPreconise.getNumOrdonnance()) {
						lastPrescription.addMedicamentPreconise(preconisation);
					} else {
						PrescriptionPreconisee prescriptionPreconisee = new PrescriptionPreconisee();
						prescriptionPreconisee.addMedicamentPreconise(preconisation);
						result.add(prescriptionPreconisee);
					}
				}
			});
		}
		return result;
	}
	
	


}
