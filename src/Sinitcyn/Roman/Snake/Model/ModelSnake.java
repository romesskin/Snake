package Sinitcyn.Roman.Snake.Model;

import java.util.ArrayList;

/*
        Класс отвечает за логику игры и создание игровых объектов
 */
public class ModelSnake{

    private static StatusGame status;                   //статус игры
    private static Direction direction;                 //направление движения змеи
    private static Coord sizeMap;                       //размер карты
    private static int time;                            //время засыпания змеи

    private  GameField gameField;                       //карта
    private int score=0;                                //счет игры
    private Snake snake;                                //объект змеи
    private ArrayList<Frog> frog;
    private int frogs;                                  //количество лягушек в игре
    private int snakeLength;                            //начальная длина змеи
    private boolean isGameOver=false;

    public ModelSnake(int cols, int rows, int snakeLength, int frogs,int _time){
        sizeMap = new Coord(cols, rows);
        gameField=new GameField();
        this.snakeLength=snakeLength;
        this.frogs=frogs;
        frog=new ArrayList<>();
        time=_time;
    }

    public void startGame(){
        status=StatusGame.PLAY;
        direction=Direction.RIGTH;
        snake=new Snake(snakeLength, time);
        snake.field=getGameField();                 //передача ссылки на игровую карту
        snake.model=this;
        gameField.addSnake(snake.getSnake());       //начальное размещение змеи на поле игры
        snake.t.start();
        for(int i=0; i<frogs;i++){
            addFrog(FieldAnimal.FROGGREEN);
        }
    }

    public void playGame() {
        gameField.zeroScore();
        status=StatusGame.PLAY;
    }

    public void pausedGame() {
        status=StatusGame.PAUSE;
    }

    public void stopGame(){
        status=StatusGame.STOP;
        direction=Direction.RIGTH;
        try{
            snake.t.join();
        }catch (InterruptedException e)
            {e.printStackTrace();}
        gameField.ClearField();
    }

    private void addFrog(FieldAnimal color){
        Frog _frog=new Frog(gameField.addFrog(color),color);
        _frog.model=this;
        _frog.field=getGameField();
        _frog.t.start();
        frog.add(_frog);
    }

    void delFrog(Coord coord) {
        for (int i=0;i<frog.size();i++){
          if(frog.get(i).getCoord().x==coord.x && frog.get(i).getCoord().y==coord.y) {
              FieldAnimal col=frog.get(i).getColor();
              frog.get(i).finish = true;
              frog.remove(i);
              addFrog(col);
              break;
          }
        }
    }

    public void setDirection(boolean b) {
        if (b) direction=direction.next();
        else direction=direction.pervious();
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
