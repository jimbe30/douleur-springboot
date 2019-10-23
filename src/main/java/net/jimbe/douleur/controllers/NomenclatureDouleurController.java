package net.jimbe.douleur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jimbe.douleur.dao.NomenclatureRepository;
import net.jimbe.douleur.entity.Nomenclature;

@RestController
@RequestMapping("/douleurs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NomenclatureDouleurController {
	
	@Autowired
	NomenclatureRepository nomenclatureRepository;

	@RequestMapping("/")
	public List<Nomenclature> getNomenclatureDouleur() {
		List<Nomenclature> nomenclatures = nomenclatureRepository.findByNomenclatureParentIsNull();
		return nomenclatures;
	}
}
