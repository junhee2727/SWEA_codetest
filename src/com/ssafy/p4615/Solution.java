package com.ssafy.p4615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer tokens;
	public static StringBuffer output;
	public static void main(String[] args) throws IOException{
		reader = new BufferedReader(new StringReader(src));
		int testcase = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for(int i = 0; i<testcase; i++) {
			
		}
	}
	
	public static String src = "\r\n"
			+ "1\r\n"
			+ "4 12\r\n"
			+ "1 2 1\r\n"
			+ "1 1 2\r\n"
			+ "4 3 1\r\n"
			+ "4 4 2\r\n"
			+ "2 1 1\r\n"
			+ "4 2 2\r\n"
			+ "3 4 1\r\n"
			+ "1 3 2\r\n"
			+ "2 4 1\r\n"
			+ "1 4 2\r\n"
			+ "4 1 2\r\n"
			+ "3 1 2";
}

