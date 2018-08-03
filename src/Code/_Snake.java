package Code;

import java.awt.*;

class _Snake {
    int x;
    int y;

    _Snake (int x, int y){
        this.x=x;
        this.y=y;
        Game.field[x][y]= Game.Animal.SNAKE;
    }

    void Paint(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillArc(x*Game.SIZE+1,y*Game.SIZE+1,Game.SIZE-2,Game.SIZE-2,0,360);
    }
}
