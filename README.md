## 목차
1. Strategy pattern
2. 옵저버패턴
3. 데코레이터 패턴
4. 팩토리 패턴
5. 싱글턴 패턴
6. 커맨드 패턴
7. 어댑터 패턴과 처사드 패턴
8. 템플릿 메소드 패턴
9. 이터레이터와 컴포지트 패턴
10. 스테이트 패턴
11. 프록시 패턴
12. 컴파운드 패턴
---
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
    
    public void flay(){
        logger.info("날라 댕기는중~");
    }

    public void display(){
        this.quack();
        this.swim();
        this.flay();
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
    public void flay() {
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
