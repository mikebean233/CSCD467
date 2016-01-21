public class Monitor {
    private Boolean isT1Turn = true;

    public synchronized Boolean getIsT1Turn() {
        return isT1Turn;
    }

    public synchronized void setT1Turn() {
        isT1Turn = true;
    }

    public synchronized void setT2Turn(){
        isT1Turn = false;
    }
}