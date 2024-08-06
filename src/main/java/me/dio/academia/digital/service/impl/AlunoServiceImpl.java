package me.dio.academia.digital.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;

@Service
public class AlunoServiceImpl implements IAlunoService {

  @Autowired
  private AlunoRepository repository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = new Aluno();
    aluno.setNome(form.getNome());
    aluno.setCpf(form.getCpf());
    aluno.setBairro(form.getBairro());
    aluno.setDataDeNascimento(form.getDataDeNascimento());

    return repository.save(aluno);
  }

  @Override
  public Aluno get(Long id) {
	Optional<Aluno> aluno = repository.findById(id);
    return aluno.get();
  }

  @Override
  public List<Aluno> getAll() {
	  List<Aluno> alunos = repository.findAll();
      return alunos;

  }

  public Aluno update(Long id, AlunoUpdateForm aluno) {
		Optional<Aluno> obj = repository.findById(id);
		updateData(obj.get(), aluno);
		return repository.save(obj.get());
	}

	private void updateData(Aluno obj, AlunoUpdateForm aluno) {
		obj.setNome(aluno.getNome());
		obj.setDataDeNascimento(aluno.getDataDeNascimento());
		obj.setBairro(aluno.getBairro());
		obj.setCpf(aluno.getCpf());
	}

  @Override
  public void delete(Long id) {
	  repository.deleteById(id);
  }

  @Override
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {

    Aluno aluno = repository.findById(id).get();

    return aluno.getAvaliacoes();

  }
  
  

}
