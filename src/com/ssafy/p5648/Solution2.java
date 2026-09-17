package com.ssafy.p5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static Node[] node_list;
	static int SIZE = 4001;
	static int res;

	static int minx, miny, maxx, maxy;

	// 상하좌우
	static int[] dx = new int[] { 0, 0, -1, 1 };
	static int[] dy = new int[] { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine().trim());

		for (int t = 0; t < test_case; t++) {
			int N = Integer.parseInt(reader.readLine().trim());

			int aliveCount = N;
			node_list = new Node[N + 1];
			res = 0;

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

				node_list[i] = new Node(cx, cy, cdir, cpow);
			}

			HashMap<Integer, Integer> count = new HashMap<>();
			while (aliveCount > 0) {
				count.clear();

				for (int i = 1; i <= N; i++) {
					Node node = node_list[i];

					if (node == null)
						continue;

					node.x += dx[node.dir];
					node.y += dy[node.dir];

					if (!isValid(node.x, node.y)) {
						node_list[i] = null;
						aliveCount--;
						continue;
					}

					int key = node.y * SIZE + node.x;

					count.put(key, count.getOrDefault(key, 0) + 1);
				}

				for (int i = 1; i <= N; i++) {
					Node node = node_list[i];

					if (node == null)
						continue;

					int key = node.y * SIZE + node.x;

					if (count.get(key) >= 2) {
						res += node.power;
						node_list[i] = null;
						aliveCount--;
					}
				}
			}

			output.append("#").append(t + 1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
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
