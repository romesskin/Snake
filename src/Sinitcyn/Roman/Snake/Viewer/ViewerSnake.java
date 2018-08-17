package Sinitcyn.Roman.Snake.Viewer;

import Sinitcyn.Roman.Snake.Controller.ControllerSnake;
import Sinitcyn.Roman.Snake.Model.FieldAnimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/*
    Класс отвечает за отображение графики и создание интерфейса игры
 */

public class ViewerSnake extends JFrame implements Observer {

    private static final int SIZE=60;                         //размер клетки поля 60х60 кратность 4 и 3
    private static final String FORMAT_SCORE = "Score: %-5d";
    private static JLabel score = new JLabel();
    private static JLabel status = new JLabel();
    private static JButton play, pause, stop;
    private int cols;
    private int rows;
    private static int k[]={SIZE/6,SIZE/4};         //коэффициенты смещения координат к[0] - для тела и лягушек, к[1] - для хвоста
    private FieldAnimal[][] field;

    public ViewerSnake(int cols, int rows)    {
        this.cols=cols;
        this.rows=rows;

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
                paintField(g);
            }
        };
        canvas.setPreferredSize(new Dimension(cols* SIZE, rows* SIZE));
        canvas.setBackground(Color.DARK_GRAY);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                ControllerSnake.mousePressButton(e);
            }
        });

        status.setText("WELCOME");
        status.setOpaque(true);
        status.setFont(new Font("", Font.BOLD, 21));

        add(bd,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);
        add(status,BorderLayout.SOUTH);

        setTitle("Roman's SNAKE");
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

        field=new FieldAnimal[cols][rows];

        for(int i=0;i<cols;i++)
            for(int j=0;j<rows;j++)
                field[i][j]=FieldAnimal.NULL;
    }

    private void setScore(int sc) {
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

    //отрисовка карты
    private void paintField(Graphics g){
        for(int j=0;j<rows;j++)
            for(int i=0;i<cols;i++){
                g.setColor(Color.WHITE);
                g.drawRect(i*SIZE, j*SIZE,SIZE,SIZE);
                switch (field[i][j]){
                    case SNAKEHEAD:
                        g.setColor(Color.YELLOW);
                        g.fillArc(i*SIZE, j*SIZE,SIZE,SIZE,0,360);
                        break;
                    case SNAKEBODY:
                        g.setColor(Color.YELLOW);
                        g.fillArc(i*SIZE+k[0], j*SIZE+k[0],SIZE*2/3,SIZE*2/3,0,360);
                        break;
                    case SNAKETAIL:
                        g.setColor(Color.YELLOW);
                        g.fillArc(i*SIZE+k[1], j*SIZE+k[1],SIZE/2,SIZE/2,0,360);
                        break;
                    case FROGGREEN:
                        g.setColor(Color.GREEN);
                        g.fillArc(i*SIZE+k[0], j*SIZE+k[0],SIZE*2/3,SIZE*2/3,0,360);
                        break;
                    case FROGRED:
                        g.setColor(Color.RED);
                        g.fillArc(i*SIZE+k[0], j*SIZE+k[0],SIZE*2/3,SIZE*2/3,0,360);
                        break;
                    case FROGBLUE:
                        g.setColor(Color.BLUE);
                        g.fillArc(i*SIZE+k[0], j*SIZE+k[0],SIZE*2/3,SIZE*2/3,0,360);
                    default:break;
                }
            }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof FieldAnimal[][])
            field= (FieldAnimal[][]) arg;
        else
            setScore((int)arg);
        repaint();
    }
}



