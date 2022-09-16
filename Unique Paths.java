//https://www.codingninjas.com/codestudio/problems/total-unique-paths_1081470?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0

You are present at point ‘A’ which is the top-left cell of an M X N matrix, your destination is point ‘B’, which is the bottom-right cell of the same matrix. Your task is to find the total number of unique paths from point ‘A’ to point ‘B’.In other words, you will be given the dimensions of the matrix as integers ‘M’ and ‘N’, your task is to find the total number of unique paths from the cell MATRIX[0][0] to MATRIX['M' - 1]['N' - 1].
  
  
  import java.util.* ;
import java.io.*; 

//TC:O(2^(m*n))
//SC: Path length
public class Solution {
    public static int countPaths(int i,int j){
        if(i==0 && j==0)
            return 1;
        if(i<0 || j<0) //out of bounds
            return 0;
        int left=countPaths(i,j-1);
        int up=countPaths(i-1,j);
        return left+up;
    }
	public static int uniquePaths(int m, int n) {
		return countPaths(m-1,n-1);
	}
}

//TC: O(M*N)
//SC:O(2(M*N))
public class Solution {
    public static int countPaths(int i,int j,int[][] dp){
        if(i==0 && j==0)
            return 1;
        if(i<0 || j<0) //out of bounds
            return 0;
        if(dp[i][j]!=-1)
            return dp[i][j];
        int left=countPaths(i,j-1,dp);
        int up=countPaths(i-1,j,dp);
        return dp[i][j]=left+up;
    }
    public static int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[i][j]=-1;
            }
        }
        return countPaths(m-1,n-1,dp);
    }
}

TC:O(M*N)
SC:O(M*N)
public class Solution {
    public static int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0)
                    dp[i][j]=1;
                else{
                    int up=0,left=0;
                    if(j>0) left=dp[i][j-1];
                    if(i>0) up=dp[i-1][j];
                    dp[i][j]=left+up;
                }                      
            }
        }
        return dp[m-1][n-1];
    }
}

//TC:O(M*N)
//SC:O(N)
public class Solution {
    public static int uniquePaths(int m, int n) {
        int[] prev=new int[n];
        
        for(int i=0;i<m;i++){
            int[] cur=new int[n];
            for(int j=0;j<n;j++){
                if(i==0 && j==0)
                    cur[j]=1;
                else{
                    int up=0,left=0;
                    if(j>0) left=cur[j-1];
                    if(i>0) up=prev[j];
                    cur[j]=left+up;
                }                      
            }
            prev=cur;
        }
        return prev[n-1];
    }
}
