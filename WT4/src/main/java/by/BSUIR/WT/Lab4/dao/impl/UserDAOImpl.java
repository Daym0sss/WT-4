package by.BSUIR.WT.Lab4.dao.impl;

import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.AbstractDAO;
import by.BSUIR.WT.Lab4.dao.Table;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.UserDAO;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapperFactory;
import by.BSUIR.WT.Lab4.entity.User;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO{

    private static final String FIND_USER_BY_EMAIL 	= "SELECT * FROM " + Table.USER + " WHERE email=?";
    private static final String SAVE_USER_QUERY 	= "INSERT INTO " + Table.USER + " (email, role_id, userInformation_id) VALUES (?, ?, ?)";
    private static final String DELETE_USER_QUERY 	= "DELETE FROM " + Table.USER + " WHERE id=?";

    public UserDAOImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), Table.USER);
    }

    @Override
    public Optional<User> findByEmail(String email) throws DAOException {
    	return executeQueryForSingleResult(FIND_USER_BY_EMAIL, email);
    }

    @Override
    public int save(User user) throws DAOException {
        return executeInsertQuery(SAVE_USER_QUERY,
                user.getRoleId(), user.getUserInformationId());
    }

    @Override
    public void removeById(int id) throws DAOException {
        executeUpdateQuery(DELETE_USER_QUERY, id);
    }
}
