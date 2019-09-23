package iterator;

import iterator.item.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class PrintMenu {

    private static final Logger logger = LoggerFactory.getLogger(PrintMenu.class);

    private LunchMenu lunchMenu;

    private DinnerMenu dinnerMenu;

    public PrintMenu(){
        lunchMenu = new LunchMenu();
        dinnerMenu = new DinnerMenu();

        printMenu();
    }

    public void printMenu(){
        printLunch();
    }

    private void printLunch(){
        Iterator iterator = lunchMenu.createIterator();
        while (iterator.hasNext()){
            logger.info(iterator.next().toString());
        }
    }
}
