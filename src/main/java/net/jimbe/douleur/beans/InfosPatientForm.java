package net.jimbe.douleur.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InfosPatientForm {

	@NotNull
	private String nomPatient;
	
	private String nomUsuPatient;
	
	private String prenomPatient;
	@JsonFormat(pattern="dd/MM/yyyy")
	@Past
	private Date dateNaissance;
	@Pattern(regexp="^[1-2][0-9]{2}(0[1-9]|1[0-2]|3[1-9]|4[0-2]|[5-9][0-9]|20)([0-9][1-9]|2[AB])[0-9]{6}",message="n° immatriculation erroné")  
	private String insee;
	private String sexe;

	public String getNomPatient() {
		return nomPatient;
	}
	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}
	public String getNomUsuPatient() {
		return nomUsuPatient;
	}
	public void setNomUsuPatient(String nomUsuPatient) {
		this.nomUsuPatient = nomUsuPatient;
	}
	public String getPrenomPatient() {
		return prenomPatient;
	}
	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getInsee() {
		return insee;
	}
	public void setInsee(String insee) {
		this.insee = insee;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return "InfosPatientForm [" + (nomPatient != null ? "nomPatient=" + nomPatient + ", " : "")
				+ (nomUsuPatient != null ? "nomUsuPatient=" + nomUsuPatient + ", " : "")
				+ (prenomPatient != null ? "prenomPatient=" + prenomPatient + ", " : "")
				+ (dateNaissance != null ? "dateNaissance=" + dateNaissance + ", " : "")
				+ (insee != null ? "insee=" + insee + ", " : "") + (sexe != null ? "sexe=" + sexe : "") + "]";
	}
	
	
	
	
	



	
}
