package WorkerThread.A8_2;

public class Channel {
    public Channel(int threads) {

    }
    public void startWorkers() {}
    public void putRequest(final Request request) {
        new Thread() {
            @Override
            public void run() {
                request.execute();
            }
        }.start();
    }
}
