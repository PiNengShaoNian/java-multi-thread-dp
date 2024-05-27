package Future.A9_3a.Content;

public class AsyncContentImpl implements Content {
    private SyncContentImpl syncContentImpl;
    private boolean ready = false;
    public synchronized void setContent(SyncContentImpl syncContentImpl) {
        this.syncContentImpl = syncContentImpl;
        this.ready = true;
        notifyAll();
    }
    public synchronized byte[] getBytes() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return syncContentImpl.getBytes();
    }
}
