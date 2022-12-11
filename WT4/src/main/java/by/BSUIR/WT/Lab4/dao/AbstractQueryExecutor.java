package by.BSUIR.WT.Lab4.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.SynchronousQueue;

import by.BSUIR.WT.Lab4.dao.connection.ConnectionPool;
import by.BSUIR.WT.Lab4.dao.connection.ProxyConnection;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapper;
import by.BSUIR.WT.Lab4.entity.Identifiable;

public class AbstractQueryExecutor <T extends Identifiable> {
	
	private final RowMapper<T> rowMapper;
	
	public AbstractQueryExecutor(RowMapper<T> rowMapper) {
		this.rowMapper = rowMapper;
	}
	
	protected List<T> executeQuery(String query, Object... params) throws DAOException{
		List<T> entities;
		try (PreparedStatement statement = createStatement(query, params)){
			ResultSet resultSet = statement.getGeneratedKeys();
			entities = createEntitiesList(resultSet);
		}catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return entities;
	}
	
	protected Optional<T> executeQueryForSingleResult(String query, Object... params) throws DAOException{
		List<T> items = executeQuery(query, params);
		if (items.isEmpty()) {
			return Optional.empty();
		}
		
		if(!(items.size() == 1)) {
			return Optional.empty();
		}
		
		return Optional.of(items.get(0));
	}
	
	protected int executeInsertQuery(String query, Object... params) throws DAOException{
		int result = 0;
		try (PreparedStatement statement = createStatement(query, params)){
			statement.executeUpdate();
			ResultSet generatedKey = statement.getGeneratedKeys();
			if (generatedKey.next()) {
				result = generatedKey.getInt(1);
			}
		}catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return result;
	}
	
	protected void executeUpdateQuery(String query, Object... params) throws DAOException{
		try (PreparedStatement statement = createStatement(query, params)){
			statement.executeUpdate();
		}catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	private PreparedStatement createStatement (String query, Object... params) throws DAOException{
		try {
			ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
			PreparedStatement preparedStatement = proxyConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i+1, params[i]);
			}
			ConnectionPool.getInstance().releaseConnection(proxyConnection);
			return preparedStatement;
		}catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	private List<T> createEntitiesList(ResultSet resultSet) throws DAOException{
		List<T> entities = new ArrayList<>();
		try {
			while (resultSet.next()) {
				T entity = rowMapper.map(resultSet);
				entities.add(entity);
			}
		}catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return entities;
		
	}
}
