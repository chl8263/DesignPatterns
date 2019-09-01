package factory.store;

import factory.pizza.Pizza;

public abstract class PizzaStore {

        public Pizza orderPizza(String type){
            Pizza pizza = createPizza(type);    // factory 가 아닌 store 에서 pizza 를 할당 받는다.

            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

            return pizza;
        }

        public abstract Pizza createPizza(String type); // 팩토리 객체대신 이 메소드를 사용한다.


}
