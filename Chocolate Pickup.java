https://www.youtube.com/watch?v=QGfn7JeXK54

import java.util.* ;
import java.io.*; 
//TC: O(3^N *3^N)
//SC:O(N) stack space
public class Solution {
    public static int findMax(int i, int j1, int j2, int r, int c, int[][] grid){
        
        //out of bounds
        if(j1<0 || j1>c || j2<0 || j2>c)
            return (int)Math.pow(-1,9);
        //base case when last row is reached
        if(i==r){
            if(j1==j2)
                return grid[i][j1];
            else
                return grid[i][j1]+grid[i][j2];
        }
        
        //explore all possibilites
        int max=(int)Math.pow(-1,9);
        for(int dj1=-1;dj1<=1;dj1++){
            for(int dj2=-1;dj2<=1;dj2++){
                int val=0;
                if(j1==j2)
                    val=grid[i][j1];
                else
                    val=grid[i][j1]+grid[i][j2];
                
                val+=findMax(i+1,j1+dj1,j2+dj2,r,c,grid);
                max=Math.max(max,val);
            }
        }
        return max;
    }
	public static int maximumChocolates(int r, int c, int[][] grid) {
		return findMax(0,0,c-1,r-1,c-1,grid);
	}
}


// TC:O(N*M*M*9)  9-> for 3*3 movements for each step
// SC:O(N*M*M)+O(N)
public class Solution {
    public static int findMax(int i, int j1, int j2, int r, int c, int[][] grid,int[][][] dp){
        
        //out of bounds
        if(j1<0 || j1>c || j2<0 || j2>c)
            return (int)Math.pow(-1,9);
        //base case when last row is reached
        if(i==r){
            if(j1==j2)
                return grid[i][j1];
            else
                return grid[i][j1]+grid[i][j2];
        }
        
        if(dp[i][j1][j2]!=-1)
            return dp[i][j1][j2];
        //explore all possibilites
        int max=(int)Math.pow(-1,9);
        for(int dj1=-1;dj1<=1;dj1++){
            for(int dj2=-1;dj2<=1;dj2++){
                int val=0;
                if(j1==j2)
                    val=grid[i][j1];
                else
                    val=grid[i][j1]+grid[i][j2];
                
                val+=findMax(i+1,j1+dj1,j2+dj2,r,c,grid,dp);
                max=Math.max(max,val);
            }
        }
        return dp[i][j1][j2]=max;
    }
    public static int maximumChocolates(int r, int c, int[][] grid) {
        int[][][] dp=new int[r][c][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                for(int k=0;k<c;k++){
                    dp[i][j][k]=-1;
                }
            }
        }
        return findMax(0,0,c-1,r-1,c-1,grid,dp);
    }
}
