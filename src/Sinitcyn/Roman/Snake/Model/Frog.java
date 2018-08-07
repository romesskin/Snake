package Sinitcyn.Roman.Snake.Model;

import Sinitcyn.Roman.Snake.Controller.ControllerSnake;

import java.awt.*;
/*
        Класс лягушки
 */
class Frog
{
    private Coord coord;
    private Color color;
    private final int k= ModelSnake.getSIZE()/6;    //коэффициент смещения координат

    Frog(Coord _coord){
        coord=_coord;
    }

    void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillArc(coord.x* ModelSnake.getSIZE()+k,coord.y* ModelSnake.getSIZE()+k, ModelSnake.getSIZE()*2/3, ModelSnake.getSIZE()*2/3,0,360);
    }
}
