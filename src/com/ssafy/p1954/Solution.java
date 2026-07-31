package com.ssafy.p1954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer output = new StringBuffer();

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Integer[][] graph;

	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			graph = new Integer[n][n];
			// 방향 param
			int dir = 0;
			// 현재 위치 (currentx, currenty)
			int cx = 0;
			int cy = 0;
			int step = 1;
			graph[0][0] = step++;
			for(int i = 0; i< n*n-1; i++) {
				cx += dx[dir];
				cy += dy[dir];
				
			}
			output.append("#").append(t+1).append("\n");
			for(Integer[] row: graph) {
				for(Integer num: row) {
					output.append(num).append(" ");
				}
				output.append("\n");
			}
		}
		System.out.println(output);
	}

	public static boolean isValid(int x, int y, int n) {

		return x >= 0 && x < n && y>= 0 && y < n;
	}
}
