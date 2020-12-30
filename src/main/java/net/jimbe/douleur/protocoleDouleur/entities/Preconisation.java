package net.jimbe.douleur.protocoleDouleur.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(name="id_douleur", insertable=false, updatable=false)
	private Long idDouleur;

	@Column(nullable=false, length=200)
	private String description;

	@Column(name="duree_max")
	private Integer dureeMax;

	@Column(name="duree_min")
	private Integer dureeMin;
	
	@Column(name="frequence_max")
	private Integer frequenceMax;

	@Column(name="frequence_min")
	private Integer frequenceMin;
	
	@Column(name="frequence_autre", length = 50)
	private String frequenceAutre;
	
	@Column(name="quantite_min")
	private Integer quantiteMin;

	@Column(name="quantite_max")
	private Integer quantiteMax;
	
	@Column(length=100)
	private String formes;

	@Column(name="num_ordonnance", nullable=false)
	private Integer numOrdonnance;
	
	@Column(name="num_medicament", nullable=false)
	private Integer numMedicament;

	@Column(length=100)
	private String recommandation;

	//bi-directional many-to-one association to Compatibilite
	@OneToMany(mappedBy="preconisation", cascade= {CascadeType.PERSIST})
	private List<Compatibilite> compatibilites;

	//bi-directional many-to-one association to Nomenclature
	@ManyToOne
	@JoinColumn(name="id_douleur")
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

	public void setDureeMax(Integer dureeMax) {
		this.dureeMax = dureeMax;
	}

	public Integer getDureeMin() {
		return this.dureeMin;
	}

	public void setDureeMin(Integer dureeMin) {
		this.dureeMin = dureeMin;
	}

	public Integer getNumOrdonnance() {
		return this.numOrdonnance;
	}

	public void setNumOrdonnance(Integer numOrdonnance) {
		this.numOrdonnance = numOrdonnance;
	}

	public String getRecommandation() {
		return this.recommandation;
	}

	public void setRecommandation(String recommandation) {
		this.recommandation = recommandation;
	}

	public List<Compatibilite> getCompatibilites() {
		if (compatibilites == null) {
			compatibilites = new ArrayList<Compatibilite>();
		}
		return this.compatibilites;
	}
	
	public Compatibilite getCompatibilite(Long idCompatibilite) {
		Compatibilite result = null;
		if (compatibilites != null && idCompatibilite != null) {
			try {
				result = compatibilites
						.stream()
						.filter(compat -> idCompatibilite.equals(compat.getId()))
						.findFirst()
						.get();
			} catch (NoSuchElementException e) {
				result = null;
			}
		}
		if (result == null) {
			result = new Compatibilite();
			addCompatibilite(result);
		}
		return result;
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

	public Long getIdDouleur() {
		return idDouleur;
	}

	public void setIdDouleur(Long idDouleur) {
		this.idDouleur = idDouleur;
	}

	public Integer getNumMedicament() {
		return numMedicament;
	}

	public void setNumMedicament(Integer numMedicament) {
		this.numMedicament = numMedicament;
	}

	public Integer getFrequenceMax() {
		return frequenceMax;
	}

	public void setFrequenceMax(Integer frequenceMax) {
		this.frequenceMax = frequenceMax;
	}

	public Integer getFrequenceMin() {
		return frequenceMin;
	}

	public void setFrequenceMin(Integer frequenceMin) {
		this.frequenceMin = frequenceMin;
	}

	public String getFrequenceAutre() {
		return frequenceAutre;
	}

	public void setFrequenceAutre(String frequenceAutre) {
		this.frequenceAutre = frequenceAutre;
	}

	public String getFormes() {
		return formes;
	}

	public void setFormes(String formes) {
		this.formes = formes;
	}

	public Integer getQuantiteMin() {
		return quantiteMin;
	}

	public void setQuantiteMin(Integer quantiteMin) {
		this.quantiteMin = quantiteMin;
	}

	public Integer getQuantiteMax() {
		return quantiteMax;
	}

	public void setQuantiteMax(Integer quantiteMax) {
		this.quantiteMax = quantiteMax;
	}

}