package CSP2016043;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        String curDir = sc.next();  //读取下一个字符串
        sc.nextLine();  //注意，如果之前不是读一行，必须先使用一次sc.nextLine()消除当前行
        for(int i=0;i<n;i++){
            arr[i] = sc.nextLine();
            if(arr[i].length()==0||arr[i].charAt(0)!='/'){
                arr[i] = curDir+"/"+arr[i];
            }
            arr[i] = arr[i].replaceAll("/{2,3}","/");
        }
        for(int i=0;i<n;i++){
            String[] sp = arr[i].trim().split("/");
            String result = "";
            for(int j=0;j<sp.length;j++){
                if(sp[j].length()==0){
                    continue;
                }
                else if(sp[j].equals(".")){
                    continue;
                }
                else if(sp[j].equals("..")){
                    int index = result.lastIndexOf("/");
                    if(index!=-1){
                        result = result.substring(0, index);
                    }
                }
                else{
                    result = result+"/"+sp[j];
                }
            }
            if(result.length()==0){
                result = "/";
            }
            arr[i] = result;
        }
        for(int i=0;i<n;i++){
            System.out.println(arr[i]);
        }
    }
}
