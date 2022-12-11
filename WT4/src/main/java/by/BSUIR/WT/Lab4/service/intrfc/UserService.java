package by.BSUIR.WT.Lab4.service.intrfc;

import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;

public interface UserService {

	Optional<User> login(String email) throws ServiceException;
	boolean register(String name, String email, String phone) throws ServiceException;
	Optional<User> retriveUserById(int userId) throws ServiceException;
	List<User> getUsersFromOrders(List<UserOrder> orders) throws ServiceException;
}
