package com.ssafy.p5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int SIZE = 4001;
	// 상하좌우
	static int[] dx = new int[] { 0, 0, -1, 1 };
	static int[] dy = new int[] { 1, -1, 0, 0 };

	static int grid[][] = new int[SIZE][SIZE];;
	static Node[] node_map;
	static HashSet<Integer> toRemove;
	static int res;
	static int time = 1;
	static int aliveCount;
	
	static int minx, miny, maxx, maxy;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine().trim());

		for (int t = 0; t < test_case; t++) {
			int N = Integer.parseInt(reader.readLine().trim());

			node_map = new Node[N + 1];
			toRemove = new HashSet<>();
			res = 0;
			aliveCount = N;

			minx = miny = 0;
			maxx = maxy = SIZE;
			
			// 노드 초기화
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(reader.readLine());
				int cx = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int cy = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int cdir = Integer.parseInt(st.nextToken());
				int cpow = Integer.parseInt(st.nextToken());
				
				minx = Math.min(minx, cx);
				maxx = Math.max(maxx, cx);
				miny = Math.min(miny, cy);
				maxy = Math.max(maxy, cy);

				grid[cy][cx] = i;
				node_map[i] = new Node(cx, cy, cdir, cpow);
			}

			
			while (aliveCount > 0) {
				for(int idx = 1; idx <= N; idx ++ ) {
					move(idx);
				}
				
				for(int idx : toRemove) {
					if(node_map[idx] == null)
						continue;
					res += node_map[idx].power;
					aliveCount --;
					
					node_map[idx] = null;
				}
				
				toRemove.clear();
				time ++; 
			}

			output.append("#").append(t+1).append(" ").append(res).append("\n");

		}
		System.out.println(output);

	}

	static void move(int idx) {
		Node node = node_map[idx];
		if (node == null)
			return;

		int nx = node.x + dx[node.dir];
		int ny = node.y + dy[node.dir];

		grid[node.y][node.x] = 0;
		
		// 맵 밖으로 나가면 remove ( power 추가 x )
		if (!isValid(nx, ny)) {
			node_map[idx] = null;
			aliveCount --;
			return;
		}
		
		//충돌이 발생한 위치로 이동한다면 remove(power 추가 )
		if(grid[ny][nx] == -time) {
			toRemove.add(idx);
			return;
		}


		// 이동할 위치에 다른 노드가 있다면 둘 다 remove( power 추가 )
		if(grid[ny][nx] > 0) {
			
			int nidx = grid[ny][nx];
			
			toRemove.add(idx);
			toRemove.add(nidx);

			grid[ny][nx] = -time;

			return;
		}
		

		grid[ny][nx] = idx;
		// 새 노드로 업데이트
		node.x = nx;
		node.y = ny;
		return;
	}

	static boolean isValid(int x, int y) {
		return x >= minx && x < maxx && y >= miny && y < maxy;
	}

	static class Node {
		int x;
		int y;
		int dir;
		int power;

		public Node(int x, int y, int dir, int power) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
		}
	}

}
