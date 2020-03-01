package items;

import textadventure.DemoWorld;
import textadventure.World;

public class Keys extends Item {

	public Keys(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
	}

	@Override
	public void doUse() {
		if(getWorld().getPlayer().getCurrentRoom().getName().equals(DemoWorld.HALLWAY)){
			getWorld().getRoom("BATHROOM").doUnlock();
			World.print("You unlock the bathroom.\n\n");
		}else if(getWorld().getPlayer().getCurrentRoom().getName().equals(DemoWorld.KITCHEN)) {
			getWorld().getRoom("OUTSIDE").doUnlock();
			World.print("You unlock the door leading outside.\n\n");
		}else {
			World.print("The " + this.getName() + " doesn't fit anything here.\n\n");
		}
	}

}
