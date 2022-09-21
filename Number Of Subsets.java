https://www.codingninjas.com/codestudio/problems/number-of-subsets_3952532

https://www.youtube.com/watch?v=ZHyb-A2Mte4&t=147s

import java.util.* ;
import java.io.*; 

//BF
//TC:O(2^n)
//SC:O(n)
public class Solution {
    public static int countWays(int i,int sum,int[] num){
        if(sum==0)
            return 1;
        if(i==0)
            return (num[i]==sum)?1:0;

        int notPick=countWays(i-1,sum,num);
        int pick=0;
        if(sum>=num[i])
            pick=countWays(i-1,sum-num[i],num);
        return notPick+pick;
    }
    public static int findWays(int num[], int tar) {
        return countWays(num.length-1,tar,num);
    }
}


//Memoization
//TC:O(n*tar)
//SC:O(n*tar)+O(n)
public class Solution {
    public static int countWays(int i,int sum,int[] num,int[][] dp){
        if(sum==0)
            return 1;
        if(i==0)
            return (num[i]==sum)?1:0;
        if(dp[i][sum]!=-1)
            return dp[i][sum];
        int notPick=countWays(i-1,sum,num,dp);
        int pick=0;
        if(sum>=num[i])
            pick=countWays(i-1,sum-num[i],num,dp);
        return dp[i][sum]=notPick+pick;
    }
    public static int findWays(int num[], int tar) {
        int n=num.length;
        int[][] dp=new int[n+1][tar+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return countWays(num.length-1,tar,num,dp);
    }
}

//Tabulation
//TC:O(n*tar)
//SC:O(n*tar)
public class Solution {
    public static int findWays(int num[], int tar) {
        int n=num.length;
        int[][] dp=new int[n][tar+1];
        for(int i=0;i<n;i++)
            dp[i][0]=1;
        if(num[0]<=tar) 
                dp[0][num[0]]=1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=tar;j++){
                int notPick=dp[i-1][j];
                int pick=0;
                if(j>=num[i])
                    pick=dp[i-1][j-num[i]];  
                
                dp[i][j]=notPick+pick;
            }
        }
        return dp[n-1][tar];
    }
}


//Space Optimized
//TC:O(n*tar)
//SC:O(k)
public class Solution {
    public static int findWays(int num[], int tar) {
        int n=num.length;
        int[] prev=new int[tar+1];
        prev[0]=1;
        if(num[0]<=tar) 
                prev[num[0]]=1;
        for(int i=1;i<n;i++){
            int cur[]=new int[tar+1];
            cur[0]=1;
            for(int j=0;j<=tar;j++){
                int notPick=prev[j];
                int pick=0;
                if(j>=num[i])
                    pick=prev[j-num[i]];  
                
                cur[j]=notPick+pick;
            }
            prev=cur;
        }
        return prev[tar];
    }
}
