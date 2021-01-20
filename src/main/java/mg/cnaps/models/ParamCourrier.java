package mg.cnaps.models;

import java.util.List;

public class ParamCourrier {

	private String destinataire;
	private java.sql.Date dateInsert;
	private String flagcou;

	public String getFlagcou() {
		return flagcou;
	}

	public void setFlagcou(String flagcou) {
		this.flagcou = flagcou;
	}

	private int page;
	private int size = 10;
	private int nbPage;
	private List<CourrierMod> l;

	public ParamCourrier() {
		super();
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public java.sql.Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(java.sql.Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public List<CourrierMod> getL() {
		return l;
	}

	public void setL(List<CourrierMod> l) {
		this.l = l;
	}

}
