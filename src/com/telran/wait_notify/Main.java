package com.telran.wait_notify;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //wait   <->   notify notifyAll

        //Thread 1 - продавец
        //
        //DeliveryService
        //
        //Thread 2 - клиент

        //Покупка произошла здесь!!
        DeliveryService deliveryService = new DeliveryService();

        Thread seller = new Thread(() -> deliveryService.delivering());
        seller.start();

        Thread.sleep(10_000);

        Thread buyer = new Thread(() -> deliveryService.delivered());
        buyer.start();

    }
}
//Доставка под клиента
//Для каждого клиента (каждой доставки) создаем новый инстанс
class DeliveryService {

    private boolean isDelivered = false;

    public void delivering() {
        System.out.println("Начинаем доставку");


        synchronized (this) { //true
            //Thread 20

            //крутим цикл, пока не доставлено
            System.out.println("Ждем ответа от клиента, что товар доставлен");
            while (!isDelivered) {
                try {
                    this.wait(1000);
                    //Thread 1
                    //Thread 2
                    //Thread 3
                    System.out.println("Прошел день. Уточняем статус");
                } catch (InterruptedException e) {}
            }
        }
        //Thread 4
    }

    public void delivered() {
        synchronized (this) {
            System.out.println("Клиент получил товар. Обновляю статус заказа");
            this.isDelivered = true;
            this.notifyAll();
        }
    }
}
