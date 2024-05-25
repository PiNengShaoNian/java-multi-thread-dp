package ThreadPerMessage.A7_7a;

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
        // 线程thread用来获取obj的锁并执行无限循环
        // thread的名称用作守护条件
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (obj) { // 在此处获取obj的锁
                    synchronized (this) {
                        this.setName("Locked"); // 改变守护条件
                        this.notifyAll(); // 通知已经获取了obj的锁
                    }
                    while (true) {
                    }
                }
            }
        };
        synchronized (thread) {
            thread.setName("");
            thread.start(); // 启动线程
            // Guarded Suspension模式
            while (thread.getName().equals("")) {
                try {
                    thread.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
