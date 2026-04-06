package com.fhsh.daitda.company.application.result;

import java.util.UUID;

public record CompanyCreateResult(UUID companyId, String name) {
	/**
	 * Creates a CompanyCreateResult populated with the given company identifier and name.
	 *
	 * @param companyId the UUID of the company
	 * @param name the company's name
	 * @return a CompanyCreateResult containing the provided `companyId` and `name`
	 */
	public static CompanyCreateResult from(UUID companyId, String name) {
		return new CompanyCreateResult(companyId, name);
	}
}