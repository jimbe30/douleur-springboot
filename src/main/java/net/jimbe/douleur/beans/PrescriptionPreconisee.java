package net.jimbe.douleur.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.jimbe.douleur.entity.Nomenclature;
import net.jimbe.douleur.entity.Preconisation;
import net.jimbe.douleur.exceptions.NomenclatureDouleurException;

public class PrescriptionPreconisee {
	
	private List<Preconisation> medicamentsPreconises = new ArrayList<Preconisation>();
	private Nomenclature nomenclatureDouleur;
	

	public List<Preconisation> getMedicamentsPreconises() {
		return medicamentsPreconises;
	}

	public void setMedicamentsPreconises(List<Preconisation> medicamentsPreconises) {
		this.medicamentsPreconises = medicamentsPreconises;
	}
	
	public void addMedicamentPreconise(Preconisation medicamentPreconise) {
		if (medicamentPreconise.getNomenclatureDouleur() != null) {
			if (nomenclatureDouleur == null) {
				nomenclatureDouleur = medicamentPreconise.getNomenclatureDouleur();
			} else if (nomenclatureDouleur.getId() != medicamentPreconise.getNomenclatureDouleur().getId()) {
				throw new NomenclatureDouleurException("Le médicament préconisé ne concerne pas la douleur traitée");
			}
		}
		medicamentsPreconises.add(medicamentPreconise);
	}
	
	@JsonIgnore
	public Preconisation getLastMedicamentPreconise() {
		return medicamentsPreconises.size() > 0 ? medicamentsPreconises.get(medicamentsPreconises.size()-1) : null;
	}

	public Nomenclature getNomenclatureDouleur() {
		return nomenclatureDouleur;
	}

	public void setNomenclatureDouleur(Nomenclature nomenclatureDouleur) {
		this.nomenclatureDouleur = nomenclatureDouleur;
	}
	
	
	
	
	

}
