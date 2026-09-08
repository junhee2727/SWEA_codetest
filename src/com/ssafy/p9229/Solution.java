package com.ssafy.p9229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int res;
	static int M;
	static int N;
	static int min;
	
	static int[] weights;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for(int t = 0; t<test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			// 봉지 개수
			N = Integer.parseInt(st.nextToken());
			// 최대 무게
			M = Integer.parseInt(st.nextToken());
			// 가능한 무게
			res = -1;
			// 무게 목록
			weights = new int[N];
			//최소 무게
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<N; i++) {
				int weight = Integer.parseInt(st.nextToken());
				weights[i] = weight;
				min = Math.min(min, weight);
			}
			
			Calculate(0, 0, 0);
			
			output.append("#").append(t + 1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
	
	static void Calculate(int depth, int value, int start) {
		if(value > M) return;
		
		if(depth == 2) {
			res = Math.max(value, res);
			return;
		}
		
		
		for(int i = start; i<N; i++) {
			int weight = weights[i];
			if(weight + min > M) continue;
			Calculate(depth + 1, value + weight, i + 1);
		}
		return;
	}
}
