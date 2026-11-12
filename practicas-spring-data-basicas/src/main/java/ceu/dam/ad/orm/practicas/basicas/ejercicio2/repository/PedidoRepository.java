package ceu.dam.ad.orm.practicas.basicas.ejercicio2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>  {
	
	

}
