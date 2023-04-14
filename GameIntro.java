// Import necessary libraries
import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class GameIntro {
    // Declare instance variables for GUI components
    private JFrame mainFrame, gameFrame, instructionsFrame;
    private JButton playButton, instructionsButton, quitButton, backButton, checkButton, giveupButton;
    private JPanel mainPanel, gamePanel, instructionsPanel, guessPanel;
    private JTextField guessField;
    private JLabel guessLabel;


    // Create a random object to generate a random number between 0 and 100
    Random rand = new Random();
    private int answer = rand.nextInt(0,101);


    // Constructor for the GameIntro class
    public GameIntro() {
        // Set up main frame
        mainFrame = new JFrame("Game Introduction");
        mainFrame.setSize(500, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up main panel 
        mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Set up play button and add it to the panel
        playButton = new JButton("Play");
        mainPanel.add(playButton);

        // Add action listener using lambda function to show the game window when the play button is clicked
        playButton.addActionListener(e -> showGameWindow());

        // Set up instructions button and add it to the panel
        instructionsButton = new JButton("Instructions");
        mainPanel.add(instructionsButton);

        // Add action listener using lambda function to show the instructions window when the instructions button is clicked
        instructionsButton.addActionListener(e -> showInstructionsWindow());

        // Set up quit button and add it to the panel
        quitButton = new JButton("Quit");
        mainPanel.add(quitButton);

        // Add action listener using lambda function to quit the program when the quit button is clicked
        quitButton.addActionListener(e -> System.exit(0));

        // Add main panel to the frame and make it visible
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
 // Method to show the game window
 private void showGameWindow() {

    // Create game Frame and set its properties
    gameFrame = new JFrame("Guess the Number Game");
    gameFrame.setSize(400, 150);
    gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    gameFrame.setLocationRelativeTo(null);

    // Create game panel 
    gamePanel = new JPanel();

    // Create guess panel and set layout
    guessPanel = new JPanel(new FlowLayout());

    // Create guess label and add it to guess panel
    guessLabel = new JLabel("Enter your guess (0-100): ");
    guessPanel.add(guessLabel);

    // Create guess text field and add it to guess panel
    guessField = new JTextField(5);
    guessPanel.add(guessField);

    // Create check button and add it to the guessPanel 
    checkButton = new JButton("Check");
    guessPanel.add(checkButton);

   // Add action listener using lambda function to call checkGuess() function when button is clicked
    checkButton.addActionListener(e -> checkGuess());

    // Add guess panel to game panel
    gamePanel.add(guessPanel, BorderLayout.CENTER);

    // Create back button and add action listener to close game window and show main window
    backButton = new JButton("Back");
    // Set it's location to the bottom of the panel
    gamePanel.add(backButton, BorderLayout.SOUTH);

     // Add action listener using lambda function dispose of frame and show main frame when the back button is pressed

    backButton.addActionListener(e -> {
        gameFrame.dispose();
        mainFrame.setVisible(true);
    });

    giveupButton = new JButton("Give Up");
    // Set it's location to the bottom of the panel
    gamePanel.add(giveupButton, BorderLayout.SOUTH);

    giveupButton.addActionListener(e -> giveUp());

    // Add game panel to game frame
    gameFrame.add(gamePanel);
    gameFrame.setVisible(true);
}


// Method to show instructions
private void showInstructionsWindow() {
    // Create new frame for instructions
    JFrame instructionsFrame = new JFrame("Instructions");
    instructionsFrame.setSize(300, 150);

    // Create new instructions panel for instructions
    JPanel instructionsPanel = new JPanel(new BorderLayout());    
    // Create label with instructions. Add some HTML to it to make it a certain size
    JLabel instructionsLabel = new JLabel("<html><body style='width: 225px;'>Guess a number between 1-100. You have unlimited guesses. " +
            "</body></html>");

    //Center the label on the Instructions panel
    instructionsPanel.add(instructionsLabel, BorderLayout.CENTER);

    // Create back button to go back to main menu
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> instructionsFrame.dispose());
    instructionsPanel.add(backButton, BorderLayout.SOUTH);

    // Add instructions panel to instructions frame
    instructionsFrame.add(instructionsPanel);

    instructionsFrame.setVisible(true);
}

// Method to check the user's guess
private void checkGuess() {
try {
    // Parse the integer value of the user's guess from the guess field
    int guess = Integer.parseInt(guessField.getText());

    // Check if the guess is out of range
    if (guess < 1 || guess > 100) {
        // Display an error message and clear the guess field
        JOptionPane.showMessageDialog(gameFrame, "Please enter a number between 1-100.");
        guessField.setText("");
    }
    // Check if the guess is correct
    else if (guess == answer) {
        // Say that the user got it right and give an option for the user to play again.
        int option = JOptionPane.showConfirmDialog(gameFrame, "You got it! The answer is " + answer + ".\nDo you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);

        // Check if the user wants to play again
        if (option == 0) {
            // Generate a new random answer and clear the guess field
            Random rand = new Random();
            answer = rand.nextInt(101);
            guessField.setText("");
        } else {
            // Close the game window and show the main window
            gameFrame.dispose();
            mainFrame.setVisible(true);
        }
    }
    // Check if the guess is too low
    else if (guess < answer) {
        // Display a message saying the guess is too low and clear the guess field
        JOptionPane.showMessageDialog(gameFrame, "Too low! Try again.");
        guessField.setText("");
    }

    else {
        // Display a message saying the guess is too high and clear the guess field
        JOptionPane.showMessageDialog(gameFrame, "Too high! Try again.");
        guessField.setText("");
    }
} catch (NumberFormatException ex) {
    // Display an error message for an invalid guess(like 9s) and clear the guess field
    JOptionPane.showMessageDialog(gameFrame, "Please enter a valid number.");
    guessField.setText("");
}
}

// Method to run if the user clicks the Giveup Button
private void giveUp() {
    JOptionPane.showMessageDialog(gameFrame, "The answer was " + answer + ".");
    
    // prompt the user to play again or quit
    int option = JOptionPane.showConfirmDialog(gameFrame, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
    
    // if the user wants to play again, set up a new game
    if (option == 0) {
        answer = rand.nextInt(101); // set a new random answer
        guessField.setText(""); // clear the guess field
    
    // if the user wants to quit, close the game window and show the main window
    } else {
        gameFrame.dispose(); // close the game window
        mainFrame.setVisible(true); // show the main window
    }
}

// Method that runs the program
public static void main(String[] args) {
    new GameIntro();
}
        
}

