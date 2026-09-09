package com.ssafy.p3421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[] non_use;
	static int N;
	static int res;
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(reader.readLine());
		for(int t = 0; t<test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			//재료의 종류
			N = Integer.parseInt(st.nextToken());
			//사용하면 안되는 쌍의 종류
			int M = Integer.parseInt(st.nextToken());
			
			
			//마스크 목록
			non_use = new int[M];
			for(int i = 0; i<M; i++) {
				st = new StringTokenizer(reader.readLine());
				int mask = 0|(1 << (Integer.parseInt(st.nextToken())-1));
				mask = mask |(1 << (Integer.parseInt(st.nextToken())-1));
				non_use[i] = mask;
			}
			
			res = 0; 
			combination(0,0);
			System.out.println("#"+(t+1)+" "+res);
		}
	}
	
	static void combination(int idx, int flag) {
		if(idx == N) {
			for(int i: non_use) {
				if((flag & i) == i)
					return;
			}
			res++;
			return;
		}
		

		combination(idx+1,flag|(1 << idx));
		combination(idx+1, flag);
	}
}
