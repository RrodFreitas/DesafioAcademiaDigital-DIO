package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

	@Autowired
	private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
		AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
		Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

		avaliacaoFisica.setAluno(aluno);
		avaliacaoFisica.setPeso(form.getPeso());
		avaliacaoFisica.setAltura(form.getAltura());

		return avaliacaoFisicaRepository.save(avaliacaoFisica);
	}

	@Override
	public AvaliacaoFisica getById(Long id) {
		Optional<AvaliacaoFisica> avaliacao = avaliacaoFisicaRepository.findById(id);
		return avaliacao.get();
	}

	@Override
	public List<AvaliacaoFisica> getAll() {
		List<AvaliacaoFisica> avaliacoes = avaliacaoFisicaRepository.findAll();
		return avaliacoes;
	}

	public void delete(Long id) {
		avaliacaoFisicaRepository.deleteById(id);
	}
	
	public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm avaliacaoFisica) {
		Optional<AvaliacaoFisica> obj = avaliacaoFisicaRepository.findById(id);
		updateData(obj.get(), avaliacaoFisica);
		return avaliacaoFisicaRepository.save(obj.get());
	}

	private void updateData(AvaliacaoFisica obj, AvaliacaoFisicaUpdateForm avaliacaoFisica) {
		obj.setPeso(avaliacaoFisica.getPeso());
		obj.setAltura(avaliacaoFisica.getAltura());
	}
}
