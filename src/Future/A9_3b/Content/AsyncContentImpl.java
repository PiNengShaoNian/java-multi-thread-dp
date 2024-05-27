package Future.A9_3b.Content;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class AsyncContentImpl extends FutureTask<SyncContentImpl> implements Content {
    public AsyncContentImpl(Callable<SyncContentImpl> callable) {
        super(callable);
    }

    public synchronized byte[] getBytes() {
        byte[] bytes = null;
        try {
            bytes = get().getBytes();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
