package by.BSUIR.WT.Lab4.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.undo.AbstractUndoableEdit;

import by.BSUIR.WT.Lab4.dao.DAO;
import by.BSUIR.WT.Lab4.dao.DAOFactory;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.ApartmentDAO;
import by.BSUIR.WT.Lab4.dao.intrfs.UserOrderDAO;
import by.BSUIR.WT.Lab4.entity.Apartment;
import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.ApartmentService;

public class ApartmentServiceImpl implements ApartmentService{

	@Override
	public List<Apartment> retriveApartmentByStatus(String status) throws ServiceException {
		try {
			ApartmentDAO apartmentDAO = DAOFactory.getInstance().getApartamentDAO();
			List<Apartment> result = apartmentDAO.findByStatus(status);
			return result;
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean addNewApartment(String status, String number, String price) throws ServiceException {
		if (status == null || number == null || price == null) {
			return false;
		}
		
		double dblPrice;
		int intNumber;
		try {
			dblPrice = Double.parseDouble(price);
			intNumber = Integer.parseInt(number);
		}catch(NumberFormatException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
		ApartmentDAO apartmentDAO = DAOFactory.getInstance().getApartamentDAO();
		Apartment apartment = buildApartment(status, intNumber, dblPrice);
		try {
			apartmentDAO.save(apartment);
			return true;
		}catch(DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Apartment> retrieveApartamentsByUserOrders(List<UserOrder> userOrders) throws ServiceException {
		try {
			ApartmentDAO apartmentDAO = DAOFactory.getInstance().getApartamentDAO();
			List<Apartment> result = new ArrayList<>();
			for (UserOrder userOrder : userOrders) {
				result.add(apartmentDAO.finndById(userOrder.getApartmentId()).get());
			}
			return result;
		}catch(DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
    @Override
    public List<Apartment> retriveApartamentsByUserId(int userId) throws ServiceException {
        try {
            UserOrderDAO userOrderDao=DAOFactory.getInstance().getUserOrderDAO();
            List<UserOrder> userOrders=userOrderDao.findByUserId(userId);
            List<Apartment> result=new ArrayList<>();
            ApartmentDAO apartamentDao= DAOFactory.getInstance().getApartamentDAO();
            for (UserOrder userOrder : userOrders) {
                result.add(apartamentDao.finndById(userOrder.getApartmentId()).get());
            }
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
	
	@Override
	public Optional<Apartment> retriveApartmentById(int apartmentId) throws ServiceException {
		try {
			ApartmentDAO apartmentDAO = DAOFactory.getInstance().getApartamentDAO();
			Optional<Apartment> result = apartmentDAO.finndById(apartmentId);
			return result;
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
    @Override
    public void removeApartmentById(int apartmentId) throws ServiceException {
        try {
            ApartmentDAO apartamentDAO= DAOFactory.getInstance().getApartamentDAO();
            UserOrderDAO userOrderDAO=DAOFactory.getInstance().getUserOrderDAO();

            List<UserOrder> userOrders=userOrderDAO.findByApartmentId(apartmentId);
            for (UserOrder userOrder : userOrders) {
                userOrderDAO.removeById(userOrder.getId());
            }
            apartamentDAO.removeById(apartmentId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<Apartment> retriveAllApartments() throws ServiceException {
        try {
            ApartmentDAO apartamentDAO = DAOFactory.getInstance().getApartamentDAO();
            List<Apartment> result = apartamentDAO.findAll();
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    
    @Override
    public boolean updateApartmentInforamtion(String id, String status, String number, String price) throws ServiceException {
    	if(status == null || price == null){
            return false;
        }

        double dblPrice;
        int intId;
        int intNumber;
        try{
        	dblPrice = Double.parseDouble(price);
        	intId = Integer.parseInt(id);
        	intNumber = Integer.parseInt(number);
        }catch (NumberFormatException e) {
        	throw new ServiceException(e.getMessage(), e);
        }

        ApartmentDAO apartamentDAO = DAOFactory.getInstance().getApartamentDAO();
        Apartment apartment = buildApartment(status, intNumber, dblPrice);
        try {
            apartamentDAO.updateApartmentById(intId, apartment);
            return true;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }	
	
    @Override
    public void updateApartmentStatusById(int id, String status) throws ServiceException {
        ApartmentDAO apartamentDAO = DAOFactory.getInstance().getApartamentDAO();
        try {
            apartamentDAO.updateApartmentStatusById(id,status);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    
	private Apartment buildApartment(String status, int number, double price) {
		Apartment apartment = new Apartment();
		apartment.setStatus(status);
		apartment.setNumber(number);
		apartment.setPrice(price);
		return apartment;
	}
	
}
