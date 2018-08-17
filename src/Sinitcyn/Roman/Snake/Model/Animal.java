package Sinitcyn.Roman.Snake.Model;

abstract class Animal implements Runnable{
    protected GameField field;
    protected ModelSnake model;
    protected Thread t;
    protected boolean finish;

    Animal(){
        t=new Thread(this);
        finish=false;
    }
}
