package mg.cnaps.services;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.CourrierMod;
import mg.cnaps.repository.CourrierRepository;

@Service
public class CourrierServiceImpl implements CourrierService {

	public static int max = 50;

	@Autowired
	CourrierRepository repository;

	@Override
	public void save(CourrierMod cou) {
		repository.save(cou);

	}

	@Override
	public CourrierMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourrierMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page - 1, max))).getContent();
	}

	@Override
	public void delete(CourrierMod entity) {
		repository.delete(entity);

	}

	@Override
	public int nombrepage() {
		return (int) (repository.count() / max) + 1;
	}

	@Override
	public long seqDemande() {
		return repository.seqDemande();
	}

	@Override
	public List<CourrierMod> getByDestinataireDateCou(String destinataire, Date dateInsert, String flagcou) {
//		return repository.getByDestinataireDateCou(destinataire, dateInsert, flagcou);
		return null;
	}

	@Override
	public CourrierMod getByDestinataireDateCouIdserviceAdresse(String destinataire, String expediteur,
			Date dateCourrier, int idservice, String adresse) {
		return repository.getByDestinataireDateCouIdserviceAdresse(destinataire, expediteur, dateCourrier, idservice,
				adresse);
	}

	@Override
	public List<CourrierMod> findallcourrier(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
