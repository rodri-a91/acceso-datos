package ceu.dam.ad.pedidos.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.pedidos.model.ArticuloPedido;
import java.util.List;



public interface ArticuloPedidoRepository extends JpaRepository<ArticuloPedido, Long> {
	
	List<ArticuloPedido> findByIdPedido(Long idPedido);
	
	List<ArticuloPedido> findByCodBarras(String codBarras);

	

}
