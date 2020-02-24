package textadventure;
import java.util.Calendar;
import java.util.GregorianCalendar;

import items.Clothes;
import items.Container;
import items.Item;
import items.Scenery;
import items.UselessItem;

public class DemoWorld extends World {
	/* Room String constants
	 * 
	 * Instead of hard-coding everywhere, room names are defined here.
	 * They're also public and static so they can be accessed
	 * anywhere using DemoWorld.stringName
	 */
	public static final String BEDROOM = "YOUR BEDROOM";
	public static final String HALLWAY = "HALLWAY";
	public static final String BATHROOM = "BATHROOM";
	public static final String KITCHEN = "KITCHEN";
	public static final String OUTSIDE = "OUTSIDE";

	/** Keeps track of time using built-in Java object */
	private Calendar localTime;
	long startMilliseconds;
	
	public void createPlayer() {
		setPlayer(new Player(getRoom(BEDROOM), this, "Player", "You are looking good today!"));
		getPlayer().addItem(new Clothes(this, "clothes", 15, Item.TAKEABLE, "Your school clothes for the day."));
	}

	public void createRooms() {
		// Create rooms and add them to our HashMap
		addRoom(new Room(BEDROOM, "Your bedroom is simple yet functional.\n\n", this));
		addRoom(new Room(HALLWAY, "A short hallway leading to the kitched is covered in family photos.  There is a bathroom to the west.\n\n", this));
		addRoom(new Room(BATHROOM, "You are inside a simple bathroom with light blue walls.\n\n", Room.LOCKED, this));
		addRoom(new Room(KITCHEN, "The kitchen is void of people.  You'd better eat and run.  There is an exit leading outside to the east.\n\n", this));
		addRoom(new Room(OUTSIDE, "You step outside into the brisk morning air.\n\n", Room.LOCKED, this));

		// Define multiple room exits at once.  Order is north, east, south, west
		getRoom(HALLWAY).setExits(getRoom(KITCHEN), null, getRoom(BEDROOM), getRoom(BATHROOM));
		getRoom(KITCHEN).setExits(null, getRoom(OUTSIDE), getRoom(HALLWAY), null);
		getRoom(BATHROOM).setExits(null, getRoom(HALLWAY), null, null);
		
		// Define exits individually. This method could also be used to set exits
		// other than north, south, east, west
		getRoom(BEDROOM).setExit("north", getRoom(HALLWAY));
		getRoom(OUTSIDE).setExit("west", getRoom(KITCHEN));
		getRoom(BATHROOM).setExit("east", getRoom(HALLWAY));

		// Create game items and add them to rooms
		Container cubby = new Container(this, "cubby_hole", 15, Item.NOT_TAKEABLE, "This is where you store important small items.");
		UselessItem rock = new UselessItem(this, "rock", 10, Item.TAKEABLE, "It's just your pet rock with googly eyes and a silly smile.");
		UselessItem keys = new UselessItem(this, "keys", 2, Item.TAKEABLE, "It's a set of keys");
		getRoom(BEDROOM).addItem(cubby);
		cubby.addItem(rock);
		cubby.addItem(keys);

		getRoom(HALLWAY).addItem(new UselessItem(this, "picture", 15, Item.TAKEABLE, "The picture features you, age five, crying in front of an animatronic Santa Clause."));
		getRoom(KITCHEN).addItem(new UselessItem(this, "stove", 100, Item.NOT_TAKEABLE, "An unremarkable gas stove stares back at you."));

		Container fridge = new Container(this, "refrigerator", 100, Item.NOT_TAKEABLE, "A standard white refrigerator hums quietly.");
		getRoom(KITCHEN).addItem(fridge);
		getRoom(KITCHEN).addItem(new Scenery(this, "table", 150, "An empty kitchen table."));
		getRoom(KITCHEN).addItem(new Container(this, "toaster", 5, Item.TAKEABLE, "A black, smudgy toaster."));

		getRoom(BATHROOM).addItem(new UselessItem(this, "toothbrush", 5, Item.TAKEABLE, "Your trusty toothbrush."));
	}
	
	/**
	 * Updates and prints out the current game time. Every second spent between game
	 * commands becomes a second spent within the game.
	 */
	private boolean updateTime() {
		long endMilliseconds = System.currentTimeMillis();		
		localTime.add(Calendar.SECOND, (int)((endMilliseconds - startMilliseconds)/1000));
		int hour = localTime.get(Calendar.HOUR_OF_DAY);
		int minute = localTime.get(Calendar.MINUTE);
		int second = localTime.get(Calendar.SECOND);		
		startMilliseconds = endMilliseconds;
		System.out.printf("The time is %d:%02d:%02d A.M.\n\n", hour, minute, second);
		if (localTime.get(Calendar.MINUTE) >= 20) {
			print("Ack!  You missed your carpool!  You're so triggered!!\n\n");
			print("\nThank you for playing!  Good bye!\n\n");
			return true;
		}
		return false;
	}

	@Override
	public void initializeGame() {
		createRooms();
		createPlayer();
		
		// Start game timer 
		localTime = new GregorianCalendar(2018, Calendar.AUGUST, 14, 7, 15, 0); // August 14, 2018 7:15 A.M.
		startMilliseconds = System.currentTimeMillis();
	}
	
	@Override
	public void printWelcome() {
		print("You are lying in bed drifting in and out of consciousness as the early light of dawn "
				+ "creeps into your room from a nearby window.  It's 7:15 A.M. and your carpool leaves at 7:20 A.M. "
				+ "which means you should probably get up.\n\n");
		print("Welcome to Breakfast Run!\n");
		print("(c) 2018 By Mr. Ferrante\n");
		print("Type 'help' if you need help.\n\n");
		print(getPlayer().getCurrentRoom().getDescription());
		updateTime();
	}

	@Override
	public void onCommandFinished() {
		if (updateTime()) setGameOver(true);
	}

}
