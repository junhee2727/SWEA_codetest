package com.ssafy.p1247;

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

	static List<Node> lst;
	static int min;
	static boolean[] visited;
	static Node en;
	static int n;
	
	//30분
	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t < test_case; t++) {
			n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			st = new StringTokenizer(reader.readLine());

			// start node, end node
			Node sn = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			en = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// lst 초기화
			lst = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				lst.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			//max 초기화
			min = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				// visited 배열
				visited = new boolean[n];
				visited[i] = true;
				DFS(i, 1, getLen(sn, lst.get(i)), visited);
			}
			output.append("#").append(t+1).append(" ").append(min).append("\n");
		}
		System.out.println(output);
	}

	static void DFS(int cur, int cnt, int cost, boolean[] visited) {
		if (cnt == n) {
			min = Math.min(min, cost + getLen(lst.get(cur), en));
			return;
		}
		
		if(cost > min) {
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				DFS(i, cnt+1, cost + getLen(lst.get(cur), lst.get(i)), visited);
				visited[i] = false;
			}
		}
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

	static int getLen(Node a, Node b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}
