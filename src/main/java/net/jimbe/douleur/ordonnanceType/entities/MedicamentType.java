package net.jimbe.douleur.ordonnanceType.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the medicament_type database table.
 * 
 */
@Entity
@Table(name="medicament_type")
@NamedQuery(name="MedicamentType.findAll", query="SELECT m FROM MedicamentType m")
public class MedicamentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	@JsonIgnore
	private Long id;

	@Column(nullable=false, length=100)
	private String description;

	@Column(nullable=false, length=100)
	private String formes;

	//bi-directional many-to-one association to OrdonnanceType
	@ManyToOne
	@JoinColumn(name="id_ordo_type", nullable=false)
	@JsonIgnore
	private OrdonnanceType ordonnanceType;

	//bi-directional many-to-one association to ProduitType
	@OneToMany(mappedBy="medicament", cascade=CascadeType.PERSIST)
	private List<ProduitType> produits;
	
	public MedicamentType() {
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

	@JsonIgnore
	public String getConcatFormes() {
		return this.formes != null ? this.formes.trim() : "" ;
	}

	public void setConcatFormes(String formes) {
		this.formes = formes;
	}
	
	public List<String> getFormes() {
		return Arrays.asList(getConcatFormes().split("\\|"));
	}

	public void setFormes(List<String> formes) {
		setConcatFormes(formes.stream().reduce((a, b) -> a.concat("|").concat(b)).get());
	}

	public OrdonnanceType getOrdonnanceType() {
		return this.ordonnanceType;
	}

	public void setOrdonnanceType(OrdonnanceType ordonnanceType) {
		this.ordonnanceType = ordonnanceType;
	}

	public List<ProduitType> getProduits() {
		if (this.produits == null) {
			this.produits = new ArrayList<ProduitType>();
		}
		return this.produits;
	}

	public void setProduits(List<ProduitType> produitTypes) {
		this.produits = produitTypes;
		this.produits.forEach(prod -> prod.setMedicament(this));
	}

	public ProduitType addProduit(ProduitType produitType) {
		getProduits().add(produitType);
		produitType.setMedicament(this);

		return produitType;
	}

	public ProduitType removeProduit(ProduitType produitType) {
		getProduits().remove(produitType);
		produitType.setMedicament(null);

		return produitType;
	}


	@Override
	public String toString() {
		return "MedicamentType [" + (id != null ? "id=" + id + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (formes != null ? "formes=" + formes + ", " : "")
				+ (produits != null ? "produits=" + produits : "") + "]";
	}

}