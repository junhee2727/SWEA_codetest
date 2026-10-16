package com.ssafy.p5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 1; t<= test_case; t++) {
			Deque<Character> deq = new ArrayDeque<>();
			Set<Integer> set = new HashSet<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>((o,l) ->  {
				return -Integer.compare(o, l);
			});
			
			st = new StringTokenizer(reader.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			String tmp = reader.readLine();
			for(int i = 0; i < N; i++) {
				deq.offerLast(tmp.charAt(i));
			}
			
			for(int k = 0; k < N/4; k++) {
				for(int i = 0; i < 4; i++) {
					String temp = "";
					for(int j = 0; j < N/4; j++) {
						char c = deq.pollFirst();
						temp += c;
						deq.offerLast(c);
					}
					
					set.add(Integer.parseInt(temp, 16));
				}
				deq.offerLast(deq.pollFirst());
			}
			
			
			
			set.forEach((el) -> {pq.add(el);});
			
			for(int i = 0; i<K-1; i++) {
				pq.poll();
			}
			
			output.append("#").append(t).append(" ").append(pq.poll()).append("\n");
		}
		System.out.println(output);
	}
}
