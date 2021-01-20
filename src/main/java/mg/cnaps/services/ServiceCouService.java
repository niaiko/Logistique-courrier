package mg.cnaps.services;

import java.util.List;

import mg.cnaps.models.ServiceCouMod;


public interface ServiceCouService extends CRUDService<ServiceCouMod> {
	 long seqDemande();
	 List<ServiceCouMod> getByLibelleServCou(String libelleservicecou);
	 
}
