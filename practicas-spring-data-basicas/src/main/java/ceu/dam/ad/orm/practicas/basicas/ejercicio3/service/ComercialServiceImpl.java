package ceu.dam.ad.orm.practicas.basicas.ejercicio3.service;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.CentroComercial;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Marca;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Pais;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Tienda;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.repository.CentroComercialRepository;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.repository.MarcaRepository;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.repository.PaisRepository;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.repository.TiendaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ComercialServiceImpl implements ComercialService {
	final PaisRepository paisRepo;
	final MarcaRepository marcaRepo;
	final CentroComercialRepository centroRepo;
	final TiendaRepository tiendaRepo;

	@Override
	public List<Pais> buscarPaises(String filtro) throws ComercialException {
		try {
			return paisRepo.findByDescripcionStartingWith(filtro);

		} catch (DataAccessException e) {
			throw new ComercialException("Error buscando países", e);
		}

	}

	@Override
	public void insertarMarca(Marca marca) throws ComercialException {
		try {
			marcaRepo.save(marca);

		} catch (DataAccessException e) {
			throw new ComercialException("Error buscando países", e);
		}
	}

	@Override
	@Transactional
	public void insertarCentroComercial(CentroComercial cc) throws ComercialException {
		try {
			centroRepo.save(cc);

		} catch (DataAccessException e) {
			throw new ComercialException("Error insertando el centro comercial", e);
		}
	}

	@Override
	public CentroComercial consultarCentroComercial(String uuidCentro) throws ComercialException, NotFoundException {
		try {
			return centroRepo.findById(UUID.fromString(uuidCentro))
					.orElseThrow(() -> new NotFoundException("No existe ese centro comercial"));

		} catch (DataAccessException e) {
			throw new ComercialException("Error buscando centros comerciales", e);
		}
	}

	@Override
	@Transactional
	public void borrarCentroComercial(String uuidCentro) throws ComercialException {
		try {
			centroRepo.delete(consultarCentroComercial(uuidCentro));
		} catch (DataAccessException e) {
			throw new ComercialException("Error borrando centro comercial", e);
		} catch (NotFoundException e) {
			throw new ComercialException("Ese centro comercial no existe", e);
		}
	}

	@Override
	public Tienda consultarTienda(Long idTienda) throws ComercialException, NotFoundException {
		try {
			return tiendaRepo.findById(idTienda).orElseThrow(() -> new NotFoundException("No existe esa tienda"));

		} catch (DataAccessException e) {
			throw new ComercialException("Error buscando tiendas", e);
		}
	}

	@Override
	public void borrarTienda(Long idTienda) throws ComercialException {
		try {
			tiendaRepo.delete(consultarTienda(idTienda));
		} catch (DataAccessException e) {
			throw new ComercialException("Error borrando la tienda", e);
		} catch (NotFoundException e) {
			throw new ComercialException("Esa tienda no existe", e);
		}
	}

}
