package net.jimbe.douleur.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.jimbe.douleur.entity.Nomenclature;

@RepositoryRestResource(path="douleurs")
// http://localhost:8080/api/douleurs
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long>  {

	public List<Nomenclature> findByNomenclatureParentIsNull();
	
}
