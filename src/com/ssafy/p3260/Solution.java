package com.ssafy.p3260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for (int t = 0; t < test_case; t++) {
			int[] a = new int[100];
			int[] b = new int[100];

			Integer[] res = new Integer[101];

			st = new StringTokenizer(reader.readLine());

			String string_a = st.nextToken();
			// 최하단 수부터 오름차순으로 배열에 정렬
			for (int i = 0; i < string_a.length(); i++) {
				a[i] = Integer.parseInt(string_a.charAt(string_a.length() - i - 1) + "");
			}

			String string_b = st.nextToken();
			// 최하단 수부터 오름차순으로 배열에 정렬
			for (int i = 0; i < string_b.length(); i++) {
				b[i] = Integer.parseInt(string_b.charAt(string_b.length() - i - 1) + "");
			}

			// 둘 중 더 큰수
			int max_len = Math.max(string_a.length(), string_b.length());

			// 올림이 있었는지.
			int is_upper = 0;
			for (int i = 0; i < 100; i++) {
				int tmp = a[i] + b[i] + is_upper;
				if (tmp >= 10) {
					tmp -= 10;
					is_upper = 1;
				} else {
					is_upper = 0;
				}
				res[i] = tmp;
			}
			
			

			output.append("#").append(t + 1).append(" ");
			for (int i = max_len + 1; i >= 0; i--) {
				if (res[i] == null) {
					continue;
				}
				output.append(res[i]);
			}
			output.append("\n");
		}
		System.out.println(output);
	}
}
