import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JMyMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    GameField gameField;
    int numRows;
    int numColumns;
    int[] machineRules;
    //--------------------------------------------------------
    int delay;
    Long maxMem = 0L;

    public JMyMenu(PlayingWindow frame, GameField gameField, int numRows, int numColumns, int[] machineRules, int delay) {
        this.gameField = gameField;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.machineRules = machineRules;
        this.delay = delay;
        JMenuBar menuBar = new JMenuBar();
        // Добавление в главное меню выпадающих пунктов меню

        menuBar.add(createFileMenu(frame));
        menuBar.add(new Figures(machineRules));
        // Подключаем меню к интерфейсу приложения
        frame.setJMenuBar(menuBar);
        // Открытие окна

    }
    //--------------------------------------------------------


    private JMenu createFileMenu(PlayingWindow frame) {
        // Создание выпадающего меню
        JMenu file = new JMenu("Игра");
        // Пункт меню "Открыть" с изображением
        JMenuItem start = new JMenuItem("Старт");
        JMenuItem stop = new JMenuItem("Стоп");
        JMenuItem step = new JMenuItem("Один шаг");
        JMenuItem clear = new JMenuItem("Очистить поле");
        // Добавление к пункту меню изображения

        file.add(start);
        file.add(stop);
        file.add(step);
        file.add(clear);
        // Добавление разделителя

        step();
        MyRunnable myRunnable = new MyRunnable();
        Thread[] thread = new Thread[1];
        boolean[] flag = {true};

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (flag[0]) {
                    flag[0] = false;

                    thread[0] = new Thread(myRunnable);
                    thread[0].start();
                }
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!flag[0]) {
                    thread[0].interrupt();
                    flag[0] = true;
                }
            }
        });
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                step();
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!flag[0]) {
                    thread[0].interrupt();
                    flag[0] = true;
                }

                int size = numRows * numColumns;
                for (int i = 0; i < size; i++) {
                    ((MyCell) gameField.getComponent(i)).setIconeDed();
                }
                for (int i = 0; i < size; i++) {
                    ((MyCell) gameField.getComponent(i)).go();
                }

            }
        });
        return file;
    }

    public class MyRunnable implements Runnable {

        public void run() {
            boolean stop = false;
            while (!Thread.currentThread().isInterrupted() && !stop) {
                step();

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    stop = true;
                }
            }
        }
    }

    void step() {

        int size = numRows * numColumns;

        for (int i = 0; i < size; i++) {
            ((MyCell) gameField.getComponent(i)).getNextStep(i, numRows, numColumns, gameField, machineRules);
        }

        for (int i = 0; i < size; i++) {
            ((MyCell) gameField.getComponent(i)).go();
        }

    }
}