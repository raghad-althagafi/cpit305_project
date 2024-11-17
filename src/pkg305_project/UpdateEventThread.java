/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author shaha
 */
public class UpdateEventThread extends Thread {

    private int rows;
    private Database db;
    private userInterface ui;

    public UpdateEventThread(Database db, userInterface ui) {
        this.db = db;
        this.ui = ui;
        this.rows = db.checkRows();
    }

    public void run() {
        while (true) {
            try {
                int newRows = db.checkRows();
                if (newRows != rows) {
                    ui.readFromDatabase(db);
                    rows = newRows;
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                continue;
            }
        }

    }
}
