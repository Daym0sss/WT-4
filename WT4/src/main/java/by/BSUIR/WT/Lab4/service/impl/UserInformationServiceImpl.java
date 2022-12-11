package by.BSUIR.WT.Lab4.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.DAOFactory;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.UserInformationDAO;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserInformation;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.UserInformationService;

public class UserInformationServiceImpl implements UserInformationService {

	@Override
	public Optional<UserInformation> retriveUserInformationById(int userInformationId) throws ServiceException {
		try {
			UserInformationDAO userInformationDAO = DAOFactory.getInstance().getUserInformationDAO();
			Optional<UserInformation> result = userInformationDAO.finndById(userInformationId);
			return result;
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<UserInformation> getUserInformationFromUsers(List<User> users) throws ServiceException {
		List<UserInformation> result = new ArrayList<>();
		try {
			for (User user : users) {
				Optional<UserInformation> userInformation = retriveUserInformationById(user.getUserInformationId());
				if (userInformation.isPresent()) {
					result.add(userInformation.get());
				}
			}
		}catch (ServiceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return result;
	}
}
