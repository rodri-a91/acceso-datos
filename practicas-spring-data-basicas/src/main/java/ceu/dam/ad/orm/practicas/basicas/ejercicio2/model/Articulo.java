package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

<<<<<<< HEAD
public class Articulo {
	
	private Long id;
	private String descripcion;
	private String codBarras;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", descripcion=" + descripcion + ", codBarras=" + codBarras + "]";
	}
	
	
	
=======
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Articulo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String descripcion;
	@Column(name = "codigo_barras")
	private String codBarras;

>>>>>>> 6f88b4af03db3f55d701bd39d441e0200dcf7747
}
