package org.grace.luna.validator;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import org.grace.luna.annotation.MobilePhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 身份证验证器
 *
 * @author Alex Wang
 * Created on 2022/10/31 10:28:10
 */
public class MobilePhoneValidator implements ConstraintValidator<MobilePhone, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return StrUtil.isBlank(value) || Validator.isMobile(value.toString());
    }

}
