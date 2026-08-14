package com.ssafy.p1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t < test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			st = new StringTokenizer(reader.readLine());
			Node comp = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Node home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
