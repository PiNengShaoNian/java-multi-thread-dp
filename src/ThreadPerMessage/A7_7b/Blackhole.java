package ThreadPerMessage.A7_7b;

public class Blackhole {
    public static void enter(Object obj) {
        System.out.println("Step 1");
        magic(obj);
        System.out.println("Step 2");
        synchronized (obj) {
            System.out.println("Step 3 (never reached here)");  // 不会执行到这里
        }
    }

    private static void magic(Object obj) {
        // 线程thread获取obj的锁之后，一直等待自身终止
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    synchronized (this) {
                        this.notifyAll();
                    }
                    try {
                        this.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        synchronized (thread) {
            thread.start();
            try {
                thread.wait();
            } catch (InterruptedException e) {
            }
        }
    }
}
