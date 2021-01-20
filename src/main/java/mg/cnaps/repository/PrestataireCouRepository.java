package mg.cnaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.cnaps.models.PrestataireCouMod;

public interface PrestataireCouRepository extends JpaRepository<PrestataireCouMod,String> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();



}
