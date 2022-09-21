https://www.codingninjas.com/codestudio/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494

https://www.youtube.com/watch?v=GS_OqZb2CWc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=17

import java.util.* ;
import java.io.*; 
import java.lang.*;

//Tabulation Using Subset sum=k problem's concept
//TC:O(n*target)
//SC:O(n*target)
public class Solution {
	public static int minSubsetSumDifference(int[] arr, int n) {   
        int target=0;
        for(int x:arr)
            target+=x;        
		boolean[][] dp=new boolean[n+1][target+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=target;j++){
                if(i==0 && j==0)
                    dp[i][j]=true;
                else if(i==0)
                    dp[i][j]=false;
                else if(j==0)
                    dp[i][j]=true;
                else{
                    if(dp[i-1][j])
                        dp[i][j]=true;
                    else if(arr[i-1]<=j && dp[i-1][j-arr[i-1]]==true)
                        dp[i][j]=true;
                }
            }
        }
        
        int min=Integer.MAX_VALUE;
        for(int i=0;i<=target;i++){
            if(dp[n][i]){
                int sum1=i;
                int sum2=target-sum1;
                min=Math.min(min,Math.abs(sum1-sum2));
            }
        }
        return min;
	}

}


//TC:O(n*target)
//SC:O(target)
public class Solution {
    public static int minSubsetSumDifference(int[] arr, int n) {   
        int target=0;
        for(int x:arr)
            target+=x;        
        boolean[] prev=new boolean[target+1];
        
        prev[0]=true;
        
        for(int i=1;i<=n;i++){
            boolean[] cur=new boolean[target+1];
           // cur[i][0]=true;
            for(int j=0;j<=target;j++){
                if(i==0 && j==0)
                    cur[j]=true;
                else if(i==0)
                    cur[j]=false;
                else if(j==0)
                    cur[j]=true;
                else{
                    if(prev[j]==true)
                        cur[j]=true;
                    else if(arr[i-1]<=j && prev[j-arr[i-1]]==true)
                        cur[j]=true;
                }
            }
            prev=cur;
        }
        
        int min=Integer.MAX_VALUE;
        for(int i=0;i<=target;i++){
            if(prev[i]){
                int sum1=i;
                int sum2=target-sum1;
                min=Math.min(min,Math.abs(sum1-sum2));
            }
        }
        return min;
    }

}
