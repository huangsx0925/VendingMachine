package cn.moonshotacademy;

import java.util.Scanner;
import java.util.HashMap;

public class UI {
    Scanner s = new Scanner(System.in);
    HashMap<String, String> output_map = new HashMap<>();

    public void print(Object obj) {
        System.out.print(obj);
    }

    public String get_line() {
        return s.nextLine();
    }

    public String[] get_words() {
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

    public int[] get_ints() {
        String[] in = this.get_words();
        int[] ret = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            ret[i] = Integer.valueOf(in[i]);
        }
        return ret;
    }

    public void output(String sign) {
        String out = output_map.get(sign);
        // What if out is null?
        System.out.println(out);
    }

    public void add_output(String sign, String line) {
        output_map.put(sign, line);
    }

    public void check_warning(String str) {
        this.output(str);
    }

    public void add_warning() {
        this.add_output("wrong arguments", "Read your input again. Look at what you've wrote.\r\n");
        this.add_output("Not enough storage", "Don't drink that much. Be healthy.\r\n");
        this.add_output("double login", "You can't log in when logged in, just like you can't die when dead\r\n");
        this.add_output("wrong password", "Are you trying to break in someone else's account?\r\n");
        this.add_output("id not found", "Are you sure this guy really exist?\r\n");
        this.add_output("not enough money", "You have no money here...\r\n");
        this.add_output("double logout", "You can't log out when logged out, just like you can't die when dead\r\n");
        this.add_output("password not successfully confirmed", "Treat your password seriously please\r\n");
        this.add_output("not enough reference", "What do you ******* want?\r\n");
        this.add_output("undefined command", "I don't understand\r\n");
        this.add_output("not enough discount", "There's no free lunch! You have no discount now!\r\n");
        this.add_output("none", "");
    }
}