package com.ssafy.p1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	
	static int[] dx = new int[]{1,-1,0,0};
	static int[] dy = new int[] {0,0,1,-1};
	
	
	public static void main(String[] args)throws IOException{

		int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
		
		for(int t = 0; t<10; t++) {
			st = new StringTokenizer(reader.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			char[][] grid = new char[H][W];
			
			int cx = -1;
			int cy = -1;
			
			for(int i = 0; i<H; i++) {
				String s = reader.readLine();
				for(int j = 0; j<W; j++) {
					char cur = s.charAt(j);
					
					if("<>^v".contains(cur+"")) {
						cx = j;
						cy = i;
					}
					
					grid[i][j] = cur;
				}
			}
			
			//커맨드 개수 생략
			reader.readLine();
			
			for(char c: reader.readLine().toCharArray()) {
				if(c == 'S') {
					int dir = "><v^".indexOf(grid[cy][cx] + "");
					
					int nx = cx;
					int ny = cy;
					
					//cy,cx 에서 시작해서 맵 끝까지 한방향으로 순회
					while(isValid(nx,ny,W,H)) {
						
						if(grid[ny][nx] == '*') {
							grid[ny][nx] = '.';
							break;
						}
						
						if(grid[ny][nx] == '#') break;
						
						nx += dx[dir];
						ny += dy[dir];
					}
				}
				
				//UDLR인 경우
				else {
					int dir = "RLDU".indexOf(c+"");
					
					int nx = cx+dx[dir];
					int ny = cy+dy[dir];
					
					//다음 칸이 평지인 경우
					if(isValid(nx, ny, W, H) && grid[ny][nx] == '.') {
						grid[cy][cx] = '.';
						
						cx = nx;
						cy = ny;
					}
					
					grid[cy][cx] = "><v^".charAt(dir);
				}	
			}
			
			
			output.append("#").append(t+1).append(" ");
			for(char[] row: grid) {
				for(char c: row) {
					output.append(c);
				}
				output.append("\n");
			}
		}
		System.out.println(output);
	}
	
	//x, y, w, h를 넣으면 valid한 값인지 출력
	static boolean isValid(int x, int y, int w, int h) {
		return x >= 0 && x < w && y>= 0 && y<h;
	}
	
}
