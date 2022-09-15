https://www.codingninjas.com/codestudio/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0
https://www.youtube.com/watch?v=AE39gJYuRog&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=8

// Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. 
// (Running, Fighting Practice or Learning New Moves).
// Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. 
// Can you help Ninja find out the maximum merit points Ninja can earn?


//BF
public class Solution {
    public static int getMaxPoints(int day,int last,int[][] points){
        
        //base case
        if(day==0){
            int maxi=0;
            for(int i=0;i<3;i++){
                if(i!=last)
                    maxi=Math.max(maxi,points[0][i]);
            }
            return maxi;
        }
        
        int maxi=0;
        for(int i=0;i<3;i++){
            if(i!=last){
                int point=points[day][i]+getMaxPoints(day-1,i,points);
                maxi=Math.max(maxi,point);   
            }
        }
        return maxi;
    }
    public static int ninjaTraining(int n, int points[][]) {
        // Write your code here..
        return getMaxPoints(n-1,3,points);
    }

}


//Memoization
//TC:O((N*4)*3)
//SC:O(N^2) if column size is n, here it is 4, so 4N
public class Solution {
    public static int getMaxPoints(int day,int last,int[][] points,int[][] dp){  
        //base case
        if(day==0){
            int maxi=0;
            for(int i=0;i<3;i++){
                if(i!=last)
                    maxi=Math.max(maxi,points[0][i]);
            }
            return maxi;
        }
        if(dp[day][last]!=-1)
            return dp[day][last];
        int maxi=0;
        for(int i=0;i<3;i++){
            if(i!=last){
                int point=points[day][i]+getMaxPoints(day-1,i,points,dp);
                maxi=Math.max(maxi,point);   
            }
        }
        return dp[day][last]=maxi;
    }
    public static int ninjaTraining(int n, int points[][]) {
        // Write your code here..
        int[][] dp=new int[n][4];
        for(int i=0;i<n;i++){
            for(int j=0;j<4;j++)
                dp[i][j]=-1;
        }
        
        return getMaxPoints(n-1,3,points,dp);
    }

}

//TC:O(N*4*3)
//SC:O(4N)
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {
        // Write your code here..
        int[][] dp=new int[n][4];
        dp[0][0]=Math.max(points[0][1],points[0][2]);
        dp[0][1]=Math.max(points[0][0],points[0][2]);
        dp[0][2]=Math.max(points[0][0],points[0][1]);
        dp[0][3]=Math.max(points[0][0],Math.max(points[0][1],points[0][2]));
                          
        //int day=0,last=0,task=0;
        for(int day=1;day<n;day++){
            for(int last=0;last<4;last++){
                dp[day][last]=0;
                for(int task=0;task<3;task++){
                    if(task!=last){
                        int point=points[day][task]+dp[day-1][task];
                        dp[day][last]=Math.max(dp[day][last],point);   
                    }    
                }
            }
        }
        return dp[n-1][3];
    }

}


//Space Optimized
//TC:O(N*4*3)
//SC:O(1)
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {
        // Write your code here..
        int[] dp=new int[4];
        dp[0]=Math.max(points[0][1],points[0][2]);
        dp[1]=Math.max(points[0][0],points[0][2]);
        dp[2]=Math.max(points[0][0],points[0][1]);
        dp[3]=Math.max(points[0][0],Math.max(points[0][1],points[0][2]));
                          
        //int day=0,last=0,task=0;
        for(int day=1;day<n;day++){
            int[] cur=new int[4]; 
            for(int last=0;last<4;last++){
                cur[last]=0;
                for(int task=0;task<3;task++){
                    if(task!=last){
                        int point=points[day][task]+dp[task];
                        cur[last]=Math.max(cur[last],point);   
                    }    
                }
            }
            dp=cur;
        }
        return dp[3];
    }

}
