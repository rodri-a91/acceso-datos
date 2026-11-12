package ceu.dam.ad.orm.practicas.basicas.ejercicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
