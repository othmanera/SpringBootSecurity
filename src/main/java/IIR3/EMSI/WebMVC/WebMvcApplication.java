package IIR3.EMSI.WebMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import IIR3.EMSI.WebMVC.dao.ProduitRepos;
import IIR3.EMSI.WebMVC.entities.Produit;

@SpringBootApplication
public class WebMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(WebMvcApplication.class, args);
		ProduitRepos Pr = ctx.getBean(ProduitRepos.class);
		Pr.save(new Produit(null,"clavier",1800,7));
		Pr.save(new Produit(null,"Souris",7,10));
		Pr.save(new Produit(null,"Haut-parleur",1600,10));
		Pr.save(new Produit(null,"USB",1600,10));
		
	}

	@Bean
PasswordEncoder PassEncod() {
	return new BCryptPasswordEncoder();
	
}
}

