package com.ssafy.p1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution {

	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer tokens;
	public static StringBuffer output = new StringBuffer();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
//		reader = new BufferedReader(new StringReader(src));
		//테스트 케이스 횟수
		int t = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		//i = 현재 테스트 케이스
		for(int i = 0; i< t; i++) {
			tokens = new StringTokenizer(reader.readLine());
			int thisHour = Integer.parseInt(tokens.nextToken());
			int thisMin = Integer.parseInt(tokens.nextToken());
			int nextHour = Integer.parseInt(tokens.nextToken());
			int nextMin = Integer.parseInt(tokens.nextToken());
			System.out.print("#"+(i+1)+" ");
			Calculating(thisHour,thisMin,nextHour,nextMin);
			System.out.println();
		}
	}
	
	public static void Calculating(int TH, int TM, int NH, int NM) {
		int RH = TH + NH;
		int RM = TM + NM;
		if (RM >= 60) {
			RH++;
			RM = RM - 60;
		}
		if (RH > 12) {
			RH = RH - 12;
		}
		System.out.printf("%d %d",RH, RM);
	}
//	public static String src = "3 \r\n"
//			+ "3 17 1 39\r\n"
//			+ "8 22 5 10\r\n"
//			+ "6 53 2 12 ";
}
