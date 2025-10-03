package concurrency2;

class MyRunnable implements Runnable{
    @Override
    public void run(){
        for(int i = 1; i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " --> " + i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
public class RunnableExample {
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        t1.start();
        t2.start();
    }
}
