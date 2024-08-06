package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

	@Autowired
	private MatriculaRepository matriculaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public Matricula create(MatriculaForm form) {
		Matricula matricula = new Matricula();
		Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

		matricula.setAluno(aluno);

		return matriculaRepository.save(matricula);
	}

	@Override
	public Matricula getById(Long id) {
		return matriculaRepository.findById(id).get();
	}

	@Override
	public List<Matricula> getAll(String bairro) {
		List<Matricula> matriculas = matriculaRepository.findAll();
		return matriculas;
	}

	public void delete(Long id) {
		matriculaRepository.deleteById(id);
	}

	public Matricula update(Long id, Matricula matricula) {
		Optional<Matricula> obj = matriculaRepository.findById(id);
		updateData(obj.get(), matricula);
		return matriculaRepository.save(obj.get());
	}

	private void updateData(Matricula obj, Matricula matricula) {
		obj.setDataDaMatricula(matricula.getDataDaMatricula());
	}

}
