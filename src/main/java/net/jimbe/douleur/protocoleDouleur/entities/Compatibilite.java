package net.jimbe.douleur.protocoleDouleur.entities;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=50)
	private String dosages;
	
	@Column(name="unite_dosage", nullable=false, length=20)
	private String uniteDosage;

	//bi-directional many-to-one association to Preconisation
	@ManyToOne
	@JoinColumn(name="id_preconisation")
	@JsonIgnore
	private Preconisation preconisation;
	
	@Column(name="id_preconisation", insertable=false, updatable=false)
	private Long idPreconisation;

	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="id_produit", nullable=false, insertable = false, updatable = false)
	private Produit produit;
	
	@Column(name="id_produit", nullable=false)
	private Long idProduit;

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

	public Long getIdPreconisation() {
		return idPreconisation;
	}

	public void setIdPreconisation(Long idPreconisation) {
		this.idPreconisation = idPreconisation;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getUniteDosage() {
		return uniteDosage;
	}

	public void setUniteDosage(String uniteDosage) {
		this.uniteDosage = uniteDosage;
	}

}