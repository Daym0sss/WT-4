package by.BSUIR.WT.Lab4.controller;

import java.io.*;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandFactory;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.dao.connection.exception.ConnectionException;

import javax.servlet.annotation.*;

public class Controller extends HttpServlet {
	
	private static final String COMMAND 		= "command";
	private static final String PATH 			= "/hotel?";
	private static final String MAIN_COMMAND 	= "command=main";
	
	@Override
	public void init() throws ServletException{
		super.init();
		try {
			ConnectionProtocol.getInstance().initialize();
		}catch(ConnectionException e) {
			throw new RuntimeErrorException(e);
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponce responce) throws ServletException, IOException{
		process(request, responce);
	}
	
	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponce responce) throws ServletException, IOException){
		process(request, responce);
	}
	
	@Override
	public void destroy() {}
	
	public void process(HttpServletRequest request, HttpServletResponce responce) throws ServletException, IOException{
		String commandName = request.getParameter(COMMAND);
		
		if (commandName == null || "".equals(commandName)) {
			responce.sendRedirect(PATH+MAIN_COMMAND);
		}else {
			Command command = CommandFactory.getInstance().getCommand(commandName);
            RequestContextHelper contextHelper = new RequestContextHelper(request);
            CommandResult result = command.execute(contextHelper, responce);
            dispatch(result, request, responce);
		}
	}
	
    private void dispatch(CommandResult result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (result.isRedirect()) {
            response.sendRedirect(PATH + result.getPage());
        } else {
            request.getRequestDispatcher(result.getPage()).forward(request, response);
        }
    }
}