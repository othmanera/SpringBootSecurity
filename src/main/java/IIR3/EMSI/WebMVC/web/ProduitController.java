package IIR3.EMSI.WebMVC.web;

import java.net.http.HttpClient.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import IIR3.EMSI.WebMVC.dao.ProduitRepos;
import IIR3.EMSI.WebMVC.entities.Produit;
import groovy.lang.Binding;
import jakarta.validation.Valid;

@Controller
public class ProduitController {
	@Autowired
	ProduitRepos pr;

	/*
	 * @RequestMapping(value = "/list",method = RequestMethod.GET)
	 * //@GetMapping("/list")
	 * 
	 * public String ListProduit(Model model) { List<Produit> Malist= pr.findAll();
	 * model.addAttribute("listP",Malist) return "view"; }
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String ListProduit(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(value = "criteria", defaultValue = "") String mc) {
		Page<Produit> Malist;
		if(mc.isEmpty())
		{
			Malist = pr.findAll(PageRequest.of(page, 10));
			System.out.println("iuqsuf");
		}
		else
		{
			Malist = pr.rechercheQuery(mc,PageRequest.of(page, 10));			
		}
		//Page<Produit> Malist = pr.findByDesignContains(mc,PageRequest.of(page, 4));
		model.addAttribute("listP", Malist.getContent());
		model.addAttribute("nbrTotalPage",Malist.getTotalPages());
		model.addAttribute("currentPage",page);
		model.addAttribute("mc",mc);
		return "view";
	}
	
	@GetMapping("/delete")
	public String removeProduct(Model model, @RequestParam("ID")Integer id, @RequestParam("criteria")String mc)
	{
		pr.deleteById(id);
		return "redirect:/list?criteria="+mc;
	}
	
	@GetMapping(value="/formAjout")
	public String AfficheForm(Model model) {
		model.addAttribute("produit",new Produit());
		return "formAjout";
		
	}
	
	@PostMapping(value="/saveProduit")
	public String saveProduit(Model model, @Valid Produit p, BindingResult br ) {
		if(br.hasErrors())
			return "formAjout";
		
		pr.save(p);
		return  "redirect:/list";
	}
	
	

}
