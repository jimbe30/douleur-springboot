package net.jimbe.douleur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientApp/**")
public class HomeController {
	
	@RequestMapping()
	public ModelAndView home()
	{
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("/resources/index.html");
	    return modelAndView;
	}
	
}
