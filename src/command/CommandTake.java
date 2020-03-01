package command;

import items.Container;
import items.Item;
import textadventure.World;

public class CommandTake extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"take", "get", "grab", "hold"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length == 1) {
			String itemName = params[0];
			if (world.getPlayer().getCurrentRoom().hasItem(itemName)) {
				Item item = world.getPlayer().getCurrentRoom().getItem(itemName);
				item.doTake(world.getPlayer().getCurrentRoom());
			} else if (world.getPlayer().hasItem(itemName)) {
				World.print("You already have that!\n\n");
			} else {
				World.print("You can't see any " + itemName + " here.\n\n");
			}
		}else if (params.length == 3) {
			if(!params[1].equals("from")){
				World.print("I dont understand.\n\n");
				return;
			}
			Item container = null;
			if(world.getPlayer().hasItem(params[2])) {
				container = world.getPlayer().getItem(params[2]);
			}else {
				container = world.getPlayer().getCurrentRoom().getItem(params[2]);
			}
			if(container == null) {
				World.print("You can't see any " + params[2] + " here.\n\n");
				return;
			}
			if(!(container instanceof Container)) {
				World.print("The " + params[2] + " can't hold things!\n\n");
				return;
			}
			if(!((Container)container).hasItem(params[0])) {
				World.print("The " + params[2] + " doesn't have a " + params[0] + ".\n\n");
				return;
			}
			((Container)container).doTake(((Container)container).getItem(params[0]));
		} else {
			World.print("I don't understand.\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item] or [item] from [container]";
	}

}
