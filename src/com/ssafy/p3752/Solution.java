package com.ssafy.p3752;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t<test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			List<Integer> score_arr = new ArrayList<>();
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<n; i++) {
				score_arr.add(Integer.parseInt(st.nextToken()));
			}
			int sum = score_arr.stream().reduce((o1,o2) -> o1+o2).orElse(0);
			for(int i = 0; i<sum; i++) {
				
			}
		}
	}
}
