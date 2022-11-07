package com.ruolin.manage.validator;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.ruolin.manage.annotation.MobilePhone;
import com.ruolin.manage.annotation.PostalCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 邮编验证器
 *
 * @author Alex Wang
 * Created on 2022/10/31 10:28:10
 */
public class PostalCodeValidator implements ConstraintValidator<PostalCode, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        String regexp = "([0-8][0-7]\\d{4})";
        return StrUtil.isBlank(value) || value.toString().matches(regexp);
    }

}
