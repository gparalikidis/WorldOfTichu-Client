package commands.contacts;

import gui.LobbyWindow;
import commands.Command;

public class ContactsReport extends Command{

	@Override
	public void execute(String... params) {
		LobbyWindow.callMethod("contactsReport", params);
		
	}

}
