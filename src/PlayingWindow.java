import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayingWindow extends JFrame {
    static int width = 1000;
    static int height = 1000;
    static GameField gameField;

    public PlayingWindow(JFrame frame, int[] machineRules, int delay) {
        // Далее идёт разметка формы
        new JFrame("Игра Жизнь");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        gameField = new GameField(height / 10, width / 10);

        JMyMenu myMenu = new JMyMenu(this, gameField, height / 10, width / 10, machineRules, delay);
        add(myMenu);

        gameField.setVisible(true);
        add(gameField);
        setPreferredSize(new Dimension(width, height));
        pack();
        setVisible(false);


    }

    public void setNewGameField(GameField newGameField) {
        gameField.setVisible(false);
        gameField = newGameField;
        add(gameField);
        gameField.setVisible(true);
    }
}

