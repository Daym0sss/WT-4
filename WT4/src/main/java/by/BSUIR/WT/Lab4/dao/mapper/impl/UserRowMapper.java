package by.BSUIR.WT.Lab4.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.BSUIR.WT.Lab4.dao.mapper.Column;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapper;
import by.BSUIR.WT.Lab4.entity.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User map(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt(Column.ID));
		user.setRoleId(resultSet.getInt(Column.ROLE_ID));
		user.setUserInformationId(resultSet.getInt(Column.USER_INFORMATION_ID));
		return user;
	}
}
