package com.ssafy.p2117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[] costs;
	static int[][] grid;
	
	static int N;
	static int M;
	
	static int res;
	
	public static void main(String args[])throws IOException{
		costs = new int[23];
		for(int i = 1; i <= 22; i++) {
			costs[i] = i*i + (i-1) * (i-1);
		}
		
		int test_case = Integer.parseInt(reader.readLine().trim());
		for(int t = 1; t<= test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			grid = new int[N][N];
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(reader.readLine());
				for(int x = 0; x < N; x++) {
					grid[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			//한 집은 보장됨
			res = 1;
			
			//i: 마름모의 크기
			//홀수면 그대로, 짝수면 +1한 홀수로
			for(int i = 2; i <= N/2*2+1; i++) {
				for(int y = 0; y < N; y++) {
					for(int x = 0; x<N; x++) {
						calculate(x,y,i);
					}
				}
			}
						
			output.append("#").append(t).append(" ").append(res).append("\n");
			
		}
		System.out.println(output);
		
	}
	
	//손해를 보지 않는 한 최대한 많은 집에 서비스 제공
	static void calculate(int x, int y, int k) {
		int homeCount = 0;
		
		for(int hy = 0; hy < N; hy++) {
			for(int hx = 0; hx < N; hx++) {
				
				if(grid[hy][hx] == 0) continue;
				
				int distance = Math.abs(x - hx) + Math.abs(y - hy);
				
				if(distance < k) homeCount++;
				
			}
		}
		
		if(homeCount * M >= costs[k]) {
			res = Math.max(res, homeCount);
		}
		
	}

	static boolean isAvail(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
