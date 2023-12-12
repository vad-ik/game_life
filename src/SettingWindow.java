import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    int width = 800;
    int height = 600;

    public SettingWindow(MainWindow frame) {
        JPanel panel=new JPanel();
        new JFrame("игра Жизнь");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        GridLayout layout = new GridLayout(12, 2, 0, 0);
        panel.setLayout(layout);
        JLabel label = new JLabel("Количество соседних живых клеток");
        panel.add(label);
        label = new JLabel("Действие");
        panel.add(label);
        JSpinner spinner;
        String[] variantString = {"Клетка умирает", "Клетка не меняет своего состояния", "Клетка возрождается"};
        String[] variantDelay = {"100","500", "1000", "2000"};
        for (int i = 0; i < 9; i++) {
            label = new JLabel("  " + i);
            panel.add(label);
            spinner = new JSpinner(new SpinnerListModel(variantString));
            panel.add(spinner);
        }
        label = new JLabel(" Установить задержку");
        panel.add(label);
        spinner = new JSpinner(new SpinnerListModel(variantDelay));
        panel.add(spinner);
        JButton save = new JButton(" Сохранить изменения");
        panel.add(save);
        add(panel);

        save.addActionListener(new ActionListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i <9 ; i++) {
                    JSpinner spinner= (JSpinner)(panel.getComponent(3+2*i));
                    int result=0;
                    switch((String)spinner.getValue()){
                        case "Клетка умирает":
                            result=0;
                            break;
                        case "Клетка не меняет своего состояния":
                            result=1;
                            break;
                        case "Клетка возрождается":
                            result=2;
                            break;
                    }
                    frame.machineRules[i]=result;

                }
                JSpinner spinner= (JSpinner)(panel.getComponent(21));

                frame.delay=Integer.parseInt((String)spinner.getValue());

            }
        });


        setPreferredSize(new Dimension(width, height));
        pack();
        setVisible(true);
    }
}
