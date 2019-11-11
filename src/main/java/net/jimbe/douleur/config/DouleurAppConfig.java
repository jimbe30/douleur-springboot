package net.jimbe.douleur.config;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DouleurAppConfig {

	@Autowired
	private Environment env;

	@Value("${app.context}")
	private String appContext;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Bean
	public String chargementOK() {
		// java net.jimbe.douleur.DouleurBackendApplication "--app.context=recette" "--spring.profiles.active=R7"
		// java -jar
		// c:\dev_demo\workspaces\appli-douleur\douleur-spring-rest\target\douleur-spring-rest-1.0.jar
		// "--app.context=recette" "--spring.profiles.active=R7"
		try {
			System.out.println("contexte applicatif : " + appContext);
			System.out.println("profil actif : " + Arrays.toString(env.getActiveProfiles()));
			System.out.println("fichier de propriétés : " + env.getProperty("property.file.name"));
			System.out.println("fichier de propriétés globales : " + env.getProperty("global.property.file.name"));
			return "Chargement OK";
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage());
			return "Chargement KO";
		}
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/resources/**")
                    .addResourceLocations("classpath:/static/");
            }
            
        };
    }

}
