package net.jimbe.douleur.entity.ordonnanceType;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the forme_medic database table.
 * 
 */
@Entity
@Table(name="forme_medic")
@NamedQuery(name="FormeMedicale.findAll", query="SELECT f FROM FormeMedicale f")
public class FormeMedicale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, length=10)
	private String code;

	@Column(nullable=false, length=50)
	private String libelle;

	public FormeMedicale() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}