// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //The stats for the Labergenie car
        //this code says "I'm making a "variable" called Labergenie that is a new RaceCar with these stats ()
        RaceCar Labergenie = new RaceCar(250, 5, 5);

        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }
    }

    public static class RaceCar {
        private int topSpeed;
        private int acceleration;
        private int handling;

        public RaceCar(int topSpeed, int acceleration, int handling) {
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
    }



}