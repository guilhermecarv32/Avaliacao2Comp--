package cenario2;

public class Chute {
	
	private int velocidade = 0;
	private int rpm = 0;
	private int forca = 0;

	
	public Chute(int velocidade, int rpm, int forca) {
		this.velocidade = velocidade;
		this.rpm = rpm;
		this.forca = forca;
	}
	
	public int getVelocidade() {
		return velocidade;
	}
	
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	
	public int getRPM() {
		return rpm;
	}
	
	public void setRPM(int rpm) {
		this.rpm = rpm;
	}
	
	public int getForca() {
		return forca;
	}
	
	public void setForca(int forca) {
		this.forca = forca;
	}
	
	@Override
	public String toString() {
		return "[Velocidade: " + velocidade + "RPM: " + rpm + "Forca: " + forca;
	}
}
