package com.example.web.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils {

    private static Object initLock = new Object();

    private static MessageDigest digest = null;

    private static final String[] commonWords = new String[]{ "a", "and", "as", "at", "be", "do", "i", "if", "in", "is",
            "it", "so", "the", "to" };

    private static Map<String, String> commonWordsMap = null;

    private static final char[] digits = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
            'd', 'e', 'f' };

    public static String arrayToString(String[] ids) {
        String needId = "";
        String[] arg1 = ids;
        int arg2 = ids.length;
        for (int arg3 = 0; arg3 < arg2; ++arg3) {
            String id = arg1[arg3];
            needId = needId + "\'" + id + "\',";
        }
        return needId.substring(0, needId.length() - 1);
    }

    public static boolean isNotBlank(String str) {
        boolean result = true;
        if (null == str || "".equals(str.trim())) {
            result = false;
        }
        return result;
    }

    public static String arrayToString(List<String> ids) {
        String needId = "";
        String id;
        for (Iterator arg1 = ids.iterator(); arg1.hasNext(); needId = needId + "\'" + id + "\',") {
            id = (String) arg1.next();
        }
        return needId.substring(0, needId.length() - 1);
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (Exception arg6) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; ++j) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static final String replace(String line, String oldString, String newString, int[] count) {
        if (line == null) {
            return null;
        } else {
            byte i = 0;
            int arg10;
            if ((arg10 = line.indexOf(oldString, i)) < 0) {
                return line;
            } else {
                byte counter = 0;
                int arg11 = counter + 1;
                char[] line2 = line.toCharArray();
                char[] newString2 = newString.toCharArray();
                int oLength = oldString.length();
                StringBuffer buf = new StringBuffer(line2.length);
                buf.append(line2, 0, arg10).append(newString2);
                arg10 += oLength;
                int j;
                for (j = arg10; (arg10 = line.indexOf(oldString, arg10)) > 0; j = arg10) {
                    ++arg11;
                    buf.append(line2, j, arg10 - j).append(newString2);
                    arg10 += oLength;
                }
                buf.append(line2, j, line2.length - j);
                count[0] = arg11;
                return buf.toString();
            }
        }
    }

    public static final String escapeHTMLTags(String input) {
        if (input != null && input.length() != 0) {
            StringBuffer buf = new StringBuffer(input.length());
            boolean ch = true;
            for (int i = 0; i < input.length(); ++i) {
                char arg3 = input.charAt(i);
                if (arg3 == 60) {
                    buf.append("&lt;");
                } else if (arg3 == 62) {
                    buf.append("&gt;");
                } else {
                    buf.append(arg3);
                }
            }
            return buf.toString();
        } else {
            return input;
        }
    }

    public static final synchronized String hash(String data) {
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException arg1) {
                System.err.println("Failed to load the MD5 MessageDigest. Numeta will be unable to function normally.");
                arg1.printStackTrace();
            }
        }
        digest.update(data.getBytes());
        return toHex(digest.digest());
    }

    public static final String toHex(byte[] hash) {
        StringBuffer buf = new StringBuffer(hash.length * 2);
        for (int i = 0; i < hash.length; ++i) {
            if ((hash[i] & 255) < 16) {
                buf.append("0");
            }
            buf.append(Long.toString((long) (hash[i] & 255), 16));
        }
        return buf.toString();
    }

    public static final String[] toLowerCaseWordArray(String text) {
        if (text != null && text.length() != 0) {
            StringTokenizer tokens = new StringTokenizer(text, " ,\r\n.:/\\+");
            String[] words = new String[tokens.countTokens()];
            for (int i = 0; i < words.length; ++i) {
                words[i] = tokens.nextToken().toLowerCase();
            }
            return words;
        } else {
            return new String[0];
        }
    }

    public static final String[] removeCommonWords(String[] words) {
        int i;
        if (commonWordsMap == null) {
            Object results = initLock;
            synchronized (initLock) {
                if (commonWordsMap == null) {
                    commonWordsMap = new HashMap();
                    for (i = 0; i < commonWords.length; ++i) {
                        commonWordsMap.put(commonWords[i], commonWords[i]);
                    }
                }
            }
        }
        ArrayList arg4 = new ArrayList(words.length);
        for (i = 0; i < words.length; ++i) {
            if ( !commonWordsMap.containsKey(words[i])) {
                arg4.add(words[i]);
            }
        }
        return (String[]) ((String[]) arg4.toArray(new String[arg4.size()]));
    }

    public static String randomString() {
        return makeRandom(5);
    }

    public static String randomString(int numCharacters) {
        return makeRandom(numCharacters);
    }

    private static String makeRandom(int numChars) {
        String s = "";
        char[] letters = initLetters();
        for (int i = 0; i < numChars; ++i) {
            int d2 = (int) (Math.random() * 100.0D) % 26;
            s = s + letters[d2];
        }
        return s;
    }

    private static char[] initLetters() {
        char[] ca = new char[26];
        for (int i = 0; i < 26; ++i) {
            ca[i] = (char) (65 + i);
        }
        return ca;
    }

    public static boolean equals(String str1, String str2) {
        String s1 = NVL(str1);
        String s2 = NVL(str2);
        return s1.equals(s2);
    }

    public static final String quoteStr(String s) {
        if (s != null && s.length() >= 1) {
            char[] chars = s.toCharArray();
            StringBuffer sb = new StringBuffer();
            boolean needQuotes = false;
            for (int i = 0; i < chars.length; ++i) {
                switch (chars[i]) {
                    case '\b':
                        needQuotes = true;
                        sb.append("\\b");
                        break;
                    case '\t':
                    case ' ':
                    case '!':
                    case '#':
                    case '$':
                    case '%':
                    case '&':
                    case '\'':
                    case '(':
                    case ')':
                    case '*':
                    case '+':
                    case ',':
                    case '/':
                    case ':':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '[':
                    case ']':
                    case '^':
                    case '`':
                    case '{':
                    case '|':
                    case '}':
                    case '~':
                        needQuotes = true;
                        sb.append(chars[i]);
                        break;
                    case '\n':
                        needQuotes = true;
                        sb.append("\\n");
                        break;
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case '@':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case '_':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                    case 'v':
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':
                    default:
                        char ival;
                        if (chars[i] >= 32 && chars[i] != 127) {
                            if (chars[i] > 127) {
                                needQuotes = true;
                                ival = chars[i];
                                sb.append("\\u");
                                sb.append(digits[(ival & '') >> 12]);
                                sb.append(digits[(ival & 3840) >> 8]);
                                sb.append(digits[(ival & 240) >> 4]);
                                sb.append(digits[ival & 15]);
                            } else {
                                sb.append(chars[i]);
                            }
                            break;
                        }
                        needQuotes = true;
                        ival = chars[i];
                        sb.append('\\');
                        sb.append(digits[(ival & 192) >> 6]);
                        sb.append(digits[(ival & 56) >> 3]);
                        sb.append(digits[ival & 7]);
                        break;
                    case '\f':
                        needQuotes = true;
                        sb.append("\\f");
                        break;
                    case '\r':
                        needQuotes = true;
                        sb.append("\\r");
                        break;
                    case '\"':
                        needQuotes = true;
                        sb.append("\\\"");
                        break;
                    case '\\':
                        needQuotes = true;
                        sb.append("\\\\");
                }
            }
            if (needQuotes) {
                return "\"" + sb.toString() + "\"";
            } else {
                return sb.toString();
            }
        } else {
            return "";
        }
    }

    public boolean containsWhiteSpace(String str) {
        return str.indexOf(" ") != -1 ? true : str.indexOf("\t") != -1;
    }

    public static String removeQuote(String str) {
        if (str == null) {
            str = "";
        }
        str.trim();
        int index = str.lastIndexOf("\"");
        if (index == str.length() - 1) {
            str = str.substring(0, str.length() - 2);
        }
        index = str.indexOf("\"");
        if (index == 0) {
            str = str.substring(1);
        }
        return str;
    }

    public static String NVL(BigDecimal key) {
        return key != null && key.longValue() != 0L ? key.toString() : "";
    }

    public static String NVL(int integerValue) {
        return String.valueOf(integerValue);
    }

    public static String NVL(Double dValue) {
        return String.valueOf(dValue);
    }

    public static String NVL(double dValue) {
        return String.valueOf(dValue);
    }

    public static String NVL(float fValue) {
        return String.valueOf(fValue);
    }

    public static String NVL(boolean booleanValue) {
        return booleanValue ? "yes" : "no";
    }

    public static final String NVL(String input, String nullVal) {
        return input == null ? nullVal : input;
    }

    public static final String NVL(String input) {
        return input == null ? "" : input.trim();
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static String[] trim(String[] strs) {
        for (int i = 0; i < strs.length; ++i) {
            trim(strs[i]);
        }
        return strs;
    }

    public static String CapitalizeInitial(String str) {
        if (str == null) {
            return "";
        } else {
            str = str.trim();
            if (str.equals("")) {
                return "";
            } else {
                str = str.toLowerCase();
                String str1 = Character.toUpperCase(str.charAt(0)) + str.substring(1);
                return str1;
            }
        }
    }

    public static String removeExtraSpace(String str) {
        StringTokenizer token = new StringTokenizer(str, " ");
        StringBuffer ret = new StringBuffer();
        while (token.hasMoreElements()) {
            ret.append((String) token.nextElement() + " ");
        }
        return trim(ret.toString());
    }

    public static String filterSpecialChar(String str) {
        if (str != null && str.length() != 0) {
            String regEx = "[`~!@#$%^&*()+=|{}\':;\',\\[\\].<>/?！￥（）【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        } else {
            return "";
        }
    }

    public static String subString(String str, int length) {
        return str != null && str.length() > length ? str.substring(0, length) : str;
    }

    public static String getLimitLengthString(String str, int len) {
        try {
            int ex = 0;
            byte[] b = str.getBytes("gb2312");
            if (b.length <= len) {
                return str;
            } else {
                for (int i = 0; i < len; ++i) {
                    if (b[i] < 0) {
                        ++ex;
                    }
                }
                if (ex % 2 == 0) {
                    return new String(b, 0, len, "gb2312");
                } else {
                    return new String(b, 0, len - 1, "gb2312");
                }
            }
        } catch (Exception arg4) {
            return "";
        }
    }

    public static String getStrFromArray(String[] strs, int i) {
        if (strs != null && strs.length != 0) {
            int length = strs.length;
            return i < length && i > -1 ? strs[i] : null;
        } else {
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static String decode(String param) {
        if (isBlank(param)) {
            return null;
        } else {
            String result = null;
            try {
                result = URLDecoder.decode(param, "UTF-8");
            } catch (UnsupportedEncodingException arg2) {
                arg2.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 提取字符串中的数字
     *
     * @param str
     * @return
     */
    public static Long getNum(String str) {
        if (StringUtil.isBlank(str)) {
            return 0L;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return "".equals(m.replaceAll("").trim()) ? 0L : Long.valueOf(m.replaceAll("").trim());
    }

    /**
     * 判断一个字符串是否含有数字
     *
     * @param content
     * @return
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 去掉空格
     *
     * @param obj
     * @return
     */
    public static Object trim(Object obj) {
        try {
            if (obj != null) {
                return obj.toString().trim();
            }
        } catch (Exception e) {
            return obj;
        }
        return obj;
    }

    // 首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            StringBuilder newStr = new StringBuilder();
            newStr.append(Character.toUpperCase(s.charAt(0)));
            newStr.append(s.substring(1));

            return newStr.toString();
        }
    }

    /**
     * 判断字符串和Long类型的值是否相等<br>
     * 如果字符串为空或者Long为null那么返回false
     *
     * @param str
     * @param lon
     * @return
     */
    public static boolean StringEqualsLong(String str, Long lon) {
        if (StringUtil.isBlank(str) || lon == null) {
            return false;
        }
        return str.equals(String.valueOf(lon));
    }

    /**
     * 递归去除字符串前后指定字符串
     *
     * @param str
     *            原字符串
     * @param split
     *            去除的字符串
     * @return
     */
    public static String trimStr(String str, String split) {
        if (isBlank(str)) {
            return "";
        }
        if (str.startsWith(split))
            return trimStr(new String(new StringBuffer(str).deleteCharAt(0)), split);
        else if (str.endsWith(split))
            return trimStr(new String(new StringBuffer(str).deleteCharAt(str.length() - 1)), split);
        else
            return str;
    }
}
