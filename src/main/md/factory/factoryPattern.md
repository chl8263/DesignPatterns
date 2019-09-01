## 4. Factory pattern

### 정의
> 팩토리 메소드 패턴에서는 객체를 생성하기 위한 인터페이스를 정의하는데, 어떤 클래스의 인스턴스를 만들지는 서브클래스에서 결정하게 된다.
> 팩토리 메소드 패턴을 이용하면 클래스의 인스턴스를 만드는 일을 서브클래스에게 맡기는 것이라 할 수 있다.

### new 의 문제
new 를 사용하는것은 '구상 객체'를 만드는 것을 의미한다. 

__상 클래스를 바탕으로 코딩을 하면 나중에 코드를 수정해야 할 가능성이 높아지고, 유연성이 떨어진다.__

~~~java
Duck duck;

if(a){
  duck = new ADuck();
}else if(b){
  duck = new BDuck();
}else {
  duck = new CDuck();
}
~~~

위의 코드를 보면 조건에 따라 duck의 구현 클래스가 달리 변화되는것을 볼 수 있다.

물론 틀린 코드는 아니지만, 이런 코드가 존재한다는 것은 뭔가 변경하거나 확장해야 할 때 코드를 다시 확인하고 추가 또는 제거해야 하는 경우가 발생한다.

즉, __변화에 닫혀있는__ 코드인 것이다.

그렇다면 어떻게 코드를 구성해야 할까? 아래의 예제를 보자.

내가 피자 가게를 운영하고 피자판매를 위한 소프트 웨어를 만들었다.

~~~java
public orderPizza(){
  Pizza pizza = new Pizza();
  
  pizza.prepare();
  pizza.bake();
  pizza.cut();
  pizza.box();
  
  return pizza;
}
~~~

위의 코드는 피자를 생성하기위해 준비하고, 굽고 , 자르고, 박스에 포장하여 반환하는 코드이다.

하지만 피자의 종류는 한가지만 있는것이 아니기 때문에 분기문을 추가하였다.

~~~java
public orderPizza(String type){
  Pizza pizza;
  
  if(type.equals("cheese")){
  
    pizza = new CheesePizza();
    
  }else if(type.equals("potato")){
  
    pizza = new PotatoPizza();
    
  }else if(type.equals("pepperoni")){
  
    pizza = new PepperoniPizza();
  }
  
  pizza.prepare();
  pizza.bake();
  pizza.cut();
  pizza.box();
  
  return pizza;
}
~~~

가게를 운영하던중 신메뉴가 출시되고 인기없는 Potato 피자는 제외하기로 하였다.

~~~java
public orderPizza(String type){
  Pizza pizza;
  
  if(type.equals("cheese")){
  
    pizza = new CheesePizza();
    
  }else if(type.equals("potato")){ // 제외
  
    pizza = new PotatoPizza();  // 제외
    
  }else if(type.equals("pepperoni")){
  
    pizza = new PepperoniPizza();
    
  }else if(type.equals("meat")){
  
    pizza = new MeatPizza();
  }
  
  pizza.prepare();
  pizza.bake();
  pizza.cut();
  pizza.box();
  
  return pizza;
}
~~~

위의 orderPizza 메소드의 문제가 되는 부분은 인스턴스를 생성하는 부분이다.

__피자를 추가하고 삭제 해야할 경우가 생긴다면 매번 코드를 수정해야 한다.__

pizza 인스턴스를 생성하는 부분을 캡슐화하여 따로빼서 개체를 만드는 일만 전담 하게 하였다.

그리고 객체 생성을 처리하는 클래스를 __팩토리'__ 라고 부른다.
~~~java
public class SimplePizzaFactory{

  public Pizza createPizza(String type){
  
    Pizza pizza;
    
    if(type.equals("cheese")){
    
      pizza = new CheesePizza();
      
    }else if(type.equals("pepperoni")){
    
      pizza = new PepperoniPizza();
      
    }else if(type.equals("meat")){
    
      pizza = new MeatPizza();
    }
    
    return pizza;
  }
}
~~~

이렇게 Factory를 따로 캡슐화를 시킴으로써 변경해야 하는 경우에 여기저기 다 들어가서 고칠필요가 없이 이 팩토리 메소드만 고치면 된다.

~~~java
public class PizzaStore{
  
  SimplePizzaFactory factory;
  
