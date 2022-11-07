package com.ruolin.manage.validator;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.ruolin.manage.annotation.BankCard;
import com.ruolin.manage.annotation.MobilePhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 银行卡验证器
 *
 * @author Alex Wang
 * Created on 2022/10/31 10:28:10
 */
public class BankCardValidator implements ConstraintValidator<BankCard, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        String regexp = "([1-9]{1}(\\d{15}|\\d{18}))";
        return StrUtil.isBlank(value) || value.toString().matches(regexp);
    }

}
