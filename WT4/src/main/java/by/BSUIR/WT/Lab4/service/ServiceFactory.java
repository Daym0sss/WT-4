package by.BSUIR.WT.Lab4.service;

import by.BSUIR.WT.Lab4.service.impl.ApartmentServiceImpl;
import by.BSUIR.WT.Lab4.service.impl.RoleServiceImpl;
import by.BSUIR.WT.Lab4.service.impl.UserInformationServiceImpl;
import by.BSUIR.WT.Lab4.service.impl.UserOrderServiceImpl;
import by.BSUIR.WT.Lab4.service.impl.UserServiceImpl;
import by.BSUIR.WT.Lab4.service.intrfc.ApartmentService;
import by.BSUIR.WT.Lab4.service.intrfc.RoleService;
import by.BSUIR.WT.Lab4.service.intrfc.UserInformationService;
import by.BSUIR.WT.Lab4.service.intrfc.UserOrderService;
import by.BSUIR.WT.Lab4.service.intrfc.UserService;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final UserOrderService userOrderService = new UserOrderServiceImpl();
	private final UserInformationService userInformationService = new UserInformationServiceImpl();
	private final ApartmentService apartmentService = new ApartmentServiceImpl();
	private final UserService userService = new UserServiceImpl();
 	private final RoleService roleService = new RoleServiceImpl();
	
 	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
    public ApartmentService getApartmentService() {
        return apartmentService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserOrderService getUserOrderService() {
        return userOrderService;
    }

    public UserInformationService getUserInformationService() {
        return userInformationService;
    }
}
