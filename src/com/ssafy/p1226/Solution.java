package com.ssafy.p1226;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int dx[] = new int[]{1,-1,0,0};
	static int dy[] = new int[]{0,0,1,-1};
	
	public static void main(String[] args)throws IOException{		
		for(int t = 0; t<10; t++) {
			//테스트 케이스 번호 생략
			reader.readLine();
			
			boolean[][] visited = new boolean[16][16];
			char[][] grid = new char[16][16];
			Deque<int[]> deq = new ArrayDeque<int[]>();
			
			//[0]: y, [1]: x
			int[] cn = new int[] {-1,-1};
			int[] tn = new int[] {-1,-1};
			
			for(int i = 0; i<16; i++) {
				String s = reader.readLine();
				for(int j = 0; j<16; j++) {
					char tmp = s.charAt(j);
					if(tmp == '2') {
						cn = new int[] {i,j};
					}
					if(tmp == '3') {
						tn = new int[] {i,j};
					}
					grid[i][j] = tmp;
				}
			}
			
			deq.add(cn);
			visited[cn[0]][cn[1]] = true;
			
			
			boolean flag = false;
			while(!deq.isEmpty()) {
				cn = deq.pop();
				if(cn[0] == tn[0] && cn[1] == tn[1]) {
					flag = true;
					break;
				}
				
				for(int i = 0; i < 4; i++) {
					int nx = cn[1] + dx[i];
					int ny = cn[0] + dy[i];
					
					if(isValid(nx,ny) && grid[ny][nx] != '1' && visited[ny][nx] == false) { 
						visited[ny][nx] = true;
						deq.add(new int[] {ny,nx});
					}
				}
			}
			output.append("#").append(t+1).append(" ").append(flag?1:0).append("\n");
		}
		System.out.println(output);
	}
	
	static boolean isValid(int x, int y) {
		return x >= 0 && x < 16 && y >= 0 && y < 16;
	}
}
