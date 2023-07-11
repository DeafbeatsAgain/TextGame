import java.util.*;
import java.util.Random;
public class Main
{

    public static void main(String[] args)
    {
        Main game = new Main();
        game.start();
        //The stats for the Labergenie car
        //this code says "I'm making a "variable" called Labergenie that is a new RaceCar with these stats ()
        //RaceCar Labergenie = new RaceCar(250, 5, 5);
    }
    // Declare Class-wide Variables here
    private RaceCar[] cars = {
            new RaceCar( "Labergenie" , 5, 5, 5), //Lamber-genie stats
            new RaceCar("Ter-Berga", 6, 6, 6), //Ter-Berda stats
            new RaceCar("Slice-ler", 7, 7, 7), //Slice-ler stats
            new RaceCar("Terd", 8, 8, 6)  //Terd stats
    };
    private RaceCar[] starCars = {
            new RaceCar("The Pink One", 5, 5, 5),
            new RaceCar("Reginald P. Reginald", 5, 5, 5),
            new RaceCar("Venn \"Long-Hands\" McKenzie", 5, 5,5)
    };

    //begin AI Name maker
    private String[] aiFirstNames = {"Big-Al", "Cal", "Rip", "Thomas", "Jerf", "Guy", "Eduardo", "Steven", "Keith", "Candle-Jack"};
    private String[] aiLastNames = {"Leyva", "Daytona", "Davidson", "Geraldo", "Biggins", "Havvereff", "Goblin-Hands", "Diagram", "\"Shoulder-check\" Johnson"};
    public String getRandomName()
    {
        Random rand = new Random();
        String firstName = aiFirstNames[rand.nextInt(aiFirstNames.length)];
        String lastName = aiLastNames[rand.nextInt(aiLastNames.length)];
        return firstName + " " + lastName;
    }

    Random random = new Random();
    private List<RaceTrack> raceSchedule;
    private static final int MAX_AI_PLAYERS = 8; // Maximum number of AI players
    private int aiPlayerCount; // Number of AI players
    // End Declaring Variables here

    //Start Custom Drivers


    //End Custom Drivers
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume the newline character
                    startNewGame(scanner);
                    break;
                case 2:
                    // Continue logic
                    System.out.println("Continue game selected.");
                    break;
                case 3:
                    System.out.println("Exiting the game...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void initializeRaceSchedule()
    {
        raceSchedule = new ArrayList<>();
        raceSchedule.add(new RaceTrack("Shithouse 400", 160, 0.4, 0.5));
        raceSchedule.add(new RaceTrack("Faketona 500", 250, 0.6, 0.4));

    }


    private void startNewGame(Scanner scanner) {
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.println("\nWelcome to a new world of Racing " + playerName + "...");
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Insert narrative here to describe arriving at\n" +
                " a Fantasy version of the Daytona 500.");
        System.out.println("--------------------------------------------");

        initializeRaceSchedule();

        displayCars();

        System.out.print("Select your car (enter the number): ");
        int carIndex = scanner.nextInt();
        RaceCar selectedRaceCar = cars[carIndex - 1];

        System.out.println("\nRace Schedule:");
        displayRaceSchedule();
        System.out.print("Select a race (enter the number): ");
        int raceIndex = scanner.nextInt();
        RaceTrack selectedRace = raceSchedule.get(raceIndex - 1);

        System.out.print("Enter the number of AI players (1-" + MAX_AI_PLAYERS + "): ");
        aiPlayerCount = scanner.nextInt();
        aiPlayerCount = Math.max(1, Math.min(aiPlayerCount, MAX_AI_PLAYERS));

        System.out.println("\nStarting the game for " + playerName + "...");
        System.out.println("Selected Car: " + selectedRaceCar);
        System.out.println("Track: " + selectedRace.getTrack());
        System.out.println("Laps: " + selectedRace.getLaps());
        System.out.println();

        simulateRace(selectedRace, selectedRaceCar, playerName);
    }


