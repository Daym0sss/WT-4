package by.BSUIR.WT.Lab4.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.BSUIR.WT.Lab4.entity.Identifiable;

public interface RowMapper <T extends Identifiable> {
	
	T map(ResultSet resultSet) throws SQLException;
}
