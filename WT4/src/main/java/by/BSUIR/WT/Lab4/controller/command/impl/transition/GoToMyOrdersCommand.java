package by.BSUIR.WT.Lab4.controller.command.impl.transition;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.entity.Apartment;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserOrder;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.ApartmentService;
import by.BSUIR.WT.Lab4.service.intrfc.UserOrderService;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class GoToMyOrdersCommand implements Command{

	private static final String PAGE 		= "WEB-INF/view/myOrders.jsp";
    private static final String ERROR_PAGE 	= "WEB-INF/view/error.jsp";
    private static final String USER_ORDERS	= "userOrders";
    private static final String APARTMENTS 	= "apartments";
    private static final String USER 		= "user";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }
        try {
            UserOrderService userOrderService=ServiceFactory.getInstance().getUserOrderService();
            List<UserOrder> userOrders=userOrderService.retriveUserOrderByUserId(user.getId());
            requestContext.addRequestAttributes(USER_ORDERS, userOrders);
            ApartmentService apartmentService=ServiceFactory.getInstance().getApartmentService();
            List<Apartment> apartments=apartmentService.retriveApartamentsByUserId(user.getId());
            requestContext.addRequestAttributes(APARTMENTS, apartments);

        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }	
}
