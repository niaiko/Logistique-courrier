package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.PrestataireCouMod;
import mg.cnaps.repository.PrestataireCouRepository;

@Service
public class PrestataireCouServiceImpl implements PrestataireCouService{
	
	public static int max=50;
	
	@Autowired
	PrestataireCouRepository Presrepository;

	@Override
	public void save(PrestataireCouMod servcou) {
		Presrepository.save(servcou);
		
	}

	@Override
	public PrestataireCouMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrestataireCouMod> getAll(int page) {
		return (Presrepository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(PrestataireCouMod entity) {
		Presrepository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(Presrepository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return Presrepository.seqDemande();
	}

}
