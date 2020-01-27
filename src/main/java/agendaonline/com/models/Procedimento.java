package agendaonline.com.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Procedimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProcedimento;
	@NotNull(message = "A descrição do tipo de procedimentoO é obrigatório.")
	private String tipoProcedimento;
	@OneToMany
	private List<Consulta> consultas;
	@OneToMany
	private List<Prontuario> prontuario;

	public Long getIdProcedimento() {
		return idProcedimento;
	}

	public void setIdProcedimento(Long idProcedimento) {
		this.idProcedimento = idProcedimento;
	}

	public String getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public List<Prontuario> getProntuario() {
		return prontuario;
	}

	public void setProntuario(List<Prontuario> prontuario) {
		this.prontuario = prontuario;
	}

}
