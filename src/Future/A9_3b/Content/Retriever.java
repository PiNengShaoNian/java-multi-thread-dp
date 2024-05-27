package Future.A9_3b.Content;

import Future.A9_3b.Content.AsyncContentImpl;

import java.util.concurrent.Callable;

public class Retriever {
    public static Content retrieve(final String urlStr) {
        AsyncContentImpl future = new AsyncContentImpl(new Callable<SyncContentImpl>() {
            @Override
            public SyncContentImpl call()  {
                return new SyncContentImpl(urlStr);
            }
        });

        new Thread(future).start();
        return future;
    }
}
