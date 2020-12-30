package net.jimbe.douleur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.jimbe.douleur.ordonnanceType.OrdonnanceTypeService;
import net.jimbe.douleur.ordonnanceType.beans.ReferentielMedicaments;
import net.jimbe.douleur.ordonnanceType.entities.OrdonnanceType;

@RestController
@RequestMapping("/ordonnancesTypes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdonnanceTypeController {
	
	@Autowired
	OrdonnanceTypeService ordonnanceTypeService;
	
	

	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.POST}, consumes = {"application/json"} )
	public ResponseEntity<OrdonnanceType> enregistrerOrdonnanceType(@RequestBody OrdonnanceType ordonnanceType) {
		
		OrdonnanceType result = ordonnanceTypeService.enregistrer(ordonnanceType);
		System.out.println(result);
		
		return new  ResponseEntity<OrdonnanceType>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method= {RequestMethod.GET})
	public List<OrdonnanceType> lister() {
		List<OrdonnanceType> listeOrdonnancesTypes = ordonnanceTypeService.lister();
		System.out.println(listeOrdonnancesTypes);
		return listeOrdonnancesTypes;
	}
	
	@RequestMapping(path = "/referentielMedicaments", method= {RequestMethod.GET})
	public ReferentielMedicaments referentielMedicament() {
		
		ReferentielMedicaments referentielMedicaments = ordonnanceTypeService.chargerReferentielMedicaments();
		return referentielMedicaments;
	}

	
}
