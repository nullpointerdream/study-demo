package com.mycrawler.mycrawler;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Stack;

/*
 * @description: 校验html工具类
 * @author: chenjl40
 * @date: 2022/9/9 9:08
 **/
public class ValidateHtmlUtils {
    /**
     * 为开始标签<x>
     */
    private static final int START_LABEL = 0;
    /**
     * 为结束标签</x>
     */
    private static final int END_LABEL = 1;
    /**
     * 为自闭合标签<x/>
     */
    private static final int CLOSE_SELF_LABEL = 2;

    private static HashSet LABEL_SET = Sets.newHashSet("li","br","img","hr","area","base"
            ,"link","input","meta","basefont","frame","embed","col");

    public static void main(String[] args) {
       // String str = "<p>本製品は厚さが9.0mmの薄型DVDバーナードライブです。</p>\\n\\n<p><ul><li>ThinkCentre M710s/M910s SFF用\\n<li>バッファーメモリー  :  0.5MB\\n<li> DVD-ROM, DVD-R (Ver.1.0, Ver. 2.0 for Authoring)読取可能\\n<li>DVD-R (Ver. 2.1 for General)、DVD-Rデュアルレイヤー、DVD-RW、DVD-RAM (Ver.2.2)、DVD+R、 DVD+Rデュアルレイヤー及びDVD+RW読書き可能\\n<li>CD-ROM、CD-ROM XA、CD-I、Video CD、CD-Extra及びCD-Text読取可能\\n<li>Photo CD(シングルとマルチセッション)読取可能       \\n<li>標準CD-DA読取可能\\n<li>スーパーオーディオCD(ハイブリッドタイプのレイヤー対応)読取可能\\n<li>Orange Book Part 2準拠CD-Rディスク及びOrange Book Part 3準拠CD-RW読書き可能\\n<li>CPRM (DVD-R/RW/RAM)サポート\\n<li>DVD+R(M-DISC)読書き可能\\n<li>本製品に付属されているソフトウェア  :  Power DVD Ver.14.0、Power DVD Create Ver.10.0</li></ul></p>";
       String str = "<div>aa<p>bb</div>cc</div>dd</p><div><div></p><p>";
        System.out.println(repairHtml(str));
    }

