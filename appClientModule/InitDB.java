import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.LicenseKeeperRemote;
import session.PersistException;
import persistance.Category;
import persistance.LicensedObject;
import persistance.LicensedObjectKey;
import persistance.LicensedObjectOperatingSystem;
import persistance.LicensedObjectSoftware;
import persistance.LicensedObjectVersion;
import persistance.User;
import persistance.UserGroup;

public class InitDB {

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			LicenseKeeperRemote remote = (LicenseKeeperRemote) context
					.lookup("LicenseKeeper/LicenseKeeperBean/remote");

			remote.saveCategory(new Category("OS - Windows - Home",
					"Home edition of Windows Operating System"));
			remote.saveCategory(new Category("OS - Windows - Professional",
					"Professional edition of Windows Operating System"));
			remote.saveCategory(new Category("OS - Windows - Server",
					"Server edition of Windows Operating System"));
			remote.saveCategory(new Category("OS - Linux",
					"Edition of Linux Operating System"));
			remote.saveCategory(new Category("APPS - Multimedia",
					"Multimedia softwares"));

			Collection<Category> userCat = new ArrayList<Category>();
			userCat.add(remote.getCategory(1));
			userCat.add(remote.getCategory(2));
			userCat.add(remote.getCategory(4));
			userCat.add(remote.getCategory(5));

			UserGroup adminGr = new UserGroup("Administrators");
			UserGroup userGr = new UserGroup("Users", userCat);

			remote.saveUserGroup(adminGr);
			remote.saveUserGroup(userGr);

			remote.saveUser(new User("admin", "toor", remote.getUserGroup(1)));
			remote.saveUser(new User("demo", "demo", remote.getUserGroup(2)));

			remote.saveLicensedObjectKey(new LicensedObjectKey(
					"ASDF-SDFS-34TW-24F6-AS87"));
			remote.saveLicensedObjectKey(new LicensedObjectKey(
					"LKJJ-SDFS-34TW-24F6-AS87"));
			remote.saveLicensedObjectKey(new LicensedObjectKey(
					"AERT-SDFS-34TW-24F6-AS87"));
			remote.saveLicensedObjectKey(new LicensedObjectKey(
					"ADOB-PKHT-SDFS-34TW-24F6-AS87"));

			Collection<LicensedObjectKey> licensedOK1 = new ArrayList<LicensedObjectKey>();
			licensedOK1.add(remote.getLicensedObjectKey(1));
			Collection<LicensedObjectKey> licensedOK2 = new ArrayList<LicensedObjectKey>();
			licensedOK2.add(remote.getLicensedObjectKey(2));
			Collection<LicensedObjectKey> licensedOK3 = new ArrayList<LicensedObjectKey>();
			licensedOK3.add(remote.getLicensedObjectKey(3));
			Collection<LicensedObjectKey> licensedOK4 = new ArrayList<LicensedObjectKey>();
			licensedOK4.add(remote.getLicensedObjectKey(4));

			remote.saveLicensedObjectVersion(new LicensedObjectVersion(7, 2, 1,
					licensedOK1));
			remote.saveLicensedObjectVersion(new LicensedObjectVersion(7, 4, 1,
					licensedOK2));
			remote.saveLicensedObjectVersion(new LicensedObjectVersion(7, 3, 1,
					licensedOK3));
			remote.saveLicensedObjectVersion(new LicensedObjectVersion(7, 3, 6));
			remote.saveLicensedObjectVersion(new LicensedObjectVersion(10, 2,
					2, licensedOK4));

			Collection<LicensedObjectVersion> licensedOV1 = new ArrayList<LicensedObjectVersion>();
			licensedOV1.add(remote.getLicensedObjectVersion(1));
			Collection<LicensedObjectVersion> licensedOV2 = new ArrayList<LicensedObjectVersion>();
			licensedOV2.add(remote.getLicensedObjectVersion(2));
			Collection<LicensedObjectVersion> licensedOV3 = new ArrayList<LicensedObjectVersion>();
			licensedOV3.add(remote.getLicensedObjectVersion(3));
			Collection<LicensedObjectVersion> licensedOV4 = new ArrayList<LicensedObjectVersion>();
			licensedOV4.add(remote.getLicensedObjectVersion(4));
			Collection<LicensedObjectVersion> licensedOV5 = new ArrayList<LicensedObjectVersion>();
			licensedOV5.add(remote.getLicensedObjectVersion(5));

			remote.saveLicensedObject(new LicensedObjectOperatingSystem(
					"Microsoft", "Windows 7 Home Premium", "Home use OS",
					remote.getCategory(1), "SP1", licensedOV1));
			remote.saveLicensedObject(new LicensedObjectOperatingSystem(
					"Microsoft", "Windows 7 Professional",
					"Professional use OS", remote.getCategory(2), "-",
					licensedOV2));
			remote.saveLicensedObject(new LicensedObjectOperatingSystem(
					"Microsoft", "Windows 2008 Server", "Server use OS", remote
							.getCategory(3), "", licensedOV3));
			remote.saveLicensedObject(new LicensedObjectOperatingSystem(
					"Linux", "Ubuntu Server 10.x", "Server use OS", remote
							.getCategory(4), "", licensedOV4));
			remote.saveLicensedObject(new LicensedObjectSoftware("Adobe",
					"Photoshop CS5.5", "Bitmap images creation/edition", remote
							.getCategory(5), 2, licensedOV5));

			/*List<Category> categories = remote.getCategories();
			for (Category category : categories) {
				System.out.println(category);
			}

			List<User> users = remote.getUsers();
			for (User user : users) {
				System.out.println(user);
			}

			List<UserGroup> usergroups = remote.getUserGroups();
			for (UserGroup usergroup : usergroups) {
				System.out.println(usergroup);
			}

			List<LicensedObject> los = remote.getLicensedObjects();
			for (LicensedObject lo : los) {
				System.out.println(lo);
			}*/

			System.out.println("-------------------------------");
			System.out.println("- End of the insertion part ! -");
			System.out.println("-------------------------------");

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (PersistException e) {
			e.printStackTrace();
		}
	}

}