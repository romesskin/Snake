package Sinitcyn.Roman.Snake.Model;

import java.awt.*;
import java.util.Vector;
/*
        Класс змеи
 */
class Snake {
    //private Coord coord;
    private final int k[]={ModelSnake.getSIZE()/6,ModelSnake.getSIZE()/4};  //коэффициенты смещения координат для тела и хвоста
    private Vector<Coord> sn;                                               //массив хранения всех координат змеи

    Snake(int length){
        sn=new Vector<>();
        for (int i = length;i>0; i--) {
            sn.add(new Coord(i-1, 0));
        }
    }

    void Move(Coord coord){             //метод перемещения
        for(int i=sn.size()-1;i>0;i--) {
            sn.setElementAt(sn.get(i-1), i);
        }
        sn.setElementAt(coord,0 );
    }

    void Shorten (){                    //метод укорочения

    }

    void Lengthen(){                    //метод удлинения

    }

    void paint(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillArc(sn.firstElement().x*ModelSnake.getSIZE(),sn.firstElement().y*ModelSnake.getSIZE(),ModelSnake.getSIZE(),ModelSnake.getSIZE(),0,360);
        for (int i=0;i<sn.size()-1;i++)
            g.fillArc(sn.get(i).x*ModelSnake.getSIZE()+k[0] , sn.get(i).y*ModelSnake.getSIZE()+k[0] , ModelSnake.getSIZE()*2/3 , ModelSnake.getSIZE()*2/3, 0, 360);
        g.fillArc(sn.lastElement().x*ModelSnake.getSIZE()+k[1],sn.lastElement().y*ModelSnake.getSIZE()+k[1],ModelSnake.getSIZE()/2,ModelSnake.getSIZE()/2,0,360);
    }

    Coord getHeadSnake(){               //вовращает координаты головы
        return sn.firstElement();
    }

}
