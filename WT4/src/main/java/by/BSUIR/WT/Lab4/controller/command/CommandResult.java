package by.BSUIR.WT.Lab4.controller.command;

public class CommandResult {
	
	private final String page;
	private final CommandResultType type;
	
	public CommandResult(String page, CommandResultType resultType) {
		this.page = page;
		this.type = resultType;
	}
	
	public boolean isRedirect() {
		return type == CommandResultType.REDIRECT;
	}
	
	public boolean isForward() {
		return type == CommandResultType.FORWARD;
	}
	
	public String getPage() {
		return page;
	}
}
