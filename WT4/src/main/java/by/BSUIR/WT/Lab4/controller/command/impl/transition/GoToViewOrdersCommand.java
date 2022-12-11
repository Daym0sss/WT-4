package by.BSUIR.WT.Lab4.controller.command.impl.transition;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.entity.Apartment;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserInformation;
import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.ApartmentService;
import by.BSUIR.WT.Lab4.service.intrfc.UserInformationService;
import by.BSUIR.WT.Lab4.service.intrfc.UserOrderService;
import by.BSUIR.WT.Lab4.service.intrfc.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToViewOrdersCommand implements Command{

    private static final String PAGE 				= "WEB-INF/view/viewOrders.jsp";
    private static final String ERROR_PAGE 			= "WEB-INF/view/error.jsp";
    private static final String USER_ORDERS 		= "userOrders";
    private static final String USERS 				= "users";
    private static final String APARTMENTS 			= "apartments";
    private static final String USER_INFORMATION	= "userInformation";
    private static final String EXPECTED 			= "bloked";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        try {
            UserOrderService userOrderService = ServiceFactory.getInstance().getUserOrderService();
            List<UserOrder> userOrders = userOrderService.retriveUserOrderByStatus(EXPECTED);
            requestContext.addRequestAttributes(USER_ORDERS, userOrders);

            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = userService.getUsersFromOrders(userOrders);
            requestContext.addRequestAttributes(USERS, users);

            ApartmentService apartmentService=ServiceFactory.getInstance().getApartmentService();
            List<Apartment> apartments=apartmentService.retrieveApartamentsByUserOrders(userOrders);
            requestContext.addRequestAttributes(APARTMENTS, apartments);

            UserInformationService userInformationService = ServiceFactory.getInstance().getUserInformationService();
            List<UserInformation> userInformation = userInformationService.getUserInformationFromUsers(users);
            requestContext.addRequestAttributes(USER_INFORMATION, userInformation);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}