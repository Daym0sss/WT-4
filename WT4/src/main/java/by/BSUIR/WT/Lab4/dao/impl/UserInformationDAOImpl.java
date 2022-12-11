package by.BSUIR.WT.Lab4.dao.impl;

import by.BSUIR.WT.Lab4.dao.AbstractDAO;
import by.BSUIR.WT.Lab4.dao.Table;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.UserInformationDAO;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapperFactory;
import by.BSUIR.WT.Lab4.entity.UserInformation;

public class UserInformationDAOImpl extends AbstractDAO<UserInformation> implements UserInformationDAO{

    private static final String SAVE_USER_INFORMATION_QUERY = "INSERT INTO " + Table.USER_INFORMATION + " (name,  phone) VALUES (?, ?)";
    private static final String UPDATE_BY_ID 				= "UPDATE "+ Table.USER_INFORMATION +"  SET name=?, WHERE account_id=?";

    public UserInformationDAOImpl() {
        super(RowMapperFactory.getInstance().getUserInformationRowMapper(), Table.USER_INFORMATION);
    }

    @Override
    public int save(UserInformation userInformation) throws DAOException {
        return executeInsertQuery(SAVE_USER_INFORMATION_QUERY, userInformation.getName());
    }

    @Override
    public void updateById(int id, UserInformation userInformation) throws DAOException {
        executeUpdateQuery(UPDATE_BY_ID,userInformation.getName(),id);
    }
}
