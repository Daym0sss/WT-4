package by.BSUIR.WT.Lab4.service.intrfc;

import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserInformation;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;

public interface UserInformationService {

	Optional<UserInformation> retriveUserInformationById(int userInformationId) throws ServiceException;
	List<UserInformation> getUserInformationFromUsers(List<User> users) throws ServiceException;
}
