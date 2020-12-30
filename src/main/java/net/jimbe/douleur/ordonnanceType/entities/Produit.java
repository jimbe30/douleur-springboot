package net.jimbe.douleur.ordonnanceType.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the produit database table.
 * 
 */
@Entity(name = "OrdonnanceTypeProduit")
@Table(name="produit")
@NamedQuery(name="OrdonnanceTypeProduit.findAll", query="SELECT f FROM OrdonnanceTypeProduit f")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=10)
	private String code;

	@Column(nullable=false, length=50)
	private String designation;

	@Column(length=200)
	private String indesirable;

	@Column(length=200)
	private String indication;

	//bi-directional many-to-one association to ProduitType
	@OneToMany(mappedBy="produit")
	@JsonIgnore
	private List<ProduitType> produitTypes;

	public Produit() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getIndesirable() {
		return this.indesirable;
	}

	public void setIndesirable(String indesirable) {
		this.indesirable = indesirable;
	}

	public String getIndication() {
		return this.indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public List<ProduitType> getProduitTypes() {
		return this.produitTypes;
	}

	public void setProduitTypes(List<ProduitType> produitTypes) {
		this.produitTypes = produitTypes;
	}

	public ProduitType addProduitType(ProduitType produitType) {
		getProduitTypes().add(produitType);
		produitType.setProduit(this);

		return produitType;
	}

	public ProduitType removeProduitType(ProduitType produitType) {
		getProduitTypes().remove(produitType);
		produitType.setProduit(null);

		return produitType;
	}

	@Override
	public String toString() {
		return "Produit [" + (id != null ? "id=" + id + ", " : "") + (code != null ? "code=" + code + ", " : "")
				+ (designation != null ? "designation=" + designation + ", " : "")
				+ (indesirable != null ? "indesirable=" + indesirable + ", " : "")
				+ (indication != null ? "indication=" + indication : "") + "]";
	}

}