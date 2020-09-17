package net.jimbe.douleur.beans.protocoleDouleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PreconisationMedicament implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Correspond à l'entité Preconisation au niveau BdD
	 */

	private Long id;
	private String description;
	private Integer dureeMax;
	private Integer dureeMin;
	private List<String> formes;
	private String frequencePrecision;
	private Integer frequenceMax;
	private Integer frequenceMin;
	@JsonIgnore
	private PreconisationOrdonnance preconisationOrdonnance;
	private List<PreconisationProduit> produits;

	public PreconisationMedicament() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(Integer dureeMax) {
		this.dureeMax = dureeMax;
	}

	public Integer getDureeMin() {
		return this.dureeMin;
	}

	public void setDureeMin(Integer dureeMin) {
		this.dureeMin = dureeMin;
	}

	public List<String> getFormes() {
		return this.formes;
	}

	public void setFormes(List<String> formes) {
		this.formes = formes;
	}

	public String getFrequencePrecision() {
		return this.frequencePrecision;
	}

	public void setFrequencePrecision(String frequencePrecision) {
		this.frequencePrecision = frequencePrecision;
	}

	public Integer getFrequenceMax() {
		return this.frequenceMax;
	}

	public void setFrequenceMax(Integer frequenceMax) {
		this.frequenceMax = frequenceMax;
	}

	public Integer getFrequenceMin() {
		return this.frequenceMin;
	}

	public void setFrequenceMin(Integer frequenceMin) {
		this.frequenceMin = frequenceMin;
	}

	public PreconisationOrdonnance getPreconisationOrdonnance() {
		return this.preconisationOrdonnance;
	}

	public void setPreconisationOrdonnance(PreconisationOrdonnance preconisationOrdonnance) {
		this.preconisationOrdonnance = preconisationOrdonnance;
	}

	public PreconisationProduit addPreconisationProduit(PreconisationProduit preconisationProduit) {
		getProduits().add(preconisationProduit);
		preconisationProduit.setPreconisationMedicament(this);

		return preconisationProduit;
	}

	public PreconisationProduit removePreconisationProduit(PreconisationProduit preconisationProduit) {
		getProduits().remove(preconisationProduit);
		preconisationProduit.setPreconisationMedicament(null);

		return preconisationProduit;
	}

	public List<PreconisationProduit> getProduits() {
		if (produits == null) {
			produits = new ArrayList<PreconisationProduit>();
		}
		return produits;
	}

	public void setProduits(List<PreconisationProduit> produits) {
		this.produits = produits;
	}

}