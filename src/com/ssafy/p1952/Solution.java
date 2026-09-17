package com.ssafy.p1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 0; t<test_case; t++) {
			//1일, 1달, 3달, 1년
			int[] fee = new int[4];
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<4; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			
			//각 달의 사용 횟수
			int[] calendar = new int[12];
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<12; i++) {
				calendar[i] = Integer.parseInt(st.nextToken());
			}
			
			
			int[] suffix = new int[12];
			
			for(int i = 0; i <12; i++) {
				
			}
			
		}
	}
	
	
}
