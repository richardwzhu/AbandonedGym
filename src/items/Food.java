package items;

import interfaces.Edible;
import textadventure.World;

public class Food extends Item implements Edible{

	public Food(World world, String name, int weight, String description) {
		super(world, name, weight, description);
	}

	@Override
	public void doEat() {
		World.print("You eat the " + this.getName() + " and feel stronger!\n\n");
		getWorld().getPlayer().setHealth(getWorld().getPlayer().getHealth() + this.getWeight());
		getWorld().getPlayer().removeItem(this);
		getWorld().getPlayer().setBrush(false);
	}

	@Override
	public void doUse() {
		doEat();
	}

}
