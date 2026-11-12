package ceu.dam.ad.orm.practicas.basicas.ejercicio3.service;

import java.util.List;

import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.CentroComercial;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Marca;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Pais;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Tienda;


public interface ComercialService {

	/** Debe buscar en BBDD todos los países cuya descripción empiece por el filtro indicado.
	 * Si no se encuentra ninguno, devolverá una lista vacía.
	 * Si hay algún error, lanzará ComercialException	 */
	public List<Pais> buscarPaises(String filtro) throws ComercialException;
	
	/** Debe insertar la Marca recibida en BBDD. No se insertarán sus centros comerciales asociados
	 * ni su país.
	 * Si hay algún error, lanzará ComercialException	 */
	public void insertarMarca(Marca marca) throws ComercialException;
	
	/** Debe insertar el Centro Comercial recibido en BBDD. No se insertará su país ni marcas
	 * en ambos casos deben existir previamente. Pero sí se insertará su lista de tiendas.
	 * Si hay algún error, lanzará ComercialException	 */
	public void insertarCentroComercial(CentroComercial cc) throws ComercialException;
	
	/** Consultará un centro comercial a partir de su uuid. Si no existe, lanzará NotFoundException.
	 * La entidad consultada tendrá que traer su país, sus tiendas y sus marcas. 
	 * Si hay algún error, lanzará ComercialException	 */
	public CentroComercial consultarCentroComercial(String uuidCentro) throws ComercialException, NotFoundException;
	

	/** Consultará una tienda a partir de su id. Si no existe, lanzará NotFoundException.
	 * La entidad consultada no traerá ninguna de sus entidades asociadas. 
	 * Si hay algún error, lanzará ComercialException	 */
	public Tienda consularTienda(Long idTienda) throws ComercialException, NotFoundException;
	
	
	/** Debe borrar la tienda con el id indicado. Sólo borrará la tienda, ninguna
	 * de sus entidades asociadas.
	 * Si hay algún error, lanzará ComercialException	 */
	public void borrarTienda(Long idTienda) throws ComercialException;
	

	/** Debe borrar el centro comercial con el uuid indicado. Borrará también su lista
	 * de tiendas asociada, pero no el resto de entidades.
	 * Si hay algún error, lanzará ComercialException	 */
	public void borrarCentroComercial(String uuidCentro) throws ComercialException;
}
