package com.ssafy.p8275;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine());
		for(int t = 0; t<test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
		}
	}
}
