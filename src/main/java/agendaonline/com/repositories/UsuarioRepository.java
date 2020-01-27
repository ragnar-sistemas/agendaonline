package agendaonline.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agendaonline.com.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByNome(String nome);
}
