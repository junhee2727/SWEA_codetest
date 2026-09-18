package com.ssafy.p1232;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int node_list[][];

	public static void main(String[] args) throws IOException {
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(reader.readLine().trim());

			node_list = new int[N + 1][4];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(reader.readLine());
				// 모두 -1로 값 초기화
				Arrays.fill(node_list[i], -1);

				// 노드 번호
				node_list[i][0] = Integer.parseInt(st.nextToken());
				String token = st.nextToken();
				// 숫자면 해당 숫자를, 아니면 +-*/ 의 인덱스를 반환
				node_list[i][1] = Character.isDigit(token.charAt(0)) ? Integer.parseInt(token) : "+-*/".indexOf(token);
				if (!st.hasMoreTokens())
					continue;
				node_list[i][2] = Integer.parseInt(st.nextToken());
				node_list[i][3] = Integer.parseInt(st.nextToken());
			}

			int res = dfs(1);
			
			output.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(output);

	}

	static int dfs(int number) {
		// 피 연산자면 내 정보 제공
		if (node_list[number][2] == -1)
			return node_list[number][1];

		switch (node_list[number][1]) {
		case 0:
			return dfs(node_list[number][2]) + dfs(node_list[number][3]);
		case 1:
			return dfs(node_list[number][2]) - dfs(node_list[number][3]);
		case 2:
			return dfs(node_list[number][2]) * dfs(node_list[number][3]);
		case 3:
			return dfs(node_list[number][2]) / dfs(node_list[number][3]);
		}
		return 0;
	}

}
