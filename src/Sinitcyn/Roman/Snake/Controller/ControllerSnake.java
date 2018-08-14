package Sinitcyn.Roman.Snake.Controller;

import Sinitcyn.Roman.Snake.Model.ModelSnake;
import Sinitcyn.Roman.Snake.Model.StatusGame;
import Sinitcyn.Roman.Snake.Viewer.ViewerSnake;

import java.awt.event.MouseEvent;

/*
        Класс отвечает за взаимосвязь логики и отображения
 */

public class ControllerSnake {

    private static Sinitcyn.Roman.Snake.Model.ModelSnake model;
    private static ViewerSnake view;

    public ControllerSnake(ModelSnake modelSnake, ViewerSnake viewerSnake) {
        model=modelSnake;
        view=viewerSnake;
        model.getGameField().addObserver(view);                     //установка подписчика view на изменения от model
        view.setEnabledButton(true,false,false);
    }

    public static void Play(){
        view.setEnabledButton(false,true,true);
        view.setStatus("PLAY");
        if (ModelSnake.getStatus()!=StatusGame.PAUSE) {
//            ModelSnake.setStatus(StatusGame.PLAY);
            model.startGame();
        }
        ModelSnake.setStatus(StatusGame.PLAY);
    }

    public static void Pause(){
        view.setEnabledButton(true,false,true);
        view.setStatus("PAUSE");
        model.pausedGame();
//        ModelSnake.setStatus(StatusGame.PAUSE);
    }

    public static void Stop(){
        view.setEnabledButton(true,false,false);
        view.setStatus("STOPPED");
        model.stopGame();
//        ModelSnake.setStatus(StatusGame.STOP);

    }

    public static void mousePressButton(MouseEvent e)
    {
        switch (e.getButton()){
            case MouseEvent.BUTTON1:model.setDirection(true);break;
            case MouseEvent.BUTTON3:model.setDirection(false);break;
        }
    }
}
