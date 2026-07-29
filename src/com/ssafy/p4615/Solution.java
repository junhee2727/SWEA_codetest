package com.ssafy.p4615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer output = new StringBuffer();

	// 12시부터 시계 방향으로
	static int[] DX = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] DY = { -1, -1, 0, 1, 1, 1, 0, -1 };
	// 바꿀 색깔
	static int[] D_CL = { 0, 2, 1 };
	static int[][] graph;

	static int width;

	public static void main(String[] args) throws IOException {
		int testcase = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t < testcase; t++) {
			st = new StringTokenizer(reader.readLine());
			width = Integer.parseInt(st.nextToken());
			graph = new int[width][width];
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < width; j++) {
					if ((i == width / 2 && j == width / 2) || (i == width / 2 - 1) && (j == width / 2 - 1)) {
						graph[i][j] = 2;
					} else if ((i == width / 2 && j == width / 2 - 1) || (i == width / 2 - 1 && j == width / 2)) {
						graph[i][j] = 1;
					}
				}
			}


			int cmd_cnt = Integer.parseInt(st.nextToken());
			for (int i = 0; i < cmd_cnt; i++) {
				st = new StringTokenizer(reader.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cl = Integer.parseInt(st.nextToken());
				set(x, y, cl);
//				for(int[] row: graph) {
//				for(int num: row) {
//					System.out.print(num+"\t");
//				}
//				System.out.println("");
//			}
//				System.out.println("=================");

			}
			int rb = 0;
			int rw = 0;
			for (int[] row : graph) {
				for (int num : row) {
					if (num == 1) {
						rb++;
					} else if (num == 2) {
						rw++;
					}
				}
			}
			output.append("#").append(t + 1).append(" ").append(rb).append(" ").append(rw).append("\n");
		}
		System.out.println(output);
	}

	static void set(int x, int y, int cl) {
		// 0 base index 변환
		int nx = x - 1;
		int ny = y - 1;
		graph[ny][nx] = cl;
		// 팔방향 탐색
		for (int i = 0; i < 8; i++) {
			//초기화 과정 필요
			nx = x - 1 + DX[i];
			ny = y - 1 + DY[i];
			//nx => gx 까지 cl로 변환
			int gx = -1;
			int gy = -1;
			while (inRange(nx, ny)) {
				// 탐색한 칸이 0이면 반환
				if (graph[ny][nx] == 0)
					break;
				if (graph[ny][nx] == cl) {
					gx = nx;
					gy = ny;
					break;
				}
				// 마지막으로 탐색한 돌이 cl과 같다면 다시 탐색돌며 모두 cl로 변환
				nx += DX[i];
				ny += DY[i];
//				System.out.println("i: "+i+" nx: "+nx + " ny: "+ny);
			}
//			System.out.println("i: "+i+"cl: " + cl + " flag: " +flag);
			if (gx != -1) {
				nx = x -1 + DX[i];
				ny = y -1 + DY[i];
				while (inRange(nx, ny)) {
					if (graph[ny][nx] == 0 || (nx == gx && ny == gy))
						break;
					graph[ny][nx] = cl;
					nx += DX[i];
					ny += DY[i];
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < width;
	}

}
