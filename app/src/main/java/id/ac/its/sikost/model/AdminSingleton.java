package id.ac.its.sikost.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class AdminSingleton {


    private static final AdminSingleton ourInstance = new AdminSingleton();

    public static AdminSingleton getInstance() {
        return ourInstance;
    }

    List<Admin> admins;

    private AdminSingleton() {
        admins = new ArrayList<>();
        admins.add(new Admin("Admin", "admin", "admin"));
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }
}
