package by.BSUIR.WT.Lab4.dao.mapper;

import by.BSUIR.WT.Lab4.dao.mapper.impl.ApartmentRowMapper;
import by.BSUIR.WT.Lab4.dao.mapper.impl.RoleRowMapper;
import by.BSUIR.WT.Lab4.dao.mapper.impl.UserInformationRowMapper;
import by.BSUIR.WT.Lab4.dao.mapper.impl.UserOrderRowMapper;
import by.BSUIR.WT.Lab4.dao.mapper.impl.UserRowMapper;
import by.BSUIR.WT.Lab4.entity.Apartment;
import by.BSUIR.WT.Lab4.entity.Role;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserInformation;
import by.BSUIR.WT.Lab4.entity.UserOrder;

public class RowMapperFactory {

	private static final RowMapperFactory instance = new RowMapperFactory();
	
	private RowMapperFactory() {}
	
	public static final RowMapperFactory getInstance() {
		return instance;
	}
	
	private final RowMapper<Apartment> apartmentRowMapper = new ApartmentRowMapper();
	private final RowMapper<Role> roleRowMapper = new RoleRowMapper();
	private final RowMapper<UserInformation> userInformationRowMapper = new UserInformationRowMapper();
	private final RowMapper<UserOrder> userOrderRowMapper = new UserOrderRowMapper();
	private final RowMapper<User> userRowMapper = new UserRowMapper();

    public RowMapper<Apartment> getApartmentRowMapper() {
        return apartmentRowMapper;
    }
    
    public RowMapper<Role> getRoleRowMapper() {
        return roleRowMapper;
    }
    
    public RowMapper<UserInformation> getUserInformationRowMapper() {
        return userInformationRowMapper;
    }
    
    public RowMapper<UserOrder> getUserOrderRowMapper() {
        return userOrderRowMapper;
    }
    
    public RowMapper<User> getUserRowMapper() {
        return userRowMapper;
    }
}
