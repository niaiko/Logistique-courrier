package mg.cnaps.repository;

import java.util.List;

//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.cnaps.models.ServiceCouMod;

public interface ServiceCouRepository extends JpaRepository<ServiceCouMod,String> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();

//	@Query("select u from ServiceCouMod u where u.libelleservicecou like ?1")
//	List<ServiceCouMod> getByLibelleServCou(String libelleservicecou);

	List<ServiceCouMod> findByLibelleservicecouContainingIgnoreCase(String libelleservicecou);
	
}
