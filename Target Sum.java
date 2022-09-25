https://www.codingninjas.com/codestudio/problems/target-sum_4127362
https://www.youtube.com/watch?v=b3GD8263-PQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=22


Similar to the Partitions With Given Difference problem. 
https://www.codingninjas.com/codestudio/problems/partitions-with-given-difference_3751628


TC:O(n*target)
SC:O(n*target)
  
  
import java.util.* ;
import java.io.*; 
public class Solution {
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
                
                dp[i][j]=(notPick+pick);
            }
        }
        return dp[n-1][tar];
    }
    
    public static int targetSum(int n, int target, int[] arr) {
    	return countPartitions(n,target,arr);
    }
}
