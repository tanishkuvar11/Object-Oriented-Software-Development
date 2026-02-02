class Callme {
    synchronized void call(String msg) {
        System.out.print("[" + msg);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.print("]");
    }
}

class Caller extends Thread {
    String msg;
    Callme obj;

    public Caller(Callme targ, String s) {
        msg = s;
        obj = targ;
    }

    public void run() {
        obj.call(msg);
    }
}

class sample1 {
    public static void main(String[] args) {
        Callme target = new Callme();

        Caller c1 = new Caller(target, "Manipal");
        c1.start();

        Caller c2 = new Caller(target, "Institute");
        c2.start();

        Caller c3 = new Caller(target, "Of");
        c3.start();

        new Caller(target, "Technology").start();
    }
}