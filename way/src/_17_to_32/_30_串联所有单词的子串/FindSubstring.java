package _17_to_32._30_串联所有单词的子串;

import java.rmi.ServerException;
import java.util.*;
import java.util.stream.Collectors;

/**
 给定一个字符串s和一些[长度相同]的单词words。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 注意子串要与words 中的单词完全匹配，中间不能有其他字符，但不需要考虑words中单词串联的顺序。

 示例 1：
 输入：
 s = "barfoothefoobarman",
 words = ["foo","bar"]
 输出：[0,9]
 解释：
 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 输出的顺序不重要, [9,0] 也是有效答案。

 示例 2：
 输入：
 s = "wordgoodgoodgoodbestword",
 words = ["word","good","best","word"]
 输出：[]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/03/01
 **/
public class FindSubstring {
    public static void main(String[] args) throws ServerException {
        String s = "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel";
        String[] words = new String[]{"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"};

        String t1 = "wordgoodgoodgoodbestword";
        String[] words1 = new String[]{"word","good","best","good"};
        List<Integer> res = findSubstring_04(t1, words1);
        res.forEach(System.out::println);
    }

    /**
     * 可以用indexOf吗？
     * 1、列出所有可能组成的子串
     * 2、遍历获取位置
     * 内存和时间双溢出，不过这应该可以解决吧
     */
    private static List<Integer> findSubstring_01(String s, String[] words) {
        Set<Integer> posList = new HashSet<>();

        // 1. 所有可能的子串
        Set<String> findStrList = new HashSet<>();
        listAllStr(words, 0, findStrList);
        List<String> strList = new ArrayList<>(findStrList);

        // 2. indexOf
        for (int i = 0; i < strList.size(); i++) {
            String str = strList.get(i);
            if (s.contains(str)){
                int pos = s.indexOf(str);
                posList.add(pos);
                int left = pos + 1;
                String temp = s.substring(left);
                while (temp.contains(str)){
                    pos = temp.indexOf(str);
                    left = left+pos;
                    posList.add(left);
                    left = left+pos+1;
                    if (left > s.length()) {
                        break;
                    }
                    temp = s.substring(left);
                }
            }
        }

        return new ArrayList<>(posList);
    }

    private static void listAllStr(String[] words, int start, Set<String> strList){
        if (start == words.length) {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word);
            }
            strList.add(sb.toString());
        } else {
            for (int i = start;i < words.length; i++){
                swap(words, start,i);
                listAllStr(words, start+1, strList);
                swap(words,start,i);
            }
        }
    }

    private static void swap(String[] words, int left, int right) {
        String temp = words[left];
        words[left] = words[right];
        words[right] = temp;
    }

    /**
     * 大佬思路1
     * 思路一：
     * 因为单词长度固定的，我们可以计算出截取字符串的单词个数是否和 words 里相等，所以我们可以借用哈希表。
     * 一个是哈希表是 words，一个哈希表是截取的字符串，比较两个哈希是否相等！
     * 因为遍历和比较都是线性的，所以时间复杂度：O(n^2) 304ms
     *
     * 作者：powcai
     * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/chuan-lian-suo-you-dan-ci-de-zi-chuan-by-powcai/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private static List<Integer> findSubstring_02(String s, String[] words) {
        List<Integer> res=  new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;

        // 1. 计算目标匹配哈希值
        for (String word:words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        // 2. 计算切割字符串的哈希值
        for (int i = 0; i< s.length() - all_len+1;i++){
            String tmp = s.substring(i ,i+all_len);
            HashMap<String, Integer> tmp_map = new HashMap<>();
            for (int j= 0; j < all_len;j=j+one_word){
                String w = tmp.substring(j,j+one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w,0)+1);
            }
            if(map.equals(tmp_map)) res.add(i);
        }
        return res;
    }

    /**
     * 大佬思路2
     * 思路二：
     * 上面思路每次都要反复遍历 s；下面介绍滑动窗口。
     * 滑动窗口！
     * 我们一直在 s 维护着所有单词长度总和的一个长度队列！
     * 时间复杂度：O(n) 27ms
     *
     * 作者：powcai
     * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/chuan-lian-suo-you-dan-ci-de-zi-chuan-by-powcai/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private static List<Integer> findSubstring_03(String s, String[] words) {
        List<Integer> res=  new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;

        // 1. 计算目标匹配哈希值
        for (String word:words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        // 2， 滑动窗口匹配
        for (int i = 0; i < one_word; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()) {
                String w = s.substring(right, right + one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                right += one_word;
                count++;
                while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                    String t_w = s.substring(left, left + one_word);
                    count--;
                    tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                    left += one_word;
                }
                if (count == word_num) res.add(left);
            }
        }
        return res;
    }


    /**
     * 大佬思路2优化版
     * 思路二：
     * 上面思路每次都要反复遍历 s；下面介绍滑动窗口。
     * 滑动窗口！
     * 我们一直在 s 维护着所有单词长度总和的一个长度队列！
     * 时间复杂度：O(n) 10ms
     *
     * 作者：powcai
     * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/chuan-lian-suo-you-dan-ci-de-zi-chuan-by-powcai/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private static List<Integer> findSubstring_04(String s, String[] words) {
        List<Integer> res=  new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;

        // 1. 计算目标匹配哈希值
        for (String word:words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        // 2， 滑动窗口匹配
        for (int i = 0; i < one_word; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()) {
                String w = s.substring(right, right + one_word);
                right += one_word;
                // 多了一层判断，快速失败
                if (!map.containsKey(w)) {
                    count = 0;
                    left = right;
                    tmp_map.clear();
                }else {
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;
                    while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                        String t_w = s.substring(left, left + one_word);
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        left += one_word;
                    }
                    if (count == word_num) res.add(left);
                }

            }
        }
        return res;
    }




}
