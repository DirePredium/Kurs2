package com.my.web.comand;

import java.util.*;

public class CommandContainer {
	
	private static Map<String, ICommand> commands; 
	
	static {
		commands = new HashMap<>();
		
		commands.put("login", new LoginCommand());
		commands.put("logout", new CommandLogout());
		commands.put("messageHelp", new CommandOpenMessageHelp());
		commands.put("updateMessageHelp", new CommandUpdateMessageHelp());
		commands.put("deleteMessageHelp", new CommandDeleteMessageHelp());
		commands.put("openHelp", new CommandOpenHelp());
		commands.put("sendMessageHelp", new CommandSendMessageHelp());
		commands.put("updateUser", new CommandUpdateUser());
		commands.put("insertUser", new CommandInsertUser());
		commands.put("deleteUser", new CommandDeleteUser());
		commands.put("openUsers", new CommandOpenUserPage());
		commands.put("openTariff", new CommandOpenTariff());
		commands.put("updateUserTariff", new CommandUpdateUserTariff());
		commands.put("openEquipment", new CommandOpenEquipment());
		commands.put("updateUserEquipment", new CommandUpdateUserEquipment());
		commands.put("deleteUserEquipment", new CommandDeleteUserEquipment());
		commands.put("insertUserEquipment", new CommandInsertUserEquipment());
	}

	public static ICommand getCommand(String commandName) {
		return commands.get(commandName);
	}

}
