package ceu.dam.ad.users.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {

	private Long id;
	private String username;
	private String name;
	private String email;
	private String password;
	private LocalDate createdDate;
	private LocalDate lastLoginDate;

}
