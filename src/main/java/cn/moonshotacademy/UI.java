package cn.moonshotacademy;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("ui")
public class UI {
    Scanner s = new Scanner(System.in);
    HashMap<String, String> outputMap = new HashMap<>();

    public void print(Object obj) {
        System.out.print(obj);
    }

    public String getNextLine() {
        return s.nextLine();
    }

    public String[] getWords() {
        String[] in = new String[100];
        int l = 0;
        in[l] = "";
        String word = s.nextLine();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != (char) ' ') {
                in[l] += word.charAt(i);
            } else {
                l++;
                in[l] = "";
            }
        }
        l++;
        String[] ret = new String[l];
        for (int i = 0; i < l; i++) {
            ret[i] = in[i];
        }
        return ret;
    }

    public int[] getInts() {
        String[] in = this.getWords();
        int[] ret = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            ret[i] = Integer.valueOf(in[i]);
        }
        return ret;
    }

    public void output(String sign) {
        String out = outputMap.get(sign);
        // What if out is null?
        System.out.println(out);
    }

    public void addOutput(String sign, String line) {
        outputMap.put(sign, line);
    }

    public void checkWarning(String str) {
        this.output(str);
    }

    public void addWarning() {
        this.addOutput("wrong arguments", "Read your input again. Look at what you've wrote.\r\n");
        this.addOutput("Not enough storage", "Don't drink that much. Be healthy.\r\n");
        this.addOutput("double login", "You can't log in when logged in, just like you can't die when dead\r\n");
        this.addOutput("wrong password", "Are you trying to break in someone else's account?\r\n");
        this.addOutput("id not found", "Are you sure this guy really exist?\r\n");
        this.addOutput("not enough money", "You have no money here...\r\n");
        this.addOutput("double logout", "You can't log out when logged out, just like you can't die when dead\r\n");
        this.addOutput("password not successfully confirmed", "Treat your password seriously please\r\n");
        this.addOutput("not enough reference", "What do you ******* want?\r\n");
        this.addOutput("undefined command", "I don't understand\r\n");
        this.addOutput("not enough discount", "There's no free lunch! You have no discount now!\r\n");
        this.addOutput("none", "");
    }
}