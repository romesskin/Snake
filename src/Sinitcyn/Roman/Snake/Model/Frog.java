package Sinitcyn.Roman.Snake.Model;

/*
        Класс лягушки
 */
class Frog extends Animal
{
    static private int count=0;
    private Coord coord;
    private FieldAnimal color;

    Frog(Coord _coord, FieldAnimal _color){
        super();
        coord = _coord;
        color=_color;
    }

    private void Move(){
        field.MoveFrog(coord);
    }

    Coord getCoord(){
        return coord;
    }

    FieldAnimal getColor(){
        return color;
    }

    @Override
    public void run() {
        int c = ++count;
        System.out.println("Frog "+ c +" запущен");

        try {
            while (!finish){
                switch (ModelSnake.getStatus()){
                    case PLAY:{
                        Thread.sleep(ModelSnake.getTime()*2);
                        Move();
                        break;
                    }
                    case LOSS:
                    case WINNER:
                    case STOP:
                    {
                        finish=true;
                        break;
                    }
                    default:break;
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("Frog прерван");
            e.printStackTrace();
        }
        System.out.println("Frog "+(c)+" завершен");
//        count--;
    }
}
