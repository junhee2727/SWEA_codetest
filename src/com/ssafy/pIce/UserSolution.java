package com.ssafy.pIce;

import java.util.ArrayDeque;
import java.util.Arrays;

class UserSolution {
    private final static int MAX_N = 100;
    
    class RESULT {
    	int[][] heights;
    	RESULT() {
    		heights = new int[MAX_N][MAX_N];
    	}
    }
    
    int WIDTH;
    
    int dir[][];
    
    int[][] mIceGroup;
    int[][] mIceBlock;
    
    static int dx[] = new int[] {0,1,0,-1};
    static int dy[] = new int[] {-1,0,1,0};
    
    
    void init(int N, int M, int mIceBlock[][], int mIceGroup[][]) {
    	System.out.println(Arrays.deepToString(mIceGroup));
    	
    	WIDTH = N;
    	dir = new int[N][N];
    	for(int i = 0; i < N; i++) {
    		Arrays.fill(dir[i], -1);
    	}
    	
    	this.mIceGroup = mIceGroup;
    	this.mIceBlock = mIceBlock;
    	
    	for(int[] iceGroup : mIceGroup) {
    		bfs(iceGroup);
    	}
    		
//    	for(int[] row : dir) {
//    		for(int i : row) {
//    			System.out.print(i + "\t");
//    		}
//    		System.out.println();
//    	}
    	
    }

    RESULT oneYearLater() {
    	//융해
    	for(int y = 0; y < WIDTH; y++) {
    		for(int x = 0; x < WIDTH; x++) {
    			
    			if (mIceBlock[y][x] == 0)
    				continue;
    			
    			mIceBlock[y][x] --;
    			
    			if(mIceBlock[y][x] == 0)
    				dir[y][x] = -1;
    		}
    	}
    	
    	//이동 및 병합
    	int[][] res_grid = new int[WIDTH][WIDTH];
    	int[][] res_dir = new int[WIDTH][WIDTH];
    	for(int y = 0; y < WIDTH; y++) {
    		for(int x = 0; x < WIDTH; x++) {
    			
    			if(dir[y][x] == -1)
    				continue;
    			
    			int cdir = dir[y][x];
    			int nx = x + dx[cdir];
    			int ny = y + dy[cdir];
    			
    			
    			
    		}
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	RESULT res = new RESULT();
    	
    	return res;
    }
    
    void bfs(int[] iceGroup) {
    	
    	int cx = iceGroup[0];
    	int cy = iceGroup[1];
    	int cdir = iceGroup[2];
    	
    	ArrayDeque<int[]> que = new ArrayDeque<>();
    	boolean[][] vis = new boolean[WIDTH][WIDTH];
    	
    	que.offer(new int[] {cx,cy});
    	vis[cy][cx] = true;
    	dir[cy][cx] = cdir;
    	
    	while(!que.isEmpty()) {
    		int[] cur = que.poll();
    		cx = cur[0];
    		cy = cur[1];
    		
    		for(int i = 0; i <4; i++) {
    			int nx = ((cx + dx[i]) % WIDTH + WIDTH) % WIDTH;
    			int ny = ((cy + dy[i]) % WIDTH + WIDTH) % WIDTH;
    			
    			if(mIceBlock[ny][nx] == 0)
    				continue;
    			
    			if(vis[ny][nx])
    				continue;
    			
    			que.offer(new int[] {nx,ny});
    			
    			dir[ny][nx] = cdir;
    			vis[ny][nx] = true;
    		}
    	}
    }
    
    int[] getIceInfo(int x, int y) {
    	return null;
    }
    

    
}

