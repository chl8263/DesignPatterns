package observer;

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
