package com.ruolin.manage.util;

import org.apache.commons.lang3.StringUtils;

import static com.ruolin.manage.constant.Constant.AT;
import static com.ruolin.manage.constant.Constant.INFINITY;
import static com.ruolin.manage.constant.Constant.MASK;

/**
 * 脱敏工具类
 *
 * @author Alex Wang
 * Created on 2022/10/28 15:58:06
 */
public class DesensitizeUtil {

    /**
     * 脱敏常规字符串
     *
     * @param sensitiveText      敏感信息
     * @param left               左侧保留位数
     * @param maskLength         遮掩的位数
     * @param keepOriginalLength 是否和原文保持一致的长度
     * @return
     */
    public static String mask(String sensitiveText, int left, int maskLength, boolean keepOriginalLength) {
        if (StringUtils.isEmpty(sensitiveText)) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.length(sensitiveText) <= left) {
            return sensitiveText;
        }

        String leftText = StringUtils.left(sensitiveText, left);
        // 遮掩物
        String mask = MASK.concat(MASK);
        String rightText = StringUtils.EMPTY;

        if (INFINITY == maskLength || left + maskLength >= sensitiveText.length()) {
            // 如果大于原始长度，或者length=-1，意味着不需要右侧文本。
            if (keepOriginalLength) {
                mask = StringUtils.repeat(MASK, sensitiveText.length() - left);
            }
        } else {
            // 如果小于原始长度或不为-1，那么将右侧文本截取出来
            rightText = StringUtils.substring(sensitiveText, left + maskLength);
            if (keepOriginalLength) {
                mask = StringUtils.repeat(MASK, maskLength);
            }
        }

        return leftText.concat(mask).concat(rightText);
    }


    /**
     * 邮箱脱敏
     *
     * @param email              邮箱
     * @param left               左侧保留位数
     * @param maskLength         遮掩的位数
     * @param keepOriginalLength 是否和原文保持一致的长度
     * @return
     */
    public static String maskEmail(String email, int left, int maskLength, boolean keepOriginalLength) {
        if (StringUtils.isEmpty(email)) {
            return StringUtils.EMPTY;
        }

        // 获取@符号的位置
        int index = StringUtils.indexOf(email, AT);
        if (index == StringUtils.INDEX_NOT_FOUND) {
            return mask(email, left, maskLength, keepOriginalLength);
        }

        String rightText = StringUtils.right(email, email.length() - index);
        String leftText = StringUtils.left(email, index);
        return mask(leftText, left, maskLength, keepOriginalLength).concat(rightText);
    }
}
