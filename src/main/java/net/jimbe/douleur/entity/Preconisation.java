package net.jimbe.douleur.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the preconisation database table.
 * 
 */
@Entity
@Table(name="preconisation")
@NamedQuery(name="Preconisation.findAll", query="SELECT p FROM Preconisation p")
public class Preconisation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(name="id_douleur", insertable=false, updatable=false)
	private long idDouleur;

	@Column(nullable=false, length=200)
	private String description;

	@Column(name="duree_max")
	private Integer dureeMax;

	@Column(name="duree_min")
	private Integer dureeMin;

	@Column(name="num_ordonnance", nullable=false)
	private int numOrdonnance;
	
	@Column(name="num_medicament", nullable=false)
	private int numMedicament;

	@Column(length=100)
	private String recommandation;

	//bi-directional many-to-one association to Compatibilite
	@OneToMany(mappedBy="preconisation")
	private List<Compatibilite> compatibilites;

	//bi-directional many-to-one association to Nomenclature
	@ManyToOne
	@JoinColumn(name="id_douleur", nullable=false)
	private Nomenclature nomenclatureDouleur;

	public Preconisation() {
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

	public Integer getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}

	public Integer getDureeMin() {
		return this.dureeMin;
	}

	public void setDureeMin(int dureeMin) {
		this.dureeMin = dureeMin;
	}

	public int getNumOrdonnance() {
		return this.numOrdonnance;
	}

	public void setNumOrdonnance(int numOrdonnance) {
		this.numOrdonnance = numOrdonnance;
	}

	public String getRecommandation() {
		return this.recommandation;
	}

	public void setRecommandation(String recommandation) {
		this.recommandation = recommandation;
	}

	public List<Compatibilite> getCompatibilites() {
		return this.compatibilites;
	}

	public void setCompatibilites(List<Compatibilite> compatibilites) {
		this.compatibilites = compatibilites;
	}

	public Compatibilite addCompatibilite(Compatibilite compatibilite) {
		getCompatibilites().add(compatibilite);
		compatibilite.setPreconisation(this);

		return compatibilite;
	}

	public Compatibilite removeCompatibilite(Compatibilite compatibilite) {
		getCompatibilites().remove(compatibilite);
		compatibilite.setPreconisation(null);

		return compatibilite;
	}
	@JsonIgnore
	public Nomenclature getNomenclatureDouleur() {
		return this.nomenclatureDouleur;
	}

	public void setNomenclatureDouleur(Nomenclature nomenclatureDouleur) {
		this.nomenclatureDouleur = nomenclatureDouleur;
	}

	public long getIdDouleur() {
		return idDouleur;
	}

	public void setIdDouleur(long idDouleur) {
		this.idDouleur = idDouleur;
	}

	public int getNumMedicament() {
		return numMedicament;
	}

	public void setNumMedicament(int numMedicament) {
		this.numMedicament = numMedicament;
	}

}