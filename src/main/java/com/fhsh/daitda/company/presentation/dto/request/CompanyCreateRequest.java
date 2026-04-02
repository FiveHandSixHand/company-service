package com.fhsh.daitda.company.presentation.dto.request;

import com.fhsh.daitda.company.application.command.CompanyCreateCommand;
import com.fhsh.daitda.company.domain.enums.CompanyStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CompanyCreateRequest {
	private UUID hubId;
	private String name;
	private CompanyStatus type;
	private CompanyCreateCommand.AddressDto address; // 기존에 만든 Address 구조에 맞게 작성
}