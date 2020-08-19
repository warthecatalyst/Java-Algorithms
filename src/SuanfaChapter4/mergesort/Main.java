package SuanfaChapter4.mergesort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int[] A = {0,5,6,5,4,3,2,1,9,10,8};
        //System.out.println((0+1)/2);
        MergeSort(A,0,A.length-1);
        for(int num:A){
            System.out.print(num+" ");
        }
    }

    static void merge(int[] A,int begin,int mid,int end){
        //System.out.println("In merge, left = "+begin+",mid ="+mid+",right ="+end);
        int[] left = Arrays.copyOfRange(A,begin,mid+1);
        int[] right = Arrays.copyOfRange(A,mid+1,end+1);
        int i = 0,j = 0,k = begin;
        while(i<mid-begin+1&&j<end-mid&&k<=end){
            if(left[i]<right[j]){
                A[k++] = left[i++];
            }
            else if(left[i]>=right[j]){
                A[k++] = right[j++];
            }
        }
        if(i==mid-begin+1){
            while(k<=end){
                A[k++] = right[j++];
            }
        }
        else{
            while (k<=end){
                A[k++] = left[i++];
            }
        }
    }
    static void MergeSort(int[] A,int left,int right){
        if(left<right){
            //System.out.println("left = "+left+" right = "+right);
            int mid = (left+right)/2;
            //System.out.println("mid = "+mid);
            MergeSort(A,left,mid);
            MergeSort(A,mid+1,right);
            merge(A,left,mid,right);
//            for(int j=left;j<right;j++){
//                System.out.println("j = "+j +",A[j] = "+A[j]);
//            }
        }
    }
}
