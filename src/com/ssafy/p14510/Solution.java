package com.ssafy.p14510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			int tree_cnt = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken()); 
			st = new StringTokenizer(reader.readLine());
			Integer[] trees = new Integer[tree_cnt];
			for(int i = 0; i<tree_cnt; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
			}
			int top_height = 0;
			for(int tr : trees) {
				if(top_height < tr) {
					top_height = tr;
				}
			}
			for(int i = 0; i<trees.length; i++) {
				trees[i] = trees[i] - top_height;
			}
			int cnt = 1;
			while(true) {
				for(int i = 0; i<trees.length; i++) {
					int tmp = cnt % 2 == 0? 2:1;
					
				}
			}
		}
	}
}
