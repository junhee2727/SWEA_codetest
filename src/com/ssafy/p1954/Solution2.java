package com.ssafy.p1954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;
	
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			Integer[][] graph = new Integer[n][n];
			int dir = 0;
			int x = 0;
			int y = 0;
			for(int i = 1; i<= n*n; i++) {
				//현재 위치가 그래프 밖을 벗어나지 않았고, null 이면 저장
				if(isValid(x, y, n) && graph[y][x] == null) {
				}
				else {
					x-= dx[dir];
					y-= dy[dir];
					dir = (dir + 1 ) % 4;
					x += dx[dir];
					y += dy[dir];
				}
				graph[y][x] = i;
				x += dx[dir];
				y += dy[dir];
			}
			for(Integer[] row: graph) {
				System.out.println(Arrays.toString(row));
			}   
		}
	}
	static boolean isValid(int x,int y,int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}
