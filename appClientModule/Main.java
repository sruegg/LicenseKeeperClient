import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.LicenseKeeperRemote;
import session.PersistException;
import persistance.Category;


public class Main {
	public static void main(String[] args) {
		try {
		      Context context = new InitialContext();
		      LicenseKeeperRemote bottin = (LicenseKeeperRemote) context.lookup("LicenseKeeper/LicenseKeeperBean/remote");
		      bottin.saveContact(new Category("OS - Home", "Home edition of Operating System"));

		      List<Category> contacts = bottin.getContacts();
		      for (Category contact: contacts) {
		         System.out.println(contact);
		      }

		   } catch (NamingException e) {
		      e.printStackTrace();
		   } catch (PersistException e) {
		    e.printStackTrace();
		  }
	}

}