package Sinitcyn.Roman.Snake.Viewer;

import Sinitcyn.Roman.Snake.Controller.ControllerSnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
    Класс отвечает за отображение графики и создание интерфейса игры
 */

public class ViewerSnake extends JFrame {

    private static final String FORMAT_SCORE = "Score: %-5d";
    private static JLabel score = new JLabel();
    private static JLabel status = new JLabel();
    private static JButton play, pause, stop;

    public ViewerSnake(int cols, int rows)    {
        play = new JButton("Play");
        pause = new JButton("Pause");
        stop = new JButton("Stop");
        score.setFont(new Font("", Font.BOLD, 21));

        JPanel bd = new JPanel();
        bd.setLayout(new BoxLayout(bd, BoxLayout.X_AXIS));
        bd.add(play);
        bd.add(pause);
        bd.add(stop);
        bd.add(Box.createHorizontalGlue());
        bd.add(score);

        JPanel canvas = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                ControllerSnake.paintControl(g);
            }
        };
        canvas.setPreferredSize(new Dimension(cols* ControllerSnake.getSIZE(), rows* ControllerSnake.getSIZE()));
        canvas.setBackground(Color.DARK_GRAY);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                switch (e.getButton()){
                    case MouseEvent.BUTTON1:
                        ControllerSnake.pressLeftButton();break;
                    case MouseEvent.BUTTON3:
                        ControllerSnake.pressRigthButton();break;
                    default:break;
                }
            }
        });

        status.setText("WELCOME");
        status.setOpaque(true);
        status.setFont(new Font("", Font.BOLD, 21));

        add(bd,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);
        add(status,BorderLayout.SOUTH);

        setTitle("Roman's SNAKE");
        setMinimumSize(new Dimension(ControllerSnake.getSIZE()*5, ControllerSnake.getSIZE()*5));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(200,200);
        setVisible(true);
        pack();

        play.addActionListener( e->
                ControllerSnake.Play());

        pause.addActionListener(e->
                ControllerSnake.Pause());

        stop.addActionListener(e->
                ControllerSnake.Stop());
    }

    public void setScore(int sc) {
        score.setText(String.format(FORMAT_SCORE, sc));
    }

    public void setStatus(String text) {
        ViewerSnake.status.setText(text);
    }

    public void setEnabledButton(boolean _play,boolean _pause,boolean _stop){
       play.setEnabled(_play);
       pause.setEnabled(_pause);
       stop.setEnabled(_stop);
    }

}


