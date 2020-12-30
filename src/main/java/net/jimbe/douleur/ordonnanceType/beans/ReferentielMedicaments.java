package net.jimbe.douleur.ordonnanceType.beans;

import java.util.ArrayList;
import java.util.List;

import net.jimbe.douleur.ordonnanceType.entities.FormeMedicale;
import net.jimbe.douleur.ordonnanceType.entities.Produit;
import net.jimbe.douleur.ordonnanceType.entities.UniteDosage;

public class ReferentielMedicaments {

	List<FormeMedicale> formesMedicamenteuses;
	List<Produit> listeProduits;
	List<UniteDosage> listeUnitesDosage;
	
	public List<FormeMedicale> getFormesMedicamenteuses() {
		if (formesMedicamenteuses == null) {
			formesMedicamenteuses = new ArrayList<FormeMedicale>();
		}
		return formesMedicamenteuses;
	}
	
	public void setFormesMedicamenteuses(List<FormeMedicale> formesMedicamenteuses) {
		this.formesMedicamenteuses = formesMedicamenteuses;
	}
	
	public List<Produit> getListeProduits() {
		if (listeProduits == null) {
			listeProduits = new ArrayList<Produit>();
		}
		return listeProduits;
	}
	
	public void setListeProduits(List<Produit> listeProduits) {		
		this.listeProduits = listeProduits;
	}
	
	public List<UniteDosage> getListeUnitesDosage() {
		if (listeUnitesDosage == null) {
			listeUnitesDosage = new ArrayList<UniteDosage>();
		}
		return listeUnitesDosage;
	}
	
	public void setListeUnitesDosage(List<UniteDosage> listeUnitesDosage) {
		this.listeUnitesDosage = listeUnitesDosage;
	}
	
	
	
}
