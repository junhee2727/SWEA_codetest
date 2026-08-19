package com.ssafy.p1251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution3 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[] xl;
	static int[] yl;
	
	static int[] parent;
	
	public static void main(String args[]) throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			
			xl = new int[n];
			yl = new int[n];
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<n; i++) {
				xl[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<n; i++) {
				yl[i] = Integer.parseInt(st.nextToken());
			}
			
			double e = Double.parseDouble(new StringTokenizer(reader.readLine().trim()).nextToken());
			
			List<Edge> edgeList = new ArrayList<>();
			
			//엣지 만들기
			for(int i = 0; i<n;i++) {
				for (int j = i+1; j<n; j++) {
					long dx = (long) xl[j] - xl[i];
					long dy = (long) yl[j] - yl[i];
					
					edgeList.add(new Edge(i,j,dx * dx + dy * dy));
				}
			}
			
			//오름차순 정렬
			Collections.sort(edgeList);
			
			//처음에는 자기 자신이 대표자
			parent = new int[n];
			for(int i =0; i<n; i++) {
				parent[i] = i;
			}
			
			long res = 0;
			int cnt = 0;
			for(Edge edge: edgeList) {
				if(find(edge.from) != find(edge.to)) {
					union(edge.from, edge.to);
					
					res += edge.cost;
					cnt++;
				}
				
				if(cnt == n -1) {
					break;
				}
			}
			
			output.append("#").append(t+1).append(" ").append(Math.round(res * e)).append("\n");
		}
		System.out.println(output);
	}
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		long cost;
		public Edge(int from, int to, long cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}	
	}
	
	//부모를 찾는 함수
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	//두 그룹을 합치는 함수
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			parent[b] = a;
		}
	}
}
