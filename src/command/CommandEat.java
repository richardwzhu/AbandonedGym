package command;

import interfaces.Edible;
import items.Item;
import textadventure.World;

public class CommandEat extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"eat", "consume"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length != 1) {
			World.print("What do you want to eat?\n\n");
			return;
		}
		if (!world.getPlayer().hasItem(params[0])) {
			World.print("You don't have the " + params[0] + ".\n\n");
			return;
		}
		else {
			if (world.getPlayer().getItem(params[0]) instanceof Edible)
				((Edible)world.getPlayer().getItem(params[0])).doEat();
			else
				World.print("That's plainly inedible!\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item]";
	}
}
