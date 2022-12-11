package by.BSUIR.WT.Lab4.service.intrfc;

import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;

public interface UserOrderService {

	Optional<UserOrder> retriveUserOrderById(int userOrderId) throws ServiceException;
	List<UserOrder> retriveUserOrderByUserId(int userId) throws ServiceException;
	List<UserOrder> retriveUserOrderByStatus(String status) throws ServiceException;
	boolean updateStatusAtUserOrderById(int userOrderId, String status) throws ServiceException;
	boolean addNewUserOrder(String year, String month, String day, String hours, 
			String minutes, String leaseDuration, String userId, String apartmentId) throws ServiceException;
}
