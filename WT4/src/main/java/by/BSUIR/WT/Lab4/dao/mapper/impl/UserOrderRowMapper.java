package by.BSUIR.WT.Lab4.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.BSUIR.WT.Lab4.dao.mapper.Column;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapper;
import by.BSUIR.WT.Lab4.entity.UserOrder;

public class UserOrderRowMapper implements RowMapper<UserOrder>{
	
	@Override
	public UserOrder map(ResultSet resultSet) throws SQLException {
		UserOrder userOrder = new UserOrder();
		userOrder.setId(resultSet.getInt(Column.ID));
		userOrder.setApartmentId(resultSet.getInt(Column.APARTMENT_ID));
		userOrder.setLeaseDuration(resultSet.getInt(Column.USER_ORDER_LEASE_DURATION));
		userOrder.setStartTime(resultSet.getTimestamp(Column.USER_ORDER_START_TIME));
		userOrder.setUserId(resultSet.getInt(Column.USER_ID));
		return userOrder;
	}
}
