package net.jimbe.douleur.entity.protocoleDouleur;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * The persistent class for the nomenclature database table.
 * 
 */
@Entity
@Table(name="nomenclature")
@NamedQuery(name="Nomenclature.findAll", query="SELECT n FROM Nomenclature n")
public class Nomenclature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="infos_generales", length=2000)
	private String infosGenerales;
	
	@Column(name="id_parent")
	private Long idParent;

	@Column(nullable=false, length=100)
	private String libelle;

	@Column(length=200)
	private String recommandations;

	//bi-directional many-to-one association to Nomenclature
	@ManyToOne
	@JoinColumn(name="id_parent", insertable=false, updatable=false)
	@JsonIgnore
	private Nomenclature nomenclatureParent;

	//bi-directional many-to-one association to Nomenclature
	@OneToMany(mappedBy="nomenclatureParent", fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST})
	private List<Nomenclature> nomenclaturesEnfants;

	//bi-directional many-to-one association to Preconisation
	@OneToMany(fetch=FetchType.LAZY, mappedBy="nomenclatureDouleur", cascade= {CascadeType.PERSIST})
	@JsonIgnore
	private List<Preconisation> preconisations;

	public Nomenclature() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfosGenerales() {
		return this.infosGenerales;
	}

	public void setInfosGenerales(String infosGenerales) {
		this.infosGenerales = infosGenerales;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getRecommandations() {
		return this.recommandations;
	}

	public void setRecommandations(String recommandations) {
		this.recommandations = recommandations;
	}

	public Nomenclature getNomenclatureParent() {
		return this.nomenclatureParent;
	}

	public void setNomenclatureParent(Nomenclature nomenclatureParent) {
		this.nomenclatureParent = nomenclatureParent;
	}

	public List<Nomenclature> getNomenclaturesEnfants() {
		if (nomenclaturesEnfants == null) {
			nomenclaturesEnfants = new ArrayList<Nomenclature>();
		}
		return this.nomenclaturesEnfants;
	}

	public void setNomenclaturesEnfants(List<Nomenclature> nomenclaturesEnfants) {
		this.nomenclaturesEnfants = nomenclaturesEnfants;
	}

	public Nomenclature addNomenclaturesEnfant(Nomenclature nomenclaturesEnfant) {
		getNomenclaturesEnfants().add(nomenclaturesEnfant);
		nomenclaturesEnfant.setNomenclatureParent(this);

		return nomenclaturesEnfant;
	}

	public Nomenclature removeNomenclaturesEnfant(Nomenclature nomenclaturesEnfant) {
		getNomenclaturesEnfants().remove(nomenclaturesEnfant);
		nomenclaturesEnfant.setNomenclatureParent(null);

		return nomenclaturesEnfant;
	}

	public List<Preconisation> getPreconisations() {
		if (preconisations == null) {
			preconisations = new ArrayList<>();
		}
		return this.preconisations;
	}
	
	public Preconisation getPreconisation(Long idPreconisation) {
		Preconisation result = null;
		if (preconisations != null && idPreconisation != null) {
			try {
				result = preconisations
						.stream()
						.filter(preco -> idPreconisation.equals(preco.getId())).findFirst()
						.get();
			} catch (NoSuchElementException e) {
				result = null;
			}
		}
		if (result == null) {
			result = new Preconisation();
			addPreconisation(result);
		}
		return result;
	}

	public void setPreconisations(List<Preconisation> preconisations) {
		this.preconisations = preconisations;
	}

	public Preconisation addPreconisation(Preconisation preconisation) {
		getPreconisations().add(preconisation);
		preconisation.setNomenclatureDouleur(this);

		return preconisation;
	}

	public Preconisation removePreconisation(Preconisation preconisation) {
		getPreconisations().remove(preconisation);
		preconisation.setNomenclatureDouleur(null);

		return preconisation;
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

}