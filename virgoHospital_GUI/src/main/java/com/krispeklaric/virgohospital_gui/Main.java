package com.krispeklaric.virgohospital_gui;

import javax.swing.SwingUtilities;

/**
 *
 * @author Kris
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final OutpatientModule outModule = new OutpatientModule();
                outModule.setVisible(true);
            }
        });

    }

}
