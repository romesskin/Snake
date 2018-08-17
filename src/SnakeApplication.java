import Sinitcyn.Roman.Snake.Controller.ControllerSnake;
import Sinitcyn.Roman.Snake.Viewer.*;
import Sinitcyn.Roman.Snake.Model.ModelSnake;


public class SnakeApplication {

    private static int cols=8;          //количество столбцов
    private static int rows=8;          //количесвто строк
    private static int frogs=4;         //начальное количество лягушек
    private static int snakeLength=5;   //начальная длина змеи
    private static int time=400;       //время засыпания змеи

    public static void main(String[] args) {
        ModelSnake Model=new ModelSnake(cols,rows,snakeLength,frogs,time);
        ViewerSnake View=new ViewerSnake(cols,rows);
        new ControllerSnake(Model,View);
    }
}



