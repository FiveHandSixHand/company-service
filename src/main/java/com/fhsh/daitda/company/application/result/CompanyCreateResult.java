package com.fhsh.daitda.company.application.result;

import java.util.UUID;

public record CompanyCreateResult(UUID companyId, String name) {
	public static CompanyCreateResult from(UUID companyId, String name) {
		  return new CompanyCreateResult(companyId, name);
	}
}