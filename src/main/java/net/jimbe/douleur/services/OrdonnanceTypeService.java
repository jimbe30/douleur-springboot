package net.jimbe.douleur.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jimbe.douleur.dao.ordonnanceType.OrdonnanceTypeRepository;
import net.jimbe.douleur.entity.ordonnanceType.OrdonnanceType;

@Service
public class OrdonnanceTypeService {

	@Autowired
	OrdonnanceTypeRepository ordonnanceTypeRepository;

	@Transactional
	public OrdonnanceType enregistrer(OrdonnanceType ordonnanceType) {

		if (ordonnanceType != null) {
			if (ordonnanceType.getId() != null) {
				supprimerOrdonnanceType(ordonnanceType.getId());
			}
			ordonnanceType.setId(null);
			ordonnanceType = ordonnanceTypeRepository.save(ordonnanceType);
			return ordonnanceType;
		}
		return null;
	}
	
	
	public List<OrdonnanceType> lister() {
		List<OrdonnanceType> all = ordonnanceTypeRepository.findAll();
		return all;
	}
	
	@Transactional
	public boolean supprimerOrdonnanceType(Long id) {
		ordonnanceTypeRepository.deleteById(id);
		return true;
	}


}
