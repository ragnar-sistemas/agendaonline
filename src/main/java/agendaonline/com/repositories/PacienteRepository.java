package agendaonline.com.repositories;

import org.springframework.data.repository.CrudRepository;

import agendaonline.com.models.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
	
	Paciente findByNome(String nome);
}
