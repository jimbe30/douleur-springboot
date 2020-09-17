package net.jimbe.douleur.entity.ordonnanceType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the produit_type database table.
 * 
 */
@Entity
@Table(name="produit_type")
@NamedQuery(name="ProduitType.findAll", query="SELECT p FROM ProduitType p")
public class ProduitType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	@JsonIgnore
	private Long id;

	@Column(nullable=false, length=100)
	private String description;

	@Column(nullable=false, length=100)
	@JsonIgnore
	private String dosages;

	@Column(name="unite_dosage", nullable=false, length=10)
	private String uniteDosage;
	
	@Column(name="id_produit", nullable=false, length=10)
	private Long idProduit;

	//bi-directional many-to-one association to MedicamentType
	@ManyToOne
	@JoinColumn(name="id_medic_type", nullable=false)
	@JsonIgnore
	private MedicamentType medicament;

	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="id_produit", insertable=false, updatable=false)
	@JsonIgnore
	private Produit produit;

	public ProduitType() {
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

	public String getDosages() {
		return this.dosages != null ? this.dosages.trim() : "";
	}

	public void setDosages(String dosages) {
		this.dosages = dosages;
	}

	public String getUniteDosage() {
		return this.uniteDosage;
	}

	public void setUniteDosage(String uniteDosage) {
		this.uniteDosage = uniteDosage;
	}

	public MedicamentType getMedicament() {
		return this.medicament;
	}

	public void setMedicament(MedicamentType medicamentType) {
		this.medicament = medicamentType;
	}

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public List<String> getListeDosages() {
		return Arrays.asList(getDosages().split("\\|"));
	}

	public void setListeDosages(List<String> listeDosages) {
		setDosages(listeDosages.stream().reduce((a, b) -> a.concat("|").concat(b)).get());
	}

	@Override
	public String toString() {
		return "ProduitType [" + (id != null ? "id=" + id + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (dosages != null ? "dosages=" + dosages + ", " : "")
				+ (uniteDosage != null ? "uniteDosage=" + uniteDosage + ", " : "")
				+ (produit != null ? "produit=" + produit : "") + "]";
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getCode() {
		return produit.getCode();
	}

	public String getDesignation() {
		return produit.getDesignation();
	}

	public String getIndesirable() {
		return produit.getIndesirable();
	}

	public String getIndication() {
		return produit.getIndication();
	}

}