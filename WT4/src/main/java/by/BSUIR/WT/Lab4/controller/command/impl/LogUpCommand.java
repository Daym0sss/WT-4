package by.BSUIR.WT.Lab4.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.UserService;

public class LogUpCommand implements Command{

	private static final String LOG_UP_PAGE	= "WEB-INF/view/logUp.jsp";
	private static final String ERROR_PAGE 	= "WEB-INF/view/error.jsp";
	private static final String EMAIL 		= "email";
	private static final String NAME 		= "name";
	private static final String PHONE 		= "phone";
	private static final String MESSAGE 	= "message";
	private static final String ERROR 		= "error";
	private static final String OK 			= "ok";
	
	
	@Override
	public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
		RequestContext requestContext = helper.createContext();
		String message = ERROR;
		Optional<String> email = Optional.ofNullable(requestContext.getRequestParameter(EMAIL));
        Optional<String> name = Optional.ofNullable(requestContext.getRequestParameter(NAME));
        Optional<String> phone = Optional.ofNullable(requestContext.getRequestParameter(PHONE));

        try {
            if (email.isPresent() && name.isPresent() && phone.isPresent()) {
	            UserService userService = ServiceFactory.getInstance().getUserService();
	            boolean result = userService.register(name.get(), email.get(), phone.get());
	            if (result) message = OK;
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        requestContext.addRequestAttributes(MESSAGE, message);
        helper.updateRequest(requestContext);
        return new CommandResult(LOG_UP_PAGE, CommandResultType.FORWARD);
	}
}
