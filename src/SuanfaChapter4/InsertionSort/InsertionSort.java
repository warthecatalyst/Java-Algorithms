package SuanfaChapter4.InsertionSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("If you want to create the array yourself? 1 for yes and 0 for no:");
        int flag;
        flag = scanner.nextInt();
        if(flag == 0){
            int[] nums = {5,2,4,6,1,3};
            System.out.println("Before sort:");
            System.out.print("{");
            for(int num:nums){
                System.out.print(num+" ");
            }
            System.out.println("}");
            insertionSort(nums);
            System.out.println("After sort:");
            System.out.print("{");
            for(int num:nums){
                System.out.print(num+" ");
            }
            System.out.println("}");
        }
        else if(flag == 1){
            List<Integer> list = new ArrayList<>();
            System.out.println("Print the num(-1 to end)");
            int n=0;
            n = scanner.nextInt();
            while(n!=-1){
                list.add(n);
                System.out.println("Print the num(-1 to end)");
                n = scanner.nextInt();
            }
            int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
            System.out.println("Before sort:");
            System.out.print("{");
            for(int num:nums){
                System.out.print(num+" ");
            }
            System.out.println("}");
            insertionSort(nums);
            System.out.println("After sort:");
            System.out.print("{");
            for(int num:nums){
                System.out.print(num+" ");
            }
            System.out.println("}");
        }
    }
    public static void insertionSort(int[] nums){
        int n = nums.length;
        for(int j=1;j<n;j++){
            int key = nums[j];
            int i=j-1;
            while(i>=0&&nums[i]<key){
                nums[i+1] = nums[i];
                i = i-1;
            }
            nums[i+1] = key;
        }
    }
}
