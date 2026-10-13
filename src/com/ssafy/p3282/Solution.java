package com.ssafy.p3282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 1; t<= test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Node[] node_list = new Node[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(reader.readLine());
				
				int weight = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				node_list[i] = new Node(weight, value);
			}
			
			
			int bag[][] = new int[N+1][K+1];
			
			
			//무게가 0일 때, 종류가 0일 떄에는 max_value = 0
			for(int y = 1; y <= N; y++) {
				int weight = node_list[y-1].weight;
				int value = node_list[y-1].value;
				
				for(int x = 1; x <= K; x++) {
					bag[y][x] = bag[y-1][x];
					
					if(x >= weight) bag[y][x] = Math.max(bag[y-1][x], bag[y-1][x-weight] + value);	
					
				}
			}
			
			
			output.append("#").append(t).append(" ").append(bag[N][K]).append("\n");
//			System.out.println(Arrays.deepToString(bag));
		}
		System.out.println(output);
		
		
	}
	
	static class Node{
		int weight;
		int value;
		
		public Node(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		} 
	}
}
