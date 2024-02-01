package com.teamsparta.plus.domain.user.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ValidNicknameValidator : ConstraintValidator<ValidNickname, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        val isValid = value != null && value.matches(Regex("^[a-zA-Z0-9]*$")) && value.length >= 3

        if (!isValid) {
            context?.disableDefaultConstraintViolation()
            context?.buildConstraintViolationWithTemplate("닉네임은 최소 3자 이상, 알파벳 대소문자, 숫자로 구성해야 합니다")
                    ?.addConstraintViolation()
        }
        return isValid
    }
}