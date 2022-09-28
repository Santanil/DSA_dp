https://leetcode.com/problems/longest-palindromic-subsequence/

https://www.youtube.com/watch?v=6i_T5kkfv4A&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=31


//Memoization
//TC:O(n^2) //n-> size of string
//SC:O(n^2)+O(n)
class Solution {
    public static int findLPS(int l1,String s1,int l2,String s2,int[][] dp){
        //base case
        if(l1==0 || l2==0)
            return 0;
        if(dp[l1-1][l2-1]!=-1)
            return dp[l1-1][l2-1];
        if(s1.charAt(l1-1)==s2.charAt(l2-1)){
            return dp[l1-1][l2-1]=1+findLPS(l1-1,s1,l2-1,s2,dp);    
        }else{
            return dp[l1-1][l2-1]=Math.max(findLPS(l1-1,s1,l2,s2,dp),findLPS(l1,s1,l2-1,s2,dp));
        }
        
    }
    public int longestPalindromeSubseq(String s) {
        int l1=s.length();
        int[][] dp=new int[l1+1][l1+1];
        for(int i=0;i<l1;i++)
            Arrays.fill(dp[i],-1);
        StringBuilder str=new StringBuilder(s);
        str.reverse();
        return findLPS(l1,s,l1,str.toString(),dp);    
    }
}


//Tabulation
//TC:O(n^2)
//SC:O(n^2)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int l1=s.length();
        int[][] dp=new int[l1+1][l1+1];
        StringBuilder str=new StringBuilder(s);
        str.reverse();
        String s_reverse=new String(str.toString());
        for(int j=0;j<=l1;j++) dp[0][j]=0;
        
        int res=0;
        for(int i=1;i<=l1;i++){
            for(int j=1;j<=l1;j++){
                if(s.charAt(i-1)==s_reverse.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                    res=Math.max(res,dp[i][j]);
                }else
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        
        return res;    
    }
}


//Space Optimized
//TC:O(n^2)
//SC:O(n)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int l1=s.length();
        int[] prev=new int[l1+1];
        StringBuilder str=new StringBuilder(s);
        str.reverse();
        String s_reverse=new String(str.toString());
        for(int j=0;j<=l1;j++) prev[j]=0;
        
        int res=0;
        for(int i=1;i<=l1;i++){
            int[] cur=new int[l1+1];
            cur[0]=0;
            for(int j=1;j<=l1;j++){
                if(s.charAt(i-1)==s_reverse.charAt(j-1)){
                    cur[j]=1+prev[j-1];
                    res=Math.max(res,cur[j]);
                }else
                    cur[j]=Math.max(prev[j],cur[j-1]);
            }
            prev=cur;
        }
        
        return res;    
    }
}
