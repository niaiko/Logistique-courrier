package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.PrestAdresseCouMod;
import mg.cnaps.repository.PrestAdresseCouRepository;

@Service
public class PrestAdresseCouServiceImpl implements PrestAdresseCouService{
	
	public static int max=50;
	
	@Autowired
	PrestAdresseCouRepository prestarepository;

	@Override
	public void save(PrestAdresseCouMod servcou) {
		prestarepository.save(servcou);
		
	}

	@Override
	public PrestAdresseCouMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrestAdresseCouMod> getAll(int page) {
		return (prestarepository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(PrestAdresseCouMod entity) {
		prestarepository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(prestarepository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return prestarepository.seqDemande();
	}

//	@Override
//	public CourrierMod getByNumeroCourrier(String numeroCourrier, String adresse) {
//		return repository.findByNumeroCourrierAndAdresse(numeroCourrier, adresse);
//	}

}
