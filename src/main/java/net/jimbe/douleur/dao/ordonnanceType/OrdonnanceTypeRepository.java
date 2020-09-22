package net.jimbe.douleur.dao.ordonnanceType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.jimbe.douleur.entity.ordonnanceType.OrdonnanceType;

@RepositoryRestResource(path="ordonnancesTypes")
// http://localhost:6969/api/ordonnancesTypes
public interface OrdonnanceTypeRepository extends JpaRepository<OrdonnanceType, Long>  {
	
	@Query("delete from OrdonnanceType where id = :id")
	@Modifying
	public void deleteById(@Param("id") Long id);


	
}
