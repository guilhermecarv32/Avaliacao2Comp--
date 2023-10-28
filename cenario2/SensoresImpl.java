package cenario2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SensoresImpl implements Sensores<Chute>{

	 private static final int VELOCIDADE_MEDIA = 80;
	 private static final int RPM_MEDIA = 500;
	 private static final int FORCA_MEDIA = 200;
	 private static final int OSCILACAO_MAXIMA = 30;
	 
	 private static final int LIMITE_DE_LEITURAS = 20;
	 
	 private List<Chute> ultimasLeituras = new LinkedList<>();
	 public Chute ultimaLeitura = new Chute(VELOCIDADE_MEDIA, RPM_MEDIA, OSCILACAO_MAXIMA);
	 
	 boolean tem = false;
	 
	 public SensoresImpl() {
		 
	 }
	 
	 @Override
	 public List<Chute> gerarLeituras(int totalLeituras) {		 
		 
		 Random randomizador = new Random();
		 
		 int oscilacao = randomizador.nextInt(OSCILACAO_MAXIMA);
		 boolean somar = randomizador.nextBoolean();
		 int velocidade = (int) (somar ? VELOCIDADE_MEDIA + (VELOCIDADE_MEDIA * oscilacao / 100) : VELOCIDADE_MEDIA - (VELOCIDADE_MEDIA * oscilacao / 100));
		 
		 oscilacao = randomizador.nextInt(OSCILACAO_MAXIMA);
		 somar = randomizador.nextBoolean();
		 int rpm = (int) (somar ? RPM_MEDIA + (RPM_MEDIA * oscilacao / 100) : RPM_MEDIA - (RPM_MEDIA * oscilacao / 100));

		 
		 oscilacao = randomizador.nextInt(OSCILACAO_MAXIMA);
		 somar = randomizador.nextBoolean();
		 int forca = (int) (somar ? FORCA_MEDIA + (FORCA_MEDIA * oscilacao / 100) : FORCA_MEDIA - (FORCA_MEDIA * oscilacao / 100));

		 if((Math.abs(velocidade - ultimaLeitura.getVelocidade()) > OSCILACAO_MAXIMA) || (Math.abs(rpm - ultimaLeitura.getRPM()) > OSCILACAO_MAXIMA) || (Math.abs(forca - ultimaLeitura.getForca()) > OSCILACAO_MAXIMA)){
			 tem = true;
			 
			 ultimaLeitura = new Chute(velocidade, rpm, forca);
			 ultimasLeituras.add(ultimaLeitura);
			 
			 if(ultimasLeituras.size() > LIMITE_DE_LEITURAS) {
				 ultimasLeituras.add(ultimaLeitura);
			 }
			 
			 List<Chute> listaChutes = new ArrayList<>();
			 listaChutes.addAll(ultimasLeituras);
			 
			 tem = true;
		 }
		 
		 return ultimasLeituras;
	 }
	 
	 @Override
	 public Chute getLeitura() {
		 return ultimaLeitura;
	 }

	@Override
	public boolean temLeitura() {
		return tem;
	}
}
