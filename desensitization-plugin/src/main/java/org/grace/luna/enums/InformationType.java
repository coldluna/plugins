package org.grace.luna.enums;

import static org.grace.luna.constant.Constant.INFINITY;

/**
 * 信息类型
 *
 * @author Alex Wang
 * Created on 2022/10/28 14:42:15
 */
public enum InformationType {
    /**
     * 常规
     */
    GENERAL(2, 5, false),
    /**
     * 地址
     */
    ADDRESS(6, INFINITY, false),
    /**
     * 银行卡
     */
    BANK_CARD(6, 8, true),
    /**
     * 中文姓名
     */
    CHINESE_NAME(1, 5, false),
    /**
     * 邮件
     */
    EMAIL(1, INFINITY, false),
    /**
     * 手机号
     */
    PHONE(3, 4, true),
    /**
     * 身份证
     */
    IDENTITY_CARD(4, 10, true),
    /**
     * 密码
     */
    PASSWORD(0, INFINITY, false);

    /**
     * 左侧保留位数
     */
    public final int left;
    /**
     * 需要遮掩的位数
     */
    public final int maskLength;
    /**
     * 是否和原信息保持相同的长度
     */
    public final boolean keepOriginalLength;

    InformationType(int left, int maskLength, boolean keepOriginalLength) {
        this.left = left;
        this.maskLength = maskLength;
        this.keepOriginalLength = keepOriginalLength;
    }

    public int getLeft() {
        return left;
    }

    public int getMaskLength() {
        return maskLength;
    }

    public boolean isKeepOriginalLength() {
        return keepOriginalLength;
    }
}
