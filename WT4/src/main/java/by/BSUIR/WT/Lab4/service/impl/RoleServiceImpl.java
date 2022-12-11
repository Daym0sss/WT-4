package by.BSUIR.WT.Lab4.service.impl;

import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.DAOFactory;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.RoleDAO;
import by.BSUIR.WT.Lab4.entity.Role;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.RoleService;

public class RoleServiceImpl implements RoleService{
	
	@Override
	public Optional<Role> retriveRoleById(int roleId) throws ServiceException {
		try {
			RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();
			Optional<Role> result = roleDAO.finndById(roleId);
			return result;
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public Optional<Role> retriveRoleByRoleName(String roleName) throws ServiceException {
		try {
			RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();
			Optional<Role> result = roleDAO.findByRole(roleName);
			return result;
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
