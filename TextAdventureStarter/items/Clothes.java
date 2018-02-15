package items;

import textadventure.World;

public class Clothes extends Item {

	public Clothes(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
	}

	@Override
	public void doUse() {
		World.print("You put on your " + getName() + " for the day.\n\n");
		getWorld().getPlayer().removeItem(this);
	}

}
