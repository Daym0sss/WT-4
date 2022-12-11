package by.BSUIR.WT.Lab4.service.intrfc;

import java.util.Optional;

import by.BSUIR.WT.Lab4.entity.Role;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;

public interface RoleService {
	
	Optional<Role> retriveRoleById(int roleId) throws ServiceException;
	Optional<Role> retriveRoleByRoleName(String roleName) throws ServiceException;
}
