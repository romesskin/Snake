package Sinitcyn.Roman.Snake.Model;

import java.util.ArrayList;

/*
        Класс змеи
 */
class Snake extends Animal {
    private ArrayList<Coord> sn;         //массив хранения всех координат змеи
    private int time;
    private int length;

    Snake(int length, int time){
        this.length=length;
        sn=new ArrayList<>();

        for (int i = length;i>0; i--) {
            sn.add(new Coord(i-1, 0));
        }
        this.time=time;
    }

//метод перемещения
    private void Move(){
        Coord oldCoord=new Coord(sn.get(sn.size()-1).x,sn.get(sn.size()-1).y);
        Coord coord =new Coord(sn.get(0).x,sn.get(0).y);
        switch (ModelSnake.getDirection()){
            case UP: coord.y=(coord.y-1)%ModelSnake.getSizeMap().y;if (coord.y<0) coord.y+=ModelSnake.getSizeMap().y;  break;
            case RIGTH: coord.x=(coord.x+1)%ModelSnake.getSizeMap().x;   break;
            case DOWN: coord.y=(coord.y+1)%ModelSnake.getSizeMap().y;    break;
            case LEFT: coord.x=(coord.x-1)%ModelSnake.getSizeMap().x;if (coord.x<0) coord.x+=ModelSnake.getSizeMap().x;   break;
        }
        for(int i = length-1; i>0; i--) {
            sn.set(i,sn.get(i-1));
        }
        sn.set(0, coord);
        field.MoveSnake(sn,oldCoord);
    }

//метод укорочения
    void Shorten (){
        sn.remove(sn.size()-1);
    }

//метод удлинения
    void Lengthen(){

    }

    ArrayList<Coord> getSnake(){       //вовращает координаты змеи
        return sn;
    }

    @Override
    public void run() {
        super.run();
        boolean b=true;

        System.out.println("Snake запущен");
        try {
            while (b){
                switch (ModelSnake.getStatus()){
                    case PLAY:{
                        Thread.sleep(time);
                        Move();
                        break;
                    }
                    case LOSS:
                    case WINNER:
                    case STOP:{
                        b=false;
                        break;
                    }
                    default:break;
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println(" Snake прерван");
            e.printStackTrace();
        }
        System.out.println(" Snake завершен");

    }
}
