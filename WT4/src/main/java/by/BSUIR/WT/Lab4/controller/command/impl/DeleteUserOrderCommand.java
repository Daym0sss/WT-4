package by.BSUIR.WT.Lab4.controller.command.impl;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.UserOrderService;

import javax.servlet.http.HttpServletResponse;

public class DeleteUserOrderCommand implements Command{

	private static final String PAGE			= "command=viewOrders";
	private static final String USER_ORDER_ID	= "userOrderId";
	private static final String ERROR_PAGE		= "WEB-INF/view/error.jsp";
	private static final String CANCELED		= "canceled";
		
	public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
		RequestContext requestContext = helper.createContext();
		try {
			int userOrderId = Integer.parseInt(requestContext.getRequestParameter(USER_ORDER_ID));
			UserOrderService userOrderService = ServiceFactory.getInstance().getUserOrderService();
			userOrderService.updateStatusAtUserOrderById(userOrderId, CANCELED);
		}catch (ServiceException e) {
			return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
		}
		helper.updateRequest(requestContext);
		return new CommandResult(PAGE, CommandResultType.REDIRECT);
	}
}
