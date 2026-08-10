package com.ssafy.p2819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static StringTokenizer st;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	static char[][] graph;
	
	public static void main(String[] args) throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for (int t = 0; t<test_case; t++) {	
			//Set내에 동일한 String이 있는지 확인
			Set<String> visited = new HashSet<>();
			Deque<Node> deq = new ArrayDeque<>();
			graph = new char[4][4];
			for(int i = 0; i<4; i++) {
				st = new StringTokenizer(reader.readLine());
				for( int j = 0; j < 4; j++) {
					char num = st.nextToken().charAt(0);
					graph[i][j] = num;
				}
			}
			int x;
			int y;
			for(int i = 0; i<4;i++) {
				for(int j = 0; j<4; j++) {
					y = i; 
					x = j; 
					deq.add(new Node(x,y,1,""+graph[y][x]));
					while(!deq.isEmpty()) {
						Node node = deq.pop();
						if(node.cnt == 7) {
							visited.add(node.s);
							continue;
						}
						for(int k = 0; k<4; k++) {
							int nx = node.x + dx[k];
							int ny = node.y + dy[k];
							if(isValid(nx,ny,4)) {
								deq.add(new Node(nx,ny, node.cnt+1, node.s+graph[ny][nx]));
							}
						}
					}
				}
			}
			output.append("#").append(t+1).append(" ").append(visited.size()).append("\n");
		}
		System.out.println(output);
	}
	
	static boolean isValid(int x, int y, int N) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static class Node{
		public int x;
		public int y;
		public int cnt;
		public String s;
		public Node(int x, int y, int cnt, String s) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.s = s;
		}
	}
}	
