package mg.cnaps.repository;

//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.cnaps.models.PrestAdresseCouMod;

public interface PrestAdresseCouRepository extends JpaRepository<PrestAdresseCouMod,String> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();



}
