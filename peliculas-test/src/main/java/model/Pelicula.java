package model;

import lombok.Data;

@Data

public class Pelicula {
	private Long id;
	private String titulo;
	private String director;
	private Integer añoEstreno;
	private Integer duracion;
	private Integer edad;

}
