package by.BSUIR.WT.Lab4.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import by.BSUIR.WT.Lab4.dao.DAOFactory;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.RoleDAO;
import by.BSUIR.WT.Lab4.dao.intrfs.UserDAO;
import by.BSUIR.WT.Lab4.dao.intrfs.UserInformationDAO;
import by.BSUIR.WT.Lab4.entity.Role;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserInformation;
import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.UserService;

public class UserServiceImpl implements UserService{


    private static final String user = "user";

    @Override
    public Optional<User> login(String email) throws ServiceException {
        if (email == null) {
            return Optional.empty();
        }
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            return userDAO.findByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getUsersFromOrders(List<UserOrder> orders) throws ServiceException {
        List<User> users = new LinkedList<>();
        try {
            for (UserOrder order : orders) {
                Optional<User> user = retriveUserById(order.getUserId());
                if (user.isPresent()) {
                    if (!users.contains(user.get())) {
                        users.add(user.get());
                    }
                }
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return users;
    }
    
    @Override
    public boolean register(String name, String email, String phone) throws ServiceException {
        if (name == null || email == null || phone == null) {
            return false;
        }

        try {
            UserDAO userDao = DAOFactory.getInstance().getUserDAO();
            if (userDao.findByEmail(email).isPresent()) {
                return false;
            }
            RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();
            Optional<Role> role = roleDAO.findByRole(user);
            if (!role.isPresent()) {
                return false;
            }

            UserInformationDAO userInformationDAO = DAOFactory.getInstance().getUserInformationDAO();
            UserInformation userInformation = buildUserInformation(name, phone);
            int userInformationId = userInformationDAO.save(userInformation);

            User user = buildUser(email, userInformationId, role.get().getId());
            userDao.save(user);

            return true;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> retriveUserById(int userId) throws ServiceException {
        try {
            UserDAO userDao = DAOFactory.getInstance().getUserDAO();
            Optional<User> result;
            result = userDao.finndById(userId);
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private UserInformation buildUserInformation(String name, String phone) {
        UserInformation userInformation = new UserInformation();
        userInformation.setName(name);
        userInformation.setPhone(phone);
        return userInformation;
    }

    private User buildUser(String email, int userInformationId, int roleId) {
        User user = new User();
        user.setEmail(email);
        user.setUserInformationId(userInformationId);
        user.setRoleId(roleId);
        return user;
    }
}
