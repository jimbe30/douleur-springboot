package net.jimbe.douleur.protocoleDouleur.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.jimbe.douleur.ordonnanceType.entities.Produit;

public class PreconisationProduit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Correspond à l'entité Compatibilite au niveau BdD
	 */

	private Long id;
	private Long idProduit;
	private List<String> listeDosages;
	private String uniteDosage;
	private String designation;
	@JsonIgnore
	private PreconisationMedicament preconisationMedicament;
	@JsonIgnore
	private Produit produit;
	private String description; 

	public PreconisationProduit() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getListeDosages() {
		return this.listeDosages;
	}

	public void setListeDosages(List<String> dosages) {
		this.listeDosages = dosages;
	}

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public String getUniteDosage() {
		return this.uniteDosage;
	}

	public void setUniteDosage(String uniteDosage) {
		this.uniteDosage = uniteDosage;
	}

	public PreconisationMedicament getPreconisationMedicament() {
		return this.preconisationMedicament;
	}

	public void setPreconisationMedicament(PreconisationMedicament preconisationMedicament) {
		this.preconisationMedicament = preconisationMedicament;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}