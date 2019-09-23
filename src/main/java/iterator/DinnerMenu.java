package iterator;

import iterator.item.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class DinnerMenu {

    private ArrayList<MenuItem> menuItems;

    public DinnerMenu(){
        menuItems = new ArrayList<MenuItem>();

        this.addItem("양상추");
        this.addItem("콩자반");
        this.addItem("제육볶음");
    }

    public void addItem(String name){
        menuItems.add(new MenuItem(name));
    }


}
