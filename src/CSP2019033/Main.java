package CSP2019033;

import java.util.Scanner;

public class Main {
    static int n,s,l,m;
    static final int MAXN = 1005;
    static String[] C = new String[MAXN];
    static Scanner scanner = new Scanner(System.in);
    static boolean vis[] = new boolean[MAXN];   //表示这个磁盘有没有数据
    static int maxn = 0;
    public static void main(String[] args) {
//        String ans = Xor("00000000","ABCDEF19");
//        System.out.println(ans);
        n = scanner.nextInt(); s = scanner.nextInt(); l = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<l;i++){
            String next = scanner.nextLine().trim();
            String[] parts = next.split(" ");
            int line = Integer.parseInt(parts[0]);
            vis[line] = true;
            C[line] = parts[1];
            if(maxn==0){
                maxn = (n-1)*parts[1].length()/8;
            }
        }
        m = scanner.nextInt();
        for(int i=0;i<m;i++){
            int blocknum = scanner.nextInt();
            if(blocknum>=maxn){
                System.out.println("-");
                continue;
            }
            int tid = blocknum/s;
            int line = tid/(n-1);
            int rid = (n-1)-(line%n);
            int disknum = (rid+(tid%(n-1))+1)%n;
            int res = blocknum%s;
            if(vis[disknum]){   //该盘中本身就存有数据
                String ans1 = C[disknum].substring(8*s*line+8*res,8*s*line+8*res+8);
                System.out.println(ans1);
            }else{
                if(l<n-1){
                    System.out.println("-");
                    continue;
                }
                String ans1 = "00000000";
                for(int j=0;j<n;j++){
                    if(j==disknum){
                        continue;
                    }
                    ans1 = Xor(ans1,C[j].substring(8*(s*line+res),8*(s*line+res+1)));
                }
                System.out.println(ans1);
            }
        }
    }

    public static String Xor(String num1,String num2){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<num1.length();i++){
            int c = num1.charAt(i);
            int c1 = num2.charAt(i);
            if(c>='0'&&c<='9'){
                c-='0';
            }else if(c>='A'&&c<='F'){
                c = c-'A'+10;
            }
            if(c1>='0'&&c1<='9'){
                c1-='0';
            }else if(c1>='A'&&c1<='F'){
                c1 = c1-'A'+10;
            }
            int cur = c^c1;
            if(cur>=0&&cur<=9){
                stringBuilder.append(cur);
            }else{
                char c2 = (char) (cur-10+'A');
                stringBuilder.append(c2);
            }
        }
        return stringBuilder.toString();
    }
}
