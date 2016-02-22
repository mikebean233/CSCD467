import java.io.*;
import java.nio.file.AccessDeniedException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerLogger{
    private File _outputFile;
    private PrintWriter _writer;
    private boolean _enabled;

    public ServerLogger(String outputFilePath, boolean enabled)  throws Exception {
        if(enabled) {
            try {
                _outputFile = new File(outputFilePath);
                if (!_outputFile.exists()) {
                    _outputFile.createNewFile();
                } else {
                    if (!_outputFile.canWrite())
                        throw new AccessDeniedException("Unable to write to the file ");
                }
                _writer = new PrintWriter(new BufferedWriter(new FileWriter(_outputFile, true)));
            }
            catch(Exception ex){
                enabled = false;
            }
        }
        _enabled = enabled;
    }

    public synchronized void appendToLog(String entry) throws InterruptedException{
        if(Thread.currentThread().isInterrupted())
            throw new InterruptedException();
            if (entry == null)
                entry = "";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
            String dateTimeString = sdf.format(date);
            if(_enabled) {

            try {
                _writer.println(dateTimeString + " : " + entry);
                _writer.flush();
            }
            catch(Exception ex){
                _enabled = false;
            }
        }
        System.out.println(dateTimeString + " : " + entry);

    }

    public synchronized void appendToLogNoInterrupt(String entry){
        try{
            appendToLog(entry);
        }
        catch(Exception e){

        }
    }

    public void close(){
        if(_enabled)
            _writer.close();
    }
}
