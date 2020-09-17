package net.jimbe.douleur.beans.protocoleDouleur;

import java.util.ArrayList;
import java.util.List;

public class ProtocoleDouleur {
	
	/**
	 * Correspond à l'entité Nomenclature au niveau BdD
	 */
	
	private Long idDouleur;
	private Long idParent;
	private String infosGenerales;
	private String libelle;
	private String recommandations;
	
	private List<PreconisationOrdonnance> prescriptions;

	public Long getIdDouleur() {
		return idDouleur;
	}

	public void setIdDouleur(Long idDouleur) {
		this.idDouleur = idDouleur;
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

	public String getInfosGenerales() {
		return infosGenerales;
	}

	public void setInfosGenerales(String infosGenerales) {
		this.infosGenerales = infosGenerales;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getRecommandations() {
		return recommandations;
	}

	public void setRecommandations(String recommandations) {
		this.recommandations = recommandations;
	}

	public List<PreconisationOrdonnance> getPrescriptions() {
		if (prescriptions == null) {
			prescriptions = new ArrayList<PreconisationOrdonnance>();
		}
		return prescriptions;
	}

	public void setPrescriptions(List<PreconisationOrdonnance> prescriptions) {
		this.prescriptions = prescriptions;
	}
	

}
