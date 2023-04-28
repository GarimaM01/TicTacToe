/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TicTacToeGame;

/**
 *
 * @author Meteor
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HumanVsComputer extends JFrame implements ActionListener {
private static final long serialVersionUID = 1L;
private JButton[][] buttons;
private JLabel humanScoreLabel, computerScoreLabel, tiesScoreLabel;
private JButton newGameButton, resetButton, exitButton;
private int humanScore, computerScore, tiesScore;
private boolean humanTurn;

public HumanVsComputer() {
    // Set up the JFrame
    setTitle("TicTacToe (Human VS Computer)");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500,550);
    setResizable(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    
    // Set up the score panel
    JPanel scorePanel = new JPanel();
    scorePanel.setPreferredSize(new Dimension(getWidth(), 50));
    scorePanel.setLayout(new GridLayout(1, 3));
    humanScoreLabel = new JLabel("Human: 0");
    computerScoreLabel = new JLabel("Computer: 0");
    tiesScoreLabel = new JLabel("Ties: 0");
    scorePanel.add(humanScoreLabel);
    scorePanel.add(computerScoreLabel);
    scorePanel.add(tiesScoreLabel);
    add(scorePanel, BorderLayout.NORTH);
    
    // Set up the game board
    JPanel boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(3, 3));
    buttons = new JButton[3][3];
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            buttons[i][j] = new JButton("");
            buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 55));
            buttons[i][j].addActionListener(this);
            boardPanel.add(buttons[i][j]);
        }
    }
    add(boardPanel, BorderLayout.CENTER);
    
    // Set up the control panel
    JPanel controlPanel = new JPanel();
    controlPanel.setPreferredSize(new Dimension(getWidth(), 50));
    newGameButton = new JButton("New Game");
    newGameButton.addActionListener(this);
    resetButton = new JButton("Reset Scores");
    resetButton.addActionListener(this);
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    controlPanel.add(newGameButton);
    controlPanel.add(resetButton);
    controlPanel.add(exitButton);
    add(controlPanel, BorderLayout.SOUTH);
    
    // Initialize the game
    newGame();
}

private void newGame() {
    // Clear the game board
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            buttons[i][j].setEnabled(true);
            buttons[i][j].setText("");
        }
    }
   
    humanTurn = true;
    updateScoreLabels();
}

private void resetScores() {
    // Reset the scores to 0
    humanScore = 0;
    computerScore = 0;
    tiesScore = 0;
    updateScoreLabels();
}
private JFrame frame;
private void exit() {
    // Exit the game
    if(JOptionPane.showConfirmDialog(frame,"Confrim if you want to exit", "Tic Tac Toe Game", JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_NO_OPTION){
            setVisible(false);
            new TicTacToeGame().setVisible(true);
        }
}

private void updateScoreLabels() {
    // Update the score labels
    humanScoreLabel.setText("Human: " + humanScore);
    computerScoreLabel.setText("Computer: " + computerScore);
    tiesScoreLabel.setText("Ties: " + tiesScore);

}
private void makeComputerMove() {
    // Generate a random move for the computer
    Random rand = new Random();
    int row, col;
    do {
        row = rand.nextInt(3);
        col = rand.nextInt(3);
    } while(!buttons[row][col].isEnabled());
    buttons[row][col].setText("O");
    buttons[row][col].setForeground(Color.BLUE);
    buttons[row][col].setEnabled(false);
    checkForEndOfGame();
}

private void checkForEndOfGame() {
    // Check if there is a winner or a tie
    boolean gameEnded = false;
    String winner = "";
    // Check rows
    for(int i = 0; i < 3; i++) {
        if(buttons[i][0].getText().equals(buttons[i][1].getText()) && 
                buttons[i][1].getText().equals(buttons[i][2].getText()) 
                && !buttons[i][0].isEnabled()) {
            gameEnded = true;
            winner = buttons[i][0].getText();
            
            break;
        }
    }
    // Check columns
    for(int i = 0; i < 3; i++) {
        if(buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) 
                && !buttons[0][i].isEnabled()) {
        gameEnded = true;
        winner = buttons[0][i].getText();
        break;
        }
    }
    
    // Check diagonals
    if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].isEnabled()) {
        gameEnded = true;
        winner = buttons[0][0].getText();
        }
    if(buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].isEnabled()) {
        gameEnded = true;
        winner = buttons[0][2].getText();
        }
    

    // Check for a tie
    if(!gameEnded) {
        boolean allButtonsDisabled = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(buttons[i][j].isEnabled()) {
                    allButtonsDisabled = false;
                    break;
                }
            }
        }
        if(allButtonsDisabled) {
            gameEnded = true;
        }
    }


    // Handle end of game
    if(gameEnded) {
    // Disable all buttons
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            buttons[i][j].setEnabled(false);
        }
    }
    

    // Update scores and display winner
    if(winner.equals("X")) {
        humanScore++;
        JOptionPane.showMessageDialog(this, "You won the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    } else if(winner.equals("O")) {
        computerScore++;
        JOptionPane.showMessageDialog(this, "Computer won the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    } else {
        tiesScore++;
        JOptionPane.showMessageDialog(this, "The game is Tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
        updateScoreLabels();
    } else {
            // Switch to other player's turn
            humanTurn = !humanTurn;
            if(!humanTurn) {
            makeComputerMove();
            }
        }
    }
@Override
public void actionPerformed(ActionEvent e) {
    // Handle button clicks
    Object source = e.getSource();
    if(source == newGameButton) {
        newGame();
    } else if(source == resetButton) {
        resetScores();
    } else if(source == exitButton) {
        exit();
    } else {
        // A game board button was clicked
        JButton button = (JButton)source;
        if(button.isEnabled()) {
            if(humanTurn) {
                button.setText("X");
                button.setForeground(Color.ORANGE);
                
            } else {
                button.setText("O");
                button.setForeground(Color.ORANGE);
            }
            button.setEnabled(false);
            checkForEndOfGame();
        }
    }
}




//public static void main(String[] args) {
//    TicTacToe game = new TicTacToe();
//    game.setVisible(true);
//}



    /**
     * Creates new form tt1
     */
//    public HumanVsComputer() {
//        initComponents();
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HumanVsComputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HumanVsComputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HumanVsComputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HumanVsComputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HumanVsComputer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
