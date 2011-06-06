import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.CategoryRemote;
import session.PersistException;
import persistance.Category;


public class InitDB {
	public static void main(String[] args) {
		try {
		      Context context = new InitialContext();
		      CategoryRemote categoryRemote = (CategoryRemote) context.lookup("LicenseKeeper/CategoryBean/remote");
		      
		      categoryRemote.saveCategory(new Category("OS - Windows - Home", "Home edition of Windows Operating System"));
		      categoryRemote.saveCategory(new Category("OS - Windows - Professional", "Professional edition of Windows Operating System"));
		      categoryRemote.saveCategory(new Category("OS - Windows - Server", "Server edition of Windows Operating System"));
		      categoryRemote.saveCategory(new Category("OS - Linux", "Edition of Linux Operating System"));

		      List<Category> categories = categoryRemote.getCategories();
		      for (Category category: categories) {
		         System.out.println(category);
		      }

		   } catch (NamingException e) {
		      e.printStackTrace();
		   } catch (PersistException e) {
		    e.printStackTrace();
		  }
	}

}