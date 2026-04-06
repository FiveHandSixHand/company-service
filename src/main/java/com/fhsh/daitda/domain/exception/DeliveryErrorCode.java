package com.fhsh.daitda.domain.exception;

import org.springframework.http.HttpStatus;

import com.fhsh.daitda.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryErrorCode implements ErrorCode {
	DELETE_ME(HttpStatus.CONFLICT, "작성을 위한 예시입니다.");

	private final HttpStatus status;
	private final String description;
}