package Sinitcyn.Roman.Snake.Controller;

import Sinitcyn.Roman.Snake.Model.ModelSnake;
import Sinitcyn.Roman.Snake.Viewer.ViewerSnake;

import java.awt.*;

/*
        Класс отвечает за взаимосвязь логики и отображения
 */

public class ControllerSnake {

    private static Sinitcyn.Roman.Snake.Model.ModelSnake model;
    private static ViewerSnake view;
    private static StatusGame status;

    public ControllerSnake(Sinitcyn.Roman.Snake.Model.ModelSnake modelSnake, ViewerSnake viewerSnake) {
        model=modelSnake;
        view=viewerSnake;
        view.setEnabledButton(true,false,false);
        view.setScore(model.getScore());
        status=StatusGame.STOP;
        Start();
    }

    public static void Play(){
        view.setEnabledButton(false,true,true);
        view.setStatus("PLAY");
        if (status==StatusGame.STOP){
            model.startGame();
            view.repaint();
        }
        status=StatusGame.PLAY;
        System.out.println(status);
    }

    public static void Pause(){
        view.setEnabledButton(true,false,true);
        view.setStatus("PAUSE");
        status=StatusGame.PAUSE;
        System.out.println(status);
    }

    public static void Stop(){
        view.setEnabledButton(true,false,false);
        view.setStatus("STOPPED");
        status=StatusGame.STOP;
        model.clear();
        view.repaint();
        System.out.println(status);

    }

    public static void pressRigthButton() {
        model.setDirection(true);
    }

    public static void pressLeftButton() {
       model.setDirection(false);
    }

    public static void paintControl(Graphics g){
        model.paint(g);
    }

    protected static void Move(){
            model.MoveSnake();
    }

    private static void Start(){
        while (true){
            switch (status){
                case PLAY:{
                    Move();
                    view.repaint();
                    break;
                }
                case PAUSE:{
                    break;
                }
                case STOP:{
                    break;
                }
                case WINNER:{
                    break;
                }
                case LOSS:{
                    break;
                }
            }
        }
    }

    public static void setStatus(StatusGame status)
    {
        ControllerSnake.status = status;
    }

    public static StatusGame getStatus()
    {
        return status;
    }

    public static int getSIZE()
    {
        return ModelSnake.getSize();
    }

}
