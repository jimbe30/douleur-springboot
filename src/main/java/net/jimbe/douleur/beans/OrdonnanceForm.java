package net.jimbe.douleur.beans;

import java.util.ArrayList;
import java.util.List;

public class OrdonnanceForm {

	private int nbMedicaments;
	private List<Dispensation> medicaments = new ArrayList<>();
	private String recommandations;
	
	public void setMedicament(int index, Dispensation medicament) {
		this.medicaments.add(index, medicament);
	}
	
	public void addMedicament(Dispensation medicament) {
		this.medicaments.add(medicament);
	}

	public int getNbMedicaments() {
		return nbMedicaments;
	}

	public void setNbMedicaments(int nbMedicaments) {
		this.nbMedicaments = nbMedicaments;
	}

	public List<Dispensation> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(List<Dispensation> medicaments) {
		this.medicaments = medicaments;
	}

	public String getRecommandations() {
		return recommandations;
	}

	public void setRecommandations(String recommandations) {
		this.recommandations = recommandations;
	}
	
	@Override
	public String toString() {
		return "OrdonnanceForm [nbMedicaments=" + nbMedicaments + ", medicaments=" + medicaments + ", recommandations="
				+ recommandations + "]";
	}



	
}
