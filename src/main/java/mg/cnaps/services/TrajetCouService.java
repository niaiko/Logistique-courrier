package mg.cnaps.services;

import java.util.List;

import mg.cnaps.models.TrajetCouMod;


public interface TrajetCouService extends CRUDService<TrajetCouMod> {
	 long seqDemande();
	 List<TrajetCouMod> getByLibelleTrajetCou(String libelletrajetcou);
	 List<TrajetCouMod> findalltrajetcou();
}
