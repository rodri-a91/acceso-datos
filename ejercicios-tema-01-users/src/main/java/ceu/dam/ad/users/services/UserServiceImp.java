package ceu.dam.ad.users.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.users.dao.UserRepository;
import ceu.dam.ad.users.model.User;

public class UserServiceImp extends Service implements UserService {

	private final UserRepository repo;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
	 
	public UserServiceImp() {
		repo = new UserRepository();
	}

	@Override
	public User createUser(User user) throws DuplicateUserException, UserException {
		try (Connection conn = abrirConexion()) {
			logger.info("Creando usuario con estos datos: " + user);
//			1. Verificar que no existe usuario con ese email ni ese username. En caso contrario, lanzar DuplicateUserException
			User emailConsul = repo.getByEmail(conn, user.getEmail());
			User usernameConsul = repo.getByUserName(conn, user.getUsername());

			if (emailConsul != null || usernameConsul != null) {
				logger.debug("Se está intentando crear un usuario duplicado");
				throw new DuplicateUserException("Este usuario ya está registrado");
			}

//			2. Registrar el usuario en BBDD completando su fecha de alta y cifrando su password con SHA3-256
			user.setCreatedDate(LocalDate.now());
			user.setPassword(DigestUtils.sha3_256Hex(user.getPassword()));

			Long id = repo.insert(conn, user);
			
			if (id == null) {
				logger.error("Error creando usuario. El ID devuelto por BBDD es NULL");
				throw new UserException("Error creando el usuario, id null");
			}

//			3. Devolver el usuario con todos sus datos (incluyendo el ID) 
			user.setId(id);
			logger.info("Usuario creado con ID " + id);
			return user;

//			4. Si hay algún error, lanzará UserException con el origen	
		} catch (SQLException e) {
			logger.error("Error creando usuario en BBDD", e);
			throw new UserException("Error conectando a BBDD", e);
		}
	}

	@Override
	public void changePassword(Long idUser, String oldPassword, String newPassword)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		try (Connection conn = abrirConexion()) {
			User userConsul = repo.getById(conn, idUser);
//			1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
			if (userConsul == null)
				throw new UserNotFoundException("Este usuario no existe");

//			2. Verificar que la nueva password no es igual a la antigua. Si lo es, lanzar UserUnauthorizedException
			if (oldPassword.equals(newPassword))
				throw new UserUnauthorizedException("La contraseña es igual");

//			3. Verificar que la password antigua es correcta. Si no lo es, lanzar UserUnauthorizedException
			if (!DigestUtils.sha3_256Hex(oldPassword).equals(userConsul.getPassword()))
				throw new UserUnauthorizedException("La contraseña no coincide");

//			4. Actualizar el nuevo password en el usuario cifrándolo previamente.
			userConsul.setPassword(DigestUtils.sha3_256Hex(newPassword));
			repo.update(conn, userConsul);

		} catch (SQLException e) {
			throw new UserException("Error conectando a BBDD", e);
		}

	}

	@Override
	public User login(String login, String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		try (Connection conn = abrirConexion()) {
			User userConsul = (repo.getByEmail(conn, login));
//			1. Verificar que existe algún usuario con ese username o email. Si no es así, lanzar UserNotFoundException
			if (userConsul == null) {
				userConsul = repo.getByUserName(conn, login);
				if (userConsul == null)
					throw new UserNotFoundException("Este usuario no existe");
			}

//			2. Verificar que password es correcta. Si no lo es, lanzar UserUnauthorizedException 
			if (!DigestUtils.sha3_256Hex(password).equals(userConsul.getPassword()))
				throw new UserUnauthorizedException("Password incorrecta");

//			3. Actualizamos fecha del último login. Si hay algún error aquí, registramos en el log, pero continuamos.
			try {
				userConsul.setLastLoginDate(LocalDate.now());
				repo.update(conn, userConsul);

			} catch (Exception e) {
				System.err.println("Error actualizando fecha el último login");
				e.printStackTrace();
			}

//			4. Devolver el usuario con todos sus datos que ha realizado el login.
			return userConsul;

		}

		catch (SQLException e) {
			throw new UserException("Error conectando a BBDD", e);
		}

	}

	@Override
	public User getUser(Long idUser) throws UserNotFoundException, UserException {
		try (Connection conn = abrirConexion()) {
			User userConsul = repo.getById(conn, idUser);
			if (userConsul == null)
				throw new UserNotFoundException("Este usuario no existe");

			return userConsul;

		} catch (SQLException e) {
			throw new UserException("Error conectando a BBDD", e);
		}

	}

}
