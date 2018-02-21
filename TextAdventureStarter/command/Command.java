package command;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import textadventure.World;

public abstract class Command {
	private static HashMap<String, Command> commandMap = initCommandMap();
	
	private static HashMap<String, Command> initCommandMap() {
		HashMap<String, Command> map = new HashMap<>();
		File dir = new File(".");
		List<Class<?>> classes = null;
		try {
			classes = findClasses(dir, Arrays.asList("Command.class"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for (Class<?> cls : classes) {
			if (Command.class.isAssignableFrom(cls)) {
				try {
					Command cmd = (Command) cls.newInstance();
					for (String word : cmd.getCommandWords()) {
						map.put(word, cmd);
					}
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	public static List<Class<?>> findClasses(File directory, List<String> ignoredFiles) throws ClassNotFoundException {
	    List<Class<?>> classes = new ArrayList<Class<?>>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	    	if (file.isDirectory()) {
	    		classes.addAll(findClasses(file, ignoredFiles));
	    	} else if (file.getName().endsWith(".class")) {
				if (ignoredFiles != null && !ignoredFiles.contains(file.getName())) {
					String sep = File.separator;
					if (sep.equals("\\")) sep += "\\";
					String path = file.getPath();
					String fullClassName = path.substring(2, path.length() - 6).replaceAll(sep, ".").replaceAll("/", ".");
					if (fullClassName.startsWith("bin")) fullClassName = fullClassName.substring(4);
					classes.add(Class.forName(fullClassName));
				} 
	    	}
	    }
	    return classes;
	}
	
	public static boolean hasValidCommandWord(String cmdStr) {
		return commandMap.containsKey(getCommandWord(cmdStr));
	}
	
	public static void doCommand(String cmdStr, World world) {
		if (cmdStr == null || cmdStr.length() == 0) throw new IllegalArgumentException("cmdStr cannot be null or an empty string.");
		String cmd = getCommandWord(cmdStr);
		if (!hasValidCommandWord(cmdStr)) throw new IllegalArgumentException(cmd + " is not a valid command. You should check if the command word is valid before trying to do it.");
		String[] params = getCommandParameters(cmdStr);
		Command commandHandler = getCommandHandlerForWord(cmd);
		if (commandHandler == null) throw new IllegalArgumentException(cmd + " is not a valid command. You should check if the command word is valid before trying to do it.");
		commandHandler.doCommand(cmd, params, world);
	}
	
	public static Command getCommandHandlerForWord(String cmdWord) {
		return commandMap.get(cmdWord);
	}
	
	public static Set<String> getAllCommandWords() {
		return commandMap.keySet();
	}
	
	public static Set<Command> getAllCommandHandlers() {
		Set<String> commandWords = getAllCommandWords();
		Set<Command> commands = new HashSet<Command>();
		for (String word : commandWords) {
			commands.add(commandMap.get(word));
		}
		return commands;
	}
	
	public static String[] getCommandParameters(String cmdStr) {
		String[] words = cmdStr.split("\\s+");
		if (words.length > 1) return Arrays.copyOfRange(cmdStr.split("\\s+"), 1, words.length);
		return new String[0];
	}
	
	public static String getCommandWord(String cmdStr) {
		if (cmdStr == null || cmdStr.length() == 0) return null;
		return cmdStr.split("\\s+")[0];
	}
	
	public static String getHelpDescription(Command cmd) {
		String[] words = cmd.getCommandWords();
		if (words == null || words.length == 0) return "";
		String descrip = words[0] + " " + cmd.getHelpDescription();
		if (words.length > 1) descrip += " (also: ";
		for (int i = 1; i < words.length; i++) {
			descrip += words[i];
			if (i < words.length - 1) descrip += ", ";
		}
		if (words.length > 1) descrip += ")";
		return descrip;
	}
	
	/**
	 * Returns an array of command word strings that are associated with this command.
	 * @return an array of command word strings that are associated with this command.
	 */
	public abstract String[] getCommandWords();
	
	/**
	 * Perform the command with the given parameters in the given world
	 * @param cmd The command
	 * @param params An array of parameters
	 * @param world The world in which to perform the command
	 * @return true if the command was successful or false otherwise.
	 */
	public abstract void doCommand(String cmd, String[] params, World world);
	
	/**
	 * A description for the help menu. It should include a list of the expected parameters.
	 * In the menu, this command will be printed in the form:
	 * commandWord [this description] ([list of aliases])
	 * Example: go [north, south, east, west] (move, travel, walk)
	 * @return a description for this command to be used in the help menu.
	 */
	public abstract String getHelpDescription();
}
