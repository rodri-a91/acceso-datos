package ceu.dam.ad.orm.practicas.basicas.ejercicio1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Serie;
import java.util.List;


public interface SerieRepository extends JpaRepository<Serie, Long>{
	
	List<Serie> findByDescripcionContaining(String descripcion);

}
