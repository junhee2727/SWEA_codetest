package com.ssafy.p6782;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int res;
	static boolean[] vis;
	
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		for(int t = 1; t<=test_case; t++) {
			// 2 <= N <= 10^12
			long N = Long.parseLong(reader.readLine().trim());
			res = 0;
			
			//2면 종료
			while(N != 2) {
				double rtN = Math.sqrt(N);
				long rtValue = (long)rtN;
				if(rtN == rtValue) {
					N = rtValue;
					res ++;
				}
				
				//다음 N^2 까지 더하기
				else {
					long nextN = (long) Math.pow((rtValue + 1), 2);
					res += nextN - N;
					N = nextN;
				}
			}

			
			output.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
}
