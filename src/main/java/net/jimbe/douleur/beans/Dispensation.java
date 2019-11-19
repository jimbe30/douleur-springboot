package net.jimbe.douleur.beans;

import java.util.ArrayList;
import java.util.List;

public class Dispensation {

	private String medicament;
	private List<String> dosages = new ArrayList<>();
	private String quantite;
	private String forme;
	private String frequence;
	private String duree;
	
	
	public String getMedicament() {
		return medicament;
	}
	
	public void setMedicament(String medicament) {
		this.medicament = medicament;
	}
	
	public List<String> getDosages() {
		return dosages;
	}
	
	public void setDosages(List<String> dosages) {
		this.dosages = dosages;
	}
	
	public void setDosage(int index, String dosage) {
		this.dosages.add(index, dosage);
	}
	
	public void addDosage(String dosage) {
		this.dosages.add(dosage);
	}
	
	public String getQuantite() {
		return quantite;
	}
	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}
	
	public String getForme() {
		return forme;
	}
	
	public void setForme(String forme) {
		this.forme = forme;
	}
	
	public String getFrequence() {
		return frequence;
	}
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}
	
	public String getDuree() {
		return duree;
	}
	
	public void setDuree(String duree) {
		this.duree = duree;
	}

	@Override
	public String toString() {
		return "Dispensation [medicament=" + medicament + ", dosages=" + dosages + ", quantite=" + quantite + ", forme="
				+ forme + ", frequence=" + frequence + ", duree=" + duree + "]";
	}

	
	

	
}
