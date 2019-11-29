package net.jimbe.douleur.beans;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OrdonnanceForm {

	@NotNull @Valid
	private InfosPatientForm infosPatient;
	@NotNull @Valid
	private PrescriptionForm prescription;
	
	
	public InfosPatientForm getInfosPatient() {
		return infosPatient;
	}
	public void setInfosPatient(InfosPatientForm infosPatient) {
		this.infosPatient = infosPatient;
	}
	public PrescriptionForm getPrescription() {
		return prescription;
	}
	public void setPrescription(PrescriptionForm prescription) {
		this.prescription = prescription;
	}
	
	
	@Override
	public String toString() {
		return "OrdonnanceForm [" + (infosPatient != null ? "infosPatient=" + infosPatient + ", " : "")
				+ (prescription != null ? "prescription=" + prescription : "") + "]";
	}


	
}
