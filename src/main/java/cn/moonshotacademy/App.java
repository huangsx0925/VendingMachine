package cn.moonshotacademy;

public class App 
{
    public static void main(String[] args)
    {
        Storage storage = new Storage();

        Account account = new Account(storage);

        UI ui = new UI();
        ui.init(account, storage);
    }
}