https://www.codingninjas.com/codestudio/problems/frog-jump_3621012?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0

https://www.youtube.com/watch?v=EgG3jsGoPvQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=4

//BF
//TC:O(2^N)
//SC:O(N)
public class Solution {
    public static int findMinEnergy(int ind,int[] h){
        if(ind==0)
            return 0;
        int left=findMinEnergy(ind-1,h)+Math.abs(h[ind]-h[ind-1]);
        int right=Integer.MAX_VALUE;
        if(ind>1)
            right=findMinEnergy(ind-2,h)+Math.abs(h[ind]-h[ind-2]);
        return Math.min(left,right);
    }
    public static int frogJump(int n, int heights[]) {
        return findMinEnergy(n-1,heights);            
    }

}


//Memoization
//TC:O(N) since repeated subproblems are memoized
//SC:O(2N)
public class Solution {
    public static int findMinEnergy(int ind,int[] h,int[] dp){
        if(ind==0)
            return 0;
        if(dp[ind]!=-1)
               return dp[ind];
        int left=findMinEnergy(ind-1,h,dp)+Math.abs(h[ind]-h[ind-1]);
        int right=Integer.MAX_VALUE;
        if(ind>1)
            right=findMinEnergy(ind-2,h,dp)+Math.abs(h[ind]-h[ind-2]);
        return dp[ind]=Math.min(left,right);
    }
    public static int frogJump(int n, int heights[]) {
        int[] dp=new int[n+1];
        //Arrays.fill(dp,-1);
        for(int i=0;i<=n;i++)
            dp[i]=-1;
        return findMinEnergy(n-1,heights,dp);            
    }

}



//Tabulation
//TC:O(N)
//SC:O(N)
public class Solution {
    public static int frogJump(int n, int h[]) {
        int[] dp=new int[n+1];
        dp[0]=0;
        for(int i=1;i<n;i++){
            int oneStep=dp[i-1]+Math.abs(h[i]-h[i-1]);
            int twoStep=Integer.MAX_VALUE;
            if(i>1)
                twoStep=dp[i-2]+Math.abs(h[i]-h[i-2]);
            dp[i]=Math.min(oneStep,twoStep);
        }          
        return dp[n-1];
    }
}


public class Solution {
    public static int frogJump(int n, int h[]) {
        int prev2=0;
        int prev1=0;
        for(int i=1;i<n;i++){
            int oneStep=prev1+Math.abs(h[i]-h[i-1]);
            int twoStep=Integer.MAX_VALUE;
            if(i>1)
                twoStep=prev2+Math.abs(h[i]-h[i-2]);
            int cur=Math.min(oneStep,twoStep);
            prev2=prev1;
            prev1=cur;
        }          
        return prev1;
    }
}
