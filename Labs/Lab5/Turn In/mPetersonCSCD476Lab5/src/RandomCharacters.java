
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RandomCharacters extends JApplet implements Runnable, 
														 ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private JLabel outputs[];
    private Container c;
	private JCheckBox checkboxes[];
	private JButton stopButton;
	private JLabel suspendStatus;
	private final static int SIZE = 10;
   
	private Thread threads[];
	private boolean suspended[];

	public Container getContainer(){
		return c;
	}                                                        

	//add JButton
	public void init() {
		outputs = new JLabel[ SIZE ];
		checkboxes = new JCheckBox[ SIZE ];
		threads = new Thread[ SIZE ];
		stopButton = new JButton("STOP");
		suspendStatus = new JLabel();
		suspended = new boolean[ SIZE ];
		//

		c = getContentPane();
		c.setLayout( new GridLayout( SIZE + 1, 2, 10, 10 ) );

		for ( int i = 0; i < SIZE; i++ ) {
			outputs[ i ] = new JLabel();
			outputs[ i ].setBackground( Color.green );
			outputs[ i ].setOpaque( true );
			c.add( outputs[ i ] );

			checkboxes[ i ] = new JCheckBox( "Suspended" );
			checkboxes[ i ].addActionListener( this );
			c.add( checkboxes[ i ] );
		}
		suspendStatus.setBackground(Color.lightGray);
		suspendStatus.setOpaque(true);
		c.add(suspendStatus);
		updateSuspendedStatusLabel();

		// Wire the event listener to the button and add it to the content Pane
		stopButton.addActionListener(this);
		c.add(stopButton);

	}//end of init

	public void start()
	{
		// create threads and start every time start is called
		for ( int i = 0; i < threads.length; i++ ) {
			threads[ i ] =
            new Thread( this, "Thread " + (i + 1) );
			threads[ i ].start();
		}
	}//end of start

	public void run()
	{
		Thread currentThread = Thread.currentThread();
		int index = getIndex( currentThread );
		char displayChar;

		while ( threads[ index ] == currentThread ) {
			// sleep from 0 to 1 second
			try {
				Thread.sleep( (int) ( Math.random() * 1000 ) );
				synchronized( this ) {
					while ( suspended[ index ]  && threads[ index ] == currentThread )
						wait();
				}
			}
			catch ( InterruptedException e ) {
				System.err.println( "sleep interrupted" );
			}
         
			displayChar = alphabet.charAt((int) ( Math.random() * 26 ) );
			outputs[ index ].setText( currentThread.getName() + ": " + displayChar );
		}//end of while
		System.err.println( currentThread.getName() + " terminating" );
	}
	
	private int getIndex( Thread current )
	{
		for ( int i = 0; i < threads.length; i++ )
			if ( current == threads[ i ] )
				return i;

		return -1; 
	}

	public synchronized void stop()
	{
		// stop threads every time stop is called
		// as the user browses another Web page
		for ( int i = 0; i < threads.length; i++ ) 
			threads[ i ] = null;
		notifyAll();
	}

	public synchronized void actionPerformed( ActionEvent e )
	{
		// Check to see if the stop button was pressed
		if(e.getSource() == stopButton) {
			stop();
			return;
		}

		for ( int i = 0; i < checkboxes.length; i++ ) {
			if ( e.getSource() == checkboxes[ i ] ) {
				suspended[ i ] = !suspended[ i ];

				updateSuspendedStatusLabel();
				outputs[ i ].setBackground(
						!suspended[ i ] ? Color.green : Color.red );

				if ( !suspended[ i ] )
					notifyAll();
				return;
			}
		}
	}//

	// update suspendedTextLabel
	private synchronized void updateSuspendedStatusLabel(){
		String outputText = "";
		for(int j = 0; j < suspended.length; ++j)
			outputText += "[" + ((suspended[j]) ? "#" : "-") + "]";
		suspendStatus.setText(outputText);

	}

	//add main method here
	public static void main(String[] args){
		// kick off an RandomCharacters instance
		final RandomCharacters rc = new RandomCharacters();
		rc.init();
		rc.start();

		// Create our JFrame
		JFrame jframe = new JFrame();
		jframe.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				if(e.getNewState() == WindowEvent.WINDOW_CLOSING)
					rc.stop();
			}
		});
		jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jframe.setContentPane(rc.getContainer());
		jframe.pack();
		jframe.setVisible(true);
	}
}

