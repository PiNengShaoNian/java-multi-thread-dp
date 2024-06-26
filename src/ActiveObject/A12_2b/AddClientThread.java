package ActiveObject.A12_2b;

import ActiveObject.A12_2b.activeobject.ActiveObject;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class AddClientThread extends Thread {
    private final ActiveObject activeObject;
    private String x = "1";
    private String y = "1";

    public AddClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    public void run() {
        try {
            for (int i = 0; true; i++) {
                // 有返回值的调用
                Future<String> future = activeObject.add(x, y);
                Thread.sleep(100);
                String z = future.get();
                System.out.println(Thread.currentThread().getName() + ": " + x + " + " + y + " = " + z);
                x = y;
                y = z;
            }
        } catch (RejectedExecutionException | CancellationException | ExecutionException | InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        }
    }
}
