import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
public class Diners extends JFrame{

    public static void main(String[] args){
        Diners diners = new Diners();
        diners.init();
        diners.start();
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PhilCanvas display;
    Thread[] phil= new Thread[PhilCanvas.NUMPHILS];
    Fork[] fork = new Fork[PhilCanvas.NUMPHILS];
    Scrollbar slider;
    Button restart;
    Button freeze;

    public void init() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new PhilCanvas(this);
        display.setSize(300,320);
        this.setVisible(true);
        add("Center",display);
        this.setSize(300,320);
        //this.pack();
        this.setResizable(false);
        //this.setMaximumSize(this.getSize());
        this.setTitle("Michael Peterson Lab7");
        slider = new Scrollbar(Scrollbar.HORIZONTAL, 50, 5, 0, 100);
		
        restart = new Button("Restart");	
		restart.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            //if (display.deadlocked()) {
                stop();
                slider.setValue(50);
                start();
            //}
            display.thaw();
          }
        });

        freeze = new Button("Freeze");		
		freeze.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            display.freeze();
          }
        });

        Panel p1 = new Panel();
        p1.setLayout(new BorderLayout());
        p1.add("Center",slider);
        p1.add("East",restart);
        p1.add("West",freeze);
        add("South",p1);
    }

    Thread makePhilosopher(Diners d, int id, Fork left, Fork right) {

            return new Philosopher(d,id,left,right);
        }

    public int thinkingTime() {
    	int time = slider.getValue()*(int)(100*Math.random());
    	return time;
    }

    public int eatTime() {
    	int time = (slider.getValue()*(int)(50*Math.random()));
    	return time;
    }

    public int holdOneForkTime(){
        int time = (slider.getValue()*(int)(100*Math.random())) + 1;
        return time;
    }

    public int hungryTime(){
        int time = (slider.getValue()*(int)(25*Math.random()));
        return time;
    }


    public void start() {
       for (int i =0; i<display.NUMPHILS; ++i)
            fork[i] = new Fork(display,i);
       for (int i =0; i<display.NUMPHILS; ++i){
            phil[i] = makePhilosopher(this,i,
                        fork[(i-1+display.NUMPHILS)% display.NUMPHILS],
                        fork[i]);
            phil[i].start();
       }
    }

    public void stop() {
        for (int i =0; i<display.NUMPHILS; ++i) {
            phil[i].interrupt();
        }
    }

}//Diners