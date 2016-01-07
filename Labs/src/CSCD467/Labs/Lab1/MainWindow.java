package CSCD467.Labs.Lab1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	
	public void appendToTextArea(String newLine)
	{
		if(newLine != null)
			textArea.append(newLine);
	}

}
