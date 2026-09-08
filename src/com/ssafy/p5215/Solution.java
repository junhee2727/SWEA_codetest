package com.ssafy.p5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int L;

	static int[] cal_list;
	static int[] flav_list;

	static int res;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for (int t = 0; t < test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			cal_list = new int[N];
			flav_list = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(reader.readLine());
				flav_list[i] = Integer.parseInt(st.nextToken());
				cal_list[i] = Integer.parseInt(st.nextToken());
			}

			res = -1;

			Comb(0, 0, 0);

			output.append("#").append(t + 1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}

	static void Comb(int cal, int flav, int start) {
		if (cal > L)
			return;
		res = Math.max(res, flav);

		for (int i = start; i < N; i++) {
			Comb(cal + cal_list[i], flav + flav_list[i], i + 1);
		}
	}

}
