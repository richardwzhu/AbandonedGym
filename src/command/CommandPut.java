package command;

import items.Container;
import items.Item;
import textadventure.World;

public class CommandPut extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[] {"put", "place", "position"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if(!(params[1].equals("in") && params.length == 3)){
			World.print("Invalid syntax");
			return;
		}
		
		Item item = null;
		if(world.getPlayer().hasItem(params[0])) {
			item = world.getPlayer().getItem(params[0]);
		}else {
			item = world.getPlayer().getCurrentRoom().getItem(params[0]);
		}
		
		Item container = null;
		if(world.getPlayer().hasItem(params[2])) {
			container = world.getPlayer().getItem(params[2]);
		}else {
			container = world.getPlayer().getCurrentRoom().getItem(params[2]);
		}
		
		if(!world.getPlayer().hasItem(params[2]) && !world.getPlayer().getCurrentRoom().hasItem(params[2])) {
			World.print("You can't see any " + params[2] + " here!\n\n");
			return;
		}
		if(!world.getPlayer().hasItem(params[0]) && !world.getPlayer().getCurrentRoom().hasItem(params[0])) {
			World.print("You can't see any " + params[0] + " here!\n\n");
			return;
		}
		if(!(container instanceof Container)) {
			World.print("The " + params[2] + " can't hold things.\n\n");
			return;
		}
		if(params[0].equals(params[2])) {
			World.print("You can't put the " + params[2] + " into itself!\n\n");
			return;
		}
		if(world.getPlayer().hasItem(params[0])) {
			((Container)container).doPut(item, world.getPlayer());
		}else {
			((Container)container).doPut(item, world.getPlayer().getCurrentRoom());
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item] in [container]";
	}

}
