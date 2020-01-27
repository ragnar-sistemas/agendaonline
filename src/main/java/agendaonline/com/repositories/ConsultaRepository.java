package agendaonline.com.repositories;

import org.springframework.data.repository.CrudRepository;

import agendaonline.com.models.Consulta;

public interface ConsultaRepository extends CrudRepository<Consulta, Long> {

	Iterable<Consulta> findByData(String data);

	Consulta findByIdConsulta(Long idConsulta);
}
