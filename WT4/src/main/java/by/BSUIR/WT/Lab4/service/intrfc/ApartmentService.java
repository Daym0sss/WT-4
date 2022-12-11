package by.BSUIR.WT.Lab4.service.intrfc;

import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.entity.Apartment;
import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;

public interface ApartmentService {

	List<Apartment> retriveAllApartments() throws ServiceException;
	Optional<Apartment> retriveApartmentById(int apartmentId) throws ServiceException;
	void removeApartmentById(int userId) throws ServiceException;
	List<Apartment> retriveApartmentByStatus(String status) throws ServiceException;
	List<Apartment> retrieveApartamentsByUserOrders(List<UserOrder> userOrder) throws ServiceException;
	boolean addNewApartment(String status, String number, String price) throws ServiceException;
	void updateApartmentStatusById(int id, String status) throws ServiceException;
	boolean updateApartmentInforamtion(String id, String status, String number, String price) throws ServiceException;
	List<Apartment> retriveApartamentsByUserId(int userId) throws ServiceException;
}
