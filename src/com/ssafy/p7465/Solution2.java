package com.ssafy.p7465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;

	static boolean[][] graph;
	static boolean[] vis;
	static int N;

	public static void main(String[] args) throws IOException {

		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for (int t = 0; t < test_case; t++) {

			st = new StringTokenizer(reader.readLine());
			// N : 창용 마을에 사는 사람 수, M: 관계의 수
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			graph = new boolean[N][N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(reader.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				graph[a][b] = true;
				graph[b][a] = true;
			}

			vis = new boolean[N];
			int res = 0;
			for (int i = 0; i < N; i++) {
				if (vis[i])
					continue;
				DFS(i);
				res++;

			}
			output.append("#").append(t+1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}

	// DFS
	static void DFS(int start) {
		vis[start] = true;
		for (int i = 0; i < N; i++) {
			if (!graph[start][i])
				continue;
			if (vis[i])
				continue;

			DFS(i);

		}
	}
}
