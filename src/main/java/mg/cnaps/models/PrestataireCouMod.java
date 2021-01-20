package mg.cnaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "prestataire")
public class PrestataireCouMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_prest", unique = true , nullable = false )
	private int idservicecou;
	
	@Column(name = "libelle", nullable = false )
	private String libelleprestationcou;
	
	@Column(name = "code_prest", nullable = false )
	private String codeprestationcou;

	public int getIdservicecou() {
		return idservicecou;
	}

	public void setIdservicecou(int idservicecou) {
		this.idservicecou = idservicecou;
	}

	public String getLibelleprestationcou() {
		return libelleprestationcou;
	}

	public void setLibelleprestationcou(String libelleprestationcou) {
		this.libelleprestationcou = libelleprestationcou;
	}

	public String getCodeprestationcou() {
		return codeprestationcou;
	}

	public void setCodeprestationcou(String codeprestationcou) {
		this.codeprestationcou = codeprestationcou;
	}
	
	
	
}
