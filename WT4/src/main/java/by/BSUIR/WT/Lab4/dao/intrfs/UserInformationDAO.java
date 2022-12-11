package by.BSUIR.WT.Lab4.dao.intrfs;

import by.BSUIR.WT.Lab4.dao.DAO;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.entity.UserInformation;

public interface UserInformationDAO extends DAO<UserInformation>{

	void updateById(int id, UserInformation userInformation) throws DAOException;
}
