package com.ssafy.p1486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int N,B,min_len;
	static int[] len_list;
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine());
		for(int t = 0; t<test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			len_list = new int[N];
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<N; i++) {
				len_list[i] = Integer.parseInt(st.nextToken()); 
			}
			
			min_len = Integer.MAX_VALUE;
			combination(0,0);
			
			output.append("#").append(t+1).append(" ").append(min_len).append("\n");
		}
		System.out.println(output);
	}
	
	static void combination(int idx, int value) {	
		if(idx == N) {
			if(value < B) return;
			
			int comp = value - B;
			min_len = Math.min(min_len, comp);
			return;
		}
		
		combination(idx + 1, value + len_list[idx]);
		combination(idx + 1, value);
		return;
	}
}
