package ceu.dam.ad.orm.practicas.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Libro {

	@Id
	private Long id;
	private String isbn;
	private String titulo;
	private String autor;
	private String genero;
	private Integer ejemplaresDisponibles;

	@Override
	public String toString() {
		return "Libro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero
				+ ", ejemplaresDisponibles=" + ejemplaresDisponibles + "]";
	}

}
