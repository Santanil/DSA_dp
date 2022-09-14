You are given an array/list of ‘N’ integers. 
You are supposed to return the maximum sum of the subsequence with the constraint that no two elements are adjacent in the given array/list.
  
https://www.codingninjas.com/codestudio/problems/frog-jump_3621012?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0

https://www.youtube.com/watch?v=GrMBfJNk_NY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=6

//BF
//TC: O(2^N)
//SC:O(N)
import java.util.*;
public class Solution {
    public static int findMaxSum(int ind,ArrayList<Integer> nums){
        if(ind==0)
            return nums.get(ind);
        if(ind==-1)
            return 0;
        int pick=nums.get(ind)+findMaxSum(ind-2,nums);
        int notPick=0+findMaxSum(ind-1,nums);
        return Math.max(pick,notPick);
    }
    
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		return findMaxSum(nums.size()-1,nums);
	}
}


//Memoization
//TC:O(N)
//SC:O(N)
public class Solution {
    public static int findMaxSum(int ind,ArrayList<Integer> nums,int[] dp){
        if(ind==0)
            return nums.get(ind);
        if(ind==-1)
            return 0;
        if(dp[ind]!=-1)
            return dp[ind];
        int pick=nums.get(ind)+findMaxSum(ind-2,nums,dp);
        int notPick=0+findMaxSum(ind-1,nums,dp);
        return dp[ind]=Math.max(pick,notPick);
    }
    
    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        int[] dp=new int[nums.size()];
        Arrays.fill(dp,-1);
        return findMaxSum(nums.size()-1,nums,dp);
    }
}


//Tabulation
//TC:O(N)
//SC:O(N)
public class Solution {
   public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        int n=nums.size();
        int[] dp=new int[n];
        dp[0]=nums.get(0);
        for(int i=1;i<n;i++){
            int pick=nums.get(i);
            if(i>1)
                pick+=dp[i-2];
            int notPick=0+dp[i-1];
            dp[i]=Math.max(pick,notPick);
        }
        return dp[n-1];
    }
}

//Space Optimization
//TC:O(N)
//SC:(1)
public class Solution {
   public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        int n=nums.size();
        int prev2=0,prev=nums.get(0),cur;
        for(int i=1;i<n;i++){
            int pick=nums.get(i);
            if(i>1)
                pick+=prev2;
            int notPick=0+prev;
            cur=Math.max(pick,notPick);
            prev2=prev;
            prev=cur;
        }
        return prev;
    }
}
