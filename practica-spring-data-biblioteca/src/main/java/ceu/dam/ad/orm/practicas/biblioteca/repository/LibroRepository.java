package ceu.dam.ad.orm.practicas.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.orm.practicas.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
