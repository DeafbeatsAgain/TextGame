import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextGameUI {
    private static final String WINDOW_NAME = "Deaf's Game";
    private static final String[] CARS = { "Lamber-genie", "Ter-berda", "Slice-ler", "Terd" };

    private final List<Race> raceTracks;

    private final JFrame mainFrame = new JFrame(WINDOW_NAME);
    private final JPanel startPanel = new JPanel();

    public TextGameUI() {
        raceTracks = new ArrayList<>();
        raceTracks.add(new Race("Shithouse 400", "Ding-Dong Speedway, FL", 160));
        raceTracks.add(new Race("Race 2", "Track 2", 3));


        setupMainMenu();
        mainFrame.add(startPanel);
        mainFrame.setSize(800,600);
        mainFrame.setVisible(true);
    }

    private void setupMainMenu() {
        startPanel.setLayout(new FlowLayout());
        JButton startButton = new JButton("New Game");
        startButton.addActionListener(actionEvent -> {
            handleNewGame();
        });
        JButton continueButton = new JButton("Continue Game");
        continueButton.addActionListener(actionEvent -> {
            handleContinueGame();
        });
        JButton exitButton = new JButton("Close Game");
        exitButton.addActionListener(actionEvent -> {
            handleCloseGame();
        });
        startPanel.add(startButton);
        startPanel.add(continueButton);
        startPanel.add(exitButton);
        startPanel.setVisible(true);
    }

    private void handleNewGame() {
        // display cars
        JPanel carPanel = new JPanel();
        carPanel.setLayout(new FlowLayout());

        JLabel carLabel = new JLabel("Select Car: ");
        carPanel.setVisible(true);
        JComboBox<String> carSelection = new JComboBox(CARS);
        carSelection.setVisible(true);
        carPanel.add(carLabel);
        carPanel.add(carSelection);

        JLabel trackLabel = new JLabel("Select Track: ");
        trackLabel.setVisible(true);
        JComboBox<String> trackSelection = new JComboBox(raceTracks.toArray());
        trackSelection.setVisible(true);
        carPanel.add(trackLabel);
        carPanel.add(trackSelection);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(actionEvent -> {
            handleStartGame();
        });
        carPanel.setVisible(true);
        carPanel.add(startButton);
        this.mainFrame.add(carPanel);
    }

    private void handleContinueGame() {

    }

    private void handleCloseGame() {

    }

    private void handleStartGame() {

    }
}
