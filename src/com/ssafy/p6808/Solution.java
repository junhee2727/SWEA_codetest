package com.ssafy.p6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[][] gap_list;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for(int t = 0; t<test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			int[] arr_a = new int[9];
			int[] arr_b = new int[9];
			
			for(int i = 0; i<9; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				arr_a[i] = tmp;
			}
			
			int cnt = 0;
			for(int i = 1; i< 19; i++) {
				int flag = 1;
				for(int num : arr_a) {
					if(num == i) flag = 0;
				}
				if(flag == 1) arr_b[cnt++] = i;
			}
			
			boolean[] visited = new boolean[9];
			
			int res = 0;
			res += dfs(0, 0, visited, arr_a, arr_b);
			
			int fac_9 = 1;
			for(int i = 1; i<= 9; i++) {
				fac_9 *= i;
			}
			fac_9 -= res;
			
			output.append("#").append(t+1).append(" ").append(res).append(" ").append(fac_9).append("\n");
			
		}
		System.out.println(output);
	}
	
	static int dfs(int depth, int value,boolean[] visited, int[] arr_a, int[] arr_b) {
		if(depth == 9) {
			return value > 0? 1: 0;
		}
		
		
		int res = 0;
		for(int i = 0; i<9; i++) {
			if(visited[i] == true) continue;
			visited[i] = true;
			int offset = arr_a[depth] > arr_b[i]? 1: -1; 
			res += dfs(depth + 1, value + (arr_a[depth] + arr_b[i]) * offset, visited, arr_a, arr_b);
			visited[i] = false;
		}
		
		return res;
	}
}
