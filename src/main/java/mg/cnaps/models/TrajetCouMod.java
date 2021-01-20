package mg.cnaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "trajet_courrier")
public class TrajetCouMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "idtrajet", unique = true , nullable = false )
	private int idtrajetcou;
	
	@Column(name = "libelle", nullable = false )
	private String libelletrajetcou;

	public int getIdtrajetcou() {
		return idtrajetcou;
	}

	public void setIdtrajetcou(int idtrajetcou) {
		this.idtrajetcou = idtrajetcou;
	}

	public String getLibelletrajetcou() {
		return libelletrajetcou;
	}

	public void setLibelletrajetcou(String libelletrajetcou) {
		this.libelletrajetcou = libelletrajetcou;
	}
	
	
	
}
