package br.com.rdp.dto;

public enum AuthProvider {

	// GOOGLE("google"),
	LOCAL("local");

	private String providerType;

	public String getProviderType() {
		return providerType;
	}

	AuthProvider(final String providerType) {
		this.providerType = providerType;
	}
}
