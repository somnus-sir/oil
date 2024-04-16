package com.whn.hellospring.utils

/**
 * 功能描述：
 * 创建作者：尉浩楠
 * 创建时间：2018/10/9.
 */

/**
 * 判断字符串不为空
 */
fun isNotNull(num: String?): Boolean {
    return num != null && "" != num
}

/**
 * 判断字符串不为空
 */
fun isNotNullAndZero(num: String?): Boolean {
    return StringUtil.isNumber(num) && num?.toDouble() != 0.0
}


