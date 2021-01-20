package mg.cnaps.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import mg.cnaps.models.CourrierMod;


public interface CourrierService extends CRUDService<CourrierMod> {
	 long seqDemande();
//	 List<CourrierMod> listeAvecParam(int param);
//	 List<CourrierMod> getbyidacc(String idacc);
//	 List<CourrierMod> getbyidIndiv(String id_individu);
//	 List<CourrierMod> getbyidIndivAndPrestation(String id_individu, String prestation);
	 List<CourrierMod> getByDestinataireDateCou(String destinataire,Date dateInsert,String flagcou);
	 CourrierMod getByDestinataireDateCouIdserviceAdresse(String destinataire,String expediteur,Date dateCourrier, int idservice, String adresse);
	 List<CourrierMod> findallcourrier(Pageable pageable);
}
