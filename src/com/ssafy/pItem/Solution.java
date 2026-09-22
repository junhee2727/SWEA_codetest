package com.ssafy.pItem;

import java.io.IOException;
import java.util.ArrayDeque;

public class Solution {

	static int[][] grid;
	static int[] dx = new int[]{1,-1,0,0};
	static int[] dy = new int[]{0,0,1,-1};
	
	static final int SIZE = 51 * 2;
	static boolean[][] vis;
	
	public static void main(String[] args)throws IOException{
		
		int[][][] rectangles = new int[][][] {
			{{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}},
			{{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}},
			{{1,1,5,7}},
			{{2,1,7,5},{6,4,10,10}},
			{{2,2,5,5},{1,3,6,4},{3,1,4,6}}
		};
		
		int[] charx = new int[] {1,9,1,3,1};
		int[] chary = new int[] {3,7,1,1,4};
		int[] itemx = new int[] {7,6,4,7,6};
		int[] itemy = new int[] {8,1,7,10,3};
		
		
		for(int i = 0; i < 5; i++) {
			System.out.println(solution(rectangles[i], charx[i], chary[i], itemx[i], itemy[i]));
		}
		
		
	}

	static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		grid = new int[SIZE][SIZE];
		vis = new boolean[SIZE][SIZE];
		
		for(int[] rect: rectangle) {
			drawRect(rect[0] * 2, rect[1] * 2, rect[2] * 2, rect[3] * 2);
		}
		
//		for(int[] row : grid) {
//			for(int i : row) {
//				System.out.print(i);
//			}
//			System.out.println();
//		}
		
		
		return bfs(new int[] {characterX * 2, characterY * 2}, new int[] {itemX * 2, itemY * 2});
	}
	
	// sNode 부터 eNode까지 rect 그리기
	// 0: 백지, 1: 선, 2:내부
	static void drawRect(int sx, int sy, int ex, int ey) {
		
		for(int y = sy; y <= ey; y++) {
			
			for(int x = sx; x <= ex; x++) {
				
				if((x == sx || y == sy || x == ex || y == ey) && grid[y][x] != 2) {
					
					grid[y][x] = 1;
					continue;
					
				}
				
				grid[y][x] = 2;
			}
		}
		
	}
	
	// X, Y 순
	static int bfs(int[] cur, int[] item) {
		ArrayDeque<int[]> deq = new ArrayDeque<>();
		ArrayDeque<int[]> tmpdeq = new ArrayDeque<>();
		int value = 0;
		
		deq.offer(cur);
		vis[cur[1]][cur[0]] = true;
		
		while(true) {
			while(!deq.isEmpty()) {
				int[] node = deq.remove();
				
				for(int i = 0; i < 4; i++) {
					int nx = node[0] + dx[i];
					int ny = node[1] + dy[i];
					
					if(!isAvail(nx, ny))
						continue;
					if(vis[ny][nx])
						continue;
					if(grid[ny][nx] != 1)
						continue;
					
					if(nx == item[0] && ny == item[1])
						return (value + 1) / 2;
					
					tmpdeq.offer(new int[] {nx,ny});
					vis[ny][nx] = true;
				}
			}
			
			while(!tmpdeq.isEmpty()) {
				deq.offer(tmpdeq.remove());
			}
			
			value ++;
		}
		
		
		
	}
	
	static boolean isAvail(int x,int y) {
		return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
	}
}
