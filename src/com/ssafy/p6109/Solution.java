package com.ssafy.p6109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args)throws IOException{
        int test_case = Integer.parseInt(new StringTokenizer(reader.readLine().trim()).nextToken());
        for(int t = 0; t<test_case; t++) {
            //n, cmd 초기화
            st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            String cmd = st.nextToken();
            int[][] map = new int[n][n];

            //맵 초기화
            for(int i = 0; i<n;i++){
                st = new StringTokenizer(reader.readLine());
                for(int j = 0; j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            List<Integer> list;
            List<Integer> result;
            if(cmd.equals("left")){
                for(int i = 0; i<n; i++){
                    list = new ArrayList<>();
                    //0이 아닌 수를 List에 추가
                    for(int j = 0; j<n; j++){
                        if(map[i][j] != 0 ){
                            list.add(map[i][j]);
                        }
                    }

                    //같은 수 합치기
                    result = new ArrayList<>();
                    for(int j = 0; j<list.size(); j++){
                        if(j+1 <list.size() && list.get(j).equals(list.get(j+1))){
                            result.add(list.get(j) * 2);
                            j++;
                        }else{
                            result.add(list.get(j));
                        }
                    }

                    int c = 0;
                    //다시 맵에 넣기
                    for(int value: result){
                        map[i][c++] = value;
                    }
                    while(c<n){
                        map[i][c++] = 0;
                    }
                }
            }
            else if(cmd.equals("right")){
                for(int i = 0; i<n; i++){
                    list = new ArrayList<>();
                    for(int j = n-1; j>=0;j--){
                        if(map[i][j] != 0){
                            list.add(map[i][j]);
                        }
                    }

                    result = new ArrayList<>();
                    for(int j = 0; j<list.size(); j++){
                        if(j+1 < list.size() && list.get(j).equals(list.get(j+1))){
                            result.add(list.get(j) * 2);
                            j++;
                        }else{
                            result.add(list.get(j));
                        }
                    }

                    int c = n-1;
                    for(int value: result){
                        map[i][c--] = value;
                    }
                    while(c>=0){
                        map[i][c--] = 0;
                    }
                }
            }
            else if(cmd.equals("up")){
                for(int j = 0; j<n; j++){
                    list = new ArrayList<>();
                    //0이 아닌 수를 List에 추가
                    for(int i = 0; i<n; i++){
                        if(map[i][j] != 0 ){
                            list.add(map[i][j]);
                        }
                    }

                    //같은 수 합치기
                    result = new ArrayList<>();
                    for(int i = 0; i<list.size(); i++){
                        if(i+1 <list.size() && list.get(i).equals(list.get(i+1))){
                            result.add(list.get(i) * 2);
                            i++;
                        }else{
                            result.add(list.get(i));
                        }
                    }

                    int c = 0;
                    //다시 맵에 넣기
                    for(int value: result){
                        map[c++][j] = value;
                    }
                    while(c<n){
                        map[c++][j] = 0;
                    }
                }
            }
            else if(cmd.equals("down")){
                for(int j = 0; j<n; j++){
                    list = new ArrayList<>();
                    for(int i = n-1; i>=0;i--){
                        if(map[i][j] != 0){
                            list.add(map[i][j]);
                        }
                    }

                    result = new ArrayList<>();
                    for(int i = 0; i<list.size(); i++){
                        if(i+1 < list.size() && list.get(i).equals(list.get(i+1))){
                            result.add(list.get(i) * 2);
                            i++;
                        }else{
                            result.add(list.get(i));
                        }
                    }

                    int c = n-1;
                    for(int value: result){
                        map[c--][j] = value;
                    }
                    while(c>=0){
                        map[c--][j] = 0;
                    }
                }
            }
            output.append("#").append(t+1).append("\n");
            for(int[] row: map){
                for(int num: row){
                    output.append(num).append(" ");
                }
                output.append("\n");
            }
        }
        System.out.println(output);
    }
}
