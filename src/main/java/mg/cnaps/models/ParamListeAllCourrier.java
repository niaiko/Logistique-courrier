package mg.cnaps.models;

import java.util.List;

public class ParamListeAllCourrier {

	private int page;
	private int nbPage;
	private List<CourrierMod> l;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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
