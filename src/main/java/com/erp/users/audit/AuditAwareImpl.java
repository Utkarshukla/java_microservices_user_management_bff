package com.erp.users.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		// In a real application, you would fetch the currently logged-in user.
		// For simplicity, we'll return a static username here.
		return Optional.of("system"); // Replace with actual user retrieval logic
	}

}
