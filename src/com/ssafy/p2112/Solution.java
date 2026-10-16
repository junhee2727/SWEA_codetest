package com.ssafy.p2112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int grid[][];
	static int vis[];

	static int D, W, K, res, cnt;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine().trim());

		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(reader.readLine());

			// 보호필름의 두께
			D = Integer.parseInt(st.nextToken());
			// 가로크기
			W = Integer.parseInt(st.nextToken());
			// 합격 기준
			K = Integer.parseInt(st.nextToken());

			// 전체 필름
			grid = new int[D][W];

			for (int y = 0; y < D; y++) {
				st = new StringTokenizer(reader.readLine());

				for (int x = 0; x < W; x++) {
					grid[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			vis = new int[D];
			res = K;

			cnt = 0;

			// 투입하지 않음, A, B투입 세가지 상태, 최대 3^13 의 경우의 수
			dfs(0);

			output.append("#").append(t).append(" ").append(res).append("\n");
		}

		System.out.println(output);
	}

	// A투입, B투입, 투입하지 않음 상태 공간 생성
	static void dfs(int depth) {
		if (depth == D) {
			if (cnt < res)
				check();
			return;
		}
		vis[depth] = 0;
		dfs(depth + 1);

		vis[depth] = 1;
		cnt++;
		dfs(depth + 1);
		cnt--;

		vis[depth] = 2;
		cnt++;
		dfs(depth + 1);
		cnt--;
	}

	static void check() {
		for (int x = 0; x < W; x++) {
			int count = 1;
			boolean passed = false;

			for (int y = 1; y < D; y++) {
				int prev = vis[y - 1] == 0 ? grid[y - 1][x] : vis[y - 1] - 1;
				int cur = vis[y] == 0 ? grid[y][x] : vis[y] - 1;

				if (cur == prev) {
					count++;
				} else {
					count = 1;
				}

				if (count >= K) {
					passed = true;
					break;
				}
			}

			if (!passed)
				return;
		}

		res = cnt;
	}

}
