package cenario2;

import java.util.List;

public interface Sensores<T> {
    
	List<T> gerarLeituras(int totalLeituras);
	
	public T getLeitura();
	
	public boolean temLeitura();
}
