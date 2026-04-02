package com.fhsh.daitda.company.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import com.fhsh.daitda.domain.BaseEntity;
                     // BaseUserEntity  // 사용자 정보를받아오려면
					//BaseEntity 수정 엔티티 (사용자 시간)


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