package command;

import textadventure.World;

public class CommandRestart extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"restart"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
			world.initializeNewGame();
			world.play();
	}	
	
	@Override
	public String getHelpDescription() {
		return "";
	}
}
