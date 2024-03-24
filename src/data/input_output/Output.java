package data.input_output;

import classes.User;

import java.io.*;

public class Output {
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
