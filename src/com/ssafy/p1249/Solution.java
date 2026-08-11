package com.ssafy.p1249;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	static Integer[][] graph;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t < test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			graph = new Integer[n][n];
			for (int i = 0; i < n; i++) {
				String line = reader.readLine();
				for (int j = 0; j < n; j++) {
					graph[i][j] = Integer.parseInt("" + line.charAt(j));
				}
			}
			int max = 0;
			// 그래프 최대값 구하기
//			Arrays.stream(graph).forEach(item -> Arrays.stream(item).forEach(el -> {if(el > max) {max = el;}}));
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					max += graph[i][j];
				}
			}
			int[][] min_graph = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					min_graph[i][j] = max + 1;
				}
			}
			Deque<Node> deq = new ArrayDeque<>();
			min_graph[0][0] = 0;
			deq.add(new Node(0,0,0));
			while(!deq.isEmpty()) {
				Node node = deq.pop();
				// 조기 종료 코드 (3배 빨라짐!!)
				if(node.cost > min_graph[node.y][node.x]) continue;
				for(int i = 0; i<4; i++) {
					int nx = dx[i] + node.x;
					int ny = dy[i] + node.y;
					if(isValid(nx, ny, n)) {
						if(min_graph[ny][nx] > node.cost + graph[ny][nx]) {
							min_graph[ny][nx] = node.cost+graph[ny][nx];
							deq.add(new Node(nx,ny,node.cost+graph[ny][nx]));
						}
					}
				}
			}
//			Arrays.stream(min_graph).forEach(item -> System.out.println(Arrays.toString(item)));
			output.append("#").append(t + 1).append(" ").append(min_graph[n - 1][n - 1]).append("\n");
		}
		System.out.println(output);
	}

	static boolean isValid(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	static class Node {
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

	}
}
