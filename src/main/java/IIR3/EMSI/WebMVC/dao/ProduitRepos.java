package IIR3.EMSI.WebMVC.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import IIR3.EMSI.WebMVC.entities.Produit;

public interface ProduitRepos extends JpaRepository<Produit, Integer> {
	public Page<Produit> findByDesignContains(String Mc, Pageable page);
	@Query("SELECT p FROM Produit p WHERE p.design LIKE %:x% OR p.prix = CAST(:x AS double) OR p.qte = CAST(:x AS int)")
	public Page<Produit> rechercheQuery(@Param("x") String x, Pageable page);
}
