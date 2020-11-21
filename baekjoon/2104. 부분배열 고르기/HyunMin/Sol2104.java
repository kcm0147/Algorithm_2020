package project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n;
        try(Scanner input = new Scanner(System.in)){
            n = input.nextInt();
            for(int i=0; i<n; i++) 
                sol.add(input.nextInt());
        }
    
        System.out.println(sol.score(0, n-1));
    }
}

class Solution{
    private long[] numbers = new long[100000];
    private int size = 0;
    
    void add(long number){
        numbers[size++] = number;
    }
    
    long score(int left, int right){
        if(left >= right) return numbers[left] * numbers[left];
        int mid = (left + right) / 2;
        long ret = Math.max(score(left, mid), score(mid+1, right));
        ret = Math.max(ret, middle(left, right));
        return ret; 
    }
    
    long middle(int left, int right){
        int mid = (left+right) / 2;
        long sum = numbers[mid] + numbers[mid+1];
        long minValue = Math.min(numbers[mid], numbers[mid+1]);
        long ret = sum * minValue;
        
        int l = mid, r = mid+1;
        while(left < l || r < right){
            if(r < right && (left >= l || numbers[l-1] < numbers[r+1])){
                r++;
                minValue = Math.min(minValue, numbers[r]);
                sum += numbers[r];
            }else{
                l--;
                minValue = Math.min(minValue, numbers[l]);
                sum += numbers[l];
            }
            ret = Math.max(ret, sum * minValue);
        }
        return ret;
    }
}