package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.SpecCouMod;
import mg.cnaps.repository.SpecCouRepository;

@Service
public class SpecCouServiceImpl implements SpecCouService{
	
	public static int max=50;
	
	@Autowired
	SpecCouRepository specrepository;

	@Override
	public void save(SpecCouMod servcou) {
		specrepository.save(servcou);
		
	}

	@Override
	public SpecCouMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpecCouMod> getAll(int page) {
		return (specrepository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(SpecCouMod entity) {
		specrepository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(specrepository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return specrepository.seqDemande();
	}

}
