package com.ssafy.p1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args)throws IOException{
		for(int t = 0; t<10; t++) {
			reader.readLine();
			
			
			Deque<Integer> deq = new ArrayDeque<>();
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<8; i++) {
				deq.addLast(Integer.parseInt(st.nextToken()));
			}
			
			
			int offset = 1;
			while(true) {
				int tmp = deq.pop() - offset;
				
				if(tmp <= 0) {
					deq.addLast(0);
					break;
				}
				deq.addLast(tmp);
	
				offset = (offset) % 5 + 1;
			}
			
			
			output.append("#").append(t+1).append(" ");
			for(int i = 0; i < 8; i++) {
				output.append(deq.pop()+" ");
			}
			output.append("\n");
		}
		System.out.println(output);
	}
	
}
