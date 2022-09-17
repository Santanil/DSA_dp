https://www.codingninjas.com/codestudio/problems/maze-obstacles_977241

https://www.youtube.com/watch?v=TmhpgXScLyY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=10

import java.util.*;
public class Solution {
    public static int countPath(int i,int j,ArrayList<ArrayList<Integer>> mat){
        if(mat.get(i).get(j)==-1 || (i<0 || j<0))
            return 0;
        if(i==0 && j==0)
            return 1;
        double left=0.0,right=0.0;
        if(j>0) left=countPath(i,j-1,mat)%(Math.pow(10,9)+7);
        if(i>0) right=countPath(i-1,j,mat)%(Math.pow(10,9)+7);
        return (int)((left+right)%(Math.pow(10,9)+7));
    }
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        return countPath(n-1,m-1,mat);        
    }

}

//memoization
public class Solution {
    public static int countPath(int i,int j,ArrayList<ArrayList<Integer>> mat, int[][] dp){
        if(mat.get(i).get(j)==-1 || (i<0 || j<0))
            return 0;
        if(i==0 && j==0)
            return 1;
        if(dp[i][j]!=-1)
            return dp[i][j];               
        double left=0.0,right=0.0;
        if(j>0) left=countPath(i,j-1,mat,dp)%(Math.pow(10,9)+7);
        if(i>0) right=countPath(i-1,j,mat,dp)%(Math.pow(10,9)+7);
        return dp[i][j]=(int)((left+right)%(Math.pow(10,9)+7));
    }
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int[][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                dp[i][j]=-1;
        }
        return countPath(n-1,m-1,mat,dp);        
    }

}

public class Solution {
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int[][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat.get(i).get(j)==-1)
                    dp[i][j]=0;
                else if(i==0 && j==0)
                    dp[i][j]=1;
                else{
                    double left=0.0,right=0.0;
                    if(j>0) left=dp[i][j-1]%(Math.pow(10,9)+7);
                    if(i>0) right=dp[i-1][j]%(Math.pow(10,9)+7);
                    dp[i][j]=(int)((left+right)%(Math.pow(10,9)+7));
                }
            }
        }
        return dp[n-1][m-1];        
    }

}
