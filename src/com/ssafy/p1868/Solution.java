package com.ssafy.p1868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int[][] cnt;
	static char[][] grid;
	static int N;

	// 좌상단부터 시계방향
	static int[] dx = new int[] { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[] dy = new int[] { -1, -1, -1, 0, 1, 1, 1, 0 };

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine().trim());
		for (int t = 0; t < test_case; t++) {
			N = Integer.parseInt(reader.readLine().trim());

			cnt = new int[N][N];
			grid = new char[N][N];

			for (int i = 0; i < N; i++) {
				String s = reader.readLine();
				for (int j = 0; j < N; j++) {
					grid[i][j] = s.charAt(j);
				}
			}

			// *칸은 -1로 채움
			// 근처에 *이 있으면 cnt를 채움
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					char c = grid[i][j];
					if (c == '*') {
						cnt[i][j] = -1;
						continue;
					}
					for (int k = 0; k < 8; k++) {
						int nx = j + dx[k];
						int ny = i + dy[k];

						if (!isAvail(nx, ny))
							continue;

						if (grid[ny][nx] == '*') {
							cnt[i][j] = 1;
							break;
						}
					}
				}
			}

			
			int sum = 0;
			boolean isSelected[][] = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isSelected[i][j] || cnt[i][j] != 0)
						continue;

					sum++;
					bfs(i, j, isSelected);
				}
			}

			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(cnt[i][j] != -1 && !isSelected[i][j])
						sum++;
				}
			}
			
			

			output.append("#").append(t + 1).append(" ").append(sum).append("\n");
		}
		System.out.println(output);
	}

	static boolean isAvail(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void bfs(int i, int j, boolean[][] isSelected) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		
		
		que.add(new int[] { i, j });
		isSelected[i][j] = true;

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int cy = cur[0];
			int cx = cur[1];

			for (int k = 0; k < 8; k++) {
				int nx = cx + dx[k];
				int ny = cy + dy[k];

				if (!isAvail(nx, ny))
					continue;

				if (isSelected[ny][nx])
					continue;

				if (cnt[ny][nx] == 0)
					que.add(new int[] { ny, nx });

				isSelected[ny][nx] = true;
			}

		}
	}

}
