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

    public void display(){
        this.quack();
        this.swim();
    }
}
~~~

위와같이 Duck class를 만들고 quack , swim 이라는 오리가 울고, 수영하는 동작을 나타내는 메소드 들을 만들었다.
