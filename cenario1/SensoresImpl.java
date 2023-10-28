package cenario1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensoresImpl implements Sensores<Chute> {

    private static final int VELOCIDADE_MEDIA = 80;
    private static final int RPM_MEDIO = 500;
    private static final int FORCA_MEDIA = 200;
    private static final int OSCILACAO_MAXIMA = 30;
    
    Chute leitura = new Chute(VELOCIDADE_MEDIA, RPM_MEDIO, FORCA_MEDIA);
    private boolean gerouLeituras = false;
    
    @Override
    public List<Chute> gerarLeituras(int totalLeituras) {
        List<Chute> leituras = new ArrayList<>();

        Random randomizador = new Random();
        for (int i = 0; i < totalLeituras; i++) {
            int oscilacaoVelocidade = VELOCIDADE_MEDIA * randomizador.nextInt(OSCILACAO_MAXIMA) / 100;
            int oscilacaoRPM = RPM_MEDIO * randomizador.nextInt(OSCILACAO_MAXIMA) / 100;
            int oscilacaoForca = FORCA_MEDIA * randomizador.nextInt(OSCILACAO_MAXIMA) / 100;

            int velocidade = (randomizador.nextBoolean() ? VELOCIDADE_MEDIA + oscilacaoVelocidade : VELOCIDADE_MEDIA - oscilacaoVelocidade);
            int rpm = (randomizador.nextBoolean() ? RPM_MEDIO + oscilacaoRPM : RPM_MEDIO - oscilacaoRPM);
            int forca = (randomizador.nextBoolean() ? FORCA_MEDIA + oscilacaoForca : FORCA_MEDIA - oscilacaoForca);

            leitura = new Chute(velocidade, rpm, forca);
            leituras.add(leitura);
            gerouLeituras = true;
        }      
        
        return leituras;
    }

    @Override
    public boolean temLeitura() {
    	return gerouLeituras;
    }
    
	@Override
	public Chute getLeitura() {
		return leitura;
	}
}
