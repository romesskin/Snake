package Sinitcyn.Roman.Snake.Model;

import Sinitcyn.Roman.Snake.Controller.ControllerSnake;
import Sinitcyn.Roman.Snake.Controller.StatusGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/*
        Класс отвечает за логику игры и создание игровых объектов
 */
public class ModelSnake {

    private static final int SIZE=72;   //размер клетки 72х72
    Coord sizeMap;                      //размер карты
    private ArrayList<Frog> frog;       //массив лягушек
    private Snake snake;                //объект змеи
    private int score=0;                //счет игры
    private int frogs;                  //количество лягушек в игре
    private int time;                   //время засыпания змеи
    private int snakeLength;            //начальная длина змеи
    private Random rand=new Random();
    private boolean isGameOver=false;

    enum Animal {SNAKE,RED_FROG,GREEN_FROG,BLUE_FROG, NULL}
    private Animal Map[][];

    //Направление движения змеи
    enum Direction {RIGTH,DOWN,LEFT,UP;
        Direction next(){
            return this.ordinal()==3 ? Direction.values()[this.ordinal()-3] : Direction.values()[this.ordinal()+1];
        }
        Direction pervious(){
            return this.ordinal()==0 ? Direction.values()[this.ordinal()+3] : Direction.values()[this.ordinal()-1];
        }
    }
    private Direction direction;

    public ModelSnake(int cols, int rows, int snakeLength, int frogs,int time){
        this.snakeLength=snakeLength;
        sizeMap = new Coord(cols,rows);
        this.time=time;
        ControllerSnake.setStatus(StatusGame.STOP);          //начальный статус игры
        direction=Direction.RIGTH;

        //обнуление массива поля
        Map=new Animal[cols][rows];
        mapSetToZero();

        this.snake=new Snake(snakeLength);
        frog=new ArrayList<>();
        this.frogs=frogs;
    }

    //перемещение змеи
    public void MoveSnake(){
        delay(time);
        Coord coord=new Coord(snake.getHeadSnake().x,snake.getHeadSnake().y);
        switch (direction){
            case UP: coord.y=(coord.y-1)%sizeMap.y;if (coord.y<0) coord.y+=sizeMap.y;  break;
            case RIGTH: coord.x=(coord.x+1)%sizeMap.x;   break;
            case DOWN: coord.y=(coord.y+1)%sizeMap.y;    break;
            case LEFT: coord.x=(coord.x-1)%sizeMap.x;if (coord.x<0) coord.x+=sizeMap.x;   break;
        }

            snake.Move(coord);
            System.out.println("x= "+coord.x+" y= "+coord.y);

    }

    public void paint(Graphics g){
        paintMap(g);
        if (ControllerSnake.getStatus()==StatusGame.PLAY) {
            for (Frog _frog : frog) _frog.paint(g);
            snake.paint(g);
        }
    }

    //отрисовка сетки
    private void paintMap(Graphics g){
        g.setColor(Color.WHITE);
        for(int y=0;y<sizeMap.y;y++)
            g.drawLine(0,y*SIZE,sizeMap.x*SIZE,y*SIZE);
        for(int x=0;x<sizeMap.x;x++)
            g.drawLine(x*SIZE,0,x*SIZE,sizeMap.y*SIZE);
    }

    public int getScore() {
        return score;
    }

    private void mapSetToZero(){
        for (int j=0;j<sizeMap.y;j++)
            for (int i=0;i<sizeMap.x;i++)
                Map[i][j]=Animal.NULL;
    }

    private void setFrogsToMap(){
        for (int i=0;i<frogs;i++){
            Coord frogCoord = new Coord(rand.nextInt(sizeMap.x),rand.nextInt(sizeMap.y));
            Color color=Color.GREEN;
            frog.add(new Frog(frogCoord));
        }
    }
    //очистка массивов при нажатии STOP
    public void clear() {
        frog.clear();
        mapSetToZero();
        ControllerSnake.setStatus(StatusGame.STOP);
        direction=Direction.RIGTH;
    }

    public void startGame(){
        setFrogsToMap();
        snake=new Snake(snakeLength);
    }

    public static int getSize(){ return SIZE; }

    public void setScore() {
        score = ++score;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public void setDirection(boolean b) {
        if (b) direction=direction.next();
                else direction=direction.pervious();
    }

    void delay(int t){
        try {
            Thread.sleep(t);
        } catch (Exception e)
        { e.printStackTrace(); }
    }
}
