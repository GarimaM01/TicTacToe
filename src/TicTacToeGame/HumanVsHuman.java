/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TicTacToeGame;

/**
 *
 * @author Meteor
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HumanVsHuman extends JFrame {

    private JButton[][] buttons;
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;
    private JLabel tieScoreLabel;
    private int player1Score = 0;
    private int player2Score = 0;
    private int tieScore = 0;
    private boolean playerX = true;
    private boolean gameOver = false;

    public HumanVsHuman() {
        // Set up the frame
        super("TicTacToe (Human VS Human)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 550));
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up the score panel
        JPanel scorePanel = new JPanel();
        scorePanel.setPreferredSize(new Dimension(getWidth(), 50));
        scorePanel.setLayout(new GridLayout(1, 3));
        player1ScoreLabel = new JLabel("Player 1: " + player1Score);
        player2ScoreLabel = new JLabel("Player 2: " + player2Score);
        tieScoreLabel = new JLabel("Ties: " + tieScore);
        scorePanel.add(player1ScoreLabel);
        scorePanel.add(player2ScoreLabel);
        scorePanel.add(tieScoreLabel);
        add(scorePanel, BorderLayout.NORTH);

        // Set up the board panel
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 55));
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!gameOver) {
                            JButton button = (JButton) e.getSource();
                            if (button.getText().equals("")) {
                                if (playerX) {
                                    button.setText("X");
                                    button.setForeground(Color.ORANGE);
                                } else {
                                    button.setText("O");
                                    button.setForeground(Color.BLUE);
                                }
                                playerX = !playerX;
                                checkWin();
                            }
                        }
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Set up the control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        JButton resetButton = new JButton("Reset Scores");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitGame();
            }
        });
        controlPanel.add(newGameButton);
        controlPanel.add(resetButton);
        controlPanel.add(exitButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Finalize the frame
        pack();
        setVisible(true);
    }

    private void checkWin() {
        // Check horizontal win
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") && 
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
buttons[i][1].getText().equals(buttons[i][2].getText())) {
endGame(buttons[i][0].getText());
return;
}
}
            // Check vertical win
    for (int i = 0; i < 3; i++) {
        if (!buttons[0][i].getText().equals("") && 
            buttons[0][i].getText().equals(buttons[1][i].getText()) && 
            buttons[1][i].getText().equals(buttons[2][i].getText())) {
            endGame(buttons[0][i].getText());
            return;
        }
    }

    // Check diagonal win
    if (!buttons[0][0].getText().equals("") && 
        buttons[0][0].getText().equals(buttons[1][1].getText()) && 
        buttons[1][1].getText().equals(buttons[2][2].getText())) {
        endGame(buttons[0][0].getText());
        return;
    }
    if (!buttons[0][2].getText().equals("") && 
        buttons[0][2].getText().equals(buttons[1][1].getText()) && 
        buttons[1][1].getText().equals(buttons[2][0].getText())) {
        endGame(buttons[0][2].getText());
        return;
    }

    // Check tie
    boolean tie = true;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (buttons[i][j].getText().equals("")) {
                tie = false;
                break;
            }
        }
        if (!tie) {
            break;
        }
    }
    if (tie) {
        endGame("");
        return;
    }
}

private void endGame(String winner) {
    if (winner.equals("X")) {
        player1Score++;
        player1ScoreLabel.setText("Player 1: " + player1Score);
        JOptionPane.showMessageDialog(this, "Player 1 wins!");
    } else if (winner.equals("O")) {
        player2Score++;
        player2ScoreLabel.setText("Player 2: " + player2Score);
        JOptionPane.showMessageDialog(this, "Player 2 wins!");
    } else {
        tieScore++;
        tieScoreLabel.setText("Tie: " + tieScore);
        JOptionPane.showMessageDialog(this, "Tie game!");
    }
    gameOver = true;
}

private void newGame() {
    playerX = true;
    gameOver = false;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            buttons[i][j].setText("");
        }
    }
}

private void resetGame() {
    player1Score = 0;
    player2Score = 0;
    tieScore = 0;
    player1ScoreLabel.setText("Player 1: " + player1Score);
    player2ScoreLabel.setText("Player 2: " + player2Score);
    tieScoreLabel.setText("Tie: " + tieScore);
    newGame();
}

private void exitGame() {
    int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the game?", "Exit Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (choice == JOptionPane.YES_OPTION) {
        setVisible(false);
        new TicTacToeGame().setVisible(true);
    }
}

//public static void main(String[] args) {
//    new TicTacToeGame();
//}



    /**
     * Creates new form tt5
     */
//    public HumanVsHuman() {
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
            java.util.logging.Logger.getLogger(HumanVsHuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HumanVsHuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HumanVsHuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HumanVsHuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HumanVsHuman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
