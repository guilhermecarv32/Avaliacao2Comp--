package cenario1;

import java.util.Random;

public abstract class Executor implements Runnable{
	
	protected int totalLeituras = 0;
	
	public Executor(int totalLeituras) {
		this.totalLeituras = totalLeituras;
	}
	
	@Override
	public void run() {
		Random randomizador = new Random();
		
		for(int i = 0; i < totalLeituras; i++) {
			this.processarLeitura(i + 1);
			
			try {
				Thread.sleep(randomizador.nextInt(1000));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract void processarLeitura(int leituraAtual);
}
