package cenario2;

import java.util.List;

public interface Atuador<Leitura, Resultado> {

	Resultado atuar(List<Leitura> leituras);
}
