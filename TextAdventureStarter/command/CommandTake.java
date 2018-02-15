package command;

import items.Item;
import textadventure.World;

public class CommandTake extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"take", "get", "grab", "hold"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length >= 1) {
			String secondWord = params[0];
			if (world.getPlayer().getCurrentRoom().hasItem(secondWord)) {
				Item item = world.getPlayer().getCurrentRoom().getItem(secondWord);
				item.doTake();
			}
			else if (world.getPlayer().hasItem(secondWord))
				World.print("You already have that!\n\n");
			else
				World.print("You can't see any " + secondWord + " here.\n\n");
		}
		else
			World.print("What do you want to take?\n\n");
	}

	@Override
	public String getHelpDescription() {
		return "[item]";
	}

}
