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
			Command commandhandler = Command.getCommandHandlerForWord(params[0]);
			World.print(Command.getHelpDescription(commandhandler) + "\n\n");
		} else {
			World.print("This is a text adventure game.  Your job is to explore your surroundings, " +
				        "collect and use items, and discover how to win the game.\n\n");
			World.print("Valid Commands:\n\n");
			for(Command command : getAllCommandHandlers()) {
				World.print(Command.getHelpDescription(command) + "\n");
			}
			World.print("\n");			
		}
	}

	@Override
	public String getHelpDescription() {
		return "[command (optional)]";
	}
}
