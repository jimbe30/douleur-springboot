package net.jimbe.douleur.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.jimbe.douleur.entity.ordonnanceType.OrdonnanceType;

@RepositoryRestResource(path="ordonnancesTypes")
// http://localhost:6969/api/ordonnancesTypes
public interface OrdonnanceTypeRepository extends JpaRepository<OrdonnanceType, Long>  {


	
}
