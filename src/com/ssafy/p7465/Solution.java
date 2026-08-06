package com.ssafy.p7465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer output = new StringBuffer();
    static StringTokenizer st;
    static Map<Integer, ArrayList<Integer>> graph;
    static HashSet<Integer> visited;

    public static void main(String[] args) throws IOException {
        int test_case = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
        for(int t = 0; t< test_case; t++){
            graph = new HashMap<Integer, ArrayList<Integer>>();
            st = new StringTokenizer(reader.readLine());
            // N : 창용 마을에 사는 사람 수, M: 관계의 수
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            // 그래프 각 정점에 리스트 초기화
            for(int i = 1; i<=N; i++){
                graph.put(i, new ArrayList<Integer>());
            }
            for(int i = 0; i<M; i++){
                st = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            visited = new HashSet<Integer>();
            int res = 0;
            for(int i = 1; i<=N; i++){
                if(!visited.contains(i)){
                    DFS(i);
                    res++;
                }
            }
            System.out.println("#"+(t+1)+" "+res);
        }
    }
    //DFS
    static void DFS(int start){
//        System.out.println("current: "+start);
        visited.add(start);
        for(int num: graph.get(start)){
            if(!visited.contains(num)) {
                DFS(num);
            }
        }
    }
}
