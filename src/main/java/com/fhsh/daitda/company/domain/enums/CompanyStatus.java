package com.fhsh.daitda.company.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyStatus {
	MANUFACTURER("생산업체"),
	RECEIVER("수령업체");

	private final String description;
}