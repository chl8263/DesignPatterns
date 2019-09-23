package iterator;

import iterator.item.MenuItem;

public class LunchMenuIterator implements Iterator {

    private MenuItem menuItems[];
    private int position = 0;

    public LunchMenuIterator(MenuItem items []){
        this.menuItems = items;
    }

    @Override
    public boolean hasNext() {
        if(position >= menuItems.length || menuItems[position] == null)
            return false;
        else return true;
    }

    @Override
    public Object next() {
        return menuItems[position++].getName();
    }
}
