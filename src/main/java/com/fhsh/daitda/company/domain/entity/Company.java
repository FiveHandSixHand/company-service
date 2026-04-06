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
	private UUID companyId; // ✨ 규칙 적용: 식별자 필드명 수정

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

	/**
	 * Create a new Company instance with the specified hub, type, name, and address.
	 *
	 * @param hubId   the identifier of the hub the company belongs to
	 * @param type    the company's status/type
	 * @param name    the company's name
	 * @param address the company's address
	 * @return        a Company populated with the provided properties; `companyId` is not set here and will be assigned by persistence
	 */
	public static Company create(UUID hubId, CompanyStatus type, String name, Address address) {
		return Company.builder()
			.hubId(hubId)
			.type(type)
			.name(name)
			.address(address)
			.build();



	}

	/**
	 * Update the company's name, type, and address.
	 *
	 * @param name    the new company name
	 * @param type    the new company status/type
	 * @param address the new address for the company
	 *
	 * Note: auditing fields (e.g., updatedAt, updatedBy) are updated automatically by the entity auditing mechanism.
	 */
	public void update(String name, CompanyStatus type, Address address) {
		this.name = name;
		this.type = type;
		this.address = address;
		// updated_at, updated_by는 Auditing 기능으로 자동 업데이트됩니다.
	}

	/**
	 * Marks the entity as deleted by setting the deletion timestamp and the ID of the user who performed the deletion.
	 *
	 * @param userId the UUID of the user performing the deletion
	 */
	public void delete(UUID userId) {
		// userId를 String으로 변환하여 부모의 delete 로직 실행
		// 내부적으로 deletedAt 세팅과 deletedBy 세팅이 한꺼번에 일어납니다.
		super.delete(userId);
	}
}