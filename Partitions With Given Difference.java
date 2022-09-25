https://www.codingninjas.com/codestudio/problems/partitions-with-given-difference_3751628

https://www.youtube.com/watch?v=zoilQD1kYSg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=20


import java.util.* ;
import java.io.*; 


//Memoization
public class Solution {
    static int mod=(int)Math.pow(10,9)+7;
    public static int countWays(int i,int sum,int[] num,int[][] dp){
        if(i==0){
            if(sum==0 && num[0]==0)
                return 2;
            if(sum==0 || sum==num[0])
                return 1;
            else
                return 0;
        }
        if(dp[i][sum]!=-1)
            return dp[i][sum];
        int notPick=countWays(i-1,sum,num,dp);
        int pick=0;
        if(sum>=num[i])
            pick=countWays(i-1,sum-num[i],num,dp);
        return dp[i][sum]=(notPick+pick)%mod;
    }
	public static int countPartitions(int n, int d, int[] arr) {
		int sum=0;
        for(int x:arr)
            sum+=x;
        
        if((sum-d)<0 || (sum-d)%2!=0)
            return 0;
        int target=(sum-d)/2;
        int[][] dp=new int[n][target+1];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return countWays(n-1,target,arr,dp);
	}
}


//Tabulation
public class Solution {
    static int mod=(int)Math.pow(10,9)+7;
    public static int countPartitions(int n, int d, int[] num) {
        int sum=0;
        for(int x:num)
            sum+=x;
        
        if((sum-d)<0 || (sum-d)%2!=0)
            return 0;
        int tar=(sum-d)/2;
//         int n=num.length;
        int[][] dp=new int[n][tar+1];
        
        if(num[0]==0)
            dp[0][0]=2;
        else
            dp[0][0]=1;
         
        if(num[0]!=0 && num[0]<=tar) 
                dp[0][num[0]]=1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=tar;j++){
                int notPick=dp[i-1][j];
                int pick=0;
                if(j>=num[i])
                    pick=dp[i-1][j-num[i]];  
                
                dp[i][j]=(notPick+pick)%mod;
            }
        }
        return dp[n-1][tar];
    }
}


//Space Optimization
public class Solution {
    static int mod=(int)Math.pow(10,9)+7;
    public static int countPartitions(int n, int d, int[] num) {
        int sum=0;
        for(int x:num)
            sum+=x;
        
        if((sum-d)<0 || (sum-d)%2!=0)
            return 0;
        int tar=(sum-d)/2;

        int[] prev=new int[tar+1];
        int[] cur=new int[tar+1];
        if(num[0]==0)
            prev[0]=2;
        else
            prev[0]=1;
         
        if(num[0]!=0 && num[0]<=tar) 
                prev[num[0]]=1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=tar;j++){
                int notPick=prev[j];
                int pick=0;
                if(num[i]<=j)
                    pick=prev[j-num[i]];  
                
                cur[j]=(notPick+pick)%mod;
            }
            prev=cur;
        }
        return prev[tar];
    }
}
