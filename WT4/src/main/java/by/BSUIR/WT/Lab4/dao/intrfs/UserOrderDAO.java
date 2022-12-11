package by.BSUIR.WT.Lab4.dao.intrfs;

import java.util.List;

import by.BSUIR.WT.Lab4.dao.DAO;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.entity.UserOrder;

public interface UserOrderDAO extends DAO<UserOrder> {

	List<UserOrder> findByStatus(String status) throws DAOException;
	List<UserOrder> findByUserId(int userId) throws DAOException;
	List<UserOrder> findByApartmentId(int apartmentId) throws DAOException;
	void updateStatusById(int id, String status) throws DAOException;

}
