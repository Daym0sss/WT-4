package by.BSUIR.WT.Lab4.dao;

import by.BSUIR.WT.Lab4.dao.impl.ApartmentDAOImp;
import by.BSUIR.WT.Lab4.dao.impl.RoleDAOImpl;
import by.BSUIR.WT.Lab4.dao.impl.UserDAOImpl;
import by.BSUIR.WT.Lab4.dao.impl.UserInformationDAOImpl;
import by.BSUIR.WT.Lab4.dao.impl.UserOrderDAOImpl;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();
	
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }
    
	private final UserDAOImpl userDao = new UserDAOImpl();
    private final RoleDAOImpl roleDao = new RoleDAOImpl();
    private final UserInformationDAOImpl userInformationDao = new UserInformationDAOImpl();
    private final UserOrderDAOImpl userOrderDao=new UserOrderDAOImpl();
    private final ApartmentDAOImp apartamentDao=new ApartmentDAOImp();

    public UserDAOImpl getUserDAO() {
        return userDao;
    }

    public RoleDAOImpl getRoleDAO() {
        return roleDao;
    }

    public UserInformationDAOImpl getUserInformationDAO() {
        return userInformationDao;
    }

    public UserOrderDAOImpl getUserOrderDAO() {
        return userOrderDao;
    }

    public ApartmentDAOImp getApartamentDAO() {
        return apartamentDao;
    }
}
