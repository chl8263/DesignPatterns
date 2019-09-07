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

### 2. Synchronized를 이용한 Singleton

~~~java
public class Singleton {

    private static Singleton singletonObject;    

    private Singleton(){}

    public static synchronized Singleton getInstance(){  // 다중 thread 환경에서 동기화를 위해 synchronized 사용
        if(singletonObject == null){
            singletonObject = new Singleton();
        }

        return singletonObject;
    }
}
~~~

위의 코드를 보면 직관적으로 알 수있을 것이다. 멀티 쓰레드 환경에서 instance 를 2개를 만드는 불상사가 발생하지 않게 하기 위하여

getInstance() 메소드에 동기화 키워드인 synchronized를 붙였다.

이제 한 쓰레드가 이 메서드 사용을 끝마칠때까지 다른 쓰레드는 기다려야 한다.

동기화 문제는 해결되어 보이는듯 하나 문제점은 여전히 존재한다.

__synchronized 키워드를 사용하면 성능저하가 100 배__ 정도 된다고 한다.

getInstance() 의 속도가 어플리케이션에 중요한 부분이 아니라면 크게 문제 되지는 않겠지만 더 효율적인 방법이 있다.

### 3. 정적 초기화시 Singleton 생성

~~~java
public class Singleton {

    private static Singleton singletonObject = new Singleton(); // 정적 초기화

    private Singleton(){}

    public static synchronized Singleton getInstance(){  
        return singletonObject;
    }
}
~~~

이 구현법을 사용하면 클래스가 로딩될떄 JVM 에서 유일한 인스턴스를 생성해 준다. 로딩시 외에는 인스턴스를 생성할 수 없기 때문에 다수의 객체를 생성하는 문제점을 해결했다.

하지만 getInstance() 가 많은 자원을 필요로 한다면 최초 로딩시 속도 저하의 문제가 발생할 수 있다.

### 4. DCL(Double-checking Locking)을 이용한 Singleton 생성

~~~java
public class Singleton {

    private volatile static Singleton singletonObject;  // 변수 최신화를 위한 volatile 키워드를 붙여줌

    private Singleton(){}

    public static Singleton getInstance(){  
        if(singletonObject == null){    // 인스턴스가 있는지 확인
            synchronized(Singleton.class){  // 최초에만 동기화
                if(singletonObject == null){    // 인스턴스가 있는지 다시한번 확인
                    singletonObject = new Singleton();
                }
            }
        }
        return singletonObject;
    }
}
~~~

DCL 을 사용하면, 인스턴스가 생성되어 있는지 확인한 다음 생성되지 않을을 때만 동기화를 할 수 있다.

하지만 DCL을 이용하면 Multi-processer 가 shared-momery를 사용하면서 문제가 발생하기 쉽다고 한다.

좀더 알아보자면 사실상 위의 코드는 별다른 문제가 없어보인다.

그러나 __CPU와 메모리 아키텍쳐 때문에__ multi Thread 환경에 적합하지 않는 것이다.

현대의 CPU 는 연산을 위해 다양한 종류의 메모리를 사용한다. 그 종류는 __레지스터(Register)__, __캐시(cache)__ , __RAM__ 이 될것이다.

이렇게 CPU가 사용하는 메모리의 종류가 여러개인 이유는 CPU와 메모리의 속도 차이때문인데,

여기서 문제점은 DCLP알고리즘은 CPU가 RAM 에서만 메모리를 사용한다고 가정하여 위 코드의 instance 가 레지스터나 캐시에 있을경우 

특히 Multi CPU, Core 시스템에서 각각의 코어는 전용의 레지스터 와 캐시를 가진다. 결국 RAM과 각 코어의 레지스터 혹은 캐시와 동기화, 일관성 문제 가 
발생할 여지가 충분히 발생한다. 

### 5. ClassHolder를 이용한 Singleton 생성

~~~java
public class Singleton {

    private Singleton(){}
    
    private static class SingleHolder{
        static final SingleTon Instance = new Singleton();
    }

    public static Singleton getInstance(){  
        return SingleHolder.Instance;
    }
}
~~~

ClassHolder 를 사용한다면 Singleton 클래스의 getInstance() 메소드를 호출할 때 ClassHolder를 클래스 로더가 생성하기 때문에

좋은 성능을 보장하고 지연이 가장 적은 초기화를 가능하게 한다.

자세한 설명은 다음과 같으며 가장 좋은 방법으로 알려져 있다.

>Initialization on demand holder idiom은 lazy-loaded Singleton 으로 모든 Java 버전에서 the idiom은 좋은 성능으로 안전하고 동시 적이며 지연이 적은 초기화를 가능하게한다.
>이것은 JVM의 class loader의 매커니즘과 class의 load 시점을 이용하여 내부 class를 생성시킴으로 thread 간의 동기화 문제를 해결한다.

