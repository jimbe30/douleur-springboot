package net.jimbe.douleur.entity.ordonnanceType;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.List;


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

	public String getFormes() {
		return this.formes != null ? this.formes.trim() : "" ;
	}

	public void setFormes(String formes) {
		this.formes = formes;
	}

	public OrdonnanceType getOrdonnanceType() {
		return this.ordonnanceType;
	}

	public void setOrdonnanceType(OrdonnanceType ordonnanceType) {
		this.ordonnanceType = ordonnanceType;
	}

	public List<ProduitType> getProduits() {
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

	public List<String> getListeFormes() {
		return Arrays.asList(getFormes().split("\\|"));
	}

	public void setListeFormes(List<String> listeFormes) {
		setFormes(listeFormes.stream().reduce((a, b) -> a.concat("|").concat(b)).get());
	}

	@Override
	public String toString() {
		return "MedicamentType [" + (id != null ? "id=" + id + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (formes != null ? "formes=" + formes + ", " : "")
				+ (produits != null ? "produits=" + produits : "") + "]";
	}

}