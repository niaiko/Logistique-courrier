package mg.cnaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "courrier_spec")
public class SpecCouMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_spec", unique = true , nullable = false )
	private int idspec;
	
	@Column(name = "libelle", nullable = false )
	private String libellespeccou;

	public int getIdspec() {
		return idspec;
	}

	public void setIdspec(int idspec) {
		this.idspec = idspec;
	}

	public String getLibellespeccou() {
		return libellespeccou;
	}

	public void setLibellespeccou(String libellespeccou) {
		this.libellespeccou = libellespeccou;
	}
	
	
}
