package net.jimbe.douleur.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.jimbe.douleur.beans.OrdonnanceForm;
import net.jimbe.douleur.services.ServiceDouleur;
import net.jimbe.douleur.tools.PDFBuilder;

@RestController
@RequestMapping("/ordonnances")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdonnanceController {

	@Autowired
	ServiceDouleur serviceDouleur;


	@RequestMapping(method = RequestMethod.POST, path = "/nouvelle", consumes = "application/json", produces=MediaType.TEXT_HTML_VALUE)
	public String faireOrdonnance(@Valid @RequestBody OrdonnanceForm ordonnance) {

		String ordonnanceModeleName = "/resources/modeles/ordonnance_modele.pdf";
		String targetName = "output/ordonnance_emise.pdf";
		try {
			PDFBuilder.editOrdonnancePDF(ordonnanceModeleName, targetName, ordonnance);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Service indisponible : impossible d'éditer l'ordonnance. Veuillez recommencer ultérieurement");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		String idOrdonnance = "ordonnance_emise.pdf";		
		return idOrdonnance;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/emises/{idOrdonnance}")
	public ResponseEntity<byte[]> getOrdonnanceEmise(@PathVariable String idOrdonnance) {

		String targetFileName = "output/" + idOrdonnance;
		byte[] contents = new byte[0];
		try {
			contents = serviceDouleur.getOrdonnancePDF(targetFileName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Service indisponible : impossible d'éditer l'ordonnance. Veuillez recommencer ultérieurement");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData(idOrdonnance, idOrdonnance);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;
	}
	


}
