package com.ssafy.p1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		for (int t = 0; t < 10; t++) {
			int N = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
			int max = N / 2;

			int res = 1;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(reader.readLine());
				// 생략
				st.nextToken();

				String tmp = st.nextToken();
				// 단말노드가 아닌 경우
				if (i <= max) {
					if (tmp.charAt(0) >= '0' && tmp.charAt(0) <= '9') {
						res = 0;
					}
				}
				// 단말 노드인 경우
				if (i > max) {
					if (!(tmp.charAt(0) >= '0' && tmp.charAt(0) <= '9')) {
						res = 0;
					}
				}
			}
			output.append("#").append(t + 1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
}
