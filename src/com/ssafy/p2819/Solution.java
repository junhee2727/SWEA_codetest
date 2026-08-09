package com.ssafy.p2819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	static Integer[][] graph;
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t<test_case; t++) {
			//가지치기용
			Set<Map<int[], String>> visited = new HashSet<>();
			//BFS 용
			Deque<Map<int[], String>> deq = new ArrayDeque<>();
			graph = new Integer[4][4];
			for(int i = 0; i<4; i++) {
				st = new StringTokenizer(reader.readLine());
				for( int j = 0; j < 4; j++) {
					int num = Integer.parseInt(st.nextToken());
					graph[i][j] = num;
//					Map<int[], String> node = new HashMap<int[], String>(){[i,j], ""+num};
				}
			}
			
		}
	}
	
	static boolean isValid(int x, int y, int N) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}	
