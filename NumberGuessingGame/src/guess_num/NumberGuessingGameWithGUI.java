package guess_num;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class NumberGuessingGameWithGUI implements ActionListener{

	Random random = new Random();
	
	private int round = 1;
	private int attempts;
	private int totalScore= 0;
	private int MaxAttempts = 5;
	private int randomNumber;
	
	JFrame frame;
	JLabel title;
	JLabel roundLabel;
	JLabel guideLabel;
	JLabel feedbackLabel;
	JLabel feedbackLabel2;
	JLabel scoreLabel;
	JButton button;
	JTextField Text;
	JTextField guessField;
	GridBagConstraints gbc;
	public NumberGuessingGameWithGUI() {
		frame = new JFrame("Number Guessing Game");
		
		
		title = new JLabel(":WELCOME TO THE NUMBER GUESSING GAME:", JLabel.CENTER);
		Border border = title.getBorder();
		Border margin = new EmptyBorder(30,0,0,0);
		title.setBorder(new CompoundBorder(border, margin));
		title.setSize(400,200);
		title.setVerticalAlignment(JLabel.TOP);
		frame.add(title);
		
		roundLabel = new JLabel("ROUND: "+round, SwingConstants.CENTER);
		roundLabel.setBounds(158, 40, 70, 100);
		roundLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.add(roundLabel);
		
		guideLabel = new JLabel("Guess the number (1-100)",SwingConstants.CENTER);
		guideLabel.setBounds(10, 70, 370, 100);
		frame.add(guideLabel);
		
		Text = new JTextField();
		Text.setBounds(150, 140, 90, 80);
		Text.setHorizontalAlignment(JTextField.CENTER);
		Font font = new Font("SansSerif", Font.BOLD, 50);
		Text.setFont(font);
		frame.add(Text);
		
		button = new JButton("Check Your Number");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentY(Component.CENTER_ALIGNMENT);
		button.setBackground(Color.GREEN);
		button.addActionListener(this);
		button.setBounds(110, 240, 180, 30);
		button.setMnemonic(KeyEvent.VK_ENTER);
		button.setSize(180, 30);
		frame.add(button);
		
		//Give Feedback according to your Guess
		feedbackLabel = new JLabel("", SwingConstants.CENTER);
		feedbackLabel.setBounds(50, 170, 300, 300);
		frame.add(feedbackLabel);
		
		feedbackLabel2 = new JLabel("", SwingConstants.CENTER);
		feedbackLabel2.setBounds(50, 220, 300, 300);
		frame.add(feedbackLabel2);
        
		//Show score
        scoreLabel = new JLabel("Total score: " + totalScore, SwingConstants.CENTER);
        Border SBorder = scoreLabel.getBorder();
		Border Smargin = new EmptyBorder(0,0,80,0);
		scoreLabel.setBorder(new CompoundBorder(SBorder, Smargin));;
        scoreLabel.setVerticalAlignment(JLabel.BOTTOM);
        fontSize();
        frame.add(scoreLabel);
        
        Container c = frame.getContentPane();
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        
        //For Grid Layout
		/*c.add(title);
		c.add(roundLabel);
		c.add(guideLabel);
		c.add(Text);
		c.add(button);
		c.add(feedbackLabel);
		c.add(feedbackLabel2);
		c.add(scoreLabel);
		c.setLayout(new GridLayout(8, 1));*/
		
		frame.pack();
		frame.setSize(400,600);
		c.setBackground(Color.orange);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		generateRandomNumber();
	}
	
	private void generateRandomNumber() {
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
    }
	
	public void nextRound() {
		round++;
		roundLabel.setText("ROUND: "+round);
		feedbackLabel.setText("");
		generateRandomNumber();
	}
	
	public void fontSize() {
		Font font = new Font("SansSerif", Font.BOLD, 50);
		feedbackLabel.setFont(font);
//		Font font2 = new Font("SansSerif", Font.BOLD, 30);
//		feedbackLabel2.setFont(font2);
		Font font3 = new Font("SansSerif", Font.BOLD, 30);
		scoreLabel.setFont(font3);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int guess = Integer.parseInt(Text.getText());
			attempts++;
			
			if(guess > randomNumber) {
				feedbackLabel.setText("Too High!");
				feedbackLabel2.setText("Guess Again.");
			}
			else if(guess < randomNumber) {
				feedbackLabel.setText("Too Low!");
				feedbackLabel2.setText("Guess Again.");
			}
			else {
				int score = (MaxAttempts-attempts+1)*20;
				totalScore += score;
				scoreLabel.setText("Total score: " + totalScore);
				feedbackLabel2.setText("Correct! You've guessed the number in " + attempts + " attempts.");
                JOptionPane.showConfirmDialog(null,  "Congratulations! You've guessed the number (" + randomNumber + ") correctly in " + attempts+ " attempts. You scored " + score + " points this round." );
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to play another round?", "Next Round", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION)
                	nextRound();
                else
                	System.exit(0);
			}
			
			 if (attempts == MaxAttempts && !feedbackLabel2.getText().contains("Correct")) {
	                feedbackLabel2.setText("Maximum attempts reached. The number was " + randomNumber);
	                JOptionPane.showMessageDialog(frame, "You've reached the maximum number of attempts. The correct number was " + randomNumber);
	                int choice = JOptionPane.showConfirmDialog(frame, "Do you want to play another round?", "Next Round", JOptionPane.YES_NO_OPTION);
	                if (choice == JOptionPane.YES_OPTION) {
	                    nextRound();
	                } else {
	                    System.exit(0);
	                }
	            }
			
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null,"Invalid Input! Please enter a number between 1 and 100");
		}
		
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "You have to guess a number between 0 to 100 in 5 attempts, \nyou will get 20 points for each remaining attempt!");
		new NumberGuessingGameWithGUI();
	}

	
	
}
class ExitListener extends WindowAdapter {
	public void windoClosing() {
		System.exit(0);
	}
}
