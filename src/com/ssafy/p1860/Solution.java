package com.ssafy.p1860;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuffer output = new StringBuffer();
	public static void main(String[] args) throws IOException{
//		reader = new BufferedReader(new StringReader(src));
		int testCase = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		for(int i = 0; i<testCase; i++) {
			//테스트 케이스 번호
			output.append("#").append((i+1)).append(" ");
			String line = reader.readLine();
			tokens = new StringTokenizer(line);
			//N : 손님의 수, M초의 시간을 들이면 K개의 붕어빵을 만들 수 있음.
			int N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());
			int K = Integer.parseInt(tokens.nextToken());
			
			Integer[] customers = new Integer[N];
			line = reader.readLine();
			tokens = new StringTokenizer(line);
			int max_time = 0;
			for(int k = 0; k< N; k++) {
				int time = Integer.parseInt(tokens.nextToken());
				customers[k] = time;
				//최대 시간 구하기
				max_time = Math.max(max_time, time);
			}
			int bread = 0;
			//1. 1부터 11111 까지 돌면서 붕어빵의 개수와 손님 입장시간을 비교
			for (int k = 0; k< 11111; k++) {
				if (k % M == 0 && k!= 0 )
					bread += K;
				//customers 배열을 돌며 현재 시간이 도착시간과 같은지 비교
				for (int time : customers) {
					//같다면 현재 빵의 재고 확인
					if(time == k) {
						bread -= 1;
					}
				}
				//순회 후에 빵의 재고가 음수라면 불가능
				if(bread < 0) {
					output.append("Impossible");
					break;
				}
				//최대시간이 지나면 가능
				if(k == max_time) {
					output.append("Possible");
					break;
				}
			}
			output.append("\n");
		}
		System.out.println(output);
	}
	
//	public static String src = "4\r\n"
//			+ "2 2 2\r\n"
//			+ "3 4\r\n"
//			+ "2 2 2\r\n"
//			+ "1 2\r\n"
//			+ "2 2 1\r\n"
//			+ "4 2\r\n"
//			+ "2 2 1\r\n"
//			+ "3 2";
}
