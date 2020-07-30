package net.jimbe.douleur.entity.ordonnanceType;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ordonnance_type database table.
 * 
 */
@Entity
@Table(name="ordonnance_type")
@NamedQuery(name="OrdonnanceType.findAll", query="SELECT o FROM OrdonnanceType o")
public class OrdonnanceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=100)
	private String description;

	//bi-directional many-to-one association to MedicamentType
	@OneToMany(mappedBy="ordonnanceType", cascade=CascadeType.PERSIST)
	private List<MedicamentType> medicaments;

	public OrdonnanceType() {
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

	public List<MedicamentType> getMedicaments() {
		return this.medicaments;
	}

	public void setMedicaments(List<MedicamentType> medicamentTypes) {
		this.medicaments = medicamentTypes;
		this.medicaments.forEach(medic -> medic.setOrdonnanceType(this));
	}

	public MedicamentType addMedicament(MedicamentType medicamentType) {
		getMedicaments().add(medicamentType);
		medicamentType.setOrdonnanceType(this);
		return medicamentType;
	}

	public MedicamentType removeMedicament(MedicamentType medicamentType) {
		getMedicaments().remove(medicamentType);
		medicamentType.setOrdonnanceType(null);
		return medicamentType;
	}

	@Override
	public String toString() {
		return "OrdonnanceType [" + (id != null ? "id=" + id + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (medicaments != null ? "medicaments=" + medicaments : "") + "]";
	}

}