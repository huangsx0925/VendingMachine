package cn.moonshotacademy;

public class App {
    public static void main(String[] args) {
        UI ui = new UI();

        Storage storage = new Storage();

        Account account = new Account(storage, ui);

        Controller controller = new Controller(account,storage,ui);
        controller.init();
    }
}