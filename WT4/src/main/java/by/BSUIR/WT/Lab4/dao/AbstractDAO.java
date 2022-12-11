package by.BSUIR.WT.Lab4.dao;

import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapper;
import by.BSUIR.WT.Lab4.entity.Identifiable;

public abstract class AbstractDAO<T extends Identifiable> extends AbstractQueryExecutor<T> implements DAO<T> {

	private final String tableName;
	
	public AbstractDAO(RowMapper<T> rowMapper, String tableName) {
		super(rowMapper);
		this.tableName = tableName;
	}
	
	@Override
	public List<T> findAll() throws DAOException {
		String query = "SELECT * FROM " + tableName;
		return executeQuery(query);
	}
	
	@Override
	public Optional<T> finndById(int id) throws DAOException {
		String query = "SELECT * FROM " + tableName + " WHERE id=?";
		return executeQueryForSingleResult(query, id);
	}
	
	@Override
	public void removeById(int id) throws DAOException {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE id=?";
		executeUpdateQuery(deleteQuery, id);
	}
}
