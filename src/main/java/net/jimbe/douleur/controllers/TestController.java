package net.jimbe.douleur.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

	@RequestMapping("/redirect")
	public void testRedirection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String referer = request.getHeader("referer");
		if (referer != null) {
			String queryString = "?redirectInfo=Redirection OK depuis le serveur vers " + referer;
			response.sendRedirect(referer + queryString);
		}
		
	}


	
}
