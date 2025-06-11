package prof.ranghetti.service;

import prof.ranghetti.dao.AlunoDao;
import prof.ranghetti.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {

    public Boolean salvar(Aluno aluno){
        var dao = new AlunoDao();
        return dao.insert(aluno);
    }

    public List<Aluno> listarTodos() {
        var dao = new AlunoDao();
        List<Aluno> alunos = new ArrayList<>();
        dao.selectAll().forEach(
                object -> alunos.add((Aluno) object));

        return alunos;
    }
}
