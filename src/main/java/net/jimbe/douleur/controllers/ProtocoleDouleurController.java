package net.jimbe.douleur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.jimbe.douleur.protocoleDouleur.ProtocoleDouleurService;
import net.jimbe.douleur.protocoleDouleur.beans.ProtocoleDouleur;

@RestController
@RequestMapping("/protocolesDouleurs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProtocoleDouleurController {	

	@Autowired
	ProtocoleDouleurService protocoleDouleurService;
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.POST}, consumes = {"application/json"} )
	public ProtocoleDouleur enregistrerProtocoleDouleur(@RequestBody ProtocoleDouleur protocoleDouleur) {
		
		return protocoleDouleurService.enregistrer(protocoleDouleur);

	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{idDouleur}")
	public ProtocoleDouleur getProtocoleDouleur(@PathVariable long idDouleur) {
		ProtocoleDouleur result = protocoleDouleurService.chargerProtocole(idDouleur);
		return result;
	}

	
}
