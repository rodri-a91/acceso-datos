package ceu.dam.ad.orm.practicas.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.orm.practicas.biblioteca.model.Socio;

public interface SocioRepository extends JpaRepository<Socio, String> {

}
