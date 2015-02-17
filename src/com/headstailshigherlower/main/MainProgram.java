package com.headstailshigherlower.main;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class MainProgram implements Runnable{
	
	private JFrame mJFrame;
	private static final String APP_NAME = "Heads/Tails | Higher/Lower";
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 200;


	@Override
	public void run() {
		
		mJFrame = new JFrame(APP_NAME);
		
		mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mJFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		createComponents(mJFrame.getContentPane());
		
		mJFrame.pack();
		
		mJFrame.setVisible(true);
		
		mJFrame.setLocationRelativeTo(null);
	}


	private void createComponents(Container container) {
		// TODO Auto-generated method stub
		
	}

}
