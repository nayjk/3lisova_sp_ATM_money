import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account liza = new Account();
        Producer producer = new Producer(liza);
        Consumer consumer = new Consumer(liza);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
class Account extends Thread{
    private int balance = 0;
    int minus = 0;
    int plus = 0;
    public synchronized void setBalance(){
        while(balance >= 400){
            try{
                wait();
            }
            catch (Exception ex){
                ex.getMessage();
            }
        }
        System.out.println("Сколько денег вы хотите положить?");
        Scanner in = new Scanner(System.in);
        plus = in.nextInt();
        balance += plus;
        System.out.println("Остаток баланcа: " + balance);
        notify();
    }
    public synchronized void getMoney(){
        while(balance < 400){
            try {
                wait();
            }
            catch (Exception ex){
                ex.getMessage();
            }
        }
        System.out.println("Мама забирает у вас деньги: 400 рублей");
        int mother = 400;
        int balance2 = balance - mother;
        balance = balance2;
        System.out.println("Остаток баланса: " + balance);
        notify();
    }
}

class Producer extends Thread{
    Account account;
    Producer(Account account){
        this.account=account;
    }
    public void run(){
        for (int i = 1; i < 50; i++) {
            account.setBalance();
        }
    }
}
class Consumer extends Thread{

    Account account;
    Consumer(Account account){
        this.account=account;
    }
    public void run(){
        for (int i = 1; i < 50; i++) {
            account.getMoney();
        }
    }
}
