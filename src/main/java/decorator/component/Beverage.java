package decorator.component;

public abstract class Beverage {
    protected String description = "empty";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}