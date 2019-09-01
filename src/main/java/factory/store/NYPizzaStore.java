package factory.store;

import factory.pizza.*;
import factory.pizza.NY.NYCheesePizza;
import factory.pizza.NY.NYMeatPizza;
import factory.pizza.NY.NYOriginalPizza;
import factory.pizza.NY.NYPepperoniPizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(String type) {

        if(type.equals("cheese")){

            return new NYCheesePizza();

        }else if(type.equals("pepperoni")){

            return new NYPepperoniPizza();

        }else if(type.equals("meat")){

            return new NYMeatPizza();

        }else {

            return new NYOriginalPizza();
        }
    }
}
