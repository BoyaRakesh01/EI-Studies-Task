import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

class PhoneApp implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Phone App: Weather updated to " + temperature + "°C");
    }
}

class DesktopWidget implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Desktop Widget: Current temperature is " + temperature + "°C");
    }
}

public class WeatherStationDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        PhoneApp phoneApp = new PhoneApp();
        DesktopWidget desktopWidget = new DesktopWidget();

        station.addObserver(phoneApp);
        station.addObserver(desktopWidget);

        station.setTemperature(25.0f);  // This will update both observers
    }
}
