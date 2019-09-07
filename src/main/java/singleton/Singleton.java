package singleton;

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
