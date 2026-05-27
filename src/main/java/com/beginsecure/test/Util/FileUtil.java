package com.beginsecure.test.Util;

import com.beginsecure.test.model.Nalog;
import com.beginsecure.test.model.Database;

import java.io.*;
import java.util.Scanner;

public class FileUtil {

    public static void loadAccounts(String filename){
        try{
            Scanner scanner = new Scanner(new FileInputStream(filename),"UTF8");
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens= line.split(" ");
                Nalog account = new Nalog(tokens[0].trim(), tokens[1].trim());
                Database.getInstance().getRegistrovaniKorisnici().add(account);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addAccount(Nalog account, String fileName){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter((fileName),true));
            bw.write(account.toString());
            bw.newLine();

            bw.close();
            Database.getInstance().getRegistrovaniKorisnici().add(account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
