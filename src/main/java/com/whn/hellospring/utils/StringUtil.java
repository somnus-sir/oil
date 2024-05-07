package com.whn.hellospring.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串操作类
 */
public class StringUtil {

    /**
     * 处理重叠字符
     *  ABCCCD  -> AB3CD
     */
    public static String overlapCharacter(List<String> list) {
        int j = 0;
        String finalStr = "";
        while (j < list.size()) {
            String str = list.get(j);
            if (finalStr.contains(str)) {
                if (j - 1 < 0) {
                    finalStr = "2" + finalStr;
                } else {
                    int index = finalStr.indexOf(str);
                    if (index > 0) {
                        String timesStr = finalStr.substring(index - 1, index);
                        if (isInteger(timesStr)) {
                            int timesInt = Integer.parseInt(timesStr) + 1;
                            finalStr = finalStr.substring(0, finalStr.length() - 2);
                            finalStr += timesInt;
                            finalStr += str;
                        } else {
                            finalStr = finalStr.substring(0, finalStr.length() - 1);
                            finalStr += "2";
                            finalStr += str;
                        }
                    } else {
                        finalStr = "2" + finalStr;
                    }
                }
            } else {
                finalStr += str;
            }
            j++;
        }
        return finalStr;
    }

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 保留一位小数，四舍五入
     * 使用0.00不足位补0，#.##仅保留有效位
     */
    public static String doubleToString1(Double num) {
        if (!isNumber(num.toString())) return "0.0";
        return String.format("%.1f", num);
    }

    /**
     * 保留两位小数，四舍五入
     * 使用0.00不足位补0，#.##仅保留有效位
     */
    public static String doubleToString2(Double num) {
        if (!isNumber(num.toString())) return "0.00";
        return String.format("%.2f", num);
    }

    /**
     * 四舍五入
     * 1位小数
     */
    public static String rounding1(String num) {
        if (!isNumber(num)) return "0.0";
        return String.format("%.1f", Double.parseDouble(num));
    }

    /**
     * 四舍五入
     * 2位小数
     */
    public static String rounding2(String num) {
        if (!isNumber(num)) return "0.00";
        return String.format("%.2f", Double.parseDouble(num));
    }

    /**
     * 按长度截取中英文混合string
     *
     * @param text    字符串
     * @param length  长度
     * @param endWith 末尾追加
     * @return
     */
    public static String subStringIncludeChinese(String text, int length, String endWith) {
        text = text.trim();
        int textLength = text.length();
        int byteLength = 0;
        StringBuffer returnStr = new StringBuffer();
        int i = 0;
        while (i < textLength && byteLength < length * 2) {
            String str_i = text.substring(i, i + 1);
            if (str_i.getBytes().length == 1) {
                byteLength++;
            } else {
                byteLength += 2;
            }
            returnStr.append(str_i);
            i++;
        }
        try {
            if (byteLength < text.getBytes("GBK").length) {
                returnStr.append(endWith);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return returnStr.toString();
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param text 指定的字符串
     * @return 字符串的长度
     */
    public static int getStringLengthIncludeChinese(String text) {
        text = text.trim();
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < text.length(); i++) {
            String temp = text.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            java.util.regex.Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    /**
     * 判断是否是数字
     *
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        if (number!=null &&!"".equals(number)) {
            return false;
        }
        if (number.equals(".")) {
            return false;
        }
        String[] list = number.split("\\.");
        if (list.length > 2) {
            return false;
        }
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("[0-9.-]+");
            java.util.regex.Matcher m = p.matcher(number);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 判断是否全是英文字母组成（不区分大小写）
     *
     * @param letters
     * @return
     */
    public static boolean isLetters(String letters) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^[A-Za-z]+$");
            java.util.regex.Matcher m = p.matcher(letters);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 判断是否全是大写字母
     *
     * @param letters
     * @return
     */
    public static boolean isUppercaseLetters(String letters) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^[A-Z]+$");
            java.util.regex.Matcher m = p.matcher(letters);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 判断是否全是小写字母
     *
     * @param letters
     * @return
     */
    public static boolean isLowercaseLetters(String letters) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^[a-z]+$");
            java.util.regex.Matcher m = p.matcher(letters);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 是否数字和字母组成
     *
     * @param text
     * @return
     */
    public static boolean isNumbersAndLetters(String text) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
            java.util.regex.Matcher m = p.matcher(text);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 包含数字,字母,下划线，不可以下划线开头
     *
     * @param text
     * @return
     */
    public static boolean isNumbersUnderlineLetters(String text) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$");
            java.util.regex.Matcher m = p.matcher(text);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 是否是密码，（字母或数字开头，包含下划线）
     *
     * @param text
     * @param lowest_median  最低位数
     * @param highest_median 最高位数
     * @return
     */
    public static boolean isPassword(String text, int lowest_median, int highest_median) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{" + lowest_median + "," + highest_median + "}$");
            java.util.regex.Matcher m = p.matcher(text);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    // 过滤特殊字符
    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        java.util.regex.Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    // 中文识别
    public static boolean hasChinese(String source) {
        String reg_charset = "([\\u4E00-\\u9FA5]*+)";
        Pattern p = Pattern.compile(reg_charset);
        java.util.regex.Matcher m = p.matcher(source);
        boolean hasChinese = false;
        while (m.find()) {
            if (!"".equals(m.group(1))) {
                hasChinese = true;
            }
        }
        return hasChinese;
    }

    /**
     * 判断是否有特殊字符
     */
    public static boolean hasString(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; // 特殊字符
        Pattern p = Pattern.compile(regEx);
        java.util.regex.Matcher m = p.matcher(str);
        return m.find();
    }

    //获取当前时间
    public static String refFormatNowDate() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdFormatter.format(nowTime);
    }

    //时间格式
    public static String realtime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "";
        try {
            Date curDate = formatter.parse(time);
            str = formatter.format(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 检验数字的有效性
     *
     * @param str
     * @return
     */
    public static boolean isNumberTrue(String str) {
        String c = str.substring(str.length() - 1, str.length());
        if (".".equals(c)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]*)?$");
        java.util.regex.Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) {
        String regExp = "^\\d{11}$";
        Pattern p = Pattern.compile(regExp);
        java.util.regex.Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        java.util.regex.Matcher m = p.matcher(str);
        return m.matches();
    }

}
