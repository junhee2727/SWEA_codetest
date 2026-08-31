package com.ssafy.p2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[][] graph;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			int N = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			graph = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				String st = reader.readLine();
				for(int j = 0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.charAt(j)+"");
				}
			}
			
			
			int offset = 0;
			int mid = N/2;
			int res = 0;
			
			for(int i = 0; i<=mid; i++) {
				for(int j = mid - offset; j <= mid + offset; j++) {
					res += graph[i][j];
				}
				offset++;
			}
			offset-= 2;
			for(int i = mid+1; i<N; i++) {
				for(int j = mid - offset; j <= mid + offset; j++) {
					res += graph[i][j];
				}
				offset--;
			}
			
			
			output.append("#").append(t+1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
}
