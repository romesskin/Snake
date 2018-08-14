package Sinitcyn.Roman.Snake.Model;

/*
        Класс лягушки
 */
class Frog extends Animal
{
    private Coord coord;
    private FieldAnimal color;

    Frog(Coord _coord, FieldAnimal _color){
        coord = _coord;
        color=_color;
    }

    private void Move(){
        field.MoveFrog(coord);
    }

    @Override
    public void run() {
        super.run();
        boolean b=true;
        System.out.println("Frog запущен");
        try {
            do{
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
                        b=false;
                        break;
                    }
                    default:break;
                }
            }while (b);
        }
        catch (Exception e) {
            System.out.println("Frog прерван");
            e.printStackTrace();
        }
        System.out.println("Frog завершен");
    }
}
