package net.jimbe.douleur.protocoleDouleur.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.jimbe.douleur.protocoleDouleur.entities.Preconisation;

@RepositoryRestResource(path="douleurs/{idDouleur}")
// http://localhost:6969/api/douleurs
public interface PreconisationRepository extends JpaRepository<Preconisation, Long>  {

	@Query("select p from Preconisation p where p.idDouleur = :idDouleur order by p.numOrdonnance, p.numMedicament")
	public List<Preconisation> findByIdDouleur(@Param("idDouleur") long idDouleur);
	
}
