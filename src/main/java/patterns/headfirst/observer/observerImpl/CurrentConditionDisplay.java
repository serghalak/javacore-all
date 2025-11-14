package patterns.headfirst.observer.observerImpl;

import patterns.headfirst.observer.DisplayElement;
import patterns.headfirst.observer.Observer;
import patterns.headfirst.observer.Subject;

public class CurrentConditionDisplay implements Observer, DisplayElement {

    private int temperature;
    private int humidity;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public String toString() {
        return "CurrentConditionDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", weatherData=" + weatherData +
                '}';
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public void update(int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
}
