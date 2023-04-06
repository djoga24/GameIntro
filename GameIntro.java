import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameIntro {
    private JFrame mainFrame, gameFrame, instructionsFrame;
    private JButton playButton, instructionsButton, quitButton, backButton, checkButton;
    private JPanel mainPanel, gamePanel, instructionsPanel, guessPanel;
    private JTextField guessField;
    private JLabel guessLabel;

    public GameIntro() {
        // Set up main frame
        mainFrame = new JFrame("Game Introduction");
        mainFrame.setSize(500, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up main panel
        mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Set up play button
        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGameWindow();
            }
        });
        mainPanel.add(playButton);

        // Set up instructions button
        instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInstructionsWindow();
            }
        });
        mainPanel.add(instructionsButton);

        // Set up quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainPanel.add(quitButton);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void showGameWindow() {
        // Set up game frame
        gameFrame = new JFrame("Game");
        gameFrame.setSize(300, 201);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set up game panel
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set up guess panel
        guessPanel = new JPanel(new FlowLayout());
        guessLabel = new JLabel("Guess a number between 1-100:");
        guessField = new JTextField(10);
        guessPanel.add(guessLabel);
        guessPanel.add(guessField);
        checkButton = new JButton("Check");
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    if (guess < 1 || guess > 100) {
                        JOptionPane.showMessageDialog(gameFrame, "Please enter a number between 1-100.");
                    } else if (guess == 42) {
                        JOptionPane.showMessageDialog(gameFrame, "You got it! The answer is 42.");
                    } else if (guess < 42) {
                        JOptionPane.showMessageDialog(gameFrame, "Too low! Try again.");
                    } else {
                        JOptionPane.showMessageDialog(gameFrame, "Too high! Try again.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(gameFrame, "Please enter a valid number.");
                }
            }
        });
        guessPanel.add(checkButton);

        // Set up back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();
            }
        });
        gamePanel.add(backButton, BorderLayout.NORTH);
        gamePanel.add(guessPanel, BorderLayout.CENTER);

        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
    }
        private void showInstructionsWindow() {
            // Create new frame for instructions
            JFrame instructionsFrame = new JFrame("Instructions");
            instructionsFrame.setSize(300, 150);
        
            // Create new panel for instructions
            JPanel instructionsPanel = new JPanel(new BorderLayout());
            instructionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
            // Create label with instructions
            JLabel instructionsLabel = new JLabel("<html><body style='width: 200px;'>Guess a number between 1-100. You have unlimited guesses. " +
                    "The answer is 42. Good luck!</body></html>");
            instructionsPanel.add(instructionsLabel, BorderLayout.CENTER);
        
            // Create back button to go back to main menu
            JButton backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    instructionsFrame.dispose();
                }
            });
            instructionsPanel.add(backButton, BorderLayout.SOUTH);
        
            // Add instructions panel to instructions frame
            instructionsFrame.add(instructionsPanel);
        
            // Set visibility of instructions frame to true
            instructionsFrame.setVisible(true);
        }

        public static void main(String[] args) {
            new GameIntro();
        }
    }
    
