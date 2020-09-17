package net.jimbe.douleur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jimbe.douleur.dao.OrdonnanceTypeRepository;
import net.jimbe.douleur.entity.ordonnanceType.OrdonnanceType;

@Service
public class OrdonnanceTypeService {

	@Autowired
	OrdonnanceTypeRepository ordonnanceTypeRepository;

	public OrdonnanceType enregistrer(OrdonnanceType ordonnanceType) {
		OrdonnanceType savedOrdonnanceType = ordonnanceTypeRepository.save(ordonnanceType);
		return savedOrdonnanceType;
	}
	
	
	public List<OrdonnanceType> lister() {
		List<OrdonnanceType> all = ordonnanceTypeRepository.findAll();
		return all;
	}
	



}
