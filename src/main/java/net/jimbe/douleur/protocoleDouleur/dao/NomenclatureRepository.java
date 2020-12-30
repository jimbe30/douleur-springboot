package net.jimbe.douleur.protocoleDouleur.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.jimbe.douleur.protocoleDouleur.entities.Nomenclature;

@RepositoryRestResource(path="douleurs")
// http://localhost:6969/api/douleurs
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long>  {

	public List<Nomenclature> findByNomenclatureParentIsNull();
	
	@Query("delete from Nomenclature where id = :id")
	@Modifying
	public void deleteById(@Param("id") Long idDouleur);
	
}
