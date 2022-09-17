https://www.codingninjas.com/codestudio/problems/minimum-path-sum_985349

https://www.youtube.com/watch?v=_rgTlyky1uQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=11

import java.util.* ;
import java.io.*; 
//BF
public class Solution {
    public static int findMinPath(int i,int j,int[][] grid){
        if(i==0 && j==0)
            return grid[0][0];
        if(i<0 || j<0)
            return (int)Math.pow(10,9);
        int left=grid[i][j]+findMinPath(i,j-1,grid);
        int up=grid[i][j]+findMinPath(i-1,j,grid);
        return Math.min(left,up);
    }
    public static int minSumPath(int[][] grid) {
        int n=grid.length,m=grid[0].length;
    	return findMinPath(n-1,m-1,grid);
    }
}

//Memoization
public class Solution {
    public static int findMinPath(int i,int j,int[][] grid,int[][] dp){
        if(i==0 && j==0)
            return grid[0][0];
        if(i<0 || j<0)
            return (int)Math.pow(10,9);
        if(dp[i][j]!=-1)
            return dp[i][j];
        int left=grid[i][j]+findMinPath(i,j-1,grid,dp);
        int up=grid[i][j]+findMinPath(i-1,j,grid,dp);
        return dp[i][j]=Math.min(left,up);
    }
    public static int minSumPath(int[][] grid) {
        int n=grid.length,m=grid[0].length;
        int[][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        return findMinPath(n-1,m-1,grid,dp);
    }
}

//Tabulation
public class Solution {
    public static int minSumPath(int[][] grid) {
        int n=grid.length,m=grid[0].length;
        int[][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 && j==0)
                    dp[i][j]=grid[i][j];
                else{
                    double left=Math.pow(10,9),up=Math.pow(10,9);
                    if(j>0) left=grid[i][j]+dp[i][j-1];
                    if(i>0) up=grid[i][j]+dp[i-1][j];
                    dp[i][j]=(int)Math.min(left,up);       
                }
            }
        }
        return dp[n-1][m-1];
    }
}

//Space Optimiaztion
public class Solution {
    public static int minSumPath(int[][] grid) {
        int n=grid.length,m=grid[0].length;
        int[] prev=new int[m];
        for(int i=0;i<n;i++){
            int[] cur=new int[m];
            for(int j=0;j<m;j++){
                if(i==0 && j==0)
                    cur[j]=grid[i][j];
                else{
                    double left=Math.pow(10,9),up=Math.pow(10,9);
                    if(j>0) left=grid[i][j]+cur[j-1];
                    if(i>0) up=grid[i][j]+prev[j];
                    cur[j]=(int)Math.min(left,up);       
                }
            }
            prev=cur;
        }
        return prev[m-1];
    }
}
