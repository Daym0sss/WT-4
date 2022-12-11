package by.BSUIR.WT.Lab4.dao.intrfs;

import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.DAO;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.entity.User;

public interface UserDAO extends DAO<User>{

	Optional<User> findByEmail(String email) throws DAOException;
}
