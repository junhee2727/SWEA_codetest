package com.ssafy.p7206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static HashMap<Integer, Integer> memo = new HashMap<>();

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine().trim());
		for (int t = 0; t < test_case; t++) {

			int num = Integer.parseInt(reader.readLine());

			memo.clear();
			
			int answer = dfs(num);
			

			output.append("#").append(t + 1).append(" ").append(answer).append("\n");
//			System.out.println(answer);
		}
		System.out.println(output);
	}

	static int dfs(int value) {
		if(value < 10)
			return 0; 
		
		if(memo.containsKey(value))
			return memo.get(value);

		int max = 0;

		char[] char_arr = (value + "").toCharArray();

		int len = char_arr.length;
		int[] num_arr = new int[len];
		
		for (int i = 0; i < len; i++) {
			num_arr[i] = char_arr[i] - '0';
		}

		// splitter의 위치
		// 1. 12345를 1 2345, 12 345, 123 45. 1234 5로 분할한다
		for (int i = 0; i < len-1; i++) {
			int l = 0;
			int r = 0;
			for (int j = 0; j <= i; j++) {
				l *= 10;
				l += num_arr[j];
			}
			for (int j = i + 1; j < len; j++) {
				r *= 10;
				r += num_arr[j];
			}

			max = Math.max(max, dfs(l * r)+1);
		}

		// splitter 2개 쓰는 경우
		for (int i = 0; i < len-1; i++) {
			for (int j = i + 1; j < len-1; j++) {

				int l = 0;
				int mid = 0;
				int r = 0;

				for (int m = 0; m <= i; m++) {
					l *= 10;
					l += num_arr[m];
				}
				for (int m = i + 1; m <= j; m++) {
					mid *= 10;
					mid += num_arr[m];
				}
				for (int m = j + 1; m < len; m++) {
					r *= 10;
					r += num_arr[m];
				}

				max = Math.max(max,dfs(l * r * mid) + 1);
			}
		}
		
		memo.put(value, max);
		return max;
	}
}
