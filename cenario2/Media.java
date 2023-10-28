package cenario2;

import java.util.List;

public class Media implements Atuador<Chute, Double>{

	public Media() {	
	}
	
	@Override
	public Double atuar(List<Chute> leituras) {
		
		double somaVelocidades = 0;
	    int contador = 0;

	    for (Chute chute : leituras) {
	        for (Chute outroChute : leituras) {
	            somaVelocidades += (chute.getVelocidade() + outroChute.getVelocidade()) / 2;
	            contador++;
	        }
	    }

	    if (contador == 0) {
	        return 0.0;
	    }

	    return (somaVelocidades / contador);
	}
}
