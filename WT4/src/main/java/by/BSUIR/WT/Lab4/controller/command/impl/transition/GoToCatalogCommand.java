package by.BSUIR.WT.Lab4.controller.command.impl.transition;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.entity.Apartment;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.ApartmentService;
import by.BSUIR.WT.Lab4.service.intrfc.RoleService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToCatalogCommand implements Command{

    private static final String PAGE 		= "WEB-INF/view/catalog.jsp";
    private static final String ERROR_PAGE 	= "WEB-INF/view/error.jsp";
    private static final String APARTMENTS 	= "apartments";
    private static final String STATUS 		= "доступно";
    private static final String USER 		= "user";
    private static final String ADMIN_ROLE 	= "admin";



    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            ApartmentService apartmentService=ServiceFactory.getInstance().getApartmentService();
            try {
                List<Apartment> apartment=apartmentService.retriveApartmentByStatus(STATUS);
                requestContext.addRequestAttributes(ADMIN_ROLE, user);
                helper.updateRequest(requestContext);
            } catch (ServiceException e) {
                return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
            }
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }

        try {
            RoleService roleService=ServiceFactory.getInstance().getRoleService();
            ApartmentService apartmentService=ServiceFactory.getInstance().getApartmentService();
            List<Apartment> apartment;
            if (roleService.retriveRoleById(user.getRoleId()).get().getRole().equals(ADMIN_ROLE)){
                apartment=apartmentService.retriveAllApartments();
            }else {
                apartment=apartmentService.retriveApartmentByStatus(STATUS);
            }
            requestContext.addRequestAttributes(APARTMENTS,apartment);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}