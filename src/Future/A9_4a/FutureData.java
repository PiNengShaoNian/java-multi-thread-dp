package Future.A9_4a;

import java.util.concurrent.ExecutionException;

public class FutureData implements Data {
    private RealData realdata = null;
    private boolean ready = false;
    private ExecutionException exception = null;
    public synchronized void setRealData(RealData realdata) {
        if (ready) {
            return;
        }
        this.realdata = realdata;
        this.ready = true;
        notifyAll();
    }
    public synchronized void setException(Throwable throwable) {
        if (ready) {
            return;
        }
        this.exception = new ExecutionException(throwable);
        this.ready = true;
        notifyAll();
    }
    public synchronized String getContent() throws ExecutionException {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        if (exception != null) {
            throw exception;
        }
        return realdata.getContent();
    }
}
