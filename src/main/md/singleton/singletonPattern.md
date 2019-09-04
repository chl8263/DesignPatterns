## 5. Singleton pattern

## 정의
>싱글턴 패턴은 해당 클래스의 인스턴스가 하나만 만들어지고, 어디서든지 그 인스턴스에 접근할 수 있도록 하기 위한 패턴이다.

### 싱글톤은 언제 쓰이는가?
쓰레드 풀, 캐시 , 대화상자 , 사용자 설정 등 인스턴스를 두개 이상 만들었을때 프로그램이 이상하게 돌아간다거나 자원을 불필요하게 잡아먹는다던가 결과에 일관성이 없어지는 경우 사용하곤한다.

위의 예시에서도 볼 수 있듯이 singleton 패턴은 특정 클래스에 대해 객체 인스턴스가 하나만 만들어질 수 있도록 해주는 패턴이다.

즉, 싱글톤 패턴의 핵심은 어떠한 상황에서든 해당 객체의 인스턴스는 하나만 존재해야 한다는 것이다.

### 1. 고전적인 Singleton
~~~java
public class Singleton {

    private static Singleton singletonObject;    // 유일한 인스턴스를 저장하기위한 변수

    private Singleton(){}   // 생성자를 private 로 선언 했기 때문에 Singleton 클래스 내부에서만 인스턴스를 만들 수있다.

    public static Singleton getInstance(){
        if(singletonObject == null){     // 만들어진 객체가 없다면 생성해줌
            singletonObject = new Singleton();
        }

        return singletonObject;
    }
}
~~~

위의 구현 방법은 이 인스턴스가 필요한 시점에 getInstence()가 호출되는 시점, 즉 해당 객체의 인스턴스가 필요한 상황이 왔을떄 인스턴스를 생성한다.

이와 같이 미리 생성하지 않고 필요한 상황에서 instance를 만드는 것을 __lazy instantiation(게으른 초기화)__ 라고 한다.

이와 같은 예제의 구현으로 이제 Singleton 객체는 유일한 instance를 가지게 되었다. 하지만 이 경우 __multi-thread 환경에서 문제점이 발생한다.__





