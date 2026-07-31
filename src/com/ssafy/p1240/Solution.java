package com.ssafy.p1240;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer output = new StringBuffer();
	
	//graph 전역설정
	static char[][] graph;
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("res/p1240.txt"));
		reader = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 0; t< 10; t++) {
			//첫 줄에 테스트 케이스 번호
			reader.readLine();
			// 그래프 초기화
			graph = new char[100][];
			for(int i = 0; i< 100; i++) {
				graph[i] = reader.readLine().toCharArray();
			}
			for(char[] row: graph) {
				System.out.println(Arrays.toString(row));
			}
			//Arrays.toString(row) : (배열을 표현할 때 사용)
			//row.toString() : 기본 객체의 toString은 기본 주소값을 표현해줌.
		}
	}
	
	
}
