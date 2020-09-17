package net.jimbe.douleur.beans.protocoleDouleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PreconisationOrdonnance implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Pas d'entité correspondante en BdD
	 */

	private String description;
	private Long idDouleur;
	private Long idOrdoType;
	private int numOrdonnance;
	private List<PreconisationMedicament> medicamentsPreconises;

	public PreconisationOrdonnance() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdDouleur() {
		return this.idDouleur;
	}

	public void setIdDouleur(Long idDouleur) {
		this.idDouleur = idDouleur;
	}

	public Long getIdOrdoType() {
		return this.idOrdoType;
	}

	public void setIdOrdoType(Long idOrdoType) {
		this.idOrdoType = idOrdoType;
	}
	
	
	public PreconisationMedicament addMedicament(PreconisationMedicament preconisationMedicament) {
		getMedicamentsPreconises().add(preconisationMedicament);
		preconisationMedicament.setPreconisationOrdonnance(this);
		return preconisationMedicament;
	}

	public PreconisationMedicament removeMedicament(PreconisationMedicament preconisationMedicament) {
		getMedicamentsPreconises().remove(preconisationMedicament);
		preconisationMedicament.setPreconisationOrdonnance(null);
		return preconisationMedicament;
	}

	public List<PreconisationMedicament> getMedicamentsPreconises() {
		
		if (medicamentsPreconises == null) {
			medicamentsPreconises = new ArrayList<PreconisationMedicament>();
		}		
		return medicamentsPreconises;
	}

	public void setMedicamentsPreconises(List<PreconisationMedicament> medicamentsPreconises) {
		this.medicamentsPreconises = medicamentsPreconises;
	}

	public PreconisationMedicament getLastMedicamentPreconise() {
		int size = getMedicamentsPreconises().size();
		return size > 0 ? getMedicamentsPreconises().get(size-1) : null; 
		
	}

	public int getNumOrdonnance() {
		return numOrdonnance;
	}

	public void setNumOrdonnance(int numOrdonnance) {
		this.numOrdonnance = numOrdonnance;
	}

}