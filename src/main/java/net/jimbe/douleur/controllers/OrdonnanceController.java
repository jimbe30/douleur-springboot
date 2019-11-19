package net.jimbe.douleur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.jimbe.douleur.beans.OrdonnanceForm;
import net.jimbe.douleur.services.ServiceDouleur;

@RestController
@RequestMapping("/ordonnances")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdonnanceController {
	
	@Autowired
	ServiceDouleur serviceDouleur;

	@RequestMapping(method=RequestMethod.POST, path="/nouvelle", consumes="application/json")
	public String faireOrdonnance(@RequestBody OrdonnanceForm formulaire) {
		return "OK";
	}
	
}
