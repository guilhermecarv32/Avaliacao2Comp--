package cenario1;

import java.util.List;

public interface Atuador<Leitura, Resultado> {

	Resultado atuar(List<Leitura> leituras);
}
