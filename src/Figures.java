import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Figures extends JMenu {

    // Пункт меню "Открыть" с изображением
    JMenuItem glider = new JMenuItem("Глайдер ");
    JMenuItem gun1 = new JMenuItem("Ружьё ");
    JMenuItem eight = new JMenuItem("Восьмёрка ");
    JMenuItem agar = new JMenuItem("Агар ");


    // Пункт меню из команды с выходом из программы
    Figures(int[] machineRules) {
        // Добавим в меню пункта open
        super("Фигуры");
        add(glider);
        add(gun1);
        add(eight);
        add(agar);
        // Добавление разделителя
        addSeparator();


        glider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                machineRules[9] = 1;
            }
        });
        gun1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                machineRules[9] = 2;
            }
        });
        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                machineRules[9] = 3;
            }
        });
        agar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                machineRules[9] = 4;
            }
        });
    }
}
