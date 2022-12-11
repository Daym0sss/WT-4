package by.BSUIR.WT.Lab4.controller.command.impl;

import javax.servlet.http.HttpServletResponse;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.entity.Role;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.RoleService;
import by.BSUIR.WT.Lab4.service.intrfc.UserService;

import java.util.Optional;

public class LogInCommand implements Command{

    private static final String PROFILE_PAGE 	= "command=profile";
    private static final String ERROR_PAGE 		= "WEB-INF/view/error.jsp";
    private static final String LOGIN_PAGE 		= "WEB-INF/view/login.jsp";
    private static final String EMAIL_PARAMETER	= "email";
    private static final String USER 			= "user";
    private static final String ROLE 			= "role";
    private static final String ERROR_MESSAGE 	= "errorMessage";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        String login = requestContext.getRequestParameter(EMAIL_PARAMETER);
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            Optional<User> optionalResult = userService.login(login);
            if (optionalResult.isPresent()) {
                requestContext.addSessionAttribute(USER, optionalResult.get());

                RoleService roleService = ServiceFactory.getInstance().getRoleService();
                Optional<Role> role = roleService.retriveRoleById(optionalResult.get().getRoleId());
                role.ifPresent(value -> requestContext.addSessionAttribute(ROLE, value));

                helper.updateRequest(requestContext);
                return new CommandResult(PROFILE_PAGE, CommandResultType.REDIRECT);
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        requestContext.addRequestAttributes(ERROR_MESSAGE, true);
        helper.updateRequest(requestContext);
        return new CommandResult(LOGIN_PAGE, CommandResultType.FORWARD);
    }	
}
