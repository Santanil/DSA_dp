
https://www.codingninjas.com/codestudio/problems/ways-to-make-coin-change_630471

https://www.youtube.com/watch?v=HgyouUi11zk&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=23

import java.util.*;

//memoization
public class Solution {
    public static long coinChange(int i,int[] d,int v,long[][] dp){
        if(i==0){
            if(v%d[i]==0)
                return 1;
            else
                return 0;
        }
        if(dp[i][v]!=-1)
            return dp[i][v];
        long take=0;
        long notTake=coinChange(i-1,d,v,dp);
        if(d[i]<=v)
            take=coinChange(i,d,v-d[i],dp);
        
        return dp[i][v]=take+notTake;
    }
    public static long countWaysToMakeChange(int d[], int v){
        int n=d.length;
        long[][] dp=new long[n][v+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return coinChange(d.length-1,d,v,dp);    
    }
}



 //Tabulation
    public static long countWaysToMakeChange(int d[], int v){
        int n=d.length;
        long[][] dp=new long[n][v+1];
        //base case
        for(int i=0;i<=v;i++){
            if(i%d[0]==0)
                dp[0][i]=1;
            else
                dp[0][i]=0;
        }
        
        for(int i=1;i<n;i++){
           
            for(int j=0;j<=v;j++){
                long notTake=dp[i-1][j];
                long take=0;
                if(d[i]<=j)
                    take=dp[i][j-d[i]];
                dp[i][j]=take+notTake;
            }
        }
        return dp[n-1][v]; 
    }




//Space Optimized
    public static long countWaysToMakeChange(int d[], int v){
        int n=d.length;
        long[] prev=new long[v+1];
        //base case
        for(int i=0;i<=v;i++){
            if(i%d[0]==0)
                prev[i]=1;
            else
                prev[i]=0;
        }
        
        for(int i=1;i<n;i++){
           long[] cur=new long[v+1];
            for(int j=0;j<=v;j++){
                long notTake=prev[j];
                long take=0;
                if(d[i]<=j)
                    take=cur[j-d[i]];
                cur[j]=take+notTake;
            }
            prev=cur;
        }
        return prev[v];    
    }
