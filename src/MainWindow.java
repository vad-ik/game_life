import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JPanel {
   static int[] machineRules=new int[10];
    int delay=100;
    public MainWindow(JFrame frame) {
       MainWindow thisFrame=this;
        startSettings ();
        JButton buttonStart = new JButton("Начать");
        PlayingWindow playingWindow = new PlayingWindow(frame,machineRules,delay);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playingWindow.setVisible(true);
            }
        });
        add(buttonStart);
        JButton buttonSeting = new JButton("Настройки");
        add(buttonSeting);
        buttonSeting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingWindow settingWindow = new SettingWindow(thisFrame);
            }
        });


    }

    void startSettings (){
        machineRules[0]=0;
        machineRules[1]=0;
        machineRules[2]=1;
        machineRules[3]=2;
        machineRules[4]=0;
        machineRules[5]=0;
        machineRules[6]=0;
        machineRules[7]=0;
        machineRules[8]=0;
        machineRules[9]=0;//9 правило отвечает за постановку заготовленных фигур. 0 - обычная точка
    }
}
