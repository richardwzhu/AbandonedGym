package items;

import textadventure.World;

public class Toothbrush extends Item {

	public Toothbrush(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
	}

	@Override
	public void doUse() {
		World.print("You have brushed your teeth!\n\n");
		getWorld().getPlayer().setBrush(true);
	}

}
