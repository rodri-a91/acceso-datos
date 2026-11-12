package ceu.dam.ad.orm.practicas.basicas.ejercicio3.service;

import java.util.List;

import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.CentroComercial;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Marca;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Pais;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Tienda;

public class ComercialServiceImpl implements ComercialService{

	@Override
	public List<Pais> buscarPaises(String filtro) throws ComercialException {
		return null;
	}

	@Override
	public void insertarMarca(Marca marca) throws ComercialException {
	}

	@Override
	public void insertarCentroComercial(CentroComercial cc) throws ComercialException {
	}

	@Override
	public CentroComercial consultarCentroComercial(String uuidCentro) throws ComercialException, NotFoundException {
		return null;
	}

	@Override
	public Tienda consularTienda(Long idTienda) throws ComercialException, NotFoundException {
		return null;
	}

	@Override
	public void borrarTienda(Long idTienda) throws ComercialException {
	}

	@Override
	public void borrarCentroComercial(String uuidCentro) throws ComercialException {
	}

}