    private void simulateRace(RaceTrack selectedRace, RaceCar selectedRaceCar, String playerName)
    {
        // Retrieve the car's stats based on the selected car name
        int carIndex = -1;
        for (int i = 0; i < cars.length; i++) {
            if (selectedRaceCar.equals(cars[i])) {
                carIndex = i;
                break;
            }
        }

        if (carIndex < 0 || carIndex >= cars.length)
        {
            System.out.println("Invalid car selection.");
            return;
        }

// If we've made it this far, the car selection is valid. Get the selected RaceCar from the cars array
        selectedRaceCar = cars[carIndex];

// Use the properties of the RaceCar object in your calculations
        int topSpeed = selectedRaceCar.getTopSpeed();
        int acceleration = selectedRaceCar.getAcceleration();
        int handling = selectedRaceCar.getHandling();



        // Simulate the race with AI players
        List<RaceParticipant> participants = new ArrayList<>();
        //adds the players information to the RaceParticipants
        participants.add(new RaceParticipant(playerName, topSpeed, acceleration, handling, true, selectedRace.getLaps(), selectedRace));
        participants.addAll(generateAIPlayers(selectedRace.getLaps(), selectedRace)); // Pass selectedRace as a parameter

        // Sort the participants based on their race times in ascending order
        participants.sort(Comparator.comparingDouble(participant -> Double.parseDouble(participant.getRaceTime())));

        // Print the race simulation results in first to last order
        System.out.println("Track: " + selectedRace.getTrack());
        System.out.println("Laps: " + selectedRace.getLaps());
        for (int i = 0; i < participants.size(); i++)
        {
            RaceParticipant participant = participants.get(i);
            int position = i + 1;

            // this line prints results and restricts the times to 3 decimal places
            System.out.printf("%d. %s - Race Time: %.3f\n", position, participant.getCar(), Double.parseDouble(participant.getRaceTime()));
        }
    }

    private List<RaceParticipant> generateAIPlayers(int laps, RaceTrack selectedRace) {
        List<RaceParticipant> aiPlayers = new ArrayList<>();

        for (int i = 1; i <= aiPlayerCount; i++) {
            // Get a RaceCar object from the cars array using the AI player's index
            RaceCar aiRaceCar = cars[i % cars.length];

            // Generate a random name for the AI player
            String aiPlayerName = getRandomName();

            // Now we use the RaceCar object to get the AI Car's name and stats
            String aiCarName = aiRaceCar.getCarName();
            int aiTopSpeed = aiRaceCar.getTopSpeed() + random.nextInt(3) - 1;
            int aiAcceleration = aiRaceCar.getAcceleration() + random.nextInt(3) - 1;
            int aiHandling = aiRaceCar.getHandling() + random.nextInt(3) - 1;

            // We use the name and stats to create a new RaceParticipant object and add it to the list
            aiPlayers.add(new RaceParticipant(aiPlayerName, aiTopSpeed, aiAcceleration, aiHandling, false, laps, selectedRace));
        }

        return aiPlayers;
    }






    private static class RaceParticipant {
        private String car;
        private int topSpeed;
        private int acceleration;
        private int handling;
        private boolean isPlayer;
        private int laps;
        private double raceTime;
        private RaceTrack track; // new field

        public RaceParticipant(String car, int topSpeed, int acceleration, int handling, boolean isPlayer, int laps, RaceTrack track) { // new parameter
            this.car = car;
            this.topSpeed = topSpeed;
            this.acceleration = acceleration;
            this.handling = handling;
            this.isPlayer = isPlayer;
            this.laps = laps;
            this.track = track; // setting the new field
            this.raceTime = calculateRaceTime();
        }

        public RaceParticipant(String aiCar, int aiTopSpeed, int aiAcceleration, int aiHandling, boolean isPlayer, RaceTrack track) { // new parameter
            this(aiCar, aiTopSpeed, aiAcceleration, aiHandling, isPlayer, 0, track); // pass the new argumen`t
        }

        // other methods...
        public String getRaceTime() {
            return String.valueOf(raceTime);
        }
        public String getCar() {
            return car;
        }


        private double calculateRaceTime() {
            double baseTime = (handling * 10.0) / (topSpeed + acceleration);
            double trackEffect = this.track.getDifficulty() * this.track.getDanger();
            return (baseTime * this.track.getLaps()) + trackEffect;
        }
    }


    private void displayCars() {
        System.out.println("\nAvailable Cars:");
        for (int i = 0; i < cars.length; i++) {
            System.out.println((i + 1) + ". " + cars[i]);
        }
        System.out.println("--------------------------------------------");
    }
    private void displayRaceSchedule() {
        for (int i = 0; i < raceSchedule.size(); i++) {
            RaceTrack raceTrack = raceSchedule.get(i);
            System.out.println((i + 1) + ". " + raceTrack.getTrack() + " - Track: " + raceTrack.getTrack() + " - Laps: " + raceTrack.getLaps());
        }
    }

    private void displayMainMenu()
    {
        System.out.println("\nMain Menu:");
        System.out.println("1. New Game");
        System.out.println("2. Continue");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }



}