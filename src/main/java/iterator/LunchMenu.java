package iterator;

import iterator.item.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LunchMenu {

    private static final Logger logger = LoggerFactory.getLogger(LunchMenu.class);

    final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MenuItem menuItems [];

    public LunchMenu(){
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("돈까스");
        addItem("생선까스");
        addItem("비프까스");
    }

    public void addItem(String name){
        if(numberOfItems >= MAX_ITEMS){
            logger.info("메뉴가 가늑찼습니다. 등록할 수 없습니다.");
        }else {
            menuItems[numberOfItems++] = new MenuItem(name);
        }
    }

    public Iterator createIterator(){
        return new LunchMenuIterator(menuItems);
    }
}
