https://www.codingninjas.com/codestudio/problems/partition-equal-subset-sum_892980

https://www.youtube.com/watch?v=7win3dcgo3k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=16

import java.util.*;

//BF
//TC:O(2^n)
//SC:O(n)

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
    
    
	public static boolean canPartition(int[] arr, int n) {
		int target=0;
        for(int x:arr)
            target+=x;
                
        //if odd no result possible
        if(target%2!=0)
            return false;
        target/=2;
        return checkSubsetSum(n-1,target,arr);
	}
}


//Memoization
//TC:O(n*target)
//SC:O(n*target)
public class Solution {
       
    public static boolean checkSubsetSum(int ind,int target, int[] arr,int[][] dp){                   
       if(target==0)
           return true;
        if(ind==0)
            return arr[0]==target;
        if(dp[ind][target]!=-1){
            if(dp[ind][target]==0)
                return false;
            return true;
        }
        boolean notTake=checkSubsetSum(ind-1,target,arr,dp);
        boolean take=false;
        if(arr[ind]<=target)
            take=checkSubsetSum(ind-1,target-arr[ind],arr,dp);
        if(take | notTake)
            dp[ind][target]=1;
        else
            dp[ind][target]=0;
        return take | notTake;
    }
    
    
    public static boolean canPartition(int[] arr, int n) {
        int target=0;
        for(int x:arr)
            target+=x;
                
        //if odd no result possible
        if(target%2!=0)
            return false;
        target/=2;
        
        int[][] dp=new int[n+1][target+1];
        for(int i=0;i<=n;i++)
            Arrays.fill(dp[i],-1);
        return checkSubsetSum(n-1,target,arr,dp);
    }
}


//Tabulation
//TC:O(n*target)
//SC:O(n*target)
public class Solution {
    public static boolean canPartition(int[] arr, int n) {
        int target=0;
        for(int x:arr)
            target+=x;
                
        //if odd no result possible
        if(target%2!=0)
            return false;
        target/=2;
        
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
        return dp[n][target];
    }
}


//Space optimizatin
//TC:O(n*target)
//SC:O(n)
public class Solution {
    public static boolean canPartition(int[] arr, int n) {
        int target=0;
        for(int x:arr)
            target+=x;
                
        //if odd no result possible
        if(target%2!=0)
            return false;
        target/=2;
        
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
        return prev[target];
    }
}

