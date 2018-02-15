package command;

import textadventure.World;

public class CommandHelp extends Command {

	public CommandHelp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] getCommandWords() {
		return new String[]{"help"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length > 0) {
			Command commandhandler = Command.getCommandHandlerForWord(cmd);
			World.print(Command.getHelpDescription(commandhandler));
		} else {
			for(Command command : getAllCommandHandlers()) {
				World.print(Command.getHelpDescription(command) + "\n");
			}
			
		}
	}

	@Override
	public String getHelpDescription() {
		return "[command (optional)]";
	}

}
