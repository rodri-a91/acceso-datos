package ceu.dam.ad.pedidos.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.pedidos.model.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	

}
