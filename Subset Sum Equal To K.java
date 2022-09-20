https://www.codingninjas.com/codestudio/problems/subset-sum-equal-to-k_1550954

https://www.youtube.com/watch?v=tRpkluGqINc

import java.util.* ;
import java.io.*; 

//BF
//TC: O(2^N)
//SC: O(N)
public class Solution {
    public static boolean checkSubsetSum(int ind,int target, int[] arr){                   
       if(target==0)
           return true;
        if(ind==0)
            return arr[0]==target;
        boolean notTake=checkSubsetSum(ind-1,target,arr);
        boolean take=false;
        if(arr[ind]<=target)
            take=checkSubsetSum(ind-1,target-arr[ind],arr);
        return take | notTake;    
    }
    public static boolean subsetSumToK(int n, int k, int arr[]){
        return checkSubsetSum(n-1,k,arr);
    }
}

//Memoization
//TC:O(N*target)
//SC:O(N*target)+O(N)
public class Solution {
    public static boolean checkSubsetSum(int ind,int target, int[] arr,int[][] dp){
        if(target==0)
           return true;
        if(ind==0)
            return arr[0]==target;
        if(dp[ind][target]!=-1)                                                                                 
            return (dp[ind][target]==0)?false:true;  
        boolean notTake=checkSubsetSum(ind-1,target,arr,dp);
        boolean take=false;
        if(arr[ind]<=target)
            take=checkSubsetSum(ind-1,target-arr[ind],arr,dp);
        dp[ind][target]=(take || notTake)==true?1:0;                                                                                 
        return take || notTake;    
    }
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int limit=(int)Math.pow(10,3);
        int[][] dp=new int[limit+1][limit+1];
        for(int i=0;i<limit;i++)
            Arrays.fill(dp[i],-1);
        return checkSubsetSum(n-1,k,arr,dp);
    }
}


//https://www.youtube.com/watch?v=tRpkluGqINc
//TC:O(n*k)
//sc:O(n*k)
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int limit=(int)Math.pow(10,3);
        boolean[][] dp=new boolean[n+1][k+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=k;j++){
                if(i==0 && j==0)
                    dp[i][j]=true;
                else if(i==0)
                    dp[i][j]=false;
                else if(j==0)
                    dp[i][j]=true;
                else{
                    if(dp[i-1][j]==true)
                        dp[i][j]=true;
                    else if(arr[i-1]<=j && dp[i-1][j-arr[i-1]]==true)
                        dp[i][j]=true;
                }
            }
        }
        return dp[n][k];
    }
}

//Space Optimized
//TC:O(n*k)
//SC:O(k)
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int limit=(int)Math.pow(10,3);
        boolean[] prev=new boolean[k+1];
        
        prev[0]=true;
        
        for(int i=1;i<=n;i++){
            boolean[] cur=new boolean[k+1];
           // cur[i][0]=true;
            for(int j=0;j<=k;j++){
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
        return prev[k];
    }
}
