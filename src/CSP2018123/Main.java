package CSP2018123;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n;
    static IPAddress[] ipAddresses;
    public static void main(String[] args){
        n = scanner.nextInt();
        scanner.nextLine();
        ipAddresses = new IPAddress[n];
        for(int i=0;i<n;i++){
            String line = scanner.nextLine().trim();
            int length = 0;
            int[] toadd = new int[4];
            if(!line.contains("/")){
                length = 8;
                for(int j=0;j<line.length();j++){
                    if(line.charAt(j)=='.'){
                        length+=8;
                    }
                }
                String[] parts = line.split("\\.");
                int len = parts.length;
                int j = 0;
                for(;j<len;j++){
                    toadd[j] = Integer.parseInt(parts[j]);
                }
                if(j<3){
                    for(int k =j;k<=3;k++){
                        toadd[k] = 0;
                    }
                }
//                for(int k=0;k<4;k++){
//                    System.out.print(toadd[k]+'.');
//                }
            }else{
                String[] parts = line.split("/");
                length = Integer.parseInt(parts[1]);
                String[] parts1 = parts[0].trim().split("\\.");
                //System.out.println(Arrays.toString(parts1));
                int len = parts1.length, j = 0;
                for(;j<len;j++){
                    toadd[j] = Integer.parseInt(parts1[j]);
                }
                if(j<3){
                    for(int k=j;k<=3;k++){
                        toadd[k] = 0;
                    }
                }
//                for(int k=0;k<4;k++){
//                    System.out.print(toadd[k]+".");
//                }
            }
            ipAddresses[i] = new IPAddress(toadd,length);
        }
        Arrays.sort(ipAddresses);
//        for(int i=0;i<n;i++){
//            System.out.println(ipAddresses[i]);
//        }
        int endCount = n;
        //System.out.println(endCount);
        for(int i=0;i<endCount-1;i++){
            while(IPContains(ipAddresses[i],ipAddresses[i+1])){
                System.out.println(i+" to be contained "+ipAddresses[i]+" "+ipAddresses[i+1]);
                endCount--;
                for(int j=i+1;j<endCount;j++){
                    ipAddresses[j] = ipAddresses[j+1];
                }
            }
        }
        //System.out.println(endCount);
//        for(int i=0;i<endCount;i++){
//            System.out.println(ipAddresses[i]);
//        }
        for(int i=0;i<endCount-1;i++){
            //System.out.print(i+" ");
            IPAddress temp = IPMerge(ipAddresses[i],ipAddresses[i+1]);
            if(temp!=null){
                //System.out.println("to be merged "+ipAddresses[i]+" "+ipAddresses[i+1]);
                ipAddresses[i] = temp;
                endCount--;
                for(int j=i+1;j<endCount;j++){
                    ipAddresses[j] = ipAddresses[j+1];
                }
                if(i>0){
                    i-=2;
                }
            }
        }
        for(int i=0;i<endCount;i++){
            System.out.println(ipAddresses[i]);
        }
    }
    static boolean IPContains(IPAddress a,IPAddress b){
        //System.out.println("Src = "+a+" des = "+b);
        if(b.length<=a.length){
            return false;
        }
        long a1 = 0, b1 = 0;
        for(int i=0;i<4;i++){
            a1 += (long)a.IP[i]*pow(3-i);
            b1 += (long)b.IP[i]*pow(3-i);
        }
        //System.out.printf("%x  %x\n",a1,b1);
        a1>>=(32-a.length);
        b1>>=(32-a.length);
        //System.out.printf("%x  %x\n",a1,b1);
        return a1==b1;
    }
    static IPAddress IPMerge(IPAddress a,IPAddress b){
        if(a.length!=b.length){
            return null;
        }
        IPAddress temp = new IPAddress(a.IP,a.length-1);
        //System.out.println(temp);
        if(IPContains(temp,a)&&IPContains(temp,b)){
            return temp;
        }
        return null;
    }
    static int pow(int i){
        int ans = 1;
        for(int j=0;j<i;j++){
            ans *= 256;
        }
        return ans;
    }
    static class IPAddress implements Comparable<IPAddress>{
        int[] IP;
        int length;

        @Override
        public int compareTo(IPAddress o) {
            for(int i=0;i<4;i++){
                if(IP[i]!=o.IP[i]){
                    return IP[i]-o.IP[i];
                }
            }
            return length-o.length;
        }

        IPAddress(int[] IP,int length){
            this.IP = IP;
            this.length = length;
        }

        @Override
        public String toString() {
            return ""+IP[0]+'.'+IP[1]+'.'+IP[2]+'.'+IP[3]+'/'+length;
        }
    }
}
