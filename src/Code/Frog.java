package Code;

import java.awt.*;

class Frog
{
    private int x;
    private int y;
    private Color color;
    private int k=Game.SIZE/4;             //коэффициент смещения координат

    Frog(int _x, int _y, Color _color){
         x=_x;
         y=_y;
         color=_color;
         setFrog(x,y,color);
    }

    void Paint(Graphics g){
        g.setColor(color);
        g.fillArc(x*Game.SIZE+k,y*Game.SIZE+k,Game.SIZE/2,Game.SIZE/2,0,360);
    }

    void setFrog(int x, int y, Color color){
        Game.Animal an = Game.Animal.ZERO;
        if (color==Color.RED) an=Game.Animal.FROGRED;
        if (color==Color.GREEN) an=Game.Animal.FROGREEN;
        if (color==Color.BLUE) an=Game.Animal.FROGBLUE;
        Game.field[x][y]=an;
    }
}
