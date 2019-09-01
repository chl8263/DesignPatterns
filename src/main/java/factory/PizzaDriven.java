package factory;

import factory.store.NYPizzaStore;
import factory.store.PizzaStore;

public class PizzaDriven {

    public PizzaDriven(){
        run();
    }

    public void run(){
        PizzaStore pizzaStore = new NYPizzaStore();

        pizzaStore.orderPizza("cheese");
    }
}
