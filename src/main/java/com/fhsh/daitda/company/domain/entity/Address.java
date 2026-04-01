package com.fhsh.daitda.company.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Address {
	private String city;     // 시
	private String district; // 군/구
	private String street;   // 상세 주소
}