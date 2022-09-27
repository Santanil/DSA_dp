https://www.codingninjas.com/codestudio/problems/longest-common-substring_1235207

https://www.youtube.com/watch?v=_wP9mWNPL5w&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=28


import java.util.* ;
import java.io.*; 

//Tabulation
//TC:O(n*m)
//SC:O(n*m)
public class Solution {
	public static int lcs(String str1, String str2) {
		int l1=str1.length(),l2=str2.length();
        int[][] dp=new int [l1+1][l2+1];
        
        for(int i=0;i<=l1;i++) dp[i][0]=0;
        for(int j=0;j<=l2;j++) dp[0][j]=0;
        int ans=0;
        for(int i=1;i<=l1;i++){
            for(int j=1;j<=l2;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    //match
                    dp[i][j]=1+dp[i-1][j-1];   
                    ans=Math.max(ans,dp[i][j]);
                }else
                    dp[i][j]=0;  //not match
            }
        }
        return ans;
	}
}

//TC:O(n*m)
//SC:O(m)
public class Solution {
    public static int lcs(String str1, String str2) {
        int l1=str1.length(),l2=str2.length();
        int[] prev=new int [l2+1];
        
        for(int j=0;j<=l2;j++) prev[j]=0;
        int ans=0;
        for(int i=1;i<=l1;i++){
            int[] cur=new int[l2+1];
            cur[0]=0;
            for(int j=1;j<=l2;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    //match
                    cur[j]=1+prev[j-1];   
                    ans=Math.max(ans,cur[j]);
                }else
                    cur[j]=0;  //not match
            }
            prev=cur;
        }
        return ans;
    }
}
