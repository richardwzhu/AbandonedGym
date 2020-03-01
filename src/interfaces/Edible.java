package interfaces;

public interface Edible {
	public static final boolean POISON = true;;
	public static final boolean NOT_POISON = false;
	public static final boolean RIPE = true;
	public static final boolean NOT_RIPE = false;

	/** Eats this object */
	public void doEat();
}
