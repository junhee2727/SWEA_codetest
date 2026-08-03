package com.ssafy.p1240;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer output = new StringBuffer();

	// graph 전역설정
	static char[][] graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/p1240.txt"));
		reader = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 0; t < 10; t++) {
			// 첫 줄에 테스트 케이스 번호
			reader.readLine();
			// 그래프 초기화
			graph = new char[100][100];
			for (int i = 0; i < 100; i++) {
				graph[i] = String.join("", reader.readLine().split(" ")).toCharArray();
			}
			
			String line;
			for(int i = 0; i< 100; i++) {
				line = reader.readLine();
				st = new StringTokenizer(line);
				for(int j = 0; j< 100; j++) {
					graph[i][j] = st.nextToken().charAt(0);
				}
			}
			
			// 도착점 위치 찾기
			int x = -1;
			int y = 99;
			// 현재위치 왼쪽 오른쪽
			int cl = -1;
			int cr = -1;
			// 왼쪽 혹은 오른쪽으로 계속 이동
			int dir = 0;
			// 도착점 찾기
			for (int i = 0; i < 100; i++) {
				if (graph[99][i] == '2') {
					x = i;
					break;
				}
			}
//			System.out.println("found x : " + x);
//			 도착점에서 위로 거슬로 올라가기
			for (int i = 99; i >= 0; i--) {
				cl = x - 1;
				cr = x + 1;
				y = i;
				// 왼쪽에 길이 있는 경우
				if (cl >= 0 && graph[y][cl] == '1') {
					while (cl>= 0 && graph[y][cl] == '1') {
						x = cl;
						cl--;
					}
				} else if (cr < 100 && graph[y][cr] == '1') {
					while (cr < 100 && graph[y][cr] == '1') {
						x = cr;
						cr++;
					}
				} else {
				}
			}
			System.out.print("#"+(t+1)+" ");
			System.out.println(x);
		}
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && x < 100 && y >= 0 && y < 100;
	}

}
