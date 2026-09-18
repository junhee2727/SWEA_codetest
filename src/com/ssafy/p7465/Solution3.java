package com.ssafy.p7465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution3 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		for(int t = 1; t<=test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			
			//N : 사람의 수, M: 관계의 수 
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parent = new int[N + 1];
			
			// parent 배열을 -1 로 초기화
			// 값이 -1 인 node 는 부모 노드
			Arrays.fill(parent, -1);
			
			
			for(int i = 0; i < M; i++) { 
				st = new StringTokenizer(reader.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a,b);
			}
			
			int res = 0;
			for(int i = 1; i <= N; i++) {
				if(parent[i] == -1)
					res ++;
			}
			
			output.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB)
			return false;
		
		parent[rootB] = rootA;
		return true;
	}
	
	static int find(int a) {
		if(parent[a] < 0)
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
}
