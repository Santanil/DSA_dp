https://www.codingninjas.com/codestudio/problems/triangle_1229398?leftPanelTab=0
https://www.youtube.com/watch?v=SrP-PiLSYC0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=12



import java.util.* ;
import java.io.*; 

//BF
//TC:O(2^N)
//SC:O(N)
public class Solution {
    public static int findMinPath(int i,int j, int n, int[][] triangle){
        if(i==n)
            return triangle[i][j];
        int down=triangle[i][j]+findMinPath(i+1,j,n,triangle);
        int downRight=triangle[i][j]+findMinPath(i+1,j+1,n,triangle);
        return Math.min(down,downRight);
    } 
    public static int minimumPathSum(int[][] triangle, int n) {
        return findMinPath(0,0,n-1,triangle);
    }
}


//Memoization
//TC: O(n^2)
//SC: O(n^2)+O(n)
public class Solution {
    public static int findMinPath(int i,int j, int n, int[][] triangle,int[][] dp){
        if(i==n)
            return triangle[i][j];
        if(dp[i][j]!=-1)
            return dp[i][j];
        int down=triangle[i][j]+findMinPath(i+1,j,n,triangle,dp);
        int downRight=triangle[i][j]+findMinPath(i+1,j+1,n,triangle,dp);
        return dp[i][j]=Math.min(down,downRight);
    } 
    public static int minimumPathSum(int[][] triangle, int n) {
        int[][] dp=new int[n][n];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return findMinPath(0,0,n-1,triangle,dp);
    }
}



//Tabulation
//TC: O(n^2)
//SC: O(n^2)  //extra stack space is omitted
public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        int[][] dp=new int[n][n];
        
        //base case
        for(int i=0;i<n;i++)
            dp[n-1][i]=triangle[n-1][i];
        
        for(int i=n-2;i>=0;i--){
            for(int j=i;j>=0;j--){
                int down=triangle[i][j]+dp[i+1][j];
                int downRight=triangle[i][j]+dp[i+1][j+1];  
                dp[i][j]=Math.min(down,downRight);
            }
        }
        return dp[0][0];
    }
}


//Space Optimization
//TC:O(N^2)
//SC:O(N)
public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        int[] prev=new int[n];
        
        //base case
        for(int i=0;i<n;i++)
            prev[i]=triangle[n-1][i];
        
        for(int i=n-2;i>=0;i--){
            int[] cur=new int[n];
            for(int j=i;j>=0;j--){
                int down=triangle[i][j]+prev[j];
                int downRight=triangle[i][j]+prev[j+1];  
                cur[j]=Math.min(down,downRight);
            }
            prev=cur;
        }
        return prev[0];
    }
}
