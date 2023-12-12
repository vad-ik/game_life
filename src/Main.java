import java.awt.Dimension;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void createGUI() {
        JFrame frame = new JFrame("Игра Жизнь");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainWindow mainWindow = new MainWindow(frame);
        frame.getContentPane().add(mainWindow);

        frame.setPreferredSize(new Dimension(200, 100));

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            //public void
            public void run() {
                createGUI();
            }
        });
    }
}