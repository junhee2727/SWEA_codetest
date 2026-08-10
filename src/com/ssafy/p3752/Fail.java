package com.ssafy.p3752;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Fail {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;

	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t<test_case; t++) {
			//문제의 개수
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			List<Integer> scoreList = new ArrayList<>();
			scoreList.add(0);
			st = new StringTokenizer(reader.readLine());
			for(int i =0; i<n;i++) {
				int score = Integer.parseInt(st.nextToken());
				List<Integer>tmp = new ArrayList<>();
				scoreList.stream().forEach(item -> tmp.add(score+item));
				tmp.stream().forEach(item -> {
					if(!scoreList.contains(item)) {
						scoreList.add(item);
					}
				});
			}
			output.append("#").append(t+1).append(" ").append(scoreList.size()).append("\n");
		}
		System.out.println(output);
	}
}