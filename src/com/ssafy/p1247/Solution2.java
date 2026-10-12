package com.ssafy.p1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static boolean[] visited;
	static int[] minDist;
	static int[][] adjmatrix;
	static int[][] nodeList;
	
	public static void main(String[] args)throws IOException{
		reader.readLine();
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(reader.readLine().trim());
			
			visited = new boolean[N];
			minDist = new int[N];
			nodeList = new int[N][2];
			
			adjmatrix = new int[N][N];
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i < N; i++) {
				nodeList[i][0] = Integer.parseInt(st.nextToken());
				nodeList[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int cx = nodeList[i][0];
					int cy = nodeList[i][1];
					
					int nx = nodeList[j][0];
					int ny = nodeList[j][1];
					
					adjmatrix[i][j] =  Math.abs(nx - cx) + Math.abs(ny-cy);
				}
			}
			
			Arrays.fill(minDist, Integer.MAX_VALUE);
			minDist[0] = 0;
			
			for(int i = 0; i < N; i++) {
				
				
				
			}
			
		}
		
	}
	
}
