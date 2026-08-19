package com.ssafy.p1251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static Integer[] graph;
	static Double[] minimum;
	static Integer[] lst;
	
	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t< test_case; t++) {
			int n = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
			graph = new Integer[1_000_001];			//x, y 좌표 저장용
			minimum = new Double[1_000_001];		//해당 x 좌표의 노드가 연결된 최소 거리
			lst = new Integer[n];					//x좌표의 목록
			
			//x좌표 저장
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i<n; i++) {
				lst[i] = Integer.parseInt(st.nextToken());
			}
			
			//그래프, minimum 초기화 
			st = new StringTokenizer(reader.readLine());
			for(int x: lst) {
				graph[x] = Integer.parseInt(st.nextToken());
				minimum[x] = Double.MAX_VALUE;
			}
			
			double e = Double.parseDouble(new StringTokenizer(reader.readLine().trim()).nextToken());
			
			for(int i = 0; i<n; i++) {
				for(int j = 0; j<n; j++) {
					if(i == j) continue;			//비교하는 대상이 동일 노드면 통과
					int cx = lst[i];
					int cy = graph[cx];
					int nx = lst[j];
					int ny = graph[nx];
					minimum[nx] = Math.min(minimum[nx],Math.pow(Math.pow(nx-cx, 2) + Math.pow(ny-cy, 2), 0.5) * e);
				}
			}
			double res = 0;
			for(int x : lst) {
				System.out.println("x: "+x+" minimum_x: ");
//				res += minimum[x];
			}
			
			output.append("#").append(t+1).append(" ").append(Math.round(res)).append("\n");
		}
		System.out.println(output);
	}
}
