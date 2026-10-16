package com.ssafy.p5643;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[][] adj_matrix, res_matrix;
	static boolean[] vis;
	static int N;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		for(int t = 1; t<= test_case; t++) {
			//N: 학생 수
			N = Integer.parseInt(reader.readLine().trim());
			//M: 두 학생의 수를 비교한 수
			int M = Integer.parseInt(reader.readLine().trim());
			
			//원본 인접 행렬
			adj_matrix = new int[N][N];
			for(int[] row: adj_matrix) {
				Arrays.fill(row, -1);
			}
			
			
			//원본과 결과 모두에 행렬 연결
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(reader.readLine());
				//문제는 1-based counting이므로 0-based 로 변경
				int start = Integer.parseInt(st.nextToken()) -1;
				int end = Integer.parseInt(st.nextToken()) -1;
				
				adj_matrix[start][end] = 1;
				res_matrix[start][end] = 1;
			}
			
			for(int i = 0; i < N; i++) {
				vis = new boolean[N];
				res_matrix[i][i] = 1;
				dfs(i,i);
			}
			
//			System.out.println(Arrays.deepToString(res_matrix));
			
			int res = 0;
			for(int[] row: res_matrix) {
				boolean flag = true;
				for(int i: row) {
					if(i == -1) {
						flag = false;
						break;
					}
				}
				if(flag)	res++;
			}
			
			output.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
	
	static void dfs(int start,int cur) {
		for(int i = 0; i < N; i++) {
			if(adj_matrix[cur][i] == -1)	continue;
			if(vis[i]) continue;
			
			res_matrix[start][i] = 1;
			res_matrix[i][start] = 1;
			vis[i] = true;
			dfs(start, i);
		}
		
	}
}
