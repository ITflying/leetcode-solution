package _17_to_32._17_电话号码的字母组合;

import java.util.*;

/**
 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 
 示例:
 输入："23"
 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 说明:
 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

 * @date 2019/9/18
 **/
public class LetterCombinations {
    public static void main(String[] args) {
        String testStr = "23";
        System.out.println("所有能表示的字母组合 1：" + letterCombinations_01(testStr).toString());
        System.out.println("所有能表示的字母组合 2：" + letterCombinations_02(testStr).toString());
    }

    /**
     * 第一种做法：池子里面取值的问题
     * 刚开始觉得简单，直接用循环就做出来。速度倒是挺快的，1ms，内存占用38.7MB
     * 后来想用递归来做，却对递归概念不熟悉，没办法确定基线条件和递归条件
     */
    private static List<String> letterCombinations_01(String digits) {
        List<String> ret = new ArrayList<>();
        Map<Character, String[]> num2str = getNum2Str();
                
        // 1. 基础判断
        if (digits.length() == 0){
            return ret;
        }else if (digits.length() == 1){
            return Arrays.asList(num2str.get(digits.charAt(0)));
        }
        
        // 2. 循环获取值
        return getAllStr(num2str, digits);
    }

    private static List<String> getAllStr(Map<Character, String[]> num2str, String str){
        List<String> ret = new ArrayList<>();
        List<String> ret2;
        
        for (int i = 0; i < str.length();i++){
            String[] temp = num2str.get(str.charAt(i));
            if (ret.size() == 0){
                for (int j = 0;j<temp.length;j++){
                    ret.add(temp[j]);
                }
            }else{
                ret2 = new ArrayList<>();
                for (int k = 0; k < ret.size();k++){
                    for (int j = 0;j<temp.length;j++){
                        String tempStr = ret.get(k);
                        tempStr += temp[j];
                        ret2.add(tempStr);
                    }
                }
                ret = new ArrayList<>();
                ret.addAll(ret2);
            }
        }
        return ret;
    }

    private static Map<Character, String[]> getNum2Str() {
        Map<Character, String[]> num2char = new HashMap<>();
        num2char.put('2', new String[]{"a", "b", "c"});
        num2char.put('3', new String[]{"d", "e", "f"});
        num2char.put('4', new String[]{"g", "h", "i"});
        num2char.put('5', new String[]{"j", "k", "l"});
        num2char.put('6', new String[]{"m", "n", "o"});
        num2char.put('7', new String[]{"p", "q", "r", "s"});
        num2char.put('8', new String[]{"t", "u", "v"});
        num2char.put('9', new String[]{"w", "x", "y", "z"});

        return num2char;
    }


    /**
     * 第二种：递归
     *  基线条件：就是长度等于字符串长度的时间
     *  递归条件：就是对单个字符里面的字符进行循环，然后不断递归下一位即可
     */
    private static ArrayList<String> res;
    private static List<String> letterCombinations_02(String digits) {
        res = new ArrayList<>();
        if ("".equals(digits)){
            return res;
        }

        findCombination(digits, 0, "");
        return res;
    }
    
    private static void findCombination(String digits, int index, String s) {
        // 基线条件：位置等于长度的时候就停止，将拼接的s放入全局list中
        if (index == digits.length()){
            res.add(s);
            return;
        }
        
        Character c = digits.charAt(index);
        String letter = letterMap[c - '0'];
        for (int i = 0; i < letter.length();i++){
            findCombination(digits, index+1,s + letter.charAt(i));
        }
    }
    


    private static String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };
}
