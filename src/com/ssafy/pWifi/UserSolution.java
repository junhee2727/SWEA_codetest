package com.ssafy.pWifi;

import java.util.ArrayList;
import java.util.PriorityQueue;

class UserSolution {

	static final int BUCKET_SIZE = 100;

	ArrayList<Node>[][] bucket;

	Node[] node_list;

	int N;
	int mLimit;
	int bucketCount;

	void init(int N, int mLimit) {

		this.N = N;
		this.mLimit = mLimit;

		node_list = new Node[50001];

		bucketCount = (N + BUCKET_SIZE - 1) / BUCKET_SIZE;

		bucket = new ArrayList[bucketCount][bucketCount];

		for (int y = 0; y < bucketCount; y++) {
			for (int x = 0; x < bucketCount; x++) {
				bucket[y][x] = new ArrayList<>();
			}
		}
	}

	void addRadio(int K, int mID[], int mFreq[], int mY[], int mX[]) {

		for (int i = 0; i < K; i++) {

			int by = mY[i] / BUCKET_SIZE;
			int bx = mX[i] / BUCKET_SIZE;

			Node node = new Node(mX[i], mY[i], mID[i], mFreq[i]);

			// ID로 바로 접근하기 위한 배열
			node_list[mID[i]] = node;

			// 공간 검색을 위한 bucketg
			bucket[by][bx].add(node);
		}
	}

	int getMinPower(int mID, int mCount) {

		Node cur = node_list[mID];

		int myBy = cur.y / BUCKET_SIZE;
		int myBx = cur.x / BUCKET_SIZE;

		/*
		 * PQ의 맨 위에는 현재 선택된 후보 중 "가장 나쁜 후보"가 오도록 한다.
		 *
		 * 1. power가 큰 노드가 더 나쁨 2. power가 같다면 ID가 큰 노드가 더 나쁨
		 */
		PriorityQueue<Node_score> pq = new PriorityQueue<>((a, b) -> {
			if (a.score != b.score) {
				return Integer.compare(b.score, a.score);
			}

			return Integer.compare(b.mID, a.mID);
		});

		/*
		 * range = 0 자기 bucket
		 *
		 * range = 1 자기 주변 1칸짜리 테두리
		 *
		 * range = 2 주변 2칸짜리 테두리
		 *
		 * ...
		 */
		for (int range = 0; range < bucketCount; range++) {

			int top = myBy - range;
			int bottom = myBy + range;
			int left = myBx - range;
			int right = myBx + range;

			if (range == 0) {

				checkBucket(cur, myBy, myBx, mCount, pq);

			} else {

				/*
				 * 위쪽 / 아래쪽 테두리
				 */
				for (int bx = left; bx <= right; bx++) {

					if (bx < 0 || bx >= bucketCount) {
						continue;
					}

					if (top >= 0) {
						checkBucket(cur, top, bx, mCount, pq);
					}

					// top == bottom인 경우 중복 방지
					if (bottom < bucketCount && bottom != top) {
						checkBucket(cur, bottom, bx, mCount, pq);
					}
				}

				/*
				 * 왼쪽 / 오른쪽 테두리
				 *
				 * 위/아래 모서리는 위에서 이미 검사했으므로 top + 1 ~ bottom - 1만 확인한다.
				 */
				for (int by = top + 1; by <= bottom - 1; by++) {

					if (by < 0 || by >= bucketCount) {
						continue;
					}

					if (left >= 0) {
						checkBucket(cur, by, left, mCount, pq);
					}

					if (right < bucketCount && right != left) {
						checkBucket(cur, by, right, mCount, pq);
					}
				}
			}

			/*
			 * 현재 range까지 검사한 뒤 아직 검사하지 않은 공간에서 나올 수 있는 최소 Manhattan distance를 구한다.
			 */
			int minRemainDistance = getMinDistanceToOutside(cur, myBy, myBx, range);

			/*
			 * -1이면 도시 전체 bucket을 이미 검사했다.
			 */
			if (minRemainDistance == -1) {
				break;
			}

			/*
			 * 연결 제한 자체를 넘어간다면 바깥쪽은 더 볼 필요 없다.
			 */
			if (minRemainDistance * 10 > mLimit) {
				break;
			}

			/*
			 * 이미 mCount개를 확보했고,
			 *
			 * 앞으로 아무리 좋은 노드가 등장해도 현재 PQ의 최악 후보보다 비싸다면 종료.
			 *
			 * +1000을 하지 않는 이유: 앞으로 나올 노드가 같은 주파수일 수도 있기 때문.
			 */
			if (pq.size() == mCount) {

				int worstPower = pq.peek().score;

				if (minRemainDistance * 10 > worstPower) {
					break;
				}
			}
		}

		int answer = 0;

		while (!pq.isEmpty()) {
			answer += pq.poll().score;
		}

		return answer;
	}

	/*
	 * 특정 bucket에 있는 모든 Node를 확인한다.
	 */
	void checkBucket(Node cur, int by, int bx, int mCount, PriorityQueue<Node_score> pq) {

		if (by < 0 || by >= bucketCount || bx < 0 || bx >= bucketCount) {
			return;
		}

		for (Node next : bucket[by][bx]) {

			// 자기 자신 제외
			if (next.mID == cur.mID) {
				continue;
			}

			int distance = Math.abs(cur.y - next.y) + Math.abs(cur.x - next.x);

			int power = distance * 10;

			// 주파수가 다르면 추가 1000
			if (cur.freq != next.freq) {
				power += 1000;
			}

			// 연결 제한 초과
			if (power > mLimit) {
				continue;
			}

			Node_score candidate = new Node_score(next.mID, power);

			/*
			 * 아직 mCount개를 못 채웠다면 일단 추가.
			 */
			if (pq.size() < mCount) {

				pq.offer(candidate);

				continue;
			}

			/*
			 * PQ 맨 위 = 현재 후보 중 가장 나쁜 후보
			 */
			Node_score worst = pq.peek();

			/*
			 * candidate가 worst보다 좋은가?
			 *
			 * 1. power가 작거나 2. power가 같은데 ID가 더 작거나
			 */
			if (candidate.score < worst.score || (candidate.score == worst.score && candidate.mID < worst.mID)) {

				pq.poll();
				pq.offer(candidate);
			}
		}
	}

	/*
	 * 현재 Node와 특정 bucket 사이에서 가능한 최소 Manhattan distance.
	 */
	int getMinDistanceToBucket(Node cur, int by, int bx) {

		int minY = by * BUCKET_SIZE;
		int maxY = Math.min(N - 1, minY + BUCKET_SIZE - 1);

		int minX = bx * BUCKET_SIZE;
		int maxX = Math.min(N - 1, minX + BUCKET_SIZE - 1);

		int dy = 0;
		int dx = 0;

		if (cur.y < minY) {
			dy = minY - cur.y;
		} else if (cur.y > maxY) {
			dy = cur.y - maxY;
		}

		if (cur.x < minX) {
			dx = minX - cur.x;
		} else if (cur.x > maxX) {
			dx = cur.x - maxX;
		}

		return dx + dy;
	}

	/*
	 * 현재까지 range만큼의 bucket 사각형을 검사한 상태에서,
	 *
	 * "아직 검사하지 않은 영역"에 존재할 수 있는 점과 cur 사이의 최소 Manhattan distance를 구한다.
	 */
	int getMinDistanceToOutside(Node cur, int myBy, int myBx, int range) {

		int lowBy = Math.max(0, myBy - range);
		int highBy = Math.min(bucketCount - 1, myBy + range);

		int lowBx = Math.max(0, myBx - range);
		int highBx = Math.min(bucketCount - 1, myBx + range);

		int minY = lowBy * BUCKET_SIZE;
		int maxY = Math.min(N - 1, (highBy + 1) * BUCKET_SIZE - 1);

		int minX = lowBx * BUCKET_SIZE;
		int maxX = Math.min(N - 1, (highBx + 1) * BUCKET_SIZE - 1);

		int minDistance = Integer.MAX_VALUE;

		/*
		 * 현재 검사 영역의 위쪽에 아직 공간이 존재
		 */
		if (minY > 0) {
			minDistance = Math.min(minDistance, cur.y - (minY - 1));
		}

		/*
		 * 아래쪽
		 */
		if (maxY < N - 1) {
			minDistance = Math.min(minDistance, (maxY + 1) - cur.y);
		}

		/*
		 * 왼쪽
		 */
		if (minX > 0) {
			minDistance = Math.min(minDistance, cur.x - (minX - 1));
		}

		/*
		 * 오른쪽
		 */
		if (maxX < N - 1) {
			minDistance = Math.min(minDistance, (maxX + 1) - cur.x);
		}

		if (minDistance == Integer.MAX_VALUE) {
			return -1;
		}

		return minDistance;
	}

	static class Node {

		int x;
		int y;
		int mID;
		int freq;

		public Node(int x, int y, int mID, int freq) {
			this.x = x;
			this.y = y;
			this.mID = mID;
			this.freq = freq;
		}
	}

	static class Node_score {

		int mID;
		int score;

		public Node_score(int mID, int score) {
			this.mID = mID;
			this.score = score;
		}
	}
}