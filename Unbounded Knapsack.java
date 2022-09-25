https://www.codingninjas.com/codestudio/problems/unbounded-knapsack_1215029

https://www.youtube.com/watch?v=OgvOZ6OrJoY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=24


import java.util.* ;
import java.io.*; 

//BF
public class Solution {
    public static int maxProfit(int i, int W,int[] p,int[] w){
        //base case
        if(i==0){
            if(w[i]<=W)
                return (W/w[i])*p[i];
            return 0;
        }
        int take=Integer.MIN_VALUE;
        int notTake=0+maxProfit(i-1,W,p,w);
        if(w[i]<=W)
            take=p[i]+maxProfit(i,W-w[i],p,w);
        
        return Math.max(take,notTake);
    }
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        return maxProfit(n-1,w,profit,weight);
    }
}


//Memoization
public class Solution {
    public static int maxProfit(int i, int W,int[] p,int[] w,int[][] dp){
        //base case
        if(i==0){
            if(w[i]<=W)
                return (W/w[i])*p[i];
            return 0;
        }
        
        if(dp[i][W]!=-1)
            return dp[i][W];
        int take=Integer.MIN_VALUE;
        int notTake=0+maxProfit(i-1,W,p,w,dp);
        if(w[i]<=W)
            take=p[i]+maxProfit(i,W-w[i],p,w,dp);
        
        return dp[i][W]=Math.max(take,notTake);
    }
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        int[][] dp=new int[n][w+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return maxProfit(n-1,w,profit,weight,dp);
    }
}


//Tabulation
public class Solution {
    public static int unboundedKnapsack(int n, int W, int[] p, int[] w) {
        int[][] dp=new int[n][W+1];
        for(int i=0;i<=W;i++){
            dp[0][i]=(i/w[0])*p[0];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=W;j++){
                int take=Integer.MIN_VALUE;
                int notTake=0+dp[i-1][j];
                if(w[i]<=j)
                    take=p[i]+dp[i][j-w[i]];    
                
                dp[i][j]=Math.max(take,notTake);
            }
        }
        return dp[n-1][W];
    }
}

//Space Optimization
public class Solution {
    public static int unboundedKnapsack(int n, int W, int[] p, int[] w) {
        int[] prev=new int[W+1];
        int[] cur=new int[W+1];
        for(int i=0;i<=W;i++){
            prev[i]=(i/w[0])*p[0];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=W;j++){
                int take=Integer.MIN_VALUE;
                int notTake=0+prev[j];
                if(w[i]<=j)
                    take=p[i]+cur[j-w[i]];    
                
                cur[j]=Math.max(take,notTake);
            }
            prev=cur;
        }
        return prev[W];
    }
}
