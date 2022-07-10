import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository repository;
	
	public Autor salvar(Autor autor) {
		return repository.save(autor);
		}
}
