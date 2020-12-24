class ReadWriteLock {
    volatile boolean writer = false;
    volatile int readers = 0;

    public ReadWriteLock() {}

    public synchronized void enterRead() throws InterruptedException {
        while(writer) {
            wait();
        }
        readers++;
    }

    public synchronized void enterWrite() throws InterruptedException {
        while (writer || readers > 0) {
            wait();
        }
        writer = true;
    }

    public synchronized void exitRead() {
        readers--;
        if(readers == 0) {
           notify();
        }
    }

    public synchronized void exitWrite() {
        writer = false;
        notifyAll();
    }
}

public class BinaryAspect {
    public static void main(String[] args) throws InterruptedException {
        BinaryTree tree = new BinaryTree();

        Thread thread1 = new Thread(()-> {
            for (int i = 0;i < 10;i++) {
                tree.insert(i);
                System.out.println("Inserted " + i + " to the tree");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() ->  {
            Node n;
            for (int i = 0;i < 10;i++) {
                n = tree.search(i);
                if (n != null) {
                    System.out.println("Found " + i + "!");
                } else {
                    System.out.println("Didn't found " + i + "!");
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            int var = 5;
            for (int i = 0;i < 10; i++) {
                Node n = tree.search(var);
                if (n != null) {
                    tree.remove(var);
                    System.out.println("Removed " + var + " from the tree");
                } else {
                    System.out.println("Didn't found " + var + " in order to remove it");
                }
            }
        });
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}