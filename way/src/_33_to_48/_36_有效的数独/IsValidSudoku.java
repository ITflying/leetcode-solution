package _33_to_48._36_有效的数独;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 判断一个9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 数字1-9在每一行只能出现一次。
 数字1-9在每一列只能出现一次。
 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。


 上图是一个部分填充的有效的数独。
 数独部分空格内已填入了数字，空白格用'.'表示。

 示例1:
 输入:
 [
 ["5","3",".",".","7",".",".",".","."],
 ["6",".",".","1","9","5",".",".","."],
 [".","9","8",".",".",".",".","6","."],
 ["8",".",".",".","6",".",".",".","3"],
 ["4",".",".","8",".","3",".",".","1"],
 ["7",".",".",".","2",".",".",".","6"],
 [".","6",".",".",".",".","2","8","."],
 [".",".",".","4","1","9",".",".","5"],
 [".",".",".",".","8",".",".","7","9"]
 ]
 输出: true
 示例2:
 输入:
 [
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
 ]
 输出: false
 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。

 说明:
 一个有效的数独（部分已被填充）不一定是可解的。
 只需要根据以上规则，验证已经填入的数字是否有效即可。
 给定数独序列只包含数字1-9和字符'.'。
 给定数独永远是9x9形式的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-sudoku
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/03/25
 **/
public class IsValidSudoku {
    public static void main(String[] args) throws ServerException {
        char[][] board = new char[][]{
                {'.','.','.','.','.','.','.','.','2'},
                {'.','.','.','.','.','.','6','.','.'},
                {'.','.','1','4','.','.','8','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','3','.','.','.','.'},
                {'5','.','8','6','.','.','.','.','.'},
                {'.','9','.','.','.','.','4','.','.'},
                {'.','.','.','.','5','.','.','.','.'}
        };
        boolean isSudoku = isValidSudoku_01(board);
        System.out.println(isSudoku);
    }

    /**
     * 抽象三条规则；
     * 9*9=81，暴力循环一次，三条规则不满足的不符合要求
     */
    private static boolean isValidSudoku_01(char[][] board) {
        Map<Integer, Set<Character>> checkRow = new HashMap<>();
        Map<Integer, Set<Character>> checkLine = new HashMap<>();
        Map<Integer, Set<Character>> checkBlack = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            checkRow.put(i, new HashSet<>());
            for (int j = 0; j < board.length; j++) {
                if (i == 0) {
                    checkLine.put(j, new HashSet<>());
                    checkBlack.put(j, new HashSet<>());
                }

                char temp = board[i][j];
                if (temp == '.') {
                    continue;
                }

                if (!checkRow.get(i).add(temp)) {
                    return false;
                }

                if (!checkLine.get(j).add(temp)) {
                    return false;
                }

                int blackIndex = (i / 3) * 3 + j / 3;
                if (!checkBlack.get(blackIndex).add(temp)) {
                    return false;
                }
            }
        }
        return true;
    }
}
