package com.ssafy.p16911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {		
			// 배열 초기화
			int N = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			st = new StringTokenizer(reader.readLine());
			Integer[] num_arr = new Integer[N];
			for(int i = 0; i<N; i++) {
				num_arr[i] = Integer.parseInt(st.nextToken());
			}
			
			//내림차순 배열 생성
			List<Integer> sorted_arr = Arrays.asList(num_arr);
			sorted_arr.sort((o1, o2) -> Integer.compare(o1, o2) * -1);
			
			//visited 배열 생성 및 첫 값 삽입
			Integer[] visited = new Integer[N];
			int res = sorted_arr.get(0);
			for(int i = 0; i<N; i++) {
				if(num_arr[i] == res) {
					visited[i] = 0;
				}
			}
			
			//현재 순서로 큰 수
			int crt = -1;
			//색의 수
			int color = 1;
			for(int i = 1; i<N; i++) {
				crt = sorted_arr.get(i);
				
			}
			System.out.println(res);
		}
	}
	
}
