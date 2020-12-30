package net.jimbe.douleur.ordonnanceType.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import net.jimbe.douleur.ordonnanceType.entities.FormeMedicale;
import net.jimbe.douleur.ordonnanceType.entities.Produit;
import net.jimbe.douleur.ordonnanceType.entities.UniteDosage;

@Repository
public class ReferentielMedicamentsDAO   {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Produit> listerProduits() {
		
		List<Produit> resultList = entityManager
			.createNamedQuery("OrdonnanceTypeProduit.findAll", Produit.class)
			.getResultList();
		
		return resultList;

	}
	
	public List<UniteDosage> listerUnitesDosage() {
		
		List<UniteDosage> resultList = entityManager
			.createNamedQuery("UniteDosage.findAll", UniteDosage.class)
			.getResultList();
		
		return resultList;

	}
	
	public List<FormeMedicale> listerFormesMedicales() {
		
		List<FormeMedicale> resultList = entityManager
			.createNamedQuery("FormeMedicale.findAll", FormeMedicale.class)
			.getResultList();
		
		return resultList;

	}


	
}
