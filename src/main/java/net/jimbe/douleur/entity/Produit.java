package net.jimbe.douleur.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the produit database table.
 * 
 */
@Entity
@Table(name="produit")
@NamedQuery(name="Produit.findAll", query="SELECT p FROM Produit p")
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

	//bi-directional many-to-one association to Compatibilite
	@OneToMany(mappedBy="produit")
	private List<Compatibilite> compatibilites;

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

	public List<Compatibilite> getCompatibilites() {
		return this.compatibilites;
	}

	public void setCompatibilites(List<Compatibilite> compatibilites) {
		this.compatibilites = compatibilites;
	}

	public Compatibilite addCompatibilite(Compatibilite compatibilite) {
		getCompatibilites().add(compatibilite);
		compatibilite.setProduit(this);

		return compatibilite;
	}

	public Compatibilite removeCompatibilite(Compatibilite compatibilite) {
		getCompatibilites().remove(compatibilite);
		compatibilite.setProduit(null);

		return compatibilite;
	}

}