### 1. Strategy pattern

디자인 패턴하면 가장 많이 사용하는 오리에 대한 제품을 만드는 어플리케이션을 만든다고 가정하자.

~~~
public class Duck {

    private static final Logger logger = LoggerFactory.getLogger(Duck.class);

    public void quack(){
        logger.info("꿲꿲");
    }

    public void swim(){
        logger.info("수영하는중~");
    }
    
    public void fly(){
        logger.info("날라 댕기는중~");
    }

    public void display(){
        this.quack();
        this.swim();
        this.fly();
    }
}
~~~

위와같이 Duck class를 만들고 quack , swim 이라는 오리가 울고, 수영하고 나는 동작을 나타내는 메소드들을 만들었다.

이제 오리와 관련된 객체들은 Duck class를 상속받아 사용하기만 하면 된다.

만약 장난감 오리를 만들어야 한다면 어떻게 해야할까?

~~~
public class ToyDuck extends Duck {

    @Override
    public void display() {
        super.display();
    }
}
~~~

위와같이 ToyDuck class 를 하나 만들고 Duck를 상속 받는다.

그리고 display 메서드에 장난감오리를 위한 적절한 행동을 위해 Duck class의 메소드들을 오버라이딩 하는것이 바람직 해보인다.

~~~
public class ToyDuck extends Duck {
    private static final Logger logger = LoggerFactory.getLogger(ToyDuck.class);

    @Override
    public void quack() {
        logger.info("장난감 꿲꿲");
    }

    @Override
    public void swim() {
        logger.info("장난감 수영~");
    }

    @Override
    public void fly() {
        logger.info("장난감오리는 날 수 없습니다.");
    }

    @Override
    public void display() {
        super.display();
    }
}
~~~

그렇다면 여기서 interface를 사용하여 quack , fly 를 정의하는것은 어떨까?

어떤 오리마다 소리낼수 있고 날 수 있는 오리는 다르기 때문이다.

> interface 는 '할 수 있는' 의 의미이고 class 는 'A 는 B 이다' 의 의미이다.

~~~
public class ToyDuck extends Duck implements flayable , quackable{
    private static final Logger logger = LoggerFactory.getLogger(ToyDuck.class);

    @Override
    public void quack() {
        logger.info("장난감 꿲꿲");
    }

    @Override
    public void fly() {
        logger.info("장난감오리는 날 수 없습니다.");
    }

    @Override
    public void swim() {
        logger.info("장난감 수영~");
    }


    @Override
    public void display() {
        super.display();
    }
}
interface flayable {
    void fly();
}

interface quackable{
    void quack();
}
~~~

위의 코드와 같이 interface 로 정의하여 오리마다 특정 행동을 오버라이딩 하여 재정의 하게끔 변경 하였다.

만약 오리를 400 개 혹은 그 이상의 오리를 만들었다고 가정하자.

오리의 소리나 나는 행동을 업데이트 한다면 우리는 400개의 코드를 전부 고쳐야 한다는 치명적인 상황에 놓이게 된다.

여기서 해결점은 소리내는 행위와 나는 행위를 통합 하여 코드를 재구성할 수 있다.

~~~
public interface QuackBehavior {
    public void quack();
}
~~~

QuackBehavior 라는 quack 메소드를 가지는 interface를 만들었다.

~~~
public class Quack implements QuackBehavior {
    private static final Logger logger = LoggerFactory.getLogger(Quack.class);
    @Override
    public void quack() {
        logger.info("꿲꿲");
    }
}
~~~

위의 코드는 QuackBehavior 를 가지는 Quack class 이다. 이 클래스는 이제 해당하는 소리를 낼 수 있는 클래스인 것이다.

추가로 두가지 더 만들어 보자.

~~~
public class MuteQuack implements QuackBehavior {
    private static final Logger logger = LoggerFactory.getLogger(MuteQuack.class);
    @Override
    public void quack() {
        logger.info("조용~");
    }
}
~~~
~~~
public class SuperQuack implements QuackBehavior {
    private static final Logger logger = LoggerFactory.getLogger(SuperQuack.class);
    @Override
    public void quack() {
        logger.info("꿲꿲!!!!!!!!!!!!!");
    }
}
~~~

그리고 이제 Duck class를 다음과 같이 고칠 수 있다.

~~~
public class Duck {

    private static final Logger logger = LoggerFactory.getLogger(Duck.class);

    private QuackBehavior quackBehavior;

    public Duck(){
        quackBehavior = new Quack();
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void swim(){
        logger.info("수영하는중~");
    }

    public void display(){
        this.swim();
        this.quackBehavior.quack();
    }
}

~~~
위의 코드에서 interface타입의 맴버 변수를 생성했고 default를 Quack class로 주입했다.

이제 dispaly 메서드에서 quackBehavior.quack(); 을 어떤 class 인지 상관없이 불러 쓰면 된다.

즉, 소리나는 행위를 다른 class에 위임을 한다는 말이다.

장난감 오리를 만들때 어떻게 하면 될까.

~~~
Duck duck = new ToyDuck();
        
duck.setQuackBehavior(new MuteQuack());

duck.display();
~~~

위와 같이 setQuackBehavior 를 통해 MuteQuack 를 주입한다. 

~~~
00:03:44.384 [main] INFO strategy.ToyDuck - 장난감 수영~
00:03:44.386 [main] INFO strategy.quack.MuteQuack - 조용~

Process finished with exit code 0
~~~

결과를 위와 같이 잘 나오게 된다.

>Strategy pattern 은 간략히 말해 어떤 동작을하는 로직을 정의하고 이것들을 하나로 묶어(캡슐화) 관리하도록 하는 패턴이다.
>새로운 로직을 추가하거나 변경할때 객체의 종류 수 만큼 반복적으로 수정 반영할 필요없이 단한번에 반영할 수 있다.
