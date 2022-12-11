package by.BSUIR.WT.Lab4.controller.command;

import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public interface Command {

	public CommandResult execute(RequestContextHelper helper, HttpServletResponse response);
}
