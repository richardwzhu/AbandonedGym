package textadventure;

public class OutsideRoom extends Room {

	public OutsideRoom(String name, String description, World world) {
		super(name, description, world);
	}
	
	public void doGo() {
		World.print("Your carpool is arriving!\n\n");
		if(!getWorld().getPlayer().getBrush()) {
			World.print("Your carpool mates notice that you haven't brushed your teeth. How embarrassing! Game over!\n\n");
			System.exit(0);
		}else if(getWorld().getPlayer().getHealth() != 2) {
			World.print("Your stomach churns as you realize you forgot to toast the bread.  You get sick in on the way to school. How embarrassing! Game over!\n\n");
			System.exit(0);
		}else if(!getWorld().getPlayer().isClothed()) {
			World.print("You aren't wearing clothes. The neighbors call the police on you for public indecency! Game over!\n\n");
			System.exit(0);
		}else {
			World.print("Have a great day at school! You Win!\n\n");
			System.exit(0);
		}
	}
}