    public static boolean validationHtml(String str) {
        //消除前后的空格
        str = str.trim();
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        Stack<String> stack = new Stack<>(); //记录标签的入栈顺序
        int i = 0;
        while (i < str.length()) {
            //第一个字符不是<，则表明不是以标签开始
            if (i == 0 && str.charAt(i) != '<') {
                return false;
            }
            //最后的字符不是>，则表明不是以标签结束
            if (i == str.length() - 1 && str.charAt(i) != '>') {
                return false;
            }
            //根据"<"字符开始 ">"字符结束《 获取标签字符串eg:<xx xx="">
            if (str.charAt(i) == '<') {
                int j = getLabelEndIndex(str, i);
                if (!validateLabel(str.substring(i + 1, j), stack)) {
                    return false; //属性有语法错误
                }
                i = j;
            }
            i++;
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String repairHtml(String str) {
        //消除前后的空格
        str = str.trim();
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        StringBuffer htmlStr = new StringBuffer(str);
        Stack<String> stack = new Stack<>(); //记录标签的入栈顺序
        int i = 0;
        while (i < htmlStr.length()) {
            //根据"<"字符开始 ">"字符结束《 获取标签字符串eg:<xx xx="">
            if (htmlStr.charAt(i) == '<') {
                int j = getLabelEndIndex(htmlStr.toString(), i);
                if (j == -1) {
                    break;
                }
                String label = htmlStr.substring(i + 1, j);
                label = label.trim();
                //记录标签的类型，
                int type = START_LABEL;
                //判断是否是结束标签
                if (label.charAt(0) == '/') {
                    type = END_LABEL;
                    //为结束标签去除/
                    label = label.substring(1);
                } else if (label.charAt(label.length() - 1) == '/') {
                    //判断是否是自闭合标签,忽略
                    i = j;
                    continue;
                }
                int index = 0;
                //获取标签的名字
                while (index < label.length() && label.charAt(index) != ' ') {
                    index++;
                }
                String labelName = label.substring(0, index);
                if (LABEL_SET.contains(labelName)) {
                    if (START_LABEL == type) {
                        i = j;
                    } else {
                        htmlStr.delete(i, j + 1);
                    }
                    continue;
                }

                //开始标签入栈
                if (type == START_LABEL) {
                    stack.push(labelName);
                } else if (type == END_LABEL) {
                    if (stack.isEmpty()) {
                        htmlStr.delete(i, j + 1);
                        //删除endLabel
                        continue;
                    }
                    if (!stack.peek().equals(labelName)) {
                        String replace = "</" + stack.peek() + ">";
                        htmlStr.replace(i, j + 1, replace);
                        j = i + replace.length() - 1;
                    }
                    stack.pop();

                }
                i = j;

            }
            i++;
        }
        if (stack.isEmpty()) {
            return htmlStr.toString();
        }
        while (!stack.isEmpty()) {
            htmlStr.append("</").append(stack.pop()).append(">");
        }
        return htmlStr.toString();
    }

    private static int getLabelEndIndex(String str, int i) {
        int j = i + 1;
        //'<'之后如果是标签，紧跟在'<'之后的字符必定是a-z或者'/'
        if (j < str.length() && (str.charAt(j) >= 'a' && str.charAt(j) <= 'z' || str.charAt(j) == '/')) {
            //是否处于字符串"里面, 防止属性里面带闭合标签eg:<div id=">>" >
            boolean inStr = false; //
            while (j < str.length()) {
                if (!inStr && str.charAt(j) == '>') {  //标签结束
                    return j; //属性有语法错误
                } else if (str.charAt(j) == '"') {
                    inStr = !inStr;
                }
                j++;
            }
        }
        return -1;
    }


    public static boolean validateLabel(String str, Stack<String> stack) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        //记录标签的类型，
        int type = START_LABEL;
        //判断是否是结束标签
        if (str.charAt(0) == '/') {
            //结束标签的'/'之后必须紧跟字母
            if (!(str.length() >= 2 && str.charAt(1) >= 'a' && str.charAt(1) <= 'z')) {
                return false;
            }
            type = END_LABEL;
            //为结束标签去除/
            str = str.substring(1);
        }
        str = str.trim(); //去除字符串后面的空格
        //判断是否是自闭合标签
        if (str.charAt(str.length() - 1) == '/') {
            //不能同时为结束标签，又是自闭合标签
            if (type == END_LABEL) {
                return false;
            }
            type = CLOSE_SELF_LABEL;
            // 去除最后的'/'
            str = str.substring(0, str.length() - 1);
        }
        int i = 0;
        //获取标签的名字
        while (i < str.length() && str.charAt(i) != ' ') {
            i++;
        }
        String name = str.substring(0, i);
        //开始标签入栈
        if (type == START_LABEL) {
            stack.push(name);
        } else if (type == END_LABEL) {
            if (!stack.isEmpty() && stack.peek().equals(name)) {
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
        // return validateAttr(str, type, i);
    }

    private static boolean validateAttr(String str, int type, int i) {
        //判断其属性是否有语法错误
        boolean hasAtt = false; //判断该标签是否有属性，针对结束标签不能有属性
        boolean hasBlank = false; //判断属性name之前是否有空格
        while (i < str.length()) {
            while (i < str.length() && str.charAt(i) == ' ') {
                hasBlank = true;
                i++;
            }
            while (i < str.length() && str.charAt(i) != '=' && str.charAt(i) != ' ') i++; //获取属性名字
            if (i < str.length() && str.charAt(i) == '=') { //如果没有'='说明不是属性，不进行处理
                i++;
                if (i >= str.length() || str.charAt(i) != '"') return false;
                i++;
                while (i < str.length() && str.charAt(i) != '"') i++; //寻找下一个双引号
                if (i >= str.length()) return false;
                if (!hasBlank) return false;
            }
            hasBlank = false;
            hasAtt = true;
            i++;
        }
        if (type == 1 && hasAtt) return false;
        return true;
    }
}

