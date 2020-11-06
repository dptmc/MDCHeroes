package io.dpteam.MDCHeroes.manager;

public enum Universe {
	NONE("None"),
	DC("DC"),
	MARVEL("Marvel");

	private String simpleName;

	private Universe(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getSimpleName() {
		return this.simpleName;
	}
}
