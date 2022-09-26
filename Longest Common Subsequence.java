https://www.codingninjas.com/codestudio/problems/longest-common-subsequence_624879

https://www.youtube.com/watch?v=NPZn9jBrX8U&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=28

import java.util.*;
//BF
//TC: O(2^n *2^m)
public class Solution {
    public static int maxLen(int i1,int i2,String s,String t){
        //base case3
        if(i1<0 || i2<0)
            return 0;
        
        //match
        if(s.charAt(i1)==t.charAt(i2))
            return 1+maxLen(i1-1,i2-1,s,t);
        
        //not match
        return Math.max(maxLen(i1-1,i2,s,t),maxLen(i1,i2-1,s,t));
    }
    public static int lcs(String s, String t) {
        int l1=s.length(),l2=t.length();
        return maxLen(l1-1,l2-1,s,t);    
    }

}

//Memoization
//TC: O(n*m)
//SC: O(n*m) + O(n+m)
public class Solution {
    public static int maxLen(int i1,int i2,String s,String t,int[][] dp){
        //base case3
        if(i1<0 || i2<0)
            return 0;
        if(dp[i1][i2]!=-1)
            return dp[i1][i2];
        //match
        if(s.charAt(i1)==t.charAt(i2))
            return dp[i1][i2]=1+maxLen(i1-1,i2-1,s,t,dp);
        
        //not match
        return dp[i1][i2]=Math.max(maxLen(i1-1,i2,s,t,dp),maxLen(i1,i2-1,s,t,dp));
    }
    public static int lcs(String s, String t) {
        int l1=s.length(),l2=t.length();
        int[][] dp=new int[l1][l2];
        for(int i=0;i<l1;i++)
            Arrays.fill(dp[i],-1);
        return maxLen(l1-1,l2-1,s,t,dp);    
    }

}

//Tabulation
//TC: O(n*m)
//SC: O(n*m)
public class Solution {
    public static int lcs(String s, String t) {
        int l1=s.length(),l2=t.length();
        int[][] dp=new int[l1+1][l2+1];
        
        for(int i1=1;i1<=l1;i1++){
            for(int i2=1;i2<=l2;i2++){
                //match
                if(s.charAt(i1-1)==t.charAt(i2-1))
                    dp[i1][i2]=1+dp[i1-1][i2-1];
                else      
                    //not match
                    dp[i1][i2]=Math.max(dp[i1-1][i2],dp[i1][i2-1]);            
            }
        }
        return dp[l1][l2];    
    }

}

//Space Optimized
//TC: O(n*m)
//SC: O(m)
public class Solution {
    public static int lcs(String s, String t) {
        int l1=s.length(),l2=t.length();
        int[] prev=new int[l2+1];
        int[] cur=new int[l2+1];
        for(int i1=1;i1<=l1;i1++){
            for(int i2=1;i2<=l2;i2++){
                //match
                if(s.charAt(i1-1)==t.charAt(i2-1))
                    cur[i2]=1+prev[i2-1];
                else      
                    //not match
                    cur[i2]=Math.max(prev[i2],cur[i2-1]);            
            }
            prev=(int[])(cur.clone());
        }
        return prev[l2];    
    }

}
