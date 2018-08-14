package Sinitcyn.Roman.Snake.Model;

import java.util.Random;

/*
        Класс отвечает за логику игры и создание игровых объектов
 */
public class ModelSnake{

    private static StatusGame status;                     //статус игры
    private static Direction direction;                 //направление движения змеи
    private static Coord sizeMap;                       //размер карты
    private static int time;                            //время засыпания змеи

    private  GameField gameField;                       //карта
    private int score=0;                                //счет игры
    private Snake snake;                                //объект змеи
    private int frogs;                                  //количество лягушек в игре
    private int snakeLength;                            //начальная длина змеи
    private Random rand=new Random();
    private boolean isGameOver=false;

    public ModelSnake(int cols, int rows, int snakeLength, int frogs,int time){
        sizeMap = new Coord(cols, rows);
        gameField=new GameField();
        this.snakeLength=snakeLength;
        this.frogs=frogs;
        this.time=time;
    }

    public void startGame(){
        status=StatusGame.PLAY;
        direction=Direction.RIGTH;
        snake=new Snake(snakeLength, time);
        snake.field=getGameField();                 //передача ссылки на игровую карту
        gameField.addSnake(snake.getSnake());       //начальное размещение змеи на поле игры
        new Thread(snake,"Snake").start();
        for(int i=0;i<frogs;i++){
            addFrogs(FieldAnimal.FROGGREEN);
        }
    }

    public void pausedGame() {
        status=StatusGame.PAUSE;
    }

    public void stopGame(){
        status=StatusGame.STOP;
        direction=Direction.RIGTH;
        gameField.ClearField();
    }

    public void setDirection(boolean b) {
        if (b) direction=direction.next();
        else direction=direction.pervious();
    }

    private void addFrogs(FieldAnimal field){
        Coord coord=gameField.addFrog(field);
        Frog frog=new Frog(coord,field);
        frog.field=gameField;
        new Thread(frog,"Frog").start();
    }

    public static void setStatus(StatusGame status) {
        ModelSnake.status = status;
    }

    public GameField getGameField() { return gameField; }

    public static StatusGame getStatus(){ return status;}

    static Direction getDirection(){ return direction; }

    static Coord getSizeMap() { return sizeMap; }

    static int getTime(){ return time; }
}
