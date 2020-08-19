package CSP2015095;

public class Main {
    static final int ACMXNode = 110, CharSize = 30;
    static int[] cnt = new int[ACMXNode];
    static int charIdx(char ch){
        return ch-'a';
    }
    public static void main(String[] args){     //应该是一道DP问题,考虑了AC自动机

    }
}

class ACAUTO{
    static final int ACMXNode = 110, CharSize = 30;
    int size;
    int[][] ch = new int[ACMXNode][CharSize];
    int[] val = new int[ACMXNode];
    int[] f = new int[ACMXNode];
    int[] last = new int[ACMXNode];
    int print(int i,int j){
        if(j!=0){
            System.out.printf("模板串T第%d个字符结尾子串和AC自动机编号为%d的一样,last[%d]=%d\n",i+1,val[j],j,last[j]);
            print(i,last[j]);
        }
        return 0;
    }
}
