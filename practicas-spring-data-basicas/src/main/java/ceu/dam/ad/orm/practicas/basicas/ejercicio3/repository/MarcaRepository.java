package ceu.dam.ad.orm.practicas.basicas.ejercicio3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Marca;

public interface MarcaRepository extends JpaRepository<Marca, String> {


}
