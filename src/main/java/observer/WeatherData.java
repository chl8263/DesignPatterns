package observer;

import observer.interfaces.CustomObserver;
import observer.interfaces.Subject;

import java.util.ArrayList;

public class WeatherData implements Subject {

    private ArrayList<CustomObserver> customObservers;      // 옵저버들을 저장하는 변수
    private float temperature;                              // 기상 데이터
    private float humidity;                                 // 기상 데이터
    private float pressure;                                 // 기상 데이터

    public WeatherData(){
        customObservers = new ArrayList();                  // 생성자에서 초기화
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
    public void notifyObserver(CustomObserver o) {
        for(CustomObserver observer : customObservers){
            observer.update(this.temperature , this.humidity , this.pressure);  // 옵저버 알림
        }
    }
}
