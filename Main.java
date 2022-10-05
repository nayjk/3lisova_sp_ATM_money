import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //создавать объект
        Account Liza = new Account();
        Producer producer = new Producer(Liza);
        Consumer consumer = new Consumer(Liza);
        //запускать потоки с объектом
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
class Account{
    double balance = 0;
    void setbalance(){
         while (balance<100){
             try {
                 wait();
                 balance = balance - 100;
                 System.out.println("На счету у вас: " + balance);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             notify();
         }
    }
    void getbalance(){
        while (balance>=100){
            try {
                wait();
                Scanner money = new Scanner(System.in);
                System.out.println("Сколько денег вы положили?");
                int getmoney = money.nextInt();
                balance = balance + getmoney;
                System.out.println("На счету у вас: " + balance);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notify();
        }
    }
}
class Consumer implements Runnable{

    Account store;
    Consumer(Account store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.getbalance();
        }
    }
}
class Producer implements Runnable{

    Account store;
    Producer(Account store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.setbalance();
        }
    }
}