package mg.cnaps.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "courrier")
public class CourrierMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "numero_courrier", unique = true , nullable = false )
	private int numeroCourrier;
	
	@Column(name = "poids_courrier", length = 100, nullable = false )
	private double poidsCourrier;
	
	@Column(name = "date_courrier")
	private Date dateCourrier;
	
	@Column(name = "date_insert")
	private Date dateInsert;
	
	@Column(name = "nombre_courrier")
	private int nombreCourrier;
	
	@Column(name = "expediteur")
	private String expediteur;
	
	@Column(name = "destinataire")
	private String destinataire;
	
	@Column(name = "adresse")
	private String adresse;
	
	@Column(name = "id_spec")
	private int idspec;
	
	@Column(name = "id_service")
	private int idservice;
	
	@Column(name = "code_dr")
	private String codedr;
	
	@Column(name = "id_prest")
	private int idprest;
	
	@Column(name = "id_trajet")
	private int idtrajet;
	
	@Column(name = "user_insert")
	private String userinsert;
	
	@Column(name = "flag_courrier")
	private String flagcou;
	
	@Column(name = "observation")
	private String observation;

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public int getNumeroCourrier() {
		return numeroCourrier;
	}

	public void setNumeroCourrier(int numeroCourrier) {
		this.numeroCourrier = numeroCourrier;
	}

	public double getPoidsCourrier() {
		return poidsCourrier;
	}

	public void setPoidsCourrier(double poidsCourrier) {
		this.poidsCourrier = poidsCourrier;
	}

	public Date getDateCourrier() {
		return dateCourrier;
	}

	public void setDateCourrier(Date dateCourrier) {
		this.dateCourrier = dateCourrier;
	}

	public Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public int getNombreCourrier() {
		return nombreCourrier;
	}

	public void setNombreCourrier(int nombreCourrier) {
		this.nombreCourrier = nombreCourrier;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getIdspec() {
		return idspec;
	}

	public void setIdspec(int idspec) {
		this.idspec = idspec;
	}

	public int getIdservice() {
		return idservice;
	}

	public void setIdservice(int idservice) {
		this.idservice = idservice;
	}

	public int getIdprest() {
		return idprest;
	}

	public void setIdprest(int idprest) {
		this.idprest = idprest;
	}

	public int getIdtrajet() {
		return idtrajet;
	}

	public void setIdtrajet(int idtrajet) {
		this.idtrajet = idtrajet;
	}

	public String getUserinsert() {
		return userinsert;
	}

	public void setUserinsert(String userinsert) {
		this.userinsert = userinsert;
	}

	public String getFlagcou() {
		return flagcou;
	}

	public void setFlagcou(String flagcou) {
		this.flagcou = flagcou;
	}

	public String getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}

	public String getCodedr() {
		return codedr;
	}

	public void setCodedr(String codedr) {
		this.codedr = codedr;
	}
	
}
