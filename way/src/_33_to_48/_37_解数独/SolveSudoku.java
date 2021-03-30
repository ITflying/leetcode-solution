package _33_to_48._37_解数独;

import java.rmi.ServerException;
import java.util.*;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * 空白格用'.'表示。
 *
 * 提示：
 * 给定的数独序列只包含数字1-9和字符'.'。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是9x9形式的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/26
 **/
public class SolveSudoku {
    public static void main(String[] args) throws ServerException {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '6', '.', '.', '.', '.', '3'},
                {'4', '.', '8', '.', '3', '.', '.', '.', '1'},
                {'7', '.', '.', '2', '.', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '4', '1', '9', '.', '.', '.', '5'},
                {'.', '.', '.', '8', '.', '.', '.', '7', '9'}
        };
        solveSudoku_01(board);
    }

    /**
     * 官方递归 + 回溯
     */
    private static boolean[][] line = new boolean[9][9];
    private static boolean[][] column = new boolean[9][9];
    private static boolean[][][] block = new boolean[3][3][9];
    private static boolean valid = false;
    private static List<int[]> spaces = new ArrayList<int[]>();

    private static void solveSudoku_01(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs_01(board, 0);
    }

    private static void dfs_01(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs_01(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }

    /**
     * 官方递归 + 回溯：位运算优化版
     */
    class Solution2{
        private int[] line = new int[9];
        private int[] column = new int[9];
        private int[][] block = new int[3][3];
        private boolean valid = false;
        private List<int[]> spaces = new ArrayList<>();

        public void solveSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        spaces.add(new int[]{i, j});
                    } else {
                        int digit = board[i][j] - '0' -1;
                        flip(i, j ,digit);
                    }
                }
            }

            dfs(board, 0);
        }

        public void dfs(char[][] board, int pos) {
            if (pos == spaces.size()) {
                valid = true;
                return;
            }

            int[] space = spaces.get(pos);
            int i = space[0], j = space[1];
            int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
            for (;mask != 0 && !valid; mask &= (mask - 1)) {
                int digitMask = mask & (-mask);
                int digit = Integer.bitCount(digitMask - 1);
                flip(i, j, digit);
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos+1);
                flip(i, j ,digit);
            }
        }

        public void flip(int i, int j, int digit) {
            line[i] ^= (1 << digit);
            column[j]= (1 << digit);
            block[i /3][j/3] ^= (1<<digit);
        }
    }



}
