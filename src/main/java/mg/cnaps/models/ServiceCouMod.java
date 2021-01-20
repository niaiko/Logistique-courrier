package mg.cnaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "service_courrier")
public class ServiceCouMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_service", unique = true , nullable = false )
	private int idservicecou;
	
	@Column(name = "libelle", nullable = false )
	private String libelleservicecou;
	
	public int getIdservicecou() {
		return idservicecou;
	}

	public void setIdservicecou(int idservicecou) {
		this.idservicecou = idservicecou;
	}

	public String getLibelleservicecou() {
		return libelleservicecou;
	}

	public void setLibelleservicecou(String libelleservicecou) {
		this.libelleservicecou = libelleservicecou;
	}
	
}
