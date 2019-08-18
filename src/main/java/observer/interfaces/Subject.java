package observer.interfaces;

public interface Subject {
    public void registerObserver(CustomObserver o);
    public void removeObserver(CustomObserver o);
    public void notifyObserver(CustomObserver o);
}
