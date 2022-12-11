package by.BSUIR.WT.Lab4.controller.command.impl;

import javax.servlet.http.HttpServletResponse;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.ApartmentService;

import java.util.Optional;

public class ConfirmAddingApartmentCommand implements Command{
    
	private static final String PAGE 				= "command=addApartment";
    private static final String ERROR_PAGE 			= "WEB-INF/view/error.jsp";
    private static final String STATUS 				= "status";
    private static final String PRICE 				= "price";
    private static final String APARTMENT_NUMBER 	= "apartmentNumber";
    private static final String MESSAGE_PARAMETER	= "&message=";
    private static final String ERROR 				= "error";
    private static final String OK 					= "ok";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {

        RequestContext requestContext = helper.createContext();
        String message = ERROR;
        Optional<String> status = Optional.ofNullable(requestContext.getRequestParameter(STATUS));
        Optional<String> price = Optional.ofNullable(requestContext.getRequestParameter(PRICE));
        Optional<String> apartmentNumber = Optional.ofNullable(requestContext.getRequestParameter(APARTMENT_NUMBER));
        try {
            if (status.isPresent() && price.isPresent() && apartmentNumber.isPresent()) {
                ApartmentService apartmentService = ServiceFactory.getInstance().getApartmentService();
                boolean result = apartmentService.addNewApartment(status.get(), apartmentNumber.get(), price.get());
                if (result) {
                    message = OK;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
    }
}