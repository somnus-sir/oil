package com.whn.hellospring.utils



import java.io.UnsupportedEncodingException
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * 字符串操作类
 */
object StringUtil {



    /**
     * 处理重叠字符
     *  ABCCCD  -> AB3CD
     */
    fun overlapCharacter(list: List<String>): String {
        //排序
//        java.util.Collections.sort(list)

        var j = 0
        var finalStr = ""
        while (j < list.size) {
            val str = list[j]  //C
            //如果已经存在当前要添加的字符   ABC + C    AB2C  +C
            if (finalStr.contains(str)) {
                if (j - 1 < 0) {  //第一位 C+C
                    finalStr = "2" + finalStr
                } else {
                    val index = finalStr.indexOf(str)
                    if (index > 0) {
                        val timesStr = finalStr.substring(index - 1, index) //倍数2
                        if (isInteger(timesStr)) {// AB2C  +C
                            val timesInt = timesStr.toInt() + 1
                            finalStr = finalStr.substring(0, finalStr.length - 2)
                            finalStr += timesInt.toString()
                            finalStr += str
                        } else {// ABC + C
                            finalStr = finalStr.substring(0, finalStr.length - 1)
                            finalStr += "2"
                            finalStr += str
                        }
                    } else {  //B2C4D  + B
                        finalStr = "2" + finalStr
                    }
                }
            } else {
                finalStr += str
            }
            j++
        }
        return finalStr
    }


    /*方法二：推荐，速度最快
    * 判断是否为整数
    * @param str 传入的字符串
    * @return 是整数返回true,否则返回false
    */
    fun isInteger(str: String): Boolean {
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }


    /**
     * 保留一位小数，四舍五入
     * 使用0.00不足位补0，#.##仅保留有效位
     */
    fun doubleToString1(num: Double?): String {
//        if(!isNumber(num.toString()))return "0.0"
//        return DecimalFormat("0.0").format(num)
        if(!isNumber(num.toString()))return "0.0"
        return String.format("%.1f", num!!.toDouble())
    }


    /**
     * 保留两位小数，四舍五入
     * 使用0.00不足位补0，#.##仅保留有效位
     */
    fun doubleToString2(num: Double?): String {
//        if(!isNumber(num.toString()))return "0.00"
//        return DecimalFormat("0.00").format(num)
        if(!isNumber(num.toString()))return "0.00"
        return String.format("%.2f", num!!.toDouble())
    }


    /**
     * 四舍五入
     * 1位小数
     */
    fun rounding1(num: String?): String {
        if(!isNumber(num))return "0.0"
        return String.format("%.1f", num!!.toDouble())
    }


    /**
     * 四舍五入
     * 2位小数
     */
    fun rounding2(num: String?): String {
        if(!isNumber(num))return "0.00"
        return String.format("%.2f", num!!.toDouble())
    }


    /**
     * 按长度截取中英文混合string
     *
     * @param text    字符串
     * @param length  长度
     * @param endWith 末尾追加
     * @return
     */
    fun subStringIncludeChinese(text: String, length: Int,
                                endWith: String): String {
        var text = text
        text = text.trim { it <= ' ' }
        val textLength = text.length
        var byteLength = 0
        val returnStr = StringBuffer()
        var i = 0
        while (i < textLength && byteLength < length * 2) {
            val str_i = text.substring(i, i + 1)
            if (str_i.toByteArray().size == 1) {// 英文
                byteLength++
            } else {// 中文
                byteLength += 2
            }
            returnStr.append(str_i)
            i++
        }
        try {
            if (byteLength < text.toByteArray(charset("GBK")).size) {// getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3
                returnStr.append(endWith)
            }
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return returnStr.toString()
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param text 指定的字符串
     * @return 字符串的长度
     */
    fun getStringLengthIncludeChinese(text: String): Int {
        var text = text
        text = text.trim { it <= ' ' }
        var valueLength = 0
        val chinese = "[\u0391-\uFFE5]"
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (i in 0..text.length - 1) {
            /* 获取一个字符 */
            val temp = text.substring(i, i + 1)
            /* 判断是否为中文字符 */
            if (temp.matches(chinese.toRegex())) {
                /* 中文字符长度为2 */
                valueLength += 2
            } else {
                /* 其他字符长度为1 */
                valueLength += 1
            }
        }
        return valueLength
    }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    fun checkEmail(email: String): Boolean {
        var flag = false
        try {
            val check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
            val regex = Pattern.compile(check)
            val matcher = regex.matcher(email)
            flag = matcher.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    /**
     * 判断字符串是否是数字
     *
     * @param number
     * @return
     */
    fun isNumber(number: String?): Boolean {

        //如果为空，false
        if (!isNotNull(number)) {
            return false
        }

        //只有.
        if (number == ".") {
            return false
        }


        //如果有多个点，false
        val list = number!!.split(".")
        if (list.size > 2) {
            return false
        }


        //如果有其他字符 false
        var flag = false
        try {
            val p = Pattern.compile("[0-9.-]+")
            val m = p.matcher(number)
            flag = m.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    /**
     * 判断是否全是英文字母组成（不区分大小写）
     *
     * @param letters
     * @return
     */
    fun isLetters(letters: String): Boolean {
        var flag = false
        try {
            val p = Pattern.compile("^[A-Za-z]+$")
            val m = p.matcher(letters)
            flag = m.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    /**
     * 判断是否全是大写字母
     *
     * @param letters
     * @return
     */
    fun isUppercaseLetters(letters: String): Boolean {
        var flag = false
        try {
            val p = Pattern.compile("^[A-Z]+$")
            val m = p.matcher(letters)
            flag = m.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    /**
     * 判断是否全是小写字母
     *
     * @param letters
     * @return
     */
    fun isLowercaseLetters(letters: String): Boolean {
        var flag = false
        try {
            val p = Pattern.compile("^[a-z]+$")
            val m = p.matcher(letters)
            flag = m.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    /**
     * 是否数字和字母组成
     *
     * @param text
     * @return
     */
    fun isNumbersAndLetters(text: String): Boolean {
        var flag = false
        try {
            val p = Pattern.compile("^[A-Za-z0-9]+$")
            val m = p.matcher(text)
            flag = m.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    /**
     * 包含数字,字母,下划线，不可以下划线开头
     *
     * @param text
     * @return
     */
    fun isNumbersUnderlineLetters(text: String): Boolean {
        var flag = false
        try {
            val p = Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$")
            val m = p.matcher(text)
            flag = m.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    /**
     * 是否是密码，（字母或数字开头，包含下划线）
     *
     * @param text
     * @param lowest_median  最低位数
     * @param highest_median 最高位数
     * @return
     */
    fun isPassword(text: String, lowest_median: Int,
                   highest_median: Int): Boolean {
        var flag = false
        try {
            val p = Pattern
                    .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{"
                            + lowest_median.toString() + ","
                            + highest_median.toString() + "}$")
            val m = p.matcher(text)
            flag = m.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        }

        return flag
    }

    // 过滤特殊字符
    @Throws(PatternSyntaxException::class)
    fun StringFilter(str: String): String {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        val regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(str)
        return m.replaceAll("").trim { it <= ' ' }
    }

    // 中文识别
    fun hasChinese(source: String): Boolean {
        val reg_charset = "([\\u4E00-\\u9FA5]*+)"
        val p = Pattern.compile(reg_charset)
        val m = p.matcher(source)
        var hasChinese = false
        while (m.find()) {
            if ("" != m.group(1)) {
                hasChinese = true
            }
        }
        return hasChinese
    }

    /**
     * 判断是否有特殊字符
     */
    fun hasString(str: String): Boolean {
        val regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"// 特殊字符
        val p = Pattern.compile(regEx)
        val m = p.matcher(str)
        return m.find()
    }

    //获取当前时间
    fun refFormatNowDate(): String {
        val nowTime = Date(System.currentTimeMillis())
        val sdFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdFormatter.format(nowTime)
    }


    //时间格式
    fun Realtime(time: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var str = ""
        try {
            val curDate = formatter.parse(time)
            str = formatter.format(curDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        //获取当前时间
        return str
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    fun FormetFileSize(fileS: Long): String {
        val df = DecimalFormat("#.00")
        var fileSizeString = ""
        val wrongSize = "0B"
        if (fileS == 0L) {
            return wrongSize
        }
        if (fileS < 1024) {
            fileSizeString = df.format(fileS.toDouble()) + "B"
        } else if (fileS < 1048576) {
            fileSizeString = df.format(fileS.toDouble() / 1024) + "KB"
        } else if (fileS < 1073741824) {
            fileSizeString = df.format(fileS.toDouble() / 1048576) + "MB"
        } else {
            fileSizeString = df.format(fileS.toDouble() / 1073741824) + "GB"
        }
        return fileSizeString
    }

    /**
     * 检验数字的有效性
     *
     * @param str
     * @return
     */
    fun isNumberTrue(str: String): Boolean {
        val c = str.substring(str.length - 1, str.length)
        if (c == ".") {
            return false
        }
        val pattern = Pattern.compile("^[0-9]+(.[0-9]*)?$")
        val match = pattern.matcher(str)
        return match.matches()
    }

    /**
     * 大陆号码或香港号码均可
     */
    @Throws(PatternSyntaxException::class)
    fun isPhoneLegal(str: String): Boolean {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str)
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
    @Throws(PatternSyntaxException::class)
    fun isChinaPhoneLegal(str: String): Boolean {
        //        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(16[0-9])|(147))\\d{8}$";
        val regExp = "^\\d{11}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    @Throws(PatternSyntaxException::class)
    fun isHKPhoneLegal(str: String): Boolean {
        val regExp = "^(5|6|8|9)\\d{7}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(str)
        return m.matches()
    }


}
