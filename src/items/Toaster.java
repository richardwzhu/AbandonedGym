package items;

import textadventure.World;

public class Toaster extends Container {

	public Toaster(World world, String name, String description) {
		super(world, name, description);
	}

	@Override
	public void doUse() {
		if (numItems() > 1) {
			World.print("There are too many items in the " + getName() + "\n\n");
			return;
		}
		if (numItems() < 1) {
			World.print("There's nothing in the " + getName() + " to toast\n\n");
			return;
		}
		if (getItems().get(0).getName().equals("bread")) {
			World.print("You toast the " + getItems().get(0).getName() + ", transforming it into a light and crispy treat!\n\n");
			removeItem("bread");
			addItem(new Food(getWorld(), "toast", 2, "A light and crispy piece of toast."));
		}else {
			World.print("You attempt to toast the " + getItems().get(0).getName() + " and burn the house down! Game over :(");
			System.exit(0);
		}
	}
}
