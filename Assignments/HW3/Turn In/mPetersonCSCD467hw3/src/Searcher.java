import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Searcher extends Thread{
    private SharedQueue<String> _queue;
    private String _patternString;
    public Searcher(String patternString, SharedQueue<String> queue){
        if(patternString == null || queue == null)
            throw new NullPointerException();

        _patternString = patternString;
        _queue = queue;
    }

    @Override
    public void run(){
        int matchCount = 0;
        String thisLine;
        Pattern pattern = Pattern.compile(_patternString);
        Matcher matcher;

        while((thisLine = _queue.dequeue()) != null) {
            matcher = pattern.matcher(thisLine);
            while(matcher.find())
                ++matchCount;
        }
        _queue.setMatchCount(matchCount);
    }



}
