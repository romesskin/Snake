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
        if (ModelSnake.getStatus()!=StatusGame.PAUSE)
            model.startGame();
        model.playGame();
    }

    public static void Pause(){
        view.setEnabledButton(true,false,true);
        view.setStatus("PAUSE");
        model.pausedGame();
    }

    public static void Stop(){
        view.setEnabledButton(true,false,false);
        view.setStatus("STOPPED");
        model.stopGame();
    }

    public static void mousePressButton(MouseEvent e)
    {
        switch (e.getButton()){
            case MouseEvent.BUTTON3:model.setDirection(true);break;
            case MouseEvent.BUTTON1:model.setDirection(false);break;
        }
    }
}
