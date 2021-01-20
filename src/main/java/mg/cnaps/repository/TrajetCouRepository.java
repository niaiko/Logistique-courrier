package mg.cnaps.repository;

import java.util.List;

//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.cnaps.models.TrajetCouMod;

public interface TrajetCouRepository extends JpaRepository<TrajetCouMod,String> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();
	
	List<TrajetCouMod> findByLibelletrajetcouContainingIgnoreCase(String libelletrajetcou);
	
	@Query("select u from TrajetCouMod u ")
	List<TrajetCouMod> findalltrajetcou();
}
