## 5. Singleton pattern

## 정의
>싱글턴 패턴은 해당 클래스의 인스턴스가 하나만 만들어지고, 어디서든지 그 인스턴스에 접근할 수 있도록 하기 위한 패턴이다.

### 싱글톤은 언제 쓰이는가?
쓰레드 풀, 캐시 , 대화상자 , 사용자 설정 등 인스턴스를 두개 이상 만들었을때 프로그램이 이상하게 돌아간다거나 자원을 불필요하게 잡아먹는다던가 결과에 일관성이 없어지는 경우 사용하곤한다.


### 고전적인 싱글톤
~~~java
public class Singleton {

    private static Singleton uniqueInstance;    // 유일한 인스턴스를 저장하기위한 변수

    private Singleton(){}   // 생성자를 private 로 선언 했기 때문에 Singleton 클래스 내부에서만 인스턴스를 만들 수있다.

    public static Singleton getInstance(){
        if(uniqueInstance != null){     // 만들어진 객체가 없다면 생성해줌
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }
}
~~~

