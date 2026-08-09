package com.ssafy.p5432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution { 
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;
	
	public static void main(String args[]) throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			char[] line = reader.readLine().toCharArray();
			
			Deque<Character> deque = new ArrayDeque<>();
			int res = 0;
			boolean flag = false;
			for(int i = 0; i< line.length; i++) {
				char cmd = line[i];
				if(cmd == '(') {
					deque.addFirst(cmd);
					flag = true;
					// ')'인 경우 
				}else {
					//레이저인 경우
					if(flag) {
						deque.removeFirst();
						res += deque.size();
						flag = false;
					}
					//쇠 막대기의 끝인 경우
					else {
						deque.removeFirst();
						res++;
					}
//					System.out.println("i: " + i + "res: " + res);
				}
			}
			output.append("#").append(t+1).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
	

}
