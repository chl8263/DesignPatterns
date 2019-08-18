## 2. Observer pattern

### 정의

>observer pattern 이란 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체들한테 연락이 가고 자동으로 내용이 갱신되는 방식으로
one to many 의 의존성을 가진다.

![base](/src/main/md/observer/img/ob1.PNG)

위의 그림과 같이 observer pattern 에는 크게 두가지로 나뉜다. 

>publisher  : 여러개의 subscriber 를 가질 수 있으며 데이터를 발행하는 주체, Subject 라고도 한다.
>subscriber : 한개의 publisher 를 가질 수 있으며 publisher 가 발행한 데이터를 수신받는 주체, Observer 라고도 한다 .

observer pattern 의 class 다이어그램과 설명은 아래 그림과 같다.

![base_archi](/src/main/md/observer/img/ob2.PNG)

### 느슨한 결합
observer pattern 은 느슨하게 결합되어 있는 객체 디자인을 제공한다.

주제가 옵저버에 대해서 아는것은 옵저버가 인터페이스를 구현 한다는 것 뿐이다.

* 옵저버는 언제든지 추가될 수 있다.
* 새로운 형식의 옵저버를 추가하려고 할 때도 주제를 전혀 변경할 필요가 없다.
* 주제와 옵저버는 서로 독립접으로 재사용할 수 있다.
* 주제나 옵저버가 바뀌더라도 서로한테 영향을 미치지 않는다.

> 서로상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야 한다.

느슨하게 결합하는 디자인을 사용하면 변경사항이 생겨도 무난히 처리할 수 있는 유연한 객체지향 시스템을 구출할 수 있다.

객체 사이의 상호의존성을 최소화할 수 있기 때문이다.

### 예제 (가상 스테이션 구현하기)

예제로 기상 스테이션을 구현하는 과정을 앞으로 작성 하겠다.

기상 데이터를 수집하는 어떤 장치에서 기상데이터를 수집하여 화면에 나타내는 어플리케이션을 구현해 보겠다.

기상 데이터를 수집하는 장치는 Subject 가 되겠고 , 이를 수신하여 화면에 나타내는 주체는 Observer 가 되겠다. 

먼저, interface 부터 구현해 보겠다. 

~~~
public interface Subject {
    public void regsiterObserver(Observer o);  // 옵저버 등록
    public void removeObserver(Observer o);    // 옵저버 제거
    public void notifyObserver(Observer o);    // 옵저버들에게 상태 전송
}
~~~

~~~
public interface Observer {
    public void update(float temp , float humidity , float pressure);   // 기상정보를 수신할 상태 값들을 가지는 메소드
}
~~~

~~~
public interface DisplayElement {
    public void display();        // 화면에 표시해야하는 경우 사용할 메소드
}
~~~


subject interface 는 총 3가지로 나뉜다. 
