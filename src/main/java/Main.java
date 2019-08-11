import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import strategy.Duck;
import strategy.ToyDuck;
import strategy.quack.MuteQuack;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String [] args){

        Main main = new Main();
        main.strategy();
    }

    private void strategy(){
        Duck duck = new ToyDuck();

        duck.setQuackBehavior(new MuteQuack());

        duck.display();
    }
}
