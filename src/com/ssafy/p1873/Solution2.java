package com.ssafy.p1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {

	// 상하좌우 꼴로 구현
	static final int[] DX = { 0, 0, -1, 1 };
	static final int[] DY = { -1, 1, 0, 0 };
	static final char[] DIR_CHAR = { '^', 'v', '<', '>' };

	static int height, width, x, y;
	static char[][] graph;

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer output = new StringBuffer();

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t < test_case; t++) {
			String line = reader.readLine();
			st = new StringTokenizer(line);
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			graph = new char[height][];
			// graph,x,y 입력
			for (int i = 0; i < height; i++) {
				graph[i] = reader.readLine().toCharArray();
				for (int j = 0; j < width; j++) {
					if ("^v><".indexOf(graph[i][j]) >= 0) {
						x = j;
						y = i;
					}
				}
			}

			reader.readLine();
			char[] command_line = reader.readLine().toCharArray();
			for (char c : command_line) {
				if (c == 'S') {
					shoot();
				} else {
					move(c);
				}
			}
			output.append("#").append(t + 1).append(" ");
			for (char[] row : graph) {
				output.append(row).append("\n");
			}
		}
		System.out.println(output);
	}

	// 탱크가 움직이는 메서드
	static void move(char cmd) {
		int param = "UDLR".indexOf(cmd);
		int nx = x + DX[param];
		int ny = y + DY[param];
		if (inRange(nx, ny) && graph[ny][nx] == '.') {
			graph[y][x] = '.';
			x = nx;
			y = ny;
		}
		graph[y][x] = DIR_CHAR[param];
	}

	// 탱크가 발사하는 메서드
	static void shoot() {
		int param = "^v<>".indexOf(graph[y][x]);
		int ny = y + DY[param];
		int nx = x + DX[param];
		while (inRange(nx, ny)) {
			if (graph[ny][nx] == '#')
				break;
			if (graph[ny][nx] == '*') {
				graph[ny][nx] = '.';
				break;
			}
			ny += DY[param];
			nx += DX[param];
		}
	}
	static boolean inRange(int r, int c) {
		return r >= 0 && r < width && c >= 0 && c < height;
	}
}
