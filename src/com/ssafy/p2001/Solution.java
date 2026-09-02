package com.ssafy.p2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int M;
	static int N;
	
	
	public static void main(String[] args)throws IOException{
		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
		for(int t = 0; t<test_case; t++) {
			st = new StringTokenizer(reader.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//패딩 적용 그래프
			int [][] graph = new int[N+M-1][N+M-1];
			int max_fly = -1;
			
			//패딩을 제외한 칸에 정수 할당
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(reader.readLine());
				for(int j = 0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cur_fly = 0;
			ArrayDeque<Integer> que;
				
			//res[i][j+1] = res[i][j] - res[i:i+M][j] + res[i:i+M][j+M+1] 
			//res[i+1][j] = res[i][j] - res[i][j+M] + res[i+M+1][j:j+M]
			//좌에서 우로 진행하며 위에서 아래로 값 접근
			for(int i = 0; i<N; i++) {
				cur_fly = 0;
				que = initRow(i, graph); 
				for(int f: que) {
					cur_fly += f;
				}
				max_fly = Math.max(max_fly, cur_fly);
				
				//좌에서 우로
				for(int j = 0; j<N-1; j++) {

					//que의 마지막 값 M개 제거
					for(int k = 0; k < M; k++) {
						cur_fly -= que.removeFirst();
					}
					
					// M개 값 추가 (위에서 아래로)
					for(int k = 0; k<M; k++) {
						int tmp = graph[i+k][j+M];
						
						que.offerLast(tmp);
						cur_fly += tmp;
					}
					
					max_fly = Math.max(max_fly, cur_fly);
				}
			}
			
			System.out.println("#"+ (t+1) + " " +max_fly);
		}
	}
	
	static ArrayDeque<Integer> initRow(int row_num, int[][] graph){
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for(int i = 0; i<M; i++) {
			for(int j = 0; j<M; j++) {
				int tmp = graph[j+row_num][i];
				que.offerLast(tmp);
			}
		}
		return que;
	}
	
	
}
