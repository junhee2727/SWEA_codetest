package com.ssafy.p1948;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuffer output = new StringBuffer();
	static Integer[] days = new Integer[]{31,28,31,30,31,30,31,31,30,31,30,31};
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
//		reader = new BufferedReader(new StringReader(src)); //src 가 있으면 이거로 읽기
		int t = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for(int i = 0; i<t; i++) {
			String line = reader.readLine();
			tokens = new StringTokenizer(line);
			int thisMonth = Integer.parseInt(tokens.nextToken());
			int thisDay = Integer.parseInt(tokens.nextToken());
			int nextMonth = Integer.parseInt(tokens.nextToken());
			int nextDay = Integer.parseInt(tokens.nextToken());
			output.append("#").append(i+1).append(" ").append(solution(thisMonth, thisDay,nextMonth, nextDay)).append("\n");
		}
		System.out.println(output);
	}
	
	public static int solution(int TMonth, int TDay, int NMonth, int NDay) {
		int result = 0;
		if(TMonth == NMonth) {
			return (NDay - TDay)+1;
		}else {
			for(int d : Arrays.copyOfRange(days, TMonth-1, NMonth-1)) { 
				result += d;
			}
			result = result - TDay + NDay;
			return result + 1;
		}
	}
//	private static String src = "3 \r\n"
//			+ "3 1 3 31\r\n"
//			+ "5 5 8 15\r\n"
//			+ "7 17 12 24";
}
