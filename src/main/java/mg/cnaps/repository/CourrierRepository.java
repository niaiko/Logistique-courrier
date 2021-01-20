package mg.cnaps.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.cnaps.models.CourrierMod;

public interface CourrierRepository extends JpaRepository<CourrierMod, Integer> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery = true)
	long seqDemande();

	@Query("select u from CourrierMod u where (lower(u.destinataire) like '%'||lower(?1)||'%' or ?1 is null ) and (u.dateInsert = ?2 or cast(?2 as date) is null) and (u.flagcou = ?3 or ?3 = '') ")
	Page<CourrierMod> getByDestinataireDateCou(String destinataire, Date dateInsert, String flagcou, Pageable pageable);

	@Query("select u from CourrierMod u where (destinataire = ?1 or ?1 = '') and (expediteur = ?2 or ?2 = '') and dateInsert = ?3 and idservice = ?4 and adresse = ?5 ")
	CourrierMod getByDestinataireDateCouIdserviceAdresse(String destinataire, String expediteur, Date dateCourrier,
			int idservice, String adresse);

	@Query("select u from CourrierMod u ")
	Page<CourrierMod> findallcourrier(Pageable pageable);
}