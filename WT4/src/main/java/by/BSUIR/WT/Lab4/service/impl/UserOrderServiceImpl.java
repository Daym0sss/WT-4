package by.BSUIR.WT.Lab4.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.DAOFactory;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.UserOrderDAO;
import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.UserOrderService;

public class UserOrderServiceImpl implements UserOrderService{
	
	private static final String status = "blocked";

    @Override
    public Optional<UserOrder> retriveUserOrderById(int userOrderId) throws ServiceException {
        try {
            UserOrderDAO userOrderDAO = DAOFactory.getInstance().getUserOrderDAO();
            Optional<UserOrder> result = userOrderDAO.finndById(userOrderId);
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserOrder> retriveUserOrderByUserId(int userId) throws ServiceException {
        try {
        	UserOrderDAO userOrderDAO = DAOFactory.getInstance().getUserOrderDAO();
            List<UserOrder> result = userOrderDAO.findByUserId(userId);
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserOrder> retriveUserOrderByStatus(String status) throws ServiceException {
        try {
        	UserOrderDAO userOrderDAO = DAOFactory.getInstance().getUserOrderDAO();
            List<UserOrder> result = userOrderDAO.findByStatus(status);
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateStatusAtUserOrderById(int userOrderId, String status) throws ServiceException {
        try {
        	UserOrderDAO userOrderDao = DAOFactory.getInstance().getUserOrderDAO();
            Optional<UserOrder> userOrder = userOrderDao.finndById(userOrderId);
            if (!userOrder.isPresent()) {
                return false;
            }
            userOrderDao.updateStatusById(userOrderId, status);
            return true;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addNewUserOrder( String stringYear,String stringMonth,String stringDay,String stringHours,
                                   String stringMinutes, String stringLeaseDuration,String stringUserId,String stringApartmentId) throws ServiceException {

        if ( stringYear == null  || stringMonth==null || stringHours == null ||
                stringLeaseDuration==null || stringUserId==null || stringApartmentId==null ) {
            return false;
        }

        int year, month, day, hours, minutes, leaseDuration, userId, apartmentId;        
        try {
	        year=Integer.parseInt(stringYear);
	        month=Integer.parseInt(stringMonth);
	        day=Integer.parseInt(stringDay);
	        hours=Integer.parseInt(stringHours);
	        minutes=Integer.parseInt(stringMinutes);
	        leaseDuration=Integer.parseInt(stringLeaseDuration);
	        userId=Integer.parseInt(stringUserId);
	        apartmentId=Integer.parseInt(stringApartmentId);
	
	        Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
	        Timestamp orderDateTime = new Timestamp(year-1900, month-1, day, hours, minutes, 0 ,0);
	        if(!isDateTimeValid(currentDateTime,orderDateTime)){
	            return false;
	        }
        }catch (NumberFormatException e) {
        	throw new ServiceException(e.getMessage(), e);
        }
        try {
        	UserOrder userOrder = buildUserOrder(status, leaseDuration, userId,apartmentId);
        	UserOrderDAO userOrderDAO = DAOFactory.getInstance().getUserOrderDAO();
            userOrderDAO.save(userOrder);
            return true;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private boolean isDateTimeValid(Timestamp currentDateTime, Timestamp orderDateTime) {
        if(currentDateTime.compareTo(orderDateTime)>0){
            return false;
        }
        return true;
    }

    private UserOrder buildUserOrder( String status, int leaseDuration,int userId,int apartmentId) {
        UserOrder userOrder = new UserOrder();
        userOrder.setStatus(status);
        userOrder.setLeaseDuration(leaseDuration);
        userOrder.setUserId(userId);
        userOrder.setApartmentId(apartmentId);
        return userOrder;
    }	
}
