package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.TrajetCouMod;
import mg.cnaps.repository.TrajetCouRepository;

@Service
public class TrajetCouServiceImpl implements TrajetCouService{
	
	public static int max=50;
	
	@Autowired
	TrajetCouRepository trajrepository;

	@Override
	public void save(TrajetCouMod servcou) {
		trajrepository.save(servcou);
		
	}

	@Override
	public TrajetCouMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrajetCouMod> getAll(int page) {
		return (trajrepository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(TrajetCouMod entity) {
		trajrepository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(trajrepository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return trajrepository.seqDemande();
	}

	@Override
	public List<TrajetCouMod> getByLibelleTrajetCou(String libelletrajetcou) {
		return trajrepository.findByLibelletrajetcouContainingIgnoreCase(libelletrajetcou);
	}

	@Override
	public List<TrajetCouMod> findalltrajetcou() {
		// TODO Auto-generated method stub
		return trajrepository.findalltrajetcou();
	}

}
