package com.ssafy.p1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int grid[][];
	static int K,N, res;
	static boolean isUsed;
	static boolean[][] vis;
	
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			
			//N: 지도 변의 크기
			N = Integer.parseInt(st.nextToken());
			//K: 최대 공사 가능 깊이
			K = Integer.parseInt(st.nextToken());
			
			grid = new int[N][N];
			int max_height = 0;
			List<Peak> peak_list = new ArrayList<Peak>();
			
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(reader.readLine());
				
				for(int x = 0; x < N; x++) {
					int height = Integer.parseInt(st.nextToken());
					
					//현재가 최대 높이와 같다면 추가
					if(height == max_height)
						peak_list.add(new Peak(x,y));
					
					//최대 높이보다 높다면 기존 리스트 초기화 및 추가
					if(height > max_height) {
						max_height = height;
						peak_list.clear();
						peak_list.add(new Peak(x, y));
					}
					
					grid[y][x] = height;
				}
			}
			
			res = 0;
			isUsed = false;
			vis = new boolean[N][N];
			
			for(Peak peak: peak_list) {
				vis[peak.y][peak.x] = true;
				dfs(peak.x, peak.y, grid[peak.y][peak.x],0);
				vis[peak.y][peak.x] = false;
//				System.out.print("["+peak.x + ", " + peak.y + "]");
			}
//			System.out.println();
			
			output.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
	
	static void dfs(int x, int y, int value, int depth) {
		res = Math.max(res, depth+1);
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			 if(!isAvail(nx,ny)) continue;
			 if(vis[ny][nx]) continue;
			 
			 int nv = grid[ny][nx];
			 
			 if(nv < value) {
				 vis[ny][nx] = true;
				 dfs(nx,ny,nv, depth + 1);
				 vis[ny][nx] = false;
			 }
			 
			 else {
				 if(isUsed) continue;
				 
				 if(nv-K >= value) continue;
				 
				 isUsed = true;
				 vis[ny][nx] = true;
				 dfs(nx,ny,value-1,depth+1);
				 vis[ny][nx] = false;
				 isUsed = false;
			 }
			
		}
	}
	
	static boolean isAvail(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static class Peak{
		int x;
		int y;
		public Peak(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
