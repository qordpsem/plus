package com.teamsparta.plus.domain.user.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

@Constraint(validatedBy = [ValidNicknameValidator::class])
@ReportAsSingleViolation
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER])
annotation class ValidNickname (
        val message: String = "닉네임은 최소 3자 이상, 알파벳 대소문자, 숫자로 구성해야 합니다",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Payload>> = []
)