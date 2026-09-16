package com.ssafy.p1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static char[][] grid;
	static final int SIZE = 100;

	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	public static void main(String[] ags) throws IOException {
//		System.setIn(new FileInputStream("res/p1227.txt"));
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 0; t < 10; t++) {
			reader.readLine();
			grid = new char[SIZE][SIZE];

			for (int y = 0; y < SIZE; y++) {
				String s = reader.readLine();
				for (int x = 0; x < SIZE; x++) {
					grid[y][x] = s.charAt(x);
				}
			}

			boolean flag = false;
			for (int y = 0; y < SIZE; y++) {
				for (int x = 0; x < SIZE; x++) {
					if (grid[y][x] == '2')
						flag = bfs(new Node(x, y));
						break;
				}
			}

			output.append("#").append(t+1).append(" ").append(flag ? 1 : 0).append("\n");
		}
		System.out.println(output);
	}

	static boolean bfs(Node start) {
		ArrayDeque<Node> que = new ArrayDeque<>();
		boolean[][] vis = new boolean[SIZE][SIZE];

		que.offer(start);
		vis[start.y][start.x] = true;

		while (!que.isEmpty()) {
			Node node = que.poll();

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (!isAvail(nx, ny))
					continue;

				if (vis[ny][nx])
					continue;

				if (grid[ny][nx] == '1')
					continue;

				que.offer(new Node(nx, ny));
				vis[ny][nx] = true;

				if (grid[ny][nx] == '3')
					return true;
			}
		}

		return false;
	}

	static boolean isAvail(int x, int y) {
		return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
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
