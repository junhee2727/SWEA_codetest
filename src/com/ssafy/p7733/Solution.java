package com.ssafy.p7733;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
			
	static int[][] grid;
	static int N;
	//맛 최대 수치
	static int M;
	
	static int[] dx = new int[] {1,-1,0,0};
	static int[] dy = new int[] {0,0,1,-1};
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		for(int t = 0; t<test_case; t++) {
			N = Integer.parseInt(reader.readLine().trim());
			M = -1;
			grid = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(reader.readLine());
				for(int j = 0; j<N;j++) {
					int flav = Integer.parseInt(st.nextToken());
					grid[i][j] = flav;
					M = Math.max(M, flav);
				}
			}
			
			int max = 1;
			for(int i = 1; i < M; i++) {
				max = Math.max(max, bfs(i));
			}
			
			output.append("#").append(t+1).append(" ").append(max).append("\n");
		}
		
		System.out.println(output);
	}
	
	static int bfs(int day) {
		boolean[][] vis = new boolean[N][N];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int sum = 0;
		
		//순회하면서 day 이하의 칸은 방문체크
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if (grid[y][x] <= day)
					vis[y][x] = true;
			}
		}
		
		//나머지에 대해 bfs
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(vis[y][x])
					continue;
			
				que.offer(new int[]{y,x});
				vis[y][x] = true;
				
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					int cx = cur[1];
					int cy = cur[0];
					
					for(int i = 0; i<4; i++) {
						int nx = cx + dx[i];
						int ny = cy + dy[i];
						
						if(!isAvail(nx,ny))
							continue;
						
						if(vis[ny][nx])
							continue;
						
						que.offer(new int[] {ny,nx});
						vis[ny][nx] = true;
					}
				}
				sum ++;
			}
		}
		
		
		return sum;
	}
	
	static boolean isAvail(int x, int y) {
		return x >= 0 && x < N && y >= 0 &&  y < N;
	}
}
