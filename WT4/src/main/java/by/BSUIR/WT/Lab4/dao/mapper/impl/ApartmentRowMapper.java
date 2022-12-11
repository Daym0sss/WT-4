package by.BSUIR.WT.Lab4.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.BSUIR.WT.Lab4.dao.mapper.Column;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapper;
import by.BSUIR.WT.Lab4.entity.Apartment;

public class ApartmentRowMapper implements RowMapper<Apartment> {

	@Override
	public Apartment map(ResultSet resultSet) throws SQLException{
		Apartment  apartment = new Apartment();
		apartment.setId(resultSet.getInt(Column.ID));
		apartment.setPrice(resultSet.getDouble(Column.APARTMENT_PRICE));
		apartment.setStatus(resultSet.getString(Column.APARTMENT_STATUS));
		return apartment;
	}
}
