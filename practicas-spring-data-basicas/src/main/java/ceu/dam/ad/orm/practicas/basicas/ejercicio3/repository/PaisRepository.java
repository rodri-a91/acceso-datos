package ceu.dam.ad.orm.practicas.basicas.ejercicio3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Pais;
import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, String> {

	List<Pais> findByDescripcionStartingWith(String descripcion);

}
