package by.BSUIR.WT.Lab4.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.BSUIR.WT.Lab4.dao.mapper.Column;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapper;
import by.BSUIR.WT.Lab4.entity.Role;

public class RoleRowMapper implements RowMapper<Role>{

	@Override
	public Role map(ResultSet resultSet) throws SQLException {
		Role role = new Role();
		role.setId(resultSet.getInt(Column.ID));
		role.setRole(resultSet.getString(Column.ROLE_ROLE));
		return role;
	}
}
