package com.fhsh.daitda.company.domain.entity;

import com.fhsh.daitda.company.domain.enums.CompanyStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "p_company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Company {

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

	// ✨ 수정: String address를 Address 객체로 변경
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city", column = @Column(name = "company_city", nullable = false)),
		@AttributeOverride(name = "district", column = @Column(name = "company_district", nullable = false)),
		@AttributeOverride(name = "street", column = @Column(name = "company_street", nullable = false))
	})
	private Address address;

	// --- Audit 필드 ---
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(name = "created_by")
	private UUID createdBy;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@LastModifiedBy
	@Column(name = "updated_by")
	private UUID updatedBy;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Column(name = "deleted_by")
	private UUID deletedBy;

	// ✨ 정적 팩토리 메서드 수정: 파라미터로 Address 객체를 받음
	public static Company create(UUID hubId, CompanyStatus type, String name, Address address) {
		return Company.builder()
			.hubId(hubId)
			.type(type)
			.name(name)
			.address(address)
			.build();
	}
}