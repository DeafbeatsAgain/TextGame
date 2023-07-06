import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TextGame {
    private static final String[] cars = {"Lamber-genie", "Ter-berda", "Slice-ler", "Terd"};
    private static final int[][] carStats = {
            {15, 17, 25}, //Lamber-genie stats
            {16, 12, 20}, //Ter-Berda stats
            {15, 10, 30}, //Slice-ler stats
            {14, 16, 21}  //Terd stats
    };
    private double calculateTotalTime(double lapTime, int numLaps) {
        return lapTime * numLaps;
    }
    private List<Race> raceSchedule;

    public static void main(String[] args) {
        TextGame championship = new TextGame();
        championship.initializeRaceSchedule();
        championship.start();
    }

    public void initializeRaceSchedule() {
        // Initialize the race schedule with sample races
        raceSchedule = new ArrayList<>();
        raceSchedule.add(new Race("Shithouse 400", "Ding-Dong Speedway, FL", 160));
        raceSchedule.add(new Race("Race 2", "Track 2", 3));
        // Add more races to the schedule as needed
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        JFrame frame = new JFrame("Hello, my name is Car Man");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());

        JTextArea results = new JTextArea();

        JPanel carNamePanel = new JPanel();
        carNamePanel.setLayout(new FlowLayout());
        JLabel carNameLabel = new JLabel("Car Name");
        JTextField carNameInput = new JTextField(10);
        carNamePanel.add(carNameLabel);
        carNamePanel.add(carNameInput);

        JPanel carColorPanel = new JPanel();
        carColorPanel.setLayout(new FlowLayout());
        JLabel carColor = new JLabel("Car Color");
        JTextField carColorInput = new JTextField(10);
        carColorPanel.add(carColor);
        carColorPanel.add(carColorInput);

        JPanel buttonPanel =  new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton okButton = new JButton("Shoop");
        okButton.addActionListener(actionEvent -> {
            results.append("Boom Poop Shoop Da woop\n");
        });
        JButton noButton = new JButton("Delete");
        noButton.addActionListener(actionEvent -> {
            results.setText("");
        });
        buttonPanel.add(okButton);
        buttonPanel.add(noButton);

        buttonPanel.add(results);

        panel.add(carNamePanel);
        panel.add(carColorPanel);
        panel.add(buttonPanel);
        frame.add(panel);
        frame.setSize(1200,720);
        frame.setVisible(true);

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> startNewGame(scanner);
                case 2 ->
                    // Continue logic
                        System.out.println("Continue game selected.");
                case 3 -> {
                    System.out.println("Exiting the game...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }


    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. New Game");
        System.out.println("2. Continue");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void startNewGame(Scanner scanner) {
        // Display available cars
        displayCars();

        // Select a car for the player
        System.out.print("Select your car (enter the number): ");
        int carIndex = scanner.nextInt();
        String selectedCar = cars[carIndex - 1];

        // Select a race from the schedule
        System.out.println("\nRace Schedule:");
        displayRaceSchedule();
        System.out.print("Select a race (enter the number): ");
        int raceIndex = scanner.nextInt();
        Race selectedRace = raceSchedule.get(raceIndex - 1);

        System.out.println("\nStarting a new game...");
        System.out.println("Selected Car: " + selectedCar);
        System.out.println("Selected Race: " + selectedRace.getName());
        System.out.println("Track: " + selectedRace.getTrack());
        System.out.println("Laps: " + selectedRace.getLaps());
        System.out.println();

        simulateRace(selectedRace, selectedCar);
    }

    private void displayCars() {
        System.out.println("\nAvailable Cars:");
        for (int i = 0; i < cars.length; i++) {
            System.out.println((i + 1) + ". " + cars[i]);
        }
        System.out.println("----------------------");
    }

    private void displayRaceSchedule() {
        for (int i = 0; i < raceSchedule.size(); i++) {
            Race race = raceSchedule.get(i);
            System.out.println((i + 1) + ". " + race.getName() + " - Track: " + race.getTrack() + " - Laps: " + race.getLaps());
        }
    }

    private void simulateRace(Race race, String selectedCar) {
        int numLaps = race.getLaps();

        // Get the index of the selected car
        int selectedCarIndex = Arrays.asList(cars).indexOf(selectedCar);

        // Get the car stats from the carStats array
        int engineScore = carStats[selectedCarIndex][0];
        int tireScore = carStats[selectedCarIndex][1];
        int handlingScore = carStats[selectedCarIndex][2];

        // Simulate lap times and calculate the total time for each car
        double[] lapTimes = new double[cars.length];
        double[] totalTimes = new double[cars.length];
        for (int i = 0; i < cars.length; i++) {
            int carEngineScore = carStats[i][0];
            int carTireScore = carStats[i][1];
            int carHandlingScore = carStats[i][2];

            lapTimes[i] = simulateLapTimes(numLaps, carEngineScore, carTireScore, carHandlingScore);
            totalTimes[i] = calculateTotalTime(lapTimes[i], numLaps);
        }

        // Determine the sorted order of the cars based on total times
        int[] sortedIndices = getSortedIndices(totalTimes);

        // Display the race results in order of fastest to slowest total times
        System.out.println("========== Race Results ==========");
        for (int i = 0; i < cars.length; i++) {
            String car = cars[sortedIndices[i]];
            double lapTime = lapTimes[sortedIndices[i]];
            double totalTime = totalTimes[sortedIndices[i]];
            String result = (i == 0) ? "Winner" : "Loser";
            System.out.printf("%s: %s - Lap Time: %.2f seconds - Total Time: %.2f seconds\n", car, result, lapTime, totalTime);
        }
        System.out.println("===============================");
    }

    private double simulateLapTimes(int numLaps, int engineScore, int tireScore, int handlingScore) {
        double totalLapTime = 0.0;
        for (int lap = 0; lap < numLaps; lap++) {
            double lapTime = calculateLapTime(engineScore, tireScore, handlingScore);
            totalLapTime += lapTime;
        }
        return totalLapTime;
    }


    private int[] getSortedIndices(double[] lapTimes) {
        int[] indices = new int[lapTimes.length];
        for (int i = 0; i < lapTimes.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices);
        return indices;
    }

    private double calculateLapTime(int engineScore, int tireScore, int handlingScore) {
        double baseLapTime = 10.0; // Some base lap time value
        double engineFactor = 1.0 + (engineScore / 100.0);
        double tireFactor = 1.0 + (tireScore / 100.0);
        double handlingFactor = 1.0 + (handlingScore / 100.0);

        // Introduce a random variation between -0.5 and 0.5 seconds
        double randomVariation = Math.random() - 0.5;

        return (baseLapTime / (engineFactor * tireFactor * handlingFactor)) + randomVariation;
    }
}

class Race {
    private String name;
    private String track;
    private int laps;

    public Race(String name, String track, int laps) {
        this.name = name;
        this.track = track;
        this.laps = laps;
    }

    public String getName() {
        return name;
    }

    public String getTrack() {
        return track;
    }

    public int getLaps() {
        return laps;
    }
}
