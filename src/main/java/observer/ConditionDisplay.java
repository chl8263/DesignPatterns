package observer;

import observer.interfaces.CustomObserver;
import observer.interfaces.DisplayElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
