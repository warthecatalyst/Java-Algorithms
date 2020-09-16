package CSP2019093;

import java.util.Scanner;

public class Main {
    static final char ESC = 0x1B;
    static final String CHONGZHI = "[0m";
    static final String BEIJING = "[48;2;";
    static int n,m,p,q;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        m = scanner.nextInt(); n = scanner.nextInt();
        p = scanner.nextInt(); q = scanner.nextInt();
        scanner.nextLine();
        String[][] text = new String[n][m]; //n行m列
        for(int i=0;i<text.length;i++){
            for(int j=0;j<m;j++){   //每一行m个像素
                String line = scanner.nextLine().trim();
                String dd = "";
                line = line.replaceFirst("#","");
                if(line.length()==1){
                    dd = line+line+line+line+line+line;
                }else if(line.length()==2){
                    String a1 = line.substring(0,1);
                    String a2 = line.substring(1);
                    dd = a1+a1+a1+a2+a2+a2;
                }else if(line.length()==3){
                    String a1 = line.substring(0,1);
                    String a2 = line.substring(1,2);
                    String a3 = line.substring(2);
                    dd = a1+a1+a2+a2+a3+a3;
                }else{
                    dd = line;
                }
                text[i][j] = dd;
            }
        }
        //
//        for(int i=0;i<CHONGZHI.length();i++){
//            printChar(CHONGZHI.charAt(i));
//        }
        for(int k=0;k<n/q;k++){
            int lastR = 0,lastG = 0,lastB = 0;  //上一个RGB
            for(int l=0;l<m/p;l++){
                int i,j;
                int curR = 0,curG = 0,curB = 0;
                for(i=k*q;i<(k+1)*q;i++){
                    for(j=p*l;j<p*(l+1);j++){
                        String s = text[i][j];
                        curR += StringtoInt(s.substring(0,2));
                        curG += StringtoInt(s.substring(2,4));
                        curB += StringtoInt(s.substring(4,6));
                    }
                }
                curR/=(p*q); curG/=(p*q);curB /= (p*q);
                if(curR==lastR&&curG==lastG&&curB==lastB){
                    printChar(' ');
                    continue;
                }
                if(curR==0&&curG==0&&curB==0){
                    lastR = lastG = lastB = 0;
                    printChar(ESC);
                    for(int i1=0;i1<CHONGZHI.length();i1++){
                        printChar(CHONGZHI.charAt(i1));
                    }
                    printChar(' ');
                }else{
                    //System.out.println("ddddddddd");
                    lastR = curR; lastG = curG; lastB = curB;
                    printChar(ESC);
                    for(int i1=0;i1<BEIJING.length();i1++){
                        printChar(BEIJING.charAt(i1));
                    }
                    StringBuilder s = new StringBuilder();
                    s.append(curR).append(';').append(curG).append(';').append(curB).append('m');
                    for(int i1=0;i1<s.length();i1++){
                        printChar(s.charAt(i1));
                    }
                    printChar(' ');
                }
            }
            if(lastB==0&&lastG==0&&lastR==0){
                printChar('\n');
                continue;
            }
            printChar(ESC);
            for(int i1=0;i1<CHONGZHI.length();i1++){
                printChar(CHONGZHI.charAt(i1));
            }
            printChar('\n');
        }
    }
    static int StringtoInt(String s){
        int a = 0,b = 0;
        if(s.charAt(0)>='0'&&s.charAt(0)<='9'){
            a = s.charAt(0)-'0';
        }else{
            a = s.charAt(0)-'a'+10;
        }
        if(s.charAt(1)>='0'&&s.charAt(1)<='9'){
            b = s.charAt(1)-'0';
        }else{
            b = s.charAt(1)-'a'+10;
        }
        //System.out.println(16*a+b);
        return 16*a+b;
    }

    static void printChar(char c){
        StringBuilder s = new StringBuilder();
        int a = c/16;
        if(a>=10){
            char mm = (char) (a-10+'A');
            s.append(mm);
        }else{
            s.append(a);
        }
        int b = c%16;
        if(b>=10){
            char mm = (char)(b-10+'A');
            s.append(mm);
        }else{
            s.append(b);
        }
        System.out.print("\\x"+s);
    }
}
