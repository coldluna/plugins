package com.ruolin.manage.validator;


import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.ruolin.manage.annotation.IP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * IP验证器
 *
 * @author Alex Wang
 * Created on 2022/10/31 10:28:10
 */
public class IPValidator implements ConstraintValidator<IP, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return StrUtil.isBlank(value) || Validator.isIpv4(value.toString()) || Validator.isIpv6(value.toString());
    }

}
