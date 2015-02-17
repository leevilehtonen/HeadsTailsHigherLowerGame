package com.headstailshigherlower.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainProgram implements Runnable {

	private JFrame mJFrame;
	private static final String APP_NAME = "Heads/Tails | Higher/Lower";
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 200;
	private GameState mCurrentState = GameState.BEGIN;
	private JPanel contentArea;
	private JButton mRightBtn;
	private JButton mLeftBtn;
	private String playersChoice;
	private int streak = 0;

	@Override
	public void run() {

		mJFrame = new JFrame(APP_NAME);

		mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		mJFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		createComponents(mJFrame.getContentPane());

		mJFrame.pack();

		mJFrame.setVisible(true);

		mJFrame.setLocationRelativeTo(null);

		mCurrentState = GameState.BEGIN;
	}

	private void createComponents(Container container) {

		BorderLayout mBorderLayout = new BorderLayout();
		container.setLayout(mBorderLayout);

		mRightBtn = new JButton("Heads/Tails");
		mRightBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		container.add(mRightBtn, BorderLayout.EAST);

		mRightBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				appLogic("Right");
			}
		});

		mLeftBtn = new JButton("Higher/Lower");
		mLeftBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		container.add(mLeftBtn, BorderLayout.WEST);

		mLeftBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appLogic("Left");
			}
		});

		contentArea = new JPanel();

		beginGame();

		container.add(contentArea, BorderLayout.CENTER);

	}
	

	private void beginGame() {
		
		mCurrentState = GameState.BEGIN;
		mRightBtn.setText("Heads/Tails");
		mLeftBtn.setText("Higher/Lower");
		
		
		BoxLayout contentAreaLayout = new BoxLayout(contentArea,
				BoxLayout.Y_AXIS);
		contentArea.setLayout(contentAreaLayout);

		contentArea.add(Box.createRigidArea(new Dimension(10, 60)));

		JLabel mTopic = new JLabel("Choose game");
		mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 26));
		mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JLabel mStreak = new JLabel("Current winstreak: " +streak);
		mStreak.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		mStreak.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		contentArea.add(mTopic);
		contentArea.add(mStreak);
		
	}


	private void appLogic(String btn) {
		
		contentArea.removeAll();

		if (mCurrentState.equals(GameState.BEGIN)) {

			if (btn.equals("Left")) {
				mCurrentState = GameState.HIGHERLOWERBEGIN;
				beginHigherLowerGame();
			} else if (btn.equals("Right")) {
				mCurrentState = GameState.HEADSTAILSBEGIN;
				beginHeadsTailsGame();
			}
		} else if (mCurrentState.equals(GameState.HIGHERLOWERBEGIN)) {

			if (btn.equals("Left")) {
				mCurrentState = GameState.HIGHERLOWEREND;
				playersChoice = "HIGH";
			} else if (btn.equals("Right")) {
				mCurrentState = GameState.HIGHERLOWEREND;
				playersChoice = "LOW";
			}

			endHigherLowerGame();

		} else if (mCurrentState.equals(GameState.HEADSTAILSBEGIN)) {
			if (btn.equals("Left")) {
				mCurrentState = GameState.HEADSTAILSEND;
				playersChoice = "HEADS";
			} else if (btn.equals("Right")) {
				mCurrentState = GameState.HEADSTAILSEND;
				playersChoice = "TAILS";
			}
			
			endHeadsTailsGame();
			
		}  else if (mCurrentState.equals(GameState.HIGHERLOWEREND) || mCurrentState.equals(GameState.HEADSTAILSEND)) {
			if (btn.equals("Left")) {
				System.exit(0);
			} else if (btn.equals("Right")) {
				beginGame();
			}
		}
		
	}

	private void beginHigherLowerGame() {

		BoxLayout contentAreaLayout = new BoxLayout(contentArea,
				BoxLayout.Y_AXIS);
		contentArea.setLayout(contentAreaLayout);

		contentArea.add(Box.createRigidArea(new Dimension(10, 65)));

		JLabel mTopic = new JLabel("Pick HIGH or LOW");
		mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 26));
		mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		mRightBtn.setText("LOW");
		mLeftBtn.setText("HIGH");

		contentArea.add(mTopic);
	}

	private void beginHeadsTailsGame() {

		BoxLayout contentAreaLayout = new BoxLayout(contentArea,
				BoxLayout.Y_AXIS);
		contentArea.setLayout(contentAreaLayout);

		contentArea.add(Box.createRigidArea(new Dimension(10, 65)));

		JLabel mTopic = new JLabel("Pick HEADS or TAILS");
		mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 26));
		mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		mRightBtn.setText("TAILS");
		mLeftBtn.setText("HEADS");

		contentArea.add(mTopic);

	}

	private void endHigherLowerGame() {

		BoxLayout contentAreaLayout = new BoxLayout(contentArea,
				BoxLayout.Y_AXIS);
		contentArea.setLayout(contentAreaLayout);

		contentArea.add(Box.createRigidArea(new Dimension(10, 65)));

		int randNumber = (int) Math.round(Math.random() * 14);
		JLabel mTopic = new JLabel();

		if (randNumber < 7 && playersChoice.equals("LOW")) {
			
			mTopic.setText("You won, the number was: " + randNumber);
			mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
			mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			streak++;
			
		} else if (randNumber > 7 && playersChoice.equals("HIGH")) {
			
			mTopic.setText("You won, the number was: " + randNumber);
			mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
			mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			streak++;

		} else {
			
			mTopic.setText("You lose, the number was: " + randNumber);
			mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
			mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			streak=0;

		}
		
		JLabel mStreak = new JLabel("Current winstreak: " +streak);
		mStreak.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		mStreak.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		mRightBtn.setText("Play again");
		mLeftBtn.setText("Quit");

		contentArea.add(mTopic);
		contentArea.add(mStreak);

	}

	private void endHeadsTailsGame() {
		
		BoxLayout contentAreaLayout = new BoxLayout(contentArea,
				BoxLayout.Y_AXIS);
		contentArea.setLayout(contentAreaLayout);

		contentArea.add(Box.createRigidArea(new Dimension(10, 65)));

		double randNumber = Math.random();
		
		String side = "";	
		
		if(randNumber < 0.5) {
			side = "HEADS";
		} else {
			side = "TAILS";
		}
		
		JLabel mTopic = new JLabel();

		if (side.equals("TAILS") && playersChoice.equals("TAILS")) {
			
			mTopic.setText("You won with TAILS");
			mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
			mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			streak++;
			
		} else if (side.equals("HEADS") && playersChoice.equals("HEADS")) {
			
			mTopic.setText("You won with HEADS");
			mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
			mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			streak++;

		} else {
			mTopic.setText("You lose, you got " +side);
			mTopic.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
			mTopic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			streak=0;
		}
		
		JLabel mStreak = new JLabel("Current winstreak: " +streak);
		mStreak.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		mStreak.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		mRightBtn.setText("Play again");
		mLeftBtn.setText("Quit");

		contentArea.add(mTopic);
		contentArea.add(mStreak);

	}

}
