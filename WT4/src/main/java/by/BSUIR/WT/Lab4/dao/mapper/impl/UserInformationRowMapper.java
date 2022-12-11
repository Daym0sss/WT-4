package by.BSUIR.WT.Lab4.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.BSUIR.WT.Lab4.dao.mapper.Column;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapper;
import by.BSUIR.WT.Lab4.entity.UserInformation;

public class UserInformationRowMapper implements RowMapper<UserInformation>{

	@Override
	public UserInformation map(ResultSet resultSet) throws SQLException {
		UserInformation userInformation = new UserInformation();
		userInformation.setId(resultSet.getInt(Column.ID));
		userInformation.setName(resultSet.getString(Column.USER_INFORMATION_NAME));
		userInformation.setPhone(resultSet.getString(Column.USER_INFORMATION_PHONE));
		return userInformation;
	}
}
