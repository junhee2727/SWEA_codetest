package com.ssafy.p3499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t< test_case; t++) {
			int N = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			int mid = Math.round((float)N/2);
			
			st = new StringTokenizer(reader.readLine());
			String[] arr = new String[N];
			for(int i = 0; i< N; i++) {
				arr[i] = st.nextToken();
			}
			
			
			output.append("#").append(t+1).append(" ");
			for(int i = 0; i<mid; i++) {
				output.append(arr[i]).append(" ");
				if(i + mid != N) {
					output.append(arr[i + mid]).append(" ");
				}
			}
			output.append("\n");
		}
		System.out.println(output);
	}
}
