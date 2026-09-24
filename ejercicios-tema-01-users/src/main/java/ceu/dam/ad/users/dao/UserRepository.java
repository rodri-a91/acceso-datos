package ceu.dam.ad.users.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import ceu.dam.ad.users.model.User;

public class UserRepository {

	/** Debe insertar un usuario en BBDD. Devuelve el ID generado. */
	public Long insert(Connection conn, User user) throws SQLException {
		String sql = "INSERT INTO USER VALUES(NULL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getPassword());

		if (user.getLastLoginDate() == null) {
			stmt.setNull(4, JDBCType.DATE.ordinal());
		} else {
			stmt.setDate(4, Date.valueOf(user.getLastLoginDate()));
		}

		stmt.setString(5, user.getName());
		user.setCreatedDate(LocalDate.now());
		stmt.setDate(6, Date.valueOf(user.getCreatedDate()));
		stmt.execute();

		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getLong(1);
		}

		return null;

	}

	/**
	 * Debe consultar un usuario por su email y devolverlo. Si no existe, devolverá
	 * null
	 * 
	 * @throws SQLException
	 */
	private User fillUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setUsername(rs.getString("username"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		Date lastDateLogin = rs.getDate("last_login_date");
		user.setLastLoginDate(lastDateLogin == null ? null : lastDateLogin.toLocalDate());
		user.setName(rs.getString("name"));
		user.setCreatedDate(rs.getDate("created_date").toLocalDate());
		return user;
	}

	public User getByEmail(Connection conn, String email) throws SQLException {
		String sql = "SELECT * FROM USER WHERE EMAIL = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		if (!rs.next())
			return null;

		return fillUser(rs);
	}

	/**
	 * Debe consultar un usuario por su ID y devolverlo. Si no existe, devolverá
	 * null. NOTA: no dupliques código
	 */
	public User getById(Connection conn, Long id) throws SQLException {
		String sql = "SELECT * FROM USER WHERE ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (!rs.next())
			return null;

		return fillUser(rs);
	}

	/**
	 * Debe consultar un usuario por su email y devolverlo. Si no existe, devolverá
	 * null. NOTA: no dupliques código
	 */
	public User getByUserName(Connection conn, String userName) throws SQLException {
		String sql = "SELECT * FROM USER WHERE USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);

		ResultSet rs = stmt.executeQuery();
		if (!rs.next())
			return null;

		return fillUser(rs);
	}

	/**
	 * Debe actualizar todos los datos de un usuario y devolver el número de
	 * registros actualizados.
	 */
	public Integer update(Connection conn, User user) throws SQLException {
		String sql = "UPDATE USER SET username = ?, email = ?, password = ?, last_login_date = ?, name = ?, created_date = ? WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getPassword());
		stmt.setDate(4, Date.valueOf(user.getLastLoginDate()));
		stmt.setString(5, user.getName());
		stmt.setDate(6, Date.valueOf(user.getCreatedDate()));
		stmt.setLong(7, user.getId());

		return stmt.executeUpdate();
	}

}
