package mg.cnaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "prest_adresse")
public class PrestAdresseCouMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_prest", unique = true , nullable = false )
	private int idprestcou;
	
	@Column(name = "adresse_prest", nullable = false )
	private String adresseprestcou;
	
	@Column(name = "code_prest", nullable = false )
	private String codeprestcou;

	public int getIdprestcou() {
		return idprestcou;
	}

	public void setIdprestcou(int idprestcou) {
		this.idprestcou = idprestcou;
	}

	public String getAdresseprestcou() {
		return adresseprestcou;
	}

	public void setAdresseprestcou(String adresseprestcou) {
		this.adresseprestcou = adresseprestcou;
	}

	public String getCodeprestcou() {
		return codeprestcou;
	}

	public void setCodeprestcou(String codeprestcou) {
		this.codeprestcou = codeprestcou;
	}
	
	
}
