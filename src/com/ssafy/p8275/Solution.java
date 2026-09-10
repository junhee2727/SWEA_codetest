package com.ssafy.p8275;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, X, max_cnt;
	static Note[] note_list;
	static int[] ham_list, res_list;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(reader.readLine());
		for (int t = 0; t < test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			// 햄스터 우리의 수
			N = Integer.parseInt(st.nextToken());
			// 최대 햄스터 마리수
			X = Integer.parseInt(st.nextToken());
			// 로그의 수
			int M = Integer.parseInt(st.nextToken());

			note_list = new Note[M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(reader.readLine());
				note_list[i] = new Note(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}

			ham_list = new int[N];
			res_list = null;
			max_cnt = -1;

			dfs(0, 0);

			output.append("#").append(t + 1).append(" ");
			if (res_list == null) {
				output.append(-1);
			} else {
				for (int h : res_list) {
					output.append(h).append(" ");
				}
			}
			output.append("\n");
		}
		System.out.println(output);
	}

	static void dfs(int idx, int value) {
		if (idx == N) {
			// 조건을 만족한다면
			if (!isAvail())
				return;
			if (value > max_cnt) {
				res_list = ham_list.clone();
				max_cnt = value;
			}
			return;
		}

		for (int i = 0; i <= X; i++) {
			ham_list[idx] = i;
			dfs(idx + 1, value + i);
		}
		return;
	}

	static boolean isAvail() {
		for (Note n : note_list) {
			int sum = 0;
			for (int i = n.l - 1; i <= n.r - 1; i++) {
				sum += ham_list[i];
			}
			if (sum != n.cnt)
				return false;
		}
		return true;
	}

	static class Note {
		int l;
		int r;
		int cnt;

		public Note(int l, int r, int cnt) {
			super();
			this.l = l;
			this.r = r;
			this.cnt = cnt;
		}
	}
}
