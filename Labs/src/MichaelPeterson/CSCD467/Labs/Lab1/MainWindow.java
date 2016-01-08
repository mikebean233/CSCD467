package MichaelPeterson.CSCD467.Labs.Lab1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class MainWindow extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private Thread thread1;
	private Thread thread2;
	private int noKeysPressed;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
	
		textArea.addKeyListener(this);
		
		// create and kick off the two new threads
		thread1 = new Thread(new SecondaryThreadRunner(this), "Thread-1");
		thread2 = new Thread(new SecondaryThreadRunner(this), "Thread-2");
		thread1.start();
		thread2.start();
		
		noKeysPressed = 0;
	}
	
	public void appendToTextArea(String newLine)
	{
		if(newLine != null)
			textArea.append(newLine + System.lineSeparator());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		textArea.append(new Character(e.getKeyChar()).toString() );
		++noKeysPressed;
		if(noKeysPressed == 1)
			thread1.interrupt();
		else if(noKeysPressed == 2)
			thread2.interrupt();
		else if(noKeysPressed == 3)
			appendToTextArea("All threads are interrupted!");		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
