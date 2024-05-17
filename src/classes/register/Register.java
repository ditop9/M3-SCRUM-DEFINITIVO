package classes.register;

import app.Main;
import classes.admin.Admin;
import data.DataInput;

public class Register {
    private final Admin admin;
    private final String date;
    private final String description;

    public Admin getAdmin() {
        return admin;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Register(Admin admin, String date, String description) {
        this.admin = admin;
        this.date = date;
        this.description = description;
    }

    public static void createNewRegister(String description) {
        new RegisterDAO().create(new Register(Main.admin,
                DataInput.getActualDate(), description));
    }

    @Override
    public String toString() {
        return "Register{" +
                "admin=" + admin +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
