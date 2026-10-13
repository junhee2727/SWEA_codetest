package com.ssafy.p2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int res;
	static List<Node> node_list;
	static List<Stair> stair_list;
	
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(reader.readLine().trim());
		
		for(int t = 1; t <= test_case; t++) {
			int N = Integer.parseInt(reader.readLine().trim());
			
			res = Integer.MAX_VALUE;
			node_list = new ArrayList<Node>();
			stair_list = new ArrayList<Stair>();
			
			
			for(int y = 0; y <N; y++) {
				st = new StringTokenizer(reader.readLine());
				for(int x = 0; x < N; x++) {
					int tmp = Integer.parseInt(st.nextToken());
					
					if(tmp == 1) node_list.add(new Node(x,y));
					
					else if(tmp > 1) stair_list.add(new Stair(x,y,tmp));
				}
			}
			
			dfs(0,0);
			
			output.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(output);
	}
	
	
	//사람의 수는 최대 10이므로, 비트 마스크를 이용해 어느 계단을 방문할지를 기록한다.
	static void dfs(int depth, int vis) {
		if(depth == node_list.size()) {
			simulate(vis);
			return;
		}
		
		//해당 depth를 방문하는 경우(계단 1을 사용하는 경우)
		dfs(depth + 1, vis|(1 << depth));
		//해당 depth를 방문하지 않는 경우(계단 0을 사용하는 경우)
		dfs(depth + 1, vis);
	}
	
	//vis 배열을 이용. 최단 시간을 구한다.
	static void simulate(int vis) {
		
		PriorityQueue<Integer>[] que = new PriorityQueue[2];
		int cres[] = new int[2];
		
		for(int i = 0; i < 2; i++) {
			que[i] = new PriorityQueue<Integer>(); 
		}
		
		for(int i = 0; i < node_list.size(); i++) {
			Node node = node_list.get(i);
			int ss = (vis & (1 << i)) == 1 << i? 1 : 0;
			Stair stair = stair_list.get(ss);
			
			int dist = Math.abs(node.x - stair.x) + Math.abs(node.y - stair.y);
			que[ss].add(dist + 1);
		}
		
		//0번 계단과 1번 계단으로 가는 시뮬레이션
		for(int i = 0; i < 2; i++) {
			int time = 0;
			PriorityQueue<Integer> queue = que[i];
			Queue<Integer> tmpque = new ArrayDeque<>();
			Stair stair = stair_list.get(i);
			
			
			while(true) {
				//퇴장
				while(!tmpque.isEmpty() && tmpque.peek() == time) {
					tmpque.poll();
				}
				
				
				//입장
				while(queue.size() > 0 && tmpque.size() < 3 && queue.peek() <= time) {
					tmpque.offer(time+stair.value);
					queue.poll();
				}
				
				if(tmpque.isEmpty() && queue.isEmpty()) break;
				
				time++;
			}
			cres[i] = time;
		}
		
		
		res = Math.min(res, Math.max(cres[0], cres[1]));
	}
	
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Stair{
		int x;
		int y;
		int value;
		public Stair(int x, int y, int value) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}
