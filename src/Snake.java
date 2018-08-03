import Code.Game;
import Code.StatusGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Snake extends JFrame
{
    private static int cols;                //ширина игрового поля
    private static int rows;                //высота игрового поля
    private static int snakeLength;         //начальна длина змеи
    private static int frogs;               //количество лягушек
    private static int time;                //время засыпания змеи в мс
    private static final String FORMAT_SCORE="Score: %-5d";
    private static int sc;
    private static JLabel score=new JLabel();
    private static JLabel label=new JLabel();

    private Snake(){

     //*****************************************************************************************************************
     //ОФРМЛЕНИЕ

        JButton play=new JButton("Play");
        JButton pause=new JButton("Pause");
        pause.setEnabled(false);
        JButton stop=new JButton("Stop");
        stop.setEnabled(false);
        score.setFont(new Font("", Font.BOLD, 21));
        setTextScore();

        JPanel bd=new JPanel();
        bd.setLayout(new BoxLayout(bd,BoxLayout.X_AXIS));
        bd.add(play);
        bd.add(pause);
        bd.add(stop);
        bd.add(Box.createHorizontalGlue());
        bd.add(score);

        Game game=new Game(cols,rows);

        JPanel canvas =new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.WHITE);
                for(int x=0;x<cols;x++)
                    for (int y=0; y<rows;y++)
                        g.drawRect(x*game.getSquareSize(),y*game.getSquareSize(),game.getSquareSize(),game.getSquareSize());
                game.paint(g);
            }
        };

        canvas.setDoubleBuffered(true);
        canvas.setPreferredSize(new Dimension(cols*game.getSquareSize(), rows*game.getSquareSize()));
        canvas.setBackground(Color.DARK_GRAY);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                switch (e.getButton()){
                    case MouseEvent.BUTTON1:game.pressLeftButton();break;
                    case MouseEvent.BUTTON3:game.pressRigthButton();break;
                    default:break;
                }
            }
        });

        label.setText("WELCOME");
        label.setOpaque(true);
        label.setFont(new Font("", Font.BOLD, 21));

        add(bd,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);
        add(label,BorderLayout.SOUTH);

        setTitle("Roman's Snake");
        setMinimumSize(new Dimension(game.getSquareSize()*5,game.getSquareSize()*5));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(180,180);
        pack();
        setVisible(true);
     //*****************************************************************************************************************

        play.addActionListener( e-> {
            stop.setEnabled(true);
            pause.setEnabled(true);
            play.setEnabled(false);
            game.start();
            game.Play();
            repaint();
            label.setText(game.getState().name());
        });

        pause.addActionListener(e -> {
            stop.setEnabled(true);
            pause.setEnabled(false);
            play.setEnabled(true);

            game.Pause();
            label.setText(game.getState().name());
        });

        stop.addActionListener(e -> {
            stop.setEnabled(false);
            pause.setEnabled(false);
            play.setEnabled(true);
            game.Stop();
            repaint();
            label.setText(game.getState().name());
        });
    }

    private static void setTextScore(){
        score.setText(String.format(FORMAT_SCORE,sc));
    }

    //******************************************************************************************************************
        public static void main(String[] args) {
        
        //ввод данных
        cols=8;
        rows=8;

        new Snake();
    }
}

