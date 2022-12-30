package ec.edu.uce.modelo;

public enum TipoUsuario {
	ROLE_VENTAS("VENTAS"),
	ROLE_ADMIN("ADMIN");

	private String label;

	TipoUsuario(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
