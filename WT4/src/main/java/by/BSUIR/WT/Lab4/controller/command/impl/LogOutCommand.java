package by.BSUIR.WT.Lab4.controller.command.impl;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command{

	private static final String LOGIN_PAGE 	= "command=logIn";
	private static final String USER 		= "user";
	private static final String ROLE 		= "role";
	
	@Override
	public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
		RequestContext requestContext = helper.createContext();
		requestContext.removeSessionAttributes(USER);
		requestContext.removeSessionAttributes(ROLE);
		helper.updateRequest(requestContext);
		return new CommandResult(LOGIN_PAGE, CommandResultType.REDIRECT);
	}
}
