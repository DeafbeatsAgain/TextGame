public class RaceCar {
    private String carName; //name of the car
    private int topSpeed; //"top" speed
    private int acceleration;
    private int handling;

    public RaceCar(String carName, int topSpeed, int acceleration, int handling) {
        this.carName = carName;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.handling = handling;
    }

    /*
    Getter Methods:
    example use
    double lapTime = calcLapTime(Labergenie.getHandling());
    this will assign the lapTime variable to the result of calcLapTime
    which takes the Labergenie's handling into account
     */
    public String getCarName() { return carName;}
    public int getTopSpeed() {
        return topSpeed;
    }
    public int getAcceleration() {
        return acceleration;
    }
    public int getHandling() {
        return handling;
    }

    // Setter methods
    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }

    //Ask Cova + Ian about Java @Override, what it do??
    @Override
    public String toString() {
        return carName + ": Top Speed = " + topSpeed + ", Acceleration = " + acceleration + ", Handling = " + handling;
}
}