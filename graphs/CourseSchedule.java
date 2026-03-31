package graphs;

import java.util.*;

/**
 * LeetCode #207 - Course Schedule
 * Topological sort using DFS cycle detection
 * Time: O(V+E), Space: O(V+E)
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] pre : prerequisites) adj.get(pre[1]).add(pre[0]);

        int[] state = new int[numCourses]; // 0=unvisited, 1=visiting, 2=done

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycle(adj, state, i)) return false;
        }
        return true;
    }

    private boolean hasCycle(List<List<Integer>> adj, int[] state, int node) {
        state[node] = 1;
        for (int neighbor : adj.get(node)) {
            if (state[neighbor] == 1) return true;
            if (state[neighbor] == 0 && hasCycle(adj, state, neighbor)) return true;
        }
        state[node] = 2;
        return false;
    }
}
