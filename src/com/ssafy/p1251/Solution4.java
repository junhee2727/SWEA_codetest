package com.ssafy.p1251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution4 {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[] xl;
	static int[] yl;
	
	static int[] parent;
	static ArrayList<Line> line_list;
	
	public static void main(String[] args)throws IOException {
		int test_case = Integer.parseInt(reader.readLine().trim());
		for(int t = 0; t<test_case; t++) {
			int N = Integer.parseInt(reader.readLine().trim());
			
			xl = new int[N];
			yl = new int[N];
			line_list = new ArrayList<>();
			
			parent = new int[N];
			Arrays.fill(parent, -1);
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i < N; i++) {
				xl[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i < N; i++) {
				yl[i] = Integer.parseInt(st.nextToken());
			}

			double E = Double.parseDouble(reader.readLine().trim());
			
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					double length = Math.hypot(xl[i] - xl[j], yl[i]-yl[j]);
					line_list.add(new Line(i, j, length * length * E));
				}
			}
			
			line_list.sort(new Comparator<Line>() {
				@Override
				public int compare(Line o1, Line o2) {
					// TODO Auto-generated method stub
					return Double.compare(o1.value, o2.value);
				}
			});
			
			double result = 0;
			int cnt = 0;
			
			for(int i = 0; i < line_list.size(); i++) {
				if(cnt == N-1)
					break;
				
				Line line = line_list.get(i);
				if(union(line.start, line.end) == false)
					continue;
				
				result += line.value;
				cnt ++;
			}
			
			output.append("#").append(t+1).append(" ").append(Math.round(result)).append("\n");
			
		}
		
		System.out.println(output);
	}
	
	static int find(int node) {
		if(parent[node] == -1)
			return node;
		
		return parent[node] = find(parent[node]);
	}
	
	static boolean union(int node1, int node2) {
		int parent1 = find(node1);
		int parent2 = find(node2);
		
		if(parent1 == parent2)
			return false;
		
		parent[parent2] = parent1; 
		return true;
	}
	
	
	static class Line{
		int start;
		int end; 
		double value;
		
		public Line(int start, int end, double value) {
			super();
			this.start = start;
			this.end = end;
			this.value = value;
		}
	}
	
}
