package CSP2019123.Reference;

import java.util.*;

public class Main {
    static Scanner scanner;
    static int n;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.nextLine();
        a:		for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] part = line.split("=");
            int ct[] = new int[1500];
            for (int j = 0; j < 2; j++) {
                String ob[] = part[j].split("\\+");
                for (int k = 0; k < ob.length; k++) {
                    int xit = 0;
                    int kk;
                    for (kk = 0; kk < ob[k].length(); kk++) {
                        char c = ob[k].charAt(kk);
                        if (c <= '9' && c >= '0') xit = xit * 10 + c - '0';
                        else break;
                    }
                    if (xit == 0) xit = 1;

                    proc(ob[k], kk, ob[k].length(), xit, ct, j);
                }
            }
            for (int k = 0; k < ct.length; k++) {
                if (ct[k] != 0) {
                    System.out.println("N");
                    continue a;
                }
            }
            System.out.println("Y");
        }
    }

    static int proc(String s, int begin, int end, int xi, int[] ct, int fuh) {
        int num = 0;
        int msk = 1;
        for (int k = end - 1; k >= begin; k--) {
            char c = s.charAt(k);
            if (c <= '9' && c >= '0') {
                num += msk * (c - '0');
                msk *= 10;
            }
            else {
                msk = 1;
                if (num == 0) num = 1;
                if (c == ')') {
                    k = proc(s, begin, k, xi * num, ct, fuh);
                    num = 0;
                }
                else if (c == '(') return k;
                else {
                    int code;
                    if (c <= 'Z') {
                        code = c - 'A';
                    }
                    else {
                        k--;
                        code = (c - 'a' + 1) * 26 + s.charAt(k) - 'A';
                    }
//					System.out.println(code + " " + num * xi);
                    if (fuh == 0) ct[code] += num * xi;
                    else ct[code] -= num * xi;
                    num = 0;
                }
            }
        }
        return begin;
    }

    // System.out.print();
}

/*
11
H2+O2=H2O
2H2+O2=2H2O
H2+Cl2=2NaCl
H2+Cl2=2HCl
CH4+2O2=CO2+2H2O
CaCl2+2AgNO3=Ca(NO3)2+2AgCl
3Ba(OH)2+2H3PO4=6H2O+Ba3(PO4)2

4Au+8NaCN+2H2O+O2=4Na(Au(CN)2)+4NaOH


*/

