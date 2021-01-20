package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.ServiceCouMod;
import mg.cnaps.repository.ServiceCouRepository;

@Service
public class ServiceCouServiceImpl implements ServiceCouService{
	
	public static int max=50;
	
	@Autowired
	ServiceCouRepository Servrepository;

	@Override
	public void save(ServiceCouMod servcou) {
		Servrepository.save(servcou);
		
	}

	@Override
	public ServiceCouMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceCouMod> getAll(int page) {
		return (Servrepository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(ServiceCouMod entity) {
		Servrepository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(Servrepository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return Servrepository.seqDemande();
	}

	@Override
	public List<ServiceCouMod> getByLibelleServCou(String libelleservicecou) {
		return Servrepository.findByLibelleservicecouContainingIgnoreCase(libelleservicecou);
	}

}
