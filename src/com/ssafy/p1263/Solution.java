package com.ssafy.p1263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static boolean graph[][];
	static int n;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t < test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			n = Integer.parseInt(st.nextToken());

			graph = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (st.nextToken().equals("1")) {
						graph[i][j] = true;
						graph[j][i] = true;
					}
				}
			}

			int min = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				int res = BFS(i);
				min = Math.min(min, res);
			}
			output.append("#").append(t + 1).append(" ").append(min).append("\n");
		}
		System.out.println(output);
	}

	static int BFS(int a) {
		Deque<Integer> deq = new ArrayDeque<>();
		Deque<Integer> tmp = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
		for(int i = 0; i<n; i++) {
			visited[i] = false;
		}
		
		
		int res = 0;
		int cnt = 0;
		deq.add(a);
		visited[a] = true;

		
		while (true) {
			cnt++;
			while (!deq.isEmpty()) {
				int i = deq.pop();
				tmp.add(i);
			}

			for (int node : tmp) {
				for (int i = 0; i < n; i++) {
					if (graph[node][i] == true && visited[i] == false) {
						deq.addLast(i);
						visited[i] = true;
						res += cnt;
					}
				}
			}
			tmp.clear();
			
			
			boolean flag = true;
			for(boolean bo: visited) {
				if(bo == false) {
					flag = false;
				}
			}
			
			if(flag) {
				return res; 
			}
			
			
		}
	}
}

//GPT 힌트 받았삼.
//힌트 2 — 핵심
//
//BFS의 특징을 생각해보자.
//
//출발점이 i일 때
//
//i → 0까지 최단거리
//i → 1까지 최단거리
//i → 2까지 최단거리
//...
//i → n-1까지 최단거리
//
//를 구하려면 정말로
//
//BFS(i, 0)
//BFS(i, 1)
//BFS(i, 2)
//...
//BFS(i, n-1)
//
//처럼 BFS를 n번 해야 할까?
//
//BFS 한 번만 수행해도 무엇을 알 수 있는지 생각해봐.
//
//이게 가장 큰 힌트야.