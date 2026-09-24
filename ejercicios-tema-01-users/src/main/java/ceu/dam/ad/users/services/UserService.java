package ceu.dam.ad.users.services;

import ceu.dam.ad.users.model.User;

public interface UserService {
	
	/** Recibe un usuario que trae indicado su username, email y password (sin cifrar). El servicio tendrá que:
	 * 1. Verificar que no existe usuario con ese email ni ese username. En caso contrario, lanzar DuplicateUserException
	 * 2. Registrar el usuario en BBDD completando su fecha de alta y cifrando su password con SHA3-256
	 * 3. Devolver el usuario con todos sus datos (incluyendo el ID) 
	 * 4. Si hay algún error, lanzará UserException con el origen
	 */
	public User createUser(User user) throws DuplicateUserException, UserException;
	

	/** Recibe el id de un usuario, el password antiguo y el nuevo. Los dos sin cifrar. El servicio tendrá que:
	 * 1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
	 * 2. Verificar que la nueva password no es igual a la antigua. Si lo es, lanzar UserUnauthorizedException
	 * 3. Verificar que la password antigua es correcta. Si no lo es, lanzar UserUnauthorizedException
	 * 4. Actualizar el nuevo password en el usuario cifrándolo previamente.
	 * 5. Si hay algún error, lanzará UserException con el origen
	 */
	public void changePassword(Long idUser, String oldPassword, String newPassword) throws UserNotFoundException, UserUnauthorizedException, UserException;
	

	
	/** Recibe un login que puede ser un username o un email, y el password sin cifrar. El servicio tendrá que:
	 * 1. Verificar que existe algún usuario con ese username o email. Si no es así, lanzar UserNotFoundException
	 * 2. Verificar que password es correcta. Si lo es, lanzar UserUnauthorizedException
	 * 3. Actualizamos fecha del último login. Si hay algún error aquí, registramos en el log, pero continuamos.
	 * 4. Devolver el usuario con todos sus datos que ha realizado el login.
	 * 5. Si hay algún error, lanzará UserException con el origen
	 */
	public User login(String login, String password) throws UserNotFoundException, UserUnauthorizedException, UserException;
	
	
	
	/** Recibe el id de un usuario. El servicio tendrá que:
	 * 1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
	 * 2. Devolver los datos completos del usuario
	 * 3. Si hay algún error, lanzará UserException con el origen
	 */
	public User getUser(Long idUser) throws UserNotFoundException, UserException;
	
	
	
	
}
