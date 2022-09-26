https://www.youtube.com/watch?v=-zI4mrF2Pb4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=27



package random;

import java.util.Scanner;

public class Lcs {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter String 1:");
		String s1=sc.next();
		System.out.println("Enter String 2:");
		String s2=sc.next();
		
		
		System.out.println(getLCS(s1,s2));
		
	}

	private static String getLCS(String s1, String s2) {
		int i1=s1.length(),i2=s2.length();
		int[][] dp=new int[i1+1][i2+1];
		for(int i=1;i<i1+1;i++) {
			for(int j=1;j<i2+1;j++) {
				if(s1.charAt(i-1)==s2.charAt(j-1))
					dp[i][j]=1+dp[i-1][j-1];
				else
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		return getLCSString(s1.length(),s2.length(),s1,s2,dp);
	}

	private static String getLCSString(int i1, int i2, String s1, String s2, int[][] dp) {
		int index=dp[i1][i2]-1;
		String s="";
		while(i1>0 && i2>0) {
			if(s1.charAt(i1-1)==s2.charAt(i2-1)) {
				s=s1.charAt(i1-1)+s;
				index--;
				i1--;
				i2--;
			}else if(dp[i1-1][i2]>dp[i1][i2-1])
				i1--;
			else
				i2--;
		}
		return s;
	}
	
	
}
