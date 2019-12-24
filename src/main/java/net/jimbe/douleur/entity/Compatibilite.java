package net.jimbe.douleur.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the compatibilite database table.
 * 
 */
@Entity
@Table(name="compatibilite")
@NamedQuery(name="Compatibilite.findAll", query="SELECT c FROM Compatibilite c")
public class Compatibilite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=50)
	private String dosages;

	//bi-directional many-to-one association to Preconisation
	@ManyToOne
	@JoinColumn(name="id_preconisation", nullable=false)
	@JsonIgnore
	private Preconisation preconisation;

	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="id_produit", nullable=false)
	private Produit produit;

	public Compatibilite() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDosages() {
		return this.dosages;
	}

	public void setDosages(String dosages) {
		this.dosages = dosages;
	}

	public Preconisation getPreconisation() {
		return this.preconisation;
	}

	public void setPreconisation(Preconisation preconisation) {
		this.preconisation = preconisation;
	}

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}