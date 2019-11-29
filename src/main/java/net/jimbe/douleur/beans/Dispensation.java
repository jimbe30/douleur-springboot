package net.jimbe.douleur.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dispensation {

	private String libelle;
	private List<Map<String, String>> produits = new ArrayList<>();
	private String quantite;
	private String forme;
	private String frequence;
	private String duree;
	

	
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public List<Map<String, String>> getProduits() {
		return produits;
	}
	
	public void setProduits(List<Map<String, String>> produits) {
		this.produits = produits;
	}
	
	public void setProduit(int index, Map<String, String> produit) {
		this.produits.add(index, produit);
	}
	
	public void addProduit(Map<String, String> produit) {
		this.produits.add(produit);
	}
	
	@Override
	public String toString() {
		return "Dispensation [libelle=" + libelle + ", produits=" + produits + ", quantite=" + quantite + ", forme="
				+ forme + ", frequence=" + frequence + ", duree=" + duree + "]";
	}

	
	

	
}
