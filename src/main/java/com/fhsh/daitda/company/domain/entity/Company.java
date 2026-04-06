package com.fhsh.daitda.company.domain.entity;

import java.util.UUID;

import org.hibernate.annotations.SQLRestriction;

import com.fhsh.daitda.company.domain.enums.CompanyStatus;
import com.fhsh.daitda.domain.BaseUserEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@SQLRestriction("deleted_at IS NULL")
public class Company extends BaseUserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)

	@Column(name = "company_id", updatable = false, nullable = false)
	private UUID companyId; //  식별자 필드명 수정

	@Column(name = "hub_id", nullable = false)
	private UUID hubId;

	@Enumerated(EnumType.STRING)
	@Column(name = "company_type", nullable = false, length = 100)
	private CompanyStatus type;

	@Column(name = "company_name", nullable = false, length = 100)
	private String name;

	//  시군구 주소 입력
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city", column = @Column(name = "company_city", nullable = false)),
		@AttributeOverride(name = "district", column = @Column(name = "company_district", nullable = false)),
		@AttributeOverride(name = "street", column = @Column(name = "company_street", nullable = false))
	})
	private Address address;

	// ✨ 정적 팩토리 메서드 수정: 파라미터로 Address 객체를 받음
	public static Company create(UUID hubId, CompanyStatus type, String name, Address address) {
		return Company.builder()
			.hubId(hubId)
			.type(type)
			.name(name)
			.address(address)
			.build();
	}

	// 업체 수정
	public void update(String name, CompanyStatus type, Address address) {
		this.name = name;
		this.type = type;
		this.address = address;
		// updated_at, updated_by는 Auditing 기능으로 자동 업데이트됩니다.
	}

	// 업체 삭제
	public void delete(UUID userId) {
		// userId를 String으로 변환하여 부모의 delete 로직 실행
		// 내부적으로 deletedAt 세팅과 deletedBy 세팅이 한꺼번에 일어납니다.
		super.delete(userId);
	}
}