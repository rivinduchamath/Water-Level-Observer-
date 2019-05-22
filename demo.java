package demo;

import java.util.Random;
class WaterLevelObserver{
    public void update(int waterLevel){
        //no implementaion
    }
}
class Alarm extends WaterLevelObserver{

    @Override
    public void update(int waterLevel) {
        System.out.println(waterLevel >= 50 ? "ON" : "OFF");
    }
}

class Display extends WaterLevelObserver{

    @Override
    public void update(int waterLevel) {
        System.out.println("Water Level : " + waterLevel);
    }
}

class SMSSender extends WaterLevelObserver{

    @Override
    public void update(int waterLevel) {
        System.out.println("Sending sms : " + waterLevel);
    }
}
class Splitter extends WaterLevelObserver{

    @Override
    public void update(int waterLevel) {
        System.out.println(waterLevel>=75 ? "Splitter ON":"Splitter OFF");
    }
    
}
class ControlRoom {
    private WaterLevelObserver[] observers=new WaterLevelObserver[50];
    private int nextIndex;
    private int waterLevel;
    
    public void addWaterLevelObserver(WaterLevelObserver waterLevelObserver){
        observers[nextIndex++]=waterLevelObserver;
    }
    public void notifyWaterLevelObservers() {
        for (int i = 0; i < nextIndex; i++) {
            observers[i].update(waterLevel);
        }
    }

    public void setWaterLevel(int waterLevel) {
        if (this.waterLevel != waterLevel) {
            this.waterLevel = waterLevel;
        }
        notifyWaterLevelObservers();
    }
}

public class Demo {

    public static void main(String[] args) {
        ControlRoom controlRoom = new ControlRoom();
        controlRoom.addWaterLevelObserver(new Alarm());
        controlRoom.addWaterLevelObserver(new Display());
        controlRoom.addWaterLevelObserver(new SMSSender());
        controlRoom.addWaterLevelObserver(new Splitter());
        Random r = new Random();
        while (true) {
            int waterLevel=r.nextInt(101);//0 to 100
            controlRoom.setWaterLevel(waterLevel);
            try{Thread.sleep(1000);}catch(Exception ex){}
        }

    }
}