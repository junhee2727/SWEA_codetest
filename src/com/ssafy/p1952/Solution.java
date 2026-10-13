package com.ssafy.p1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 1; t <= test_case; t++) {
			
			int[] costs = new int[4];
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i < 4; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}
			
			
			int[] month = new int[12];
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[13];
			for(int i = 1; i < 13; i++) {;
				
				//1일권을 사용하는 경우, 1달권을 사용하는 경우 비교
				dp[i] = dp[i - 1] + Math.min(month[i-1]*costs[0], costs[1]);
				
				//3달권을 사용하는 경우
				if(i >= 3) {
					dp[i] = Math.min(dp[i], dp[i-3] + costs[2]);
				}
				
				//1년권을 사용하는 경우
				if(i >= 12) {
					dp[i] = Math.min(dp[i], costs[3]);
				}				
			}
			
			output.append("#").append(t).append(" ").append(dp[12]).append("\n");
		}
		
		System.out.println(output);
	}
	
	
}
