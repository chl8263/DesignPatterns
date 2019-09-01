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

~~~java
public interface Subject {
    public void regsiterObserver(Observer o);  // 옵저버 등록
    public void removeObserver(Observer o);    // 옵저버 제거
    public void notifyObserver(Observer o);    // 옵저버들에게 상태 전송
}
~~~

~~~java
public interface CustomObserver {
    public void update(float temp , float humidity , float pressure);
}
~~~

~~~java
public interface DisplayElement {
    public void display();        // 화면에 표시해야하는 경우 사용할 메소드
}
~~~

이제 위의 3가지 interface 를 가지고 class를 구현해 보겠다.

우선 subject의 기능을 해야하는 기상 장치 이다.

~~~java
public class WeatherData implements Subject {

    private ArrayList<CustomObserver> customObservers;      // 옵저버들을 저장하는 변수
    private float temperature;                              // 기상 데이터
    private float humidity;                                 // 기상 데이터
    private float pressure;                                 // 기상 데이터

    public WeatherData(){
        customObservers = new ArrayList();                  // 생성자에서 초기화
    }

    public void setMesurements(float temperature , float humidity , float pressure){    // 기상데이터 셋팅
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        
        mesurementChange(); // 기상데이터가 변경호출
    }

    public void mesurementChange(){
        notifyObserver();   // 기상데이터 변경시 옵저버들에게 알림
    }

    @Override
    public void registerObserver(CustomObserver o) {
        customObservers.add(o);                             // 옵저버 등록
    }

    @Override
    public void removeObserver(CustomObserver o) {
        int index = customObservers.indexOf(o);

        if(index >= 0)
            customObservers.remove(index);                  // 옵저버 해지
    }

    @Override
    public void notifyObserver() {
        for(CustomObserver observer : customObservers){
            observer.update(this.temperature , this.humidity , this.pressure);  // 옵저버 알림
        }
    }
}

~~~

설명은 코드의 주석으로 대체한다.

다음은 디스플레이 장치를 만들어 보자.

~~~java
public class ConditionDisplay implements CustomObserver , DisplayElement {

    private static final Logger logger = LoggerFactory.getLogger(ConditionDisplay.class);

    private WeatherData weatherData;

    private float temperature;                              // 기상 데이터
    private float humidity;                                 // 기상 데이터
    private float pressure;                                 // 기상 데이터

    public ConditionDisplay(WeatherData weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);              //  weatherData에 지금 Observer객체 등록
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        logger.info("current conditions : 온도 -> " + temperature + " 습도 -> " + humidity + " 압력 -> " +pressure);
    }
}
~~~

이제 기상스테이션을 만들어 지금까지의 작업들을 연동시켜 보겠다.

~~~java
public class WeatherStation {
    public WeatherStation(){
        run();
    }

    public void run(){
        WeatherData weatherData = new WeatherData();    // 주제가 되는 객체

        ConditionDisplay display_1 = new ConditionDisplay(weatherData);     // 옵저버 생성
        ConditionDisplay display_2 = new ConditionDisplay(weatherData);     // 옵저버 생성
        ConditionDisplay display_3 = new ConditionDisplay(weatherData);     // 옵저버 생성

        weatherData.setMesurements(12,15,12);       // 데이터 변경
        weatherData.setMesurements(100,123,523);    // 데이터 변경
        weatherData.setMesurements(3000,1231,9372); // 데이터 변경
    }
}
~~~

옵저버를 3개를 만들고 데이터를 변경하면 데이터가 출력이 되어야 한다.

~~~
00:05:53.310 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 12.0 습도 -> 15.0 압력 -> 12.0
00:05:53.312 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 12.0 습도 -> 15.0 압력 -> 12.0
00:05:53.312 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 12.0 습도 -> 15.0 압력 -> 12.0
00:05:53.313 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 100.0 습도 -> 123.0 압력 -> 523.0
00:05:53.313 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 100.0 습도 -> 123.0 압력 -> 523.0
00:05:53.313 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 100.0 습도 -> 123.0 압력 -> 523.0
00:05:53.313 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 3000.0 습도 -> 1231.0 압력 -> 9372.0
00:05:53.313 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 3000.0 습도 -> 1231.0 압력 -> 9372.0
00:05:53.313 [main] INFO observer.ConditionDisplay - current conditions : 온도 -> 3000.0 습도 -> 1231.0 압력 -> 9372.0

Process finished with exit code 0
~~~

잘 출력된 결과를 볼 수있다.
