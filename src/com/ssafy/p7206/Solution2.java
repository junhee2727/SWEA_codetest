package com.ssafy.p7206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static HashMap<String, Integer> visited = new HashMap<>();

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine());
		for (int t = 0; t < test_case; t++) {

			String num = reader.readLine();
			int ans = dfs(num);
			System.out.println(ans);
		}
	}

	static int dfs(String value) {
		int len = value.length();
		if (len == 1)
			return 0;
		if(visited.containsKey(value))
			return visited.get(value);

		int answer = 0;

		for (int i = 1; i < (1 << len - 1); i++) {
			int num = 1;
			int prev = 0;
			
			for (int j = 0; j < len - 1; j++) {
				if ((i & (1 << j)) != 0) {
					num *= Integer.parseInt(value.substring(prev, j + 1));
					prev = j + 1;
				}
			}
			
			num *= Integer.parseInt(value.substring(prev,len));
			
			answer = Math.max(answer, dfs(String.valueOf(num)) + 1);
		}

		visited.put(value, answer);
		
		return answer;
	}
}