  public PizzaStore(SimplePizzaFactory factory){
    this.factory = factory;
  }
  
  public orderPizza(String type){
    Pizza pizza = factory.createPizza("cheese");

    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();

    return pizza;
  }
}
~~~

그리고 앞선 orderPizza 메소드는 위의코드와 같이 변경될 것이다.

지금까지 했던 작업이 결코 팩토리 패턴은 아니다. 물론 팩토리를 사용하는 것이긴 하지만 어떻게 달라지는지 앞으로 보면 되겠다.

피자가게가 유명해져 프렌차이즈 분점을 내가 시작하였다.

하지만 여기서 문제가 생겼다. 뉴욕에서는 빵을 앏게 , 시카고 에서는 빵을 두껍게 하는 등 각자 다른 피자Factory 를 요구하기 시작했다.

그렇다면 아래와 같이 코딩하면 될것 같다.

~~~java
NYPizzaFactory factory = new NYPizzaFactory();

PizzaStore nyStore = new PizzaStore(factory);

nyStore.order("cheese");
~~~

만약 피자를 굽는 방식이나 포장 방식이 달라진다면.? 

현재 프로그램은 pizzaStore 안에 orderPizza 가 존재함으로 인해 각 지점마다 유연한 피자생산이 불가능하다.

그래서 각 지점마다 독자적인 피자생산을 위해 피자 가게와 피자 제작 과정 전체를 하나로 묶어주는 프레임 워크를 생성하기로 했다.

우선 피자 만드는 활종 자체는 전부 PizzaStore 클래스에 국한시키면서도 분점마다 고유의 스타일을 살릴 수 있도록 하는 방법이 있다.

첫번째로 createPizza() 메소드를 PizzaStore 에 다시 집어넣어야한다.

하지만 그 메소드를 추상메소드로 선언하고 각 분점마다 스타일에 맞게 구현하면 되겠다.

~~~java
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
~~~

이제 피자의 구현은 서브 클래스에서 해야한다. 피자를 주문하거나 생산하는 과정은 동일하나 빵의 두께나 모양만 스타일에 맞게 알아서 서브클래스에서 구현하게 하면 된다.

PizzaStore 는 Pizza 가 어떤것인지 전혀 모르고 orderPizza 는 단지 준비하고 자르고 포장하는것만 하면 될 뿐이다.

즉, PizzaStore 와 Pizza는 서로 __독립적__ 이라고 할 수 있다.

예를들어 뉴욕스타일의 피자는 다음과 같이 만들면 되겠다.

~~~java
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
~~~

지금까지의 작업을 정리해보면, 인스턴스 클래스를 만드는 곳을 팩토리를 하나 생성하여 객체를 생성하는 방식에서

일련의 서브클래스에서 처리하는 방식으로 넘어오게 되었다.

그리고 서브클래스에서 어떤 클래스를 만들지 결경하게 함으로써 객체 생성을 캡슐화 했다. 그리고 이것이 바로 처음 기술했던 팩토리 메소드 패턴의 정의이다.

이것을 디자인의 원칙중 __'의존성 뒤집기'__ 라고 한다. 의존성 뒤집기란

> 추상화된 것에 의존하도록 만들어야하며. 구상 클래스에 의존하도록 만들지 않도록 해야한다.

의존성 뒤집기 디자인의 원칙을 지키는데 몇가지 가이드 라인이 있는데 아래와 같다.

__* 어떤변수에도 구상 클래스에 대한 레퍼런스를 저장하지 않는다.__

new 연산자를 사용하면 구상클래스에 대한 레퍼런스를 사용하게 되는것이다. 팩토리를 사용하여 구상 클래스에 대한 레퍼런스를 변수에 저장하는 일을 미리 방지한다.

__* 구상 클래스에서 유도된 클래스를 만들지 않는다.__

구상클래스에서 유도된 클래스를 만들면 특정 구상 클래스에 의존하게 된다. 인터페이스나 추상 클래스처럼 추상화된 것으로부터 클래스를 만들어야한다.

__* 베이스 클래스에 이미 구현되어 있던 메소드를 오버라이드하지 않는다.__

이미 구현되어 있는 메소드를 오버라이드한다 하는것은 애초에 베이스클래스가 제대로 추상화된 것이 아니였다고 볼 수 있다. 베이스 클래스에서 메소드를 정의할 때는 모든 서브클래스에서 공유할 수 있는 것만 정의해야 한다.
