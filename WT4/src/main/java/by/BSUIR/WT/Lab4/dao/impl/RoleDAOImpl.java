package by.BSUIR.WT.Lab4.dao.impl;

import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.AbstractDAO;
import by.BSUIR.WT.Lab4.dao.Table;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.RoleDAO;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapperFactory;
import by.BSUIR.WT.Lab4.entity.Role;

public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO {

    private static final String SAVE_ROLE_QUERY 		= "INSERT INTO " + Table.ROLE + " (role) VALUES (?)";
    private static final String FIND_ROLE_BY_NAME_QUERY = "SELECT * FROM " + Table.ROLE + " WHERE role=?";

    public RoleDAOImpl() {
    	super(RowMapperFactory.getInstance().getRoleRowMapper(), Table.ROLE);
    }
    
    @Override
    public int save(Role role) throws DAOException {
    	return executeInsertQuery(SAVE_ROLE_QUERY, role.getRole());
    }
    
    @Override
    public Optional<Role> findByRole(String role) throws DAOException {
    	return executeQueryForSingleResult(FIND_ROLE_BY_NAME_QUERY, role);
    }
}
