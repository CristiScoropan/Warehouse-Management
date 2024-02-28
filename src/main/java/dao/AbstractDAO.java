package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.deletequery;
import dao.selectquery;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;
	private deletequery deletequery;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		deletequery = new deletequery();

	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName()).append("s");
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	private String createInsertQuery(T t) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("INSERT INTO ").append(type.getSimpleName()).append("s (");

		for (Field field : type.getDeclaredFields()) {
			String fieldName = field.getName();
			stringBuilder.append(fieldName).append(", ");
		}

		stringBuilder.setLength(stringBuilder.length() - 2);
		stringBuilder.append(") VALUES (");
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(t);
				stringBuilder.append("'").append(value).append("', ");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		stringBuilder.setLength(stringBuilder.length() - 2);
		return stringBuilder.append(")").toString();
	}

	private String createUpdateQuery(T t, int id) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("UPDATE ").append(type.getSimpleName()).append("s SET ");

		for (Field field : type.getDeclaredFields()) {
			String fieldName = field.getName();

			field.setAccessible(true);
			Object value;
			try {
				value = field.get(t);
				stringBuilder.append(fieldName).append(" = '").append(value).append("', ");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		stringBuilder.setLength(stringBuilder.length() - 2);
		return stringBuilder.append(" WHERE id = ").append(id).toString();
	}

	private String createDeleteQuery(String field, String value) {
		deletequery.clear();
		deletequery.setTable(type.getSimpleName()).setField(field).setValue(value);
		return deletequery.build();
	}

	private String createFindAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName()).append("s");
		return sb.toString();
	}

	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAllQuery();

		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e){
			LOGGER.log(Level.WARNING, type.getName()+"DAO:findAll "+ e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
			return null;
	}

		public T findById(int id) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			String query = createSelectQuery("id");
			try {
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				resultSet = statement.executeQuery();

				return createObjects(resultSet).get(0);
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
			} finally {
				ConnectionFactory.close(resultSet);
				ConnectionFactory.close(statement);
				ConnectionFactory.close(connection);
			}
			return null;
		}

	public T findByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("name");
		query = query + "'" + name + "'";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			List<T> result = createObjects(resultSet);
			if (!result.isEmpty()) {
				return result.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

		private List<T> createObjects(ResultSet resultSet) {
			List<T> list = new ArrayList<T>();
			Constructor[] ctors = type.getDeclaredConstructors();
			Constructor ctor = null;
			for (int i = 0; i < ctors.length; i++) {
				ctor = ctors[i];
				if (ctor.getGenericParameterTypes().length == 0)
					break;
			}
			try {
				while (resultSet.next()) {
					ctor.setAccessible(true);
					T instance = (T)ctor.newInstance();
					for (Field field : type.getDeclaredFields()) {
						String fieldName = field.getName();
						Object value = resultSet.getObject(fieldName);
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
						Method method = propertyDescriptor.getWriteMethod();
						method.invoke(instance, value);
					}
					list.add(instance);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			return list;
		}

		public T insert(T t) {
			Connection connection = null;
			PreparedStatement statement = null;
			String query = createInsertQuery(t);
			try {
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(query);
				statement.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				ConnectionFactory.close(statement);
				ConnectionFactory.close(connection);
			}
			return t;
		}

		public T update(T t, int id) {
			Connection connection = null;
			PreparedStatement statement = null;
			String query = createUpdateQuery(t, id);
			try {
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(query);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectionFactory.close(statement);
				ConnectionFactory.close(connection);
			}
			return t;
		}

	public boolean deleteById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery("id", String.valueOf(id));
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return false;
	}
}
