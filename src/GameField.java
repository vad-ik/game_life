import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class GameField extends JPanel {

    public GameField(int numRows, int numColumns)  {
        GridLayout layout = new GridLayout(numRows, numColumns,0,0);
        setLayout(layout);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
               MyCell jButton=new MyCell(i*numColumns+j);
               add(jButton);
            }
        }


    }



}
