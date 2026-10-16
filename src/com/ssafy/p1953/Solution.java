package com.ssafy.p1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import com.sun.imageio.plugins.common.InputStreamAdapter;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int L,N,M;
	static int[][] grid;
	
	static int[][] dx = {{1,-1,0,0}, {0,0}, {1,-1}, {0,1}, {0,1}, {0,-1},{0,1}};
	static int[][] dy = {{0,0,1,-1}, {1,-1}, {0,0}, {-1,0}, {1,0}, {1,0},{-1,0}};
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 1; t<=test_case; t++) {
			
			st = new StringTokenizer(reader.readLine());
			
			//지도의 세로, 가로 크기 : N, M
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			  
			//맨홀 뚜껑의 세로, 가로 위치: R,C
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			//소요된 시간 L
			L = Integer.parseInt(st.nextToken());
			
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(reader.readLine());
				for(int x = 0; x < M; x++) {
					grid[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			Deque<Node> deq = new ArrayDeque<>();
			Deque<Node> tmp = new ArrayDeque<>();
			boolean[][] vis = new boolean[N][M];
			int res = 1;
			
			int time = 1;
			deq.offer(new Node(C,R));
			
			while(true) {
				if(time == L)
					break;
				
				//todo: BFS
				while(!deq.isEmpty()) {
					Node node = deq.poll();
					int cx = node.x;
					int cy = node.y;
					int param = grid[cy][cx]-1;
					
					for(int i = 0; i < dx[param].length; i++) {
						int nx = cx + dx[param][i];
						int ny = cy + dy[param][i];
						
						if(!isAvail(nx, ny)) continue;
						
						if(vis[ny][nx]) continue;
							
						if(grid[ny][nx] == 0) continue;
						
						vis[ny][nx] = true;
						res++;
						tmp.offer(new Node(nx,ny));
					}
				}
				
				while(!tmp.isEmpty()) {
					deq.offer(tmp.poll());
				}
				
				
				time++;
			}
			
			output.append("#").append(t).append(" ").append(res).append("\n");
			
		}
		System.out.println(output);
	}
	
	static boolean isAvail(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
