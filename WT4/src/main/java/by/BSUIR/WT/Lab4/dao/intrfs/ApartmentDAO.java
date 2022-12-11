package by.BSUIR.WT.Lab4.dao.intrfs;

import java.util.List;

import by.BSUIR.WT.Lab4.dao.DAO;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.entity.Apartment;

public interface ApartmentDAO extends DAO<Apartment>{

	List<Apartment> findByStatus(String status) throws DAOException;
	List<Apartment> findByPrice(double from, double to) throws DAOException;
	void updateApartmentById(int id, Apartment apartment) throws DAOException;
	void updateApartmentStatusById(int id, String status) throws DAOException;
}
