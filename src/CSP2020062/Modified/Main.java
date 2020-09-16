package CSP2020062.Modified;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader inBuff=new BufferedReader(new InputStreamReader(System.in));
        String s = inBuff.readLine();
        String[] parts = s.trim().split(" ");
        int n = Integer.parseInt(parts[0]), a = Integer.parseInt(parts[1]), b = Integer.parseInt(parts[2]);
        int[][] vectoru = new int[a][2];
        for(int i=0;i<a;i++){
            String line = inBuff.readLine();
            parts = line.trim().split(" ");
            vectoru[i][0] = Integer.parseInt(parts[0]);
            vectoru[i][1] = Integer.parseInt(parts[1]);
        }
        int i=0;
        long ans = 0;
        for(int j=0;j<b&&i<vectoru.length;j++){
            String line = inBuff.readLine();
            parts = line.trim().split(" ");
            int curV = Integer.parseInt(parts[0]), curN = Integer.parseInt(parts[1]);
            while(i<vectoru.length&&curV>vectoru[i][0]){
                i++;
            }
            if(i<vectoru.length&&curV==vectoru[i][0]){
                ans+= (long)curN*vectoru[i][1];
            }
        }
        System.out.println(ans);
    }
}
