package com.ssafy.p1263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static boolean graph[][];
	static int n;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			n = Integer.parseInt(st.nextToken());
			
			graph = new boolean[n][n];
			
			for(int i = 0; i<n; i++) {
				for(int j = 0; j<n; j++) {
					if(st.nextToken().equals("1")) {
						graph[i][j] = true;
						graph[j][i] = true;
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			for(int i = 0; i<n; i++) {
				int cnt = 0;
				for(int j = 0; j<n; j++) {
					cnt += BFS(i,j);
				}
			}
			
		}
	}
	
	static int BFS(int a, int b) {
		boolean visited[] = new boolean[n];
		visited[a] = true;
		
		
		return 0;
	}
}
