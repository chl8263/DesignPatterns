## 2. Decorator pattern

### 정의

> 객체의 추가적인 요건을 동적으로 첨가한다.
> 테코레이터는 서브클래스를 만드는 것을 통해서 기능을 유연하게 확장할 수 있는 방법을 제공한다.

![base](/src/main/md/decorator/img/deco1.PNG)

__ConcreateComponent 에 새로운 행동을 동적으로 추가__ 할 수 있다.


각 __데코레이터 안에는 Compoenet 객체가 들어있다.__

__즉, 데코레이터에는 구성요소에 대한 레퍼런스가 들어있는 인스턴스 변수가 있다.__

Decorator는 자신이 장식랑 구성요소와 같은 인터페이스 또는 추상 클래스를 구현한다.

__ConcteateDecorator에는 그 객체가 장식하고 있는 것(데코레이터가 감싸고 있는 Component 객체)를 위한 인스턴스 변수가 있다.__

따라서 Decorator 는 component 의 상태를 확장할 수 있다.

테코레이터에서 새로운 메소드를 추가할 수 있다. 하지만 일반적으로 새로운 메소드를 추가하는 대신 Component에 원래 있던 메소드를 호출하기 전, 또는 후에 별도의 작업을 처리하는 방식으로 새로운 기능을 추가한다.

## 예제

카페 사업을 새로 시작한다고 가정해 보자.

처음 사업을 시작할때 클래스는 다음과 같이 구성했다.

![base](/src/main/md/decorator/img/deco2.PNG)

커피를 주문할때는 스팀이나 우유, 두유 모카 등등 을 추가할 수 있다. 그래서 아래의 클래스 다이어그램처럼 구현했다.

![base](/src/main/md/decorator/img/deco3.PNG)

__클래스 갯수가 폭발적으로 늘어나는 문제점이 생겼다.__

그래서 인스턴스 변수와 수퍼 클래스 상속을 사용하여 추가사항을 관리해보도록 수정했다.

![base](/src/main/md/decorator/img/deco4.PNG)

그렇다면 최종적으로 구현 코트는 이렇게 될 것이다.

~~~
public class Beverage{
  // member
  
  public int cost(){
    int totalCost = 0;
    if (hasMilk) totalCost += milk;
    if (hasSoy) totalCost += soy;
    if (hasMoca) totalCost += moca;
    if (hasWhip) totalCost += whip;
    return totalCost;
  }
}

public DarkRoast extands Beverage{
  @Override
  public int cost(){
    return 1200 + super.cost();
  }
}
~~~

이렇게 상속을 사용하여 구현하는것은 몇가지 문제점이 있어 보인다.

* 첨가물 가격이 바뀔 때마다 기존코드를 수정해야 한다.
* 첨가물의 종류가 많아지면 새로운 메소드를 추가해야하고, 수퍼클래스의 cost() 메소드도 고쳐야 한다.
* 만약 새로운 음료가 출시 되었다고 한다면 특정 첨가물이 안들어 가지만 필요없는 첨가물을 상속 받아야 한다.

> OCP 
> 클래스는 확장에 대해서는 열려 있어야 하지만 코드 변경에 대해서는 닫혀 있어야 한다.

상속을 사용하는 방법은 좋은 해결책이 되지 못하였다. 클래스가 어마어마 하게 많아지거나 일부 서브클래스에는 적합하지 않은기능을

베이스 클래스에 추가하게 되는 문제를 발견했었다.

이제 나는 데코레이터 패턴을 사용하여 특정 음료에서 시작하여 첨가물로 그 음료를 '장식'(Decorator) 할 것이다.

예를들어 어떤손님이 모카하고 휘핑 크림을 추가한 로스트 커피를 주문한다면 다음과 같은 식으로 할 수 있을 것이다.

1. DarkRoast 객체를 가져온다.
2. Mocha 객체로 장식한다.
3. Whip 객체로 장식한다.
4. cost() 메소드를 호출한다. 이떄 첨가물의 가격을 계산하는 일은 해당 객체들에게 위임한다.
