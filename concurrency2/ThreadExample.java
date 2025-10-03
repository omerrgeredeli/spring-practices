package concurrency2;

public class ThreadExample{
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            for(int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + " --- " +i);
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
            }
        }});
        Thread t2 = new Thread(() -> {
            for(int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName()+ " --- " +i);
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
