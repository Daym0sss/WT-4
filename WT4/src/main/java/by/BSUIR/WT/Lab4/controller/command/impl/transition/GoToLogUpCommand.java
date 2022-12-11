package by.BSUIR.WT.Lab4.controller.command.impl.transition;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public class GoToLogUpCommand implements Command{

	private static final String PAGE = "WEB-INF/view/logup.jsp";

	@Override
	public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
		RequestContext requestContext = helper.createContext();
		helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
	}
}
