
public enum turno {
	MANHA(6,12),
	TARDE(13,19);
	
	int ini,fim;

	private turno(int ini, int fim) {//enumerador para settar se é manha ou tarde
		this.ini = ini;
		this.fim = fim;
	}
	
	
	
	
	public int getIni() {
		return ini;
	}

	public void setIni(int ini) {
		this.ini = ini;
	}

	public int getFim() {
		return fim;
	}

	public void setFim(int fim) {
		this.fim = fim;
	}
	
}
