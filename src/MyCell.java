import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;
import java.awt.event.ActionListener;

public class MyCell extends JButton {
    static ImageIcon iconeLife;
    static ImageIcon iconeDed;
    int id;
    ImageIcon nextColor;
    int numColumns =PlayingWindow.width/10;
    int numRows=PlayingWindow.height/10;
    public MyCell(int id) {
this.id=id;

        try {
            iconeLife = new ImageIcon(ImageIO.read(getClass().getResource("assets/box.png")));
            iconeDed = new ImageIcon(ImageIO.read(getClass().getResource("assets/box2.png")));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        nextColor = iconeDed;
        setIcon(iconeDed);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (MainWindow.machineRules[9]) {
                    case 0:
                        setIcon(itPressed() ? iconeDed : iconeLife);
                        break;
                    case 1:
                       planer();
                        MainWindow.machineRules[9]=0;
                        break;
                    case 2:
                        gun();

                        MainWindow.machineRules[9]=0;
                        break;
                    case 3:
eight();
                        MainWindow.machineRules[9]=0;
                        break;
                    case 4:

                        for (int i = 1; i < numRows-1; i+=3) {
                            for (int j = 1; j <numColumns-1; j+=3) {
                                ((MyCell)PlayingWindow.gameField.getComponent(i*numColumns+j)).setIcon(iconeLife);
                                ((MyCell)PlayingWindow.gameField.getComponent(i*numColumns+j+1)).setIcon(iconeLife);
                                ((MyCell)PlayingWindow.gameField.getComponent((i+1)*numColumns+j)).setIcon(iconeLife);
                                ((MyCell)PlayingWindow.gameField.getComponent((i+1)*numColumns+j+1)).setIcon(iconeLife);
                            }
                        }
                        MainWindow.machineRules[9]=0;
                        break;
                }
            }
        });

    }

     boolean itPressed() {
        return (Objects.equals(getIcon(), iconeLife));
    }

     void go() {
        if (!Objects.equals(getIcon(), nextColor)) {
            setIcon(nextColor);
        }

    }
    void setIconeLife(){
        nextColor = iconeLife;
    }
    void setIconeDed(){
        nextColor = iconeDed;
    }

    int checkDiagonali(int cord, int numColumns, GameField field, int size, int lastCord) {
        cord += cord < 0 ? numColumns : 0;
        cord += cord / numColumns < lastCord / numColumns ? numColumns : 0;
        cord -= cord / numColumns > lastCord / numColumns ? numColumns : 0;
        cord -= cord >= size ? numColumns : 0;

        Component component = field.getComponent(cord);
        if (component instanceof MyCell) {
            return (((MyCell) component).itPressed()) ? 1 : 0;
        } else {
            return 0;//что то пошло не такж
        }


    }

    int check(int cord, int size, GameField field, boolean flag, int numColumns, int lastCord) {
        cord += cord < 0 ? size : 0;
        cord -= cord >= size ? size : 0;

        int diagonali = 0;
        if (flag) {
            diagonali += checkDiagonali(cord + 1, numColumns, field, size, cord);
            diagonali += checkDiagonali(cord - 1, numColumns, field, size, cord);
        } else {
            cord += cord / numColumns < lastCord / numColumns ? numColumns : 0;
            cord -= cord / numColumns > lastCord / numColumns ? numColumns : 0;
        }

        Component component = field.getComponent(cord);
        if (component instanceof MyCell) {
            return (((MyCell) field.getComponent(cord)).itPressed()) ? 1 + diagonali : diagonali;
        } else {
            return 0;//что то пошло не такж
        }


    }

    public void getNextStep(int num, int numRows, int numColumns, GameField field, int[] machineRules) {
        int lifeCell = 0;
        int size = numRows * numColumns;

        lifeCell += check(num - 1, size, field, false, numColumns, num);
        lifeCell += check(num + 1, size, field, false, numColumns, num);

        lifeCell += check(num - numColumns, size, field, true, numColumns, num);
        lifeCell += check(num + numColumns, size, field, true, numColumns, num);


        if (machineRules[lifeCell] == 0) {//0-смерть,1-нет изменений,2-жизнь
            nextColor = iconeDed;
        } else if (machineRules[lifeCell] == 2) {
            nextColor = iconeLife;
        } else {
            if (itPressed()) {
                nextColor = iconeLife;
            } else {
                nextColor = iconeDed;
            }
        }
    }
    void planer(){
        ((MyCell)PlayingWindow.gameField.getComponent(id+numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+numColumns-1)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+numColumns+1)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+1)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-numColumns)).setIcon(iconeLife);
    }
    void gun(){

        ((MyCell)PlayingWindow.gameField.getComponent(id-2)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id-3+numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-3+2*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-3-numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-3-2*numColumns)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id-5+3*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-5-3*numColumns)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id-7)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-7+2*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-7-2*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-7+3*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-7-3*numColumns)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id-16)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-17)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id-16-numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id-17-numColumns)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id+5-3*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+5-2*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+5-numColumns)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id+6)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+6-4*numColumns)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id+8+numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+8-5*numColumns)).setIcon(iconeLife);

        ((MyCell)PlayingWindow.gameField.getComponent(id+9+numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+9)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+9-5*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+9-4*numColumns)).setIcon(iconeLife);


        ((MyCell)PlayingWindow.gameField.getComponent(id+18-2*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+18-numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+17-2*numColumns)).setIcon(iconeLife);
        ((MyCell)PlayingWindow.gameField.getComponent(id+17-numColumns)).setIcon(iconeLife);

    }
    public void eight(){
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                ((MyCell)PlayingWindow.gameField.getComponent(id-i-1-j*numColumns)).setIcon(iconeLife);
                ((MyCell)PlayingWindow.gameField.getComponent(id+i+(j+1)*numColumns)).setIcon(iconeLife);
            }
        }

    }
}
