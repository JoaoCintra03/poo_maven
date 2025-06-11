package prof.ranghetti;

import prof.ranghetti.gui.AlunoGui;
import prof.ranghetti.model.Aluno;
import prof.ranghetti.service.AlunoService;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::iniciar);
    }

    private static void iniciar() {
        var alunoGui = new AlunoGui();
        alunoGui.setVisible(true);
    }
}
