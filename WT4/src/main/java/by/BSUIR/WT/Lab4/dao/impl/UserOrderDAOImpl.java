package by.BSUIR.WT.Lab4.dao.impl;

import java.util.List;

import by.BSUIR.WT.Lab4.dao.AbstractDAO;
import by.BSUIR.WT.Lab4.dao.Table;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.UserOrderDAO;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapperFactory;
import by.BSUIR.WT.Lab4.entity.UserOrder;

public class UserOrderDAOImpl extends AbstractDAO<UserOrder> implements UserOrderDAO{
	
    private static final String FIND_USER_ORDERS_BY_STATUS_QUERY 		= "SELECT * FROM " + Table.USER_ORDER + " WHERE status=?";
    private static final String UPDATE_USER_ORDER_STATUS_BY_ID_QUERY 	= "UPDATE " + Table.USER_ORDER + " SET status=? WHERE id=?";
    private static final String SAVE_USER_ORDER_QUERY 					= "INSERT INTO " + Table.USER_ORDER + " (status, start_time, lease_duration, user_id, apartment_id) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_USER_ORDERS_BY_USER_ID_QUERY		= "SELECT * FROM " + Table.USER_ORDER + " WHERE user_id=?";
    private static final String FIND_USER_ORDERS_BY_APARTMENT_ID		= "SELECT * FROM " + Table.USER_ORDER + " WHERE apartment_id=?";


    public UserOrderDAOImpl() {
        super(RowMapperFactory.getInstance().getUserOrderRowMapper(), Table.USER_ORDER);
    }

    @Override
    public List<UserOrder> findByStatus(String status) throws DAOException {
        return executeQuery(FIND_USER_ORDERS_BY_STATUS_QUERY, status);
    }

    @Override
    public List<UserOrder> findByApartmentId(int apartmentId) throws DAOException {
        return executeQuery(FIND_USER_ORDERS_BY_APARTMENT_ID, apartmentId);
    }

    @Override
    public void updateStatusById(int id, String status) throws DAOException {
        executeUpdateQuery(UPDATE_USER_ORDER_STATUS_BY_ID_QUERY, status, id);
    }

    @Override
    public int save(UserOrder userOrder) throws DAOException {
        return executeInsertQuery(SAVE_USER_ORDER_QUERY, userOrder.getStatus(),userOrder.getStartTime(),
                userOrder.getLeaseDuration(),userOrder.getUserId(),userOrder.getApartmentId());
    }

    @Override
    public List<UserOrder> findByUserId(int userId) throws DAOException {
        return executeQuery(FIND_USER_ORDERS_BY_USER_ID_QUERY,userId);
    }	

}
