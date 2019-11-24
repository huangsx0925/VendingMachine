package cn.moonshotacademy;

public class Pair {
    public Double x;
    public Double y;
    public Pair(Double x,Double y){
        this.x = x;
        this.y = y;
    }
    public int compareTo(Pair m){
        if(this.x>m.x){
            return 1;
        }
        else if(this.x<m.x){
            return -1;
        }
        else {
            return 0;
        }
    }
}