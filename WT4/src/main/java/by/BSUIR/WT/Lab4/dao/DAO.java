package by.BSUIR.WT.Lab4.dao;

import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.exception.DAOException;

public interface DAO<T> {

	List<T> findAll() throws DAOException;
	Optional<T> finndById(int id) throws DAOException;
	int save(T item) throws DAOException;
	void removeById(int id) throws DAOException;
}
