package cenario2;

import java.util.List;

public class AnaliseDeMedias implements Atuador<Chute, String>{

	private static final int MARGEM_SUP_VELOCIDADE = 80;
	private static final int MARGEM_INF_VELOCIDADE = 60;
	
	@Override
	public String atuar(List<Chute> leituras) {
		String analise = "";
		
		double mediaVelocidade = .0;
		for(Chute leitura : leituras) {
			mediaVelocidade += leitura.getVelocidade();
		}
		
		mediaVelocidade /= leituras.size();
		
		if(mediaVelocidade > MARGEM_SUP_VELOCIDADE) {
			analise += "Velocidade acima da media";
		} else if (mediaVelocidade < MARGEM_INF_VELOCIDADE) {
			analise += "Velocidade abaixo da media";
		}
		
		return analise;
	
	}
}
