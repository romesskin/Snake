package Code;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    
    private static int cols;                     //ширина игрового поля
    private static int rows;                     //высота игрового поля
    private static int snakeLength;              //начальна длина змеи
    private static int frogs;                    //количество лягушек
    private static int time;                     //время засыпания змеи в мс
    static final int SIZE=70;                    //ширина клетки
    private int score=0;
    private int milliseconds=1000;
    private Random rand=new Random();

    enum Animal {FROGRED,FROGREEN,FROGBLUE,SNAKE,ZERO}
    static Animal [][] field;  //игровое поле

    private ArrayList<Frog> _frogs;
    private _Snake snake;
    private StatusGame state;

    public Game(int _cols, int _rows){
        cols=_cols;
        rows=_rows;
        _frogs=new ArrayList<Frog>();
        field=new Animal[cols][rows];

        for (int j=0;j<cols;j++)
            for(int i=0;i<rows;i++)
                field[j][i]=Animal.ZERO;
    }

    public void start(){

        for (int i=0;i<4;i++){
            while (true){
                int x=rand.nextInt(cols-1);
                int y=rand.nextInt(rows-1);
                int col=rand.nextInt(3);

                if (field[x][y]==Animal.ZERO){
                    _frogs.add(new Frog(x,y,setColorFrog()));
                    break;
                }
            }
        }
        snake =new _Snake(0,0);
        state=StatusGame.PLAYED;
    }

    public void paint(Graphics g){

        if (getState()==StatusGame.PLAYED){
            for(Frog f: _frogs)
                f.Paint(g);

            snake.Paint(g);
        }
    }

    public int getSquareSize()      { return SIZE; }
    public int getScore()           { return score;  }

    public void pressLeftButton() {

    }

    public void pressRigthButton() {

    }

    public void Play() {
        state=StatusGame.PLAYED;
    }

    public void Pause() {
        state=StatusGame.PAUSE;
    }

    public void Stop() {
        state=StatusGame.STOP;
        _frogs.clear();
    }

    public StatusGame getState(){ return state; }

    private static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private Color setColorFrog(){
        int c=rand.nextInt(3);
        switch (c){
            case 0:return Color.GREEN;
            case 1:return Color.RED;
            case 2:return Color.BLUE;
            default:return Color.WHITE;
        }
    }

}
