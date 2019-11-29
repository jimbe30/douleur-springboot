package net.jimbe.douleur.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@RequestMapping(method = RequestMethod.POST, path = "/nouvelle", consumes = "application/json")
	public ResponseEntity<byte[]> faireOrdonnance(@Valid @RequestBody OrdonnanceForm ordonnance, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println(ordonnance);

		String ordonnanceModeleName = "resources/modeles/ordonnance_modele.pdf";
		String targetName = "resources/modeles/ordonnance_emise.pdf";
		byte[] contents = new byte[0];
		try {
			contents = PDFBuilder.editOrdonnancePDF(ordonnanceModeleName, targetName, ordonnance);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		// Here you have to set the actual filename of your pdf
		String filename = "ordonnance.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;

	}

}
