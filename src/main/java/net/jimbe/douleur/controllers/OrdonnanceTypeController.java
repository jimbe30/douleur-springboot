package net.jimbe.douleur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.jimbe.douleur.entity.ordonnanceType.OrdonnanceType;
import net.jimbe.douleur.services.ServiceOdonnanceType;

@RestController
@RequestMapping("/ordonnancesTypes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdonnanceTypeController {
	
	@Autowired
	ServiceOdonnanceType serviceOdonnanceType;

	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.POST}, consumes = {"application/json"} )
	public OrdonnanceType enregistrerOrdonnanceType(@RequestBody OrdonnanceType ordonnanceType) {
		
		OrdonnanceType result = serviceOdonnanceType.enregistrer(ordonnanceType);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(method= {RequestMethod.GET})
	public List<OrdonnanceType> lister() {
		List<OrdonnanceType> listeOrdonnancesTypes = serviceOdonnanceType.lister();
		System.out.println(listeOrdonnancesTypes);
		return listeOrdonnancesTypes;
	}

	
}
