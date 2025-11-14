package patterns.headfirst.observer.observerImpl;

import patterns.headfirst.observer.Observer;
import patterns.headfirst.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    private List<Observer> observers;

    private int temperature;
    private int humidity;
    private int pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }
    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements (int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
