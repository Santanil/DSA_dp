https://www.codingninjas.com/codestudio/problems/0-1-knapsack_920542

https://www.youtube.com/watch?v=GqOmJHQZivw&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=20

import java.util.* ;
import java.io.*; 

//BF
public class Solution{
    public static int maxProfit(int i, int[] w, int[] v ,int maxWt){
        
        //base case
        if(i==0){
            if(w[0]<=maxWt)
                return v[0];
            else
                return 0;
        }
        
        int notTake=0+maxProfit(i-1,w,v,maxWt);
        int take=Integer.MIN_VALUE;
        if(w[i]<=maxWt)
            take=v[i]+maxProfit(i-1,w,v,maxWt-w[i]);
        
        return Math.max(take,notTake);
    }
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        return maxProfit(n-1,weight,value,maxWeight);    
    }
}

Memoization
public class Solution{
    public static int maxProfit(int i, int[] w, int[] v ,int maxWt, int[][] dp){
        
        //base case
        if(i==0){
            if(w[0]<=maxWt)
                return v[0];
            else
                return 0;
        }
        if(dp[i][maxWt]!=-1)
            return dp[i][maxWt];
        int notTake=0+maxProfit(i-1,w,v,maxWt,dp);
        int take=Integer.MIN_VALUE;
        if(w[i]<=maxWt)
            take=v[i]+maxProfit(i-1,w,v,maxWt-w[i],dp);
        
        return dp[i][maxWt]=Math.max(take,notTake);
    }
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp=new int[n][maxWeight+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return maxProfit(n-1,weight,value,maxWeight,dp);    
    }
}

//Tabulation
public class Solution{
   static int knapsack(int[] w, int[] v, int n, int maxWt) {
        int[][] dp=new int[n][maxWt+1];
        for(int i=w[0];i<=maxWt;i++)
            dp[0][i]=v[0];
        
        for(int i=1;i<n;i++){
            for(int j=0;j<=maxWt;j++){
                int notTake=0+dp[i-1][j];
                int take=Integer.MIN_VALUE;
                if(w[i]<=j)
                    take=v[i]+dp[i-1][j-w[i]];
                dp[i][j]=Math.max(take,notTake);
            }
        }
        return dp[n-1][maxWt];    
    }
}

//Space Optimized
//TC:O(n*maxWt)
//SC:O(maxWt)
public class Solution{
   static int knapsack(int[] w, int[] v, int n, int maxWt) {
        int[] cur=new int[maxWt+1];
        for(int i=w[0];i<=maxWt;i++)
            cur[i]=v[0];
        
        for(int i=1;i<n;i++){
            for(int j=maxWt;j>=0;j--){
                int notTake=0+cur[j];
                int take=Integer.MIN_VALUE;
                if(w[i]<=j)
                    take=v[i]+cur[j-w[i]];
                cur[j]=Math.max(take,notTake);
            }
        }
        return cur[maxWt];    
    }
}
