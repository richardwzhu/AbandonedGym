package command;

import textadventure.World;

public class CommandLook extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"look", "l"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		World.print("\n" + world.getPlayer().getCurrentRoom().getDescription());
	}	

	@Override
	public String getHelpDescription() {
		return "";
	}

}
