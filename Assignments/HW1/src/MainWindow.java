import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;

public class MainWindow extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private Thread textRepeaterThread;
	private String nextDisplayedString;
	private Mode mode;

	private enum Mode {
		Input,
		Display
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow("Michael Peterson - HW1");
        mainWindow.addWindowListener(
        	new WindowAdapter() {
            	public void windowClosing( WindowEvent e){
                	System.exit(0);
            	}
        	}
        );
	}
	
	/**
	 * Create the frame.
	 */
	public MainWindow(String name) {
		super(name);
		nextDisplayedString = "";
		mode = Mode.Input;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Text Area
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.addKeyListener(this);
		((DefaultCaret)textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.getCaret().setVisible(true);

		// Scroll Panel
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(textArea);

		setVisible(true);
	}
	
	public void appendToTextArea(String newLine) {
		if(newLine != null)
			textArea.append(newLine + System.lineSeparator());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Character thisCharacter = new Character(e.getKeyChar());

		if(thisCharacter.charValue() == '\n' || thisCharacter.charValue() == '\r') {
			endRepeaterThreadUsingJoin();
			if(nextDisplayedString.toLowerCase().equals("exit"))
				System.exit(0);

			textRepeaterThread = new Thread(new TextRepeater(this), nextDisplayedString);
			textRepeaterThread.start();
			mode = Mode.Display;
			nextDisplayedString = "";
		}
		else {
			if( mode == Mode.Display){
				mode = Mode.Input;
				endRepeaterThreadUsingJoin();
			}
			nextDisplayedString += thisCharacter.toString();
		}
		textArea.append(thisCharacter.toString() );
	}

	private void endRepeaterThreadUsingJoin() {
		if(textRepeaterThread != null && textRepeaterThread.getState() != Thread.State.TERMINATED) {
			try {
				textRepeaterThread.interrupt();
				textRepeaterThread.join();
			}
			catch(Exception ex)
			{}
		}
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
