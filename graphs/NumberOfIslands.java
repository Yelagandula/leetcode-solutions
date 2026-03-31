package graphs;

/**
 * LeetCode #200 - Number of Islands
 * Time: O(m*n), Space: O(m*n) — BFS approach
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int r, int c) {
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        grid[r][c] = '0';
        java.util.Queue<int[]> q = new java.util.LinkedList<>();
        q.offer(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dirs) {
                int nr = cur[0]+d[0], nc = cur[1]+d[1];
                if (nr>=0 && nr<grid.length && nc>=0 && nc<grid[0].length && grid[nr][nc]=='1') {
                    grid[nr][nc] = '0';
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
