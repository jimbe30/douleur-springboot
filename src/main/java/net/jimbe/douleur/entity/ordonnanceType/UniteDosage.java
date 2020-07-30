package net.jimbe.douleur.entity.ordonnanceType;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the unite_dosage database table.
 * 
 */
@Entity
@Table(name="unite_dosage")
@NamedQuery(name="UniteDosage.findAll", query="SELECT u FROM UniteDosage u")
public class UniteDosage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String unite;

	public UniteDosage() {
	}

	public String getUnite() {
		return this.unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

}