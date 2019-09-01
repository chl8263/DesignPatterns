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
  }~~~else if(type.equals("potato")){~~~
    ~~~pizza = new PotatoPizza();~~~
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
