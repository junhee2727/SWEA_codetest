package com.ssafy.p1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int grid[][];
	static ArrayList<Node> node_list;
	static int[] dx = new int[]{0,0,-1,1};
	static int[] dy = new int[] {-1,1,0,0};

	static int res, max_cnt;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine().trim());
		for (int t = 0; t < test_case; t++) {
			N = Integer.parseInt(reader.readLine().trim());
			grid = new int[N][N];
			node_list = new ArrayList<>();
			res = Integer.MAX_VALUE;
			max_cnt = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(reader.readLine());
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (tmp == 1)
						node_list.add(new Node(j, i));
					grid[i][j] = tmp;
				}
			}
			dfs(0,0, 0);
			output.append("#").append(t+1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}

	static void dfs(int idx, int value, int cnt) {	
		if (idx == node_list.size()) {
			if(max_cnt < cnt) {
				max_cnt = cnt;
				res = value;		
			}else if(max_cnt == cnt) {
				res = Math.min(res, value);
			}
			return;
		}

		Node node = node_list.get(idx);
		
		if(isCorner(node.y,node.x)) {
			dfs(idx+1, value, cnt + 1);
			return;
		}
		
		
		for (int i = 0; i < 4; i++) {
			if(!isWritable(node.y,node.x,i)) continue;
			int cur_value = writeLine(node.y, node.x, i);
			dfs(idx + 1, value + cur_value, cnt + 1);
			eraseLine(node.y,node.x,i);
		}
		
		dfs(idx + 1, value, cnt);
		
		return;
	}

	// 상하좌우로 그리고, count를 출력
	static int writeLine(int y, int x, int dir) {

		int count = 0;
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		while(isValid(ny,nx)) {
			grid[ny][nx] = 2;
			
			ny += dy[dir];
			nx += dx[dir];
			count++;
		}
		
		return count;
	}
	
	static void eraseLine(int y, int x, int dir) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		while(isValid(ny,nx)) {
			grid[ny][nx] = 0;
			
			ny += dy[dir];
			nx += dx[dir];
		}
	}


	static boolean isCorner(int y, int x) {
		return x == 0 || y == 0 || x == N - 1 || y == N - 1;
	}

	static boolean isValid(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < N;
	}
	
	static boolean isWritable(int y, int x, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		while(isValid(ny,nx)) {
			if(grid[ny][nx] != 0)
				return false;
			nx += dx[dir];
			ny += dy[dir];
		}
		
		return true; 
	}
	

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
