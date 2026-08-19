package com.ssafy.p1251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static List<Node> lst;
	static int[] x_list;
	static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t < test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			lst = new ArrayList<>(); // x좌표의 목록
			x_list = new int[n];
			// x좌표 저장
			st = new StringTokenizer(reader.readLine());
			for (int i = 0; i < n; i++) {
				x_list[i] = Integer.parseInt(st.nextToken());
			}

			// 그래프, minimum 초기화
			st = new StringTokenizer(reader.readLine());
			for (int x : x_list) {
				lst.add(new Node(x, Integer.parseInt(st.nextToken()), Double.MAX_VALUE));
			}

			double e = Double.parseDouble(new StringTokenizer(reader.readLine().trim()).nextToken());

			graph = new ArrayList[n];
			for(int i = 0; i<n; i++) {
				graph[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < n; i++) {
				int prev = -1;
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue; // 비교하는 대상이 동일 노드면 통과
					Node cn = lst.get(i);
					Node nn = lst.get(j);
					int cx = cn.x;
					int cy = cn.y;
					int nx = nn.x;
					int ny = nn.y;
					if (cn.minimum > Math.pow(Math.pow(nx - cx, 2) + Math.pow(ny - cy, 2), 0.5) * e) {
						cn.minimum = Math.pow(Math.pow(nx - cx, 2) + Math.pow(ny - cy, 2), 0.5) * e;
						if (prev != -1) {
							graph[prev].removeLast();
						}
						graph[j].add(i);
						prev = j;
					}
				}
			}
			
			System.out.println(Arrays.toString(graph));

			double res = 0;
			// BFS 순회
			Boolean[] visited = new Boolean[n];
			for(int i = 0; i<n; i++) {
				visited[i] = false;
			}
			Deque<Integer> deq = new ArrayDeque<>();
			deq.add(0);
			visited[0] = true;
			while (!deq.isEmpty()) {
				int tmp = deq.pop();
				res += lst.get(tmp).minimum;
				for (int x : graph[tmp]) {
					if (visited[x] == false) {
						deq.add(x);
					}
				}
				System.out.println(res);
			}

			output.append("#").append(t + 1).append(" ").append(Math.round(res)).append("\n");
		}
		System.out.println(output);
	}

	static class Node {
		int x;
		int y;
		double minimum;

		public Node(int x, int y, double minimum) {
			super();
			this.x = x;
			this.y = y;
			this.minimum = minimum;
		}
	}
}
