package com.ssafy.p5643;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2 {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static long[][] adjmatrix;
	
	public static void main(String[] args)throws IOException{
		
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 1; t<= test_case;t++) {
			
			int N = Integer.parseInt(reader.readLine().trim());
			int M = Integer.parseInt(reader.readLine().trim());
			
			adjmatrix = new long[N][N];
			
			for(long[] row: adjmatrix) {
				Arrays.fill(row, Integer.MAX_VALUE);
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(reader.readLine());
				
				int start = Integer.parseInt(st.nextToken())-1;
				int end = Integer.parseInt(st.nextToken())-1;
				adjmatrix[start][end] = 1;
			}
			
			for(int i = 0; i < N; i++) {
				adjmatrix[i][i] = 0;
			}
			
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					if(i == k) continue;
					
					for(int j = 0; j < N; j++) {
						if(j == i || j == k ) continue; 
						
						adjmatrix[i][j] = Math.min(adjmatrix[i][j], adjmatrix[i][k] + adjmatrix[k][j]);
					}
				}
			}
			
			int res = 0;
			for(int i = 0; i < N; i++) {
				boolean flag = true;
				for(int j = 0; j < N; j++) {
					if(adjmatrix[i][j] != Integer.MAX_VALUE || adjmatrix[j][i] != Integer.MAX_VALUE) continue;
					
					flag = false;
					break;
				}
				
				if(flag) res++;
			}
			
//			System.out.println(Arrays.deepToString(adjmatrix));
			output.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
	
}
