package data.input_output;

import classes.User;

import java.io.*;
import java.util.ArrayList;

public class Output {
    public static void reWriteUsersFile(ArrayList<User> users) throws FileNotFoundException {
        File file = new File("database/UsersData.csv");
        PrintWriter pw = new PrintWriter(file);
        pw.println("ID,Nom,Cognom");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            pw.print(u.getIdentifier() + "," + u.getUsername() + "," + u.getPassword());
            if (i < users.size() - 1) {
                pw.println();
            }
        }
        pw.close();
    }
    public static void writeUsersFile(User user) throws IOException {
        File file = new File("database/UsersData.csv");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println();
        pw.print(user.getIdentifier() + "," + user.getUsername() + "," + user.getPassword());
        pw.close();
        fw.close();
    }
}
