https://www.codingninjas.com/codestudio/problems/rod-cutting-problem_800284

https://www.youtube.com/watch?v=mO8XpGoJwuo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=26

import java.util.*;
//BF
public class Solution {
    public static int maxProfit(int i,int n,int[] price){
        //base case
        if(i==0)
            return n*price[0];
        int take=Integer.MIN_VALUE;
        int notTake=maxProfit(i-1,n,price);
        if((i+1)<=n)
            take=price[i]+maxProfit(i,n-(i+1),price);
        
        return Math.max(take,notTake);
    }
	public static int cutRod(int price[], int n) {
		return maxProfit(n-1,n,price);
	}
}


//Memoization
public class Solution {
    public static int maxProfit(int i,int n,int[] price,int[][] dp){
        //base case
        if(i==0)
            return n*price[0];
        if(dp[i][n]!=-1)
            return dp[i][n];
        int take=Integer.MIN_VALUE;
        int notTake=maxProfit(i-1,n,price,dp);
        if((i+1)<=n)
            take=price[i]+maxProfit(i,n-(i+1),price,dp);
        
        return dp[i][n]=Math.max(take,notTake);
    }
    public static int cutRod(int price[], int n) {
        int[][] dp=new int[n][n+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return maxProfit(n-1,n,price,dp);
    }
}

//Tabulation
public class Solution {
    public static int cutRod(int price[], int n) {
        int[][] dp=new int[n][n+1];
        for(int i=0;i<=n;i++)
            dp[0][i]=i*price[0];
        
        for(int i=1;i<n;i++){
            for(int j=0;j<=n;j++){
                int take=Integer.MIN_VALUE;
                int notTake=dp[i-1][j];
                if((i+1)<=j)
                    take=price[i]+dp[i][j-(i+1)];
                dp[i][j]=Math.max(take,notTake);
            }
        }
        return dp[n-1][n];
    }
}

//Space Optimized
public class Solution {
    public static int cutRod(int price[], int n) {
        int[] prev=new int [n+1];
        int[] cur=new int [n+1];
        for(int i=0;i<=n;i++)
            prev[i]=i*price[0];
        
        for(int i=1;i<n;i++){
            for(int j=0;j<=n;j++){
                int take=Integer.MIN_VALUE;
                int notTake=prev[j];
                if((i+1)<=j)
                    take=price[i]+cur[j-(i+1)];
                cur[j]=Math.max(take,notTake);
            }
            prev=cur;
        }
        return prev[n];
    }
}
