package observer.interfaces;

import java.util.Observer;

public interface Subject {
    public void regsiterObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver(Observer o);
}
