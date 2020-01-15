package cn.moonshotacademy.vendingmachinebysamson.service;

// import java.util.Scanner;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
@Component("ui")
public class UI {
    // private Scanner s = new Scanner(System.in);
    private HashMap<String, String> outputMap = new HashMap<>();
    // private Queue<String> requestQueue = new LinkedList<String>();
    // private Queue<String> resultQueue = new LinkedList<String>();
    private Queue<String> request = new LinkedList<String>();
    private Queue<String> result = new LinkedList<String>();

    public void addResult(String str) {
        result.add(str);
    }

    public void addRequest(String str) {
        String requeststr[] = str.split("_split_");
        for (String string : requeststr) {
            request.add(string);
        }
    }

    public String getRequest() {
        return request.poll();
    }

    public String getResult() {
        String ret = "";
        while(!result.isEmpty()){
            ret += result.poll();
        }
        return ret;
    }

    public String[] getWords() {
        String[] in = new String[100];
        int l = 0;
        in[l] = "";
        String word = this.getRequest();
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
        this.addResult(out);
    }

    public void addOutput(String sign, String line) {
        outputMap.put(sign, line);
    }

    public void checkWarning(String str) {
        this.output(str);
    }

    public void addWarning() {
        this.addOutput("wrong arguments", "Read your input again. Look at what you've wrote.");
        this.addOutput("Not enough storage", "Don't drink that much. Be healthy.");
        // this.addOutput("double login", "You can't log in when logged in, just like you can't die when dead");
        this.addOutput("double login", "double login");
        this.addOutput("wrong password", "Are you trying to break in someone else's account?");
        this.addOutput("id not found", "Are you sure this guy really exist?");
        this.addOutput("not enough money", "You have no money here...");
        this.addOutput("double logout", "You can't log out when logged out, just like you can't die when dead");
        this.addOutput("password not successfully confirmed", "Treat your password seriously please");
        this.addOutput("not enough reference", "What do you ******* want?");
        this.addOutput("undefined command", "I don't understand");
        this.addOutput("not enough discount", "There's no free lunch! You have no discount now!");
        this.addOutput("none", "");
    }

    // public Scanner getS() {
    //     return s;
    // }

    // public void setS(Scanner s) {
    //     this.s = s;
    // }

    public HashMap<String, String> getOutputMap() {
        return outputMap;
    }

    public void setOutputMap(HashMap<String, String> outputMap) {
        this.outputMap = outputMap;
    }
}