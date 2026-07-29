package com.ssafy.p1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuffer output = new StringBuffer();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		String line = "";
		for (int i = 0; i < test_case; i++) {
			tokens = new StringTokenizer(reader.readLine());
			int height = Integer.parseInt(tokens.nextToken());
			int width = Integer.parseInt(tokens.nextToken());
			int x = -1;
			int y = -1;
			char[][] graph = new char[height][width];
			// 그래프 삽입
			
			for (int j = 0; j < height; j++) {
				line = reader.readLine();
				char[] charArr = line.toCharArray();
				graph[j] = charArr;
				for (int k = 0; k < width; k++) {
					// 대포
//					if (charArr[k] == '>' || charArr[k] == '<' || charArr[k] == '^' || charArr[k] == 'v') {
//						x = k;
//						y = j;
//					}
					if("^v<>".indexOf(graph[j][k]) >= 0) {
						x = k;
						y = j;
					}
				}
			}
			
			int command_cnt = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
			char[] command_line = reader.readLine().toCharArray();
			
			for (char c : command_line) {
				switch (c) {
				case 'U': {
					// 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
					if (y > 0) {
						if (graph[y - 1][x] == '.') {
							graph[y][x] = '.';
							y--;
						}
					}
					graph[y][x] = '^';

					break;
				}
				case 'D': {
					// 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
					if (y < height - 1) {
						if (graph[y + 1][x] == '.') {
							graph[y][x] = '.';
							y++;
						}
					}
					graph[y][x] = 'v';

					break;
				}
				case 'L': {
					// 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
					if (x > 0) {
						if (graph[y][x - 1] == '.') {
							graph[y][x] = '.';
							x--;
						}
					}
					graph[y][x] = '<';

					break;
				}
				case 'R': {
					// 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
					if (x < width - 1) {
						if (graph[y][x + 1] == '.') {
							graph[y][x] = '.';
							x++;
						}
					}
					graph[y][x] = '>';
					break;
				}
				case 'S': {
					// 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
					char t = graph[y][x];
					switch (t) {
					case '<':
						for (int j = x; j >= 0; j--) {
							if (graph[y][j] == '#') {
								break;
							}
							if (graph[y][j] == '*') {
								graph[y][j] = '.';
								break;
							}
						}
						break;
					case '>':
						for (int j = x; j < width; j++) {
							if (graph[y][j] == '#') {
								break;
							}
							if (graph[y][j] == '*') {
								graph[y][j] = '.';
								break;
							}
						}
						break;
					case '^':
						for (int j = y; j >= 0; j--) {
							if (graph[j][x] == '#') {
								break;
							}
							if (graph[j][x] == '*') {
								graph[j][x] = '.';
								break;
							}
						}
						break;
					case 'v':
						for (int j = y; j < height; j++) {
							if (graph[j][x] == '#') {
								break;
							}
							if (graph[j][x] == '*') {
								graph[j][x] = '.';
								break;
							}
						}
						break;
					}

				}
				default:
				}

				output.append("#").append(i + 1).append(" ");
				for (char[] row : graph) {
					output.append(row).append("\n");
				}
			}
			System.out.println(output);
		}

	}
}
