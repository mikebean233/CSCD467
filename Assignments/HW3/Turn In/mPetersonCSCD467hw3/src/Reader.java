import java.io.*;
import java.nio.Buffer;

public class Reader extends Thread{
    private SharedQueue<String> _queue;
 	private BufferedReader _bufferedReader;
    private FileReader _fileReader;
    public Reader(String fileName, SharedQueue<String> queue) throws Exception{
        if(fileName == null || queue == null)
            throw new NullPointerException();

        _queue = queue;

        File file = new File(fileName);
        if(!file. exists())
            throw new FileNotFoundException("the file " + fileName + " does not exist");
        _fileReader = new FileReader(fileName);
        _bufferedReader = new BufferedReader(_fileReader);
    }

    @Override
    public void run(){
        String thisLine;
        int lineCount = 0;
        try {
            _bufferedReader.readLine();
            while ((thisLine = _bufferedReader.readLine()) != null) {
                _queue.enqueue(thisLine);
                ++lineCount;
            }
            _queue.setLineCount(lineCount);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        _queue.setFinished();
        try{_fileReader.close();}catch(Exception e){}
    }
}
