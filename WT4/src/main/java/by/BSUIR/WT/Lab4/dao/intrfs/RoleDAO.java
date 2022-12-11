package by.BSUIR.WT.Lab4.dao.intrfs;

import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.DAO;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.entity.Role;

public interface RoleDAO extends DAO<Role>{

	Optional<Role> findByRole(String role) throws DAOException;
}
