https://www.codingninjas.com/codestudio/problems/minimum-elements_3843091?leftPanelTab=0

https://www.youtube.com/watch?v=myPeWb3Y68A&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=21


//BF
public class Solution {
    public static int findMinEle(int i,int num[],int target,int[][] dp){
        if(i==0){
            if(target%num[i]==0)
                return target/num[i];
            else
                return (int)Math.pow(10,9);
        }
        if(dp[i][target]!=-1)
            return dp[i][target];
        int notTake=0+findMinEle(i-1,num,target,dp);
        int take=(int)Math.pow(10,9);
        if(num[i]<=target)
            take=1+findMinEle(i,num,target-num[i],dp);
        
        return dp[i][target]=Math.min(take,notTake);
    }
    public static int minimumElements(int num[], int target) {
        int n=num.length;
        int[][] dp=new int[n][target+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        int ans=findMinEle(n-1,num,target,dp);
        if(ans>=Math.pow(10,9))
            return -1;
        return ans;
    }

}


//Memoization
import java.util.* ;
import java.io.*; 
import java.lang.*;
public class Solution {
    public static int findMinEle(int i,int num[],int target,int[][] dp){
        if(i==0){
            if(target%num[i]==0)
                return target/num[i];
            else
                return (int)Math.pow(10,9);
        }
        if(dp[i][target]!=-1)
            return dp[i][target];
        int notTake=0+findMinEle(i-1,num,target,dp);
        int take=(int)Math.pow(10,9);
        if(num[i]<=target)
            take=1+findMinEle(i,num,target-num[i],dp);
        
        return dp[i][target]=Math.min(take,notTake);
    }
    public static int minimumElements(int num[], int target) {
        int n=num.length;
        int[][] dp=new int[n][target+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        int ans=findMinEle(n-1,num,target,dp);
        if(ans>=Math.pow(10,9))
            return -1;
        return ans;
    }

}


//Tabulation
public class Solution {
    public static int minimumElements(int num[], int target) {
        int n=num.length;
        int[][] dp=new int[n][target+1];
        
        //base case
        for(int T=0;T<=target;T++){
            if(T%num[0]==0)
                dp[0][T]=T/num[0];
            else
                dp[0][T]=(int)Math.pow(10,9);
        }
        
        for(int i=1;i<n;i++){
            for(int j=0;j<=target;j++){
                int notTake=0+dp[i-1][j];
                int take=(int)Math.pow(10,9);
                if(num[i]<=j)
                    take=1+dp[i][j-num[i]];    
                dp[i][j]=Math.min(take,notTake);
            }
        }
        int ans=dp[n-1][target];
        if(ans>=Math.pow(10,9))
            return -1;
        return ans;
    }

}


//Space Optimization
public class Solution {
    public static int minimumElements(int num[], int target) {
        int n=num.length;
        int[] prev=new int[target+1];
        
        //base case
        for(int T=0;T<=target;T++){
            if(T%num[0]==0)
                prev[T]=T/num[0];
            else
                prev[T]=(int)Math.pow(10,9);
        }
        
        for(int i=1;i<n;i++){
            int[] cur=new int[target+1];
            for(int j=0;j<=target;j++){
                int notTake=0+prev[j];
                int take=(int)Math.pow(10,9);
                if(num[i]<=j)
                    take=1+cur[j-num[i]];    
                cur[j]=Math.min(take,notTake);
            }
            prev=cur;
        }
        int ans=prev[target];
        if(ans>=Math.pow(10,9))
            return -1;
        return ans;
    }

}
