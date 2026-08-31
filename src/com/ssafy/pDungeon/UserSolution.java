package com.ssafy.pDungeon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

//11:00 시작
class UserSolution {
	int mMaxStamina;
	int mMap[][];
	int N;
	Integer[][] min_graph = new Integer[201][201];
	Map<Integer, Node> map = new HashMap<>();
	Map<Node, Integer> map_r = new HashMap<>();

	static int[] dx = new int[] { 1, -1, 0, 0 };
	static int[] dy = new int[] { 0, 0, 1, -1 };

	void init(int N, int mMaxStamina, int mMap[][]) {
		this.mMaxStamina = mMaxStamina;
		this.mMap = mMap;
		this.N = N;
		return;
	}

	void addGate(int mGateID, int mRow, int mCol) {
		// 이 때
		mMap[mRow][mCol] = 2;
		min_graph[mGateID][mGateID] = 0;
		map.put(mGateID, new Node(mRow, mCol));
		map_r.put(new Node(mRow, mCol), mGateID);
		return;
	}

	void removeGate(int mGateID) {
		// 이 때 그래프 상관관계도 순회하며 지워야함
		Node node = map.get(mGateID);
		mMap[node.y][node.x] = 0;
		for (int i = 0; i < 201; i++) {
			min_graph[mGateID][i] = null;
			min_graph[i][mGateID] = null;
		}
		map.remove(mGateID);
		return;
	}

	int getMinTime(int mStartGateID, int mEndGateID) {
		// BFS 순회
		// 한번의 BFS로 목표 노드를 탐색할 때 까지
		// 2를 만나면 목표 노드가 아니더라도 min_graph에 추가
		// 2를 만났을 때 해당 노드가 목표 노드로 가는 최소거리를 담고 있다면, min_tmp 에 cnt + 해당 최소거리를 할당
		// min_tmp 가 cnt와 같아진다면 min_tmp를 return
		if(min_graph[mStartGateID][mEndGateID] != null) {
			return min_graph[mStartGateID][mEndGateID];
		}
		int cnt = 0;
		int stamina = mMaxStamina;
		Node startNode = map.get(mStartGateID);
		Node endNode = map.get(mEndGateID);

		Deque<Node> deq = new ArrayDeque<>();
		deq.add(startNode);

		// 임시 큐
		Deque<Node> tmp_deq = new ArrayDeque<>();

		Boolean[][] visited = new Boolean[350][350];
		visited[startNode.y][startNode.x] = true;

		int tmp_min = Integer.MAX_VALUE;

		while (true) {
//			if (cnt == tmp_min) {
//				return tmp_min;
//			}
			// deq이 빌 때 까지
			while (!deq.isEmpty()) {
				Node node = deq.pop();
				int cx = node.x;
				int cy = node.y;
				int nx;
				int ny;

				// 2를 만나면 목표 노드가 아니더라도 min_graph에 추가
				// 2를 만났을 때 해당 노드가 목표 노드로 가는 최소거리를 담고 있다면, min_tmp 에 cnt + 해당 최소거리를 할당

				if (mMap[cy][cx] == 2 && cnt != 0) {
					stamina = mMaxStamina;
					int NID = map_r.get(new Node(cy, cx));
//					System.out.println("NID: " + NID);
					
					if(NID == mEndGateID) {
						System.out.println(cnt);
						return cnt;
					}
					
					if(min_graph[mStartGateID][NID] != null && min_graph[mStartGateID][NID] > cnt) {
						min_graph[mStartGateID][NID] = cnt;
						min_graph[NID][mStartGateID] = cnt;
					}
					
					//스태미나 고려 (내 스태미나로 거기까지 도달할 수 있다면)
					if (min_graph[NID][mEndGateID] != null && stamina - min_graph[NID][mEndGateID] > 0) {
						tmp_min = Math.min(tmp_min, min_graph[NID][mEndGateID]);
					}
				}
				
				
				for (int i = 0; i < 4; i++) {
					nx = cx + dx[i];
					ny = cy + dy[i];

					if (mMap[ny][nx] != 1 && visited[ny][nx] == null) {
						visited[ny][nx] = true;
						tmp_deq.add(new Node(ny, nx));
					}
				}
			}
			while(!tmp_deq.isEmpty()) {
				deq.add(tmp_deq.pop());
			}

			if (stamina == 0) {
				System.out.println("-1");
				return -1;
			}

			cnt++;
			stamina--;
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int y, int x) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(Integer.valueOf(x), Integer.valueOf(y));
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return x == other.x && y == other.y;
		}
	}
}