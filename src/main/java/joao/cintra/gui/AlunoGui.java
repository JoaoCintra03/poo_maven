package joao.cintra.gui;

import joao.cintra.model.Aluno;
import joao.cintra.service.AlunoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AlunoGui extends JFrame {

    private JTextField tfID;
    private JTextField tfNome;
    private JTextField tfCpf;
    private JTextField tfEndereco;

    private JTable tbAlunos;

    public AlunoGui() {
        setTitle("Cadastro de Aluno");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(montarPainelEntrada(), BorderLayout.NORTH);
        getContentPane().add(montarPainelSaida(), BorderLayout.CENTER);
    }

    private JPanel montarPainelEntrada() {
        var jPanel = new JPanel(new GridBagLayout());
        var guiUtils = new GuiUtils();

        JLabel jlID = new JLabel("RA");
        tfID = new JTextField(20);
        tfID.setEditable(false);
        JLabel jlNome = new JLabel("Nome");
        tfNome = new JTextField(20);
        JLabel jlCpf = new JLabel("CPF");
        tfCpf = new JTextField(20);
        JLabel jlEndereco = new JLabel("Endereço");
        tfEndereco = new JTextField(20);
        JButton btConfirmar = new JButton("Confirmar");
        btConfirmar.addActionListener(this::confirmar);

        jPanel.add(jlID, guiUtils.montarConstraints(0, 0));
        jPanel.add(tfID, guiUtils.montarConstraints(1, 0));
        jPanel.add(jlNome, guiUtils.montarConstraints(0, 1));
        jPanel.add(tfNome, guiUtils.montarConstraints(1, 1));
        jPanel.add(jlCpf, guiUtils.montarConstraints(0, 2));
        jPanel.add(tfCpf, guiUtils.montarConstraints(1, 2));
        jPanel.add(jlEndereco, guiUtils.montarConstraints(0, 3));
        jPanel.add(tfEndereco, guiUtils.montarConstraints(1, 3));
        jPanel.add(btConfirmar, guiUtils.montarConstraints(0, 4));

        return jPanel;
    }

    private void confirmar(ActionEvent event) {
        var aluno = new Aluno();
        aluno.setId(tfID.getText().isEmpty() ? null : Long.valueOf(tfID.getText()));
        aluno.setNome(tfNome.getText());
        aluno.setCpf(tfCpf.getText());
        aluno.setEndereco(tfEndereco.getText());

        var servico = new AlunoService();
        servico.salvar(aluno);

        limparCampos();
    }

    private void limparCampos() {
        tfID.setText(null);
        tfNome.setText(null);
        tfCpf.setText(null);
        tfEndereco.setText(null);
    }

    private JScrollPane montarPainelSaida() {
        var tableModel = new DefaultTableModel();
        tableModel.addColumn("RA");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Endereço");

        var service = new AlunoService();
        service.listarTodos().forEach(a ->
                tableModel.addRow(new Object[]{
                        a.getId(),
                        a.getNome(),
                        a.getCpf(),
                        a.getEndereco()})
        );


        tbAlunos = new JTable();
        tbAlunos.setDefaultEditor(Object.class, null);
        tbAlunos.getSelectionModel()
                .addListSelectionListener(this::selecionar);
        tbAlunos.setModel(tableModel);

        return new JScrollPane(tbAlunos);
    }

    private void selecionar(ListSelectionEvent listSelectionEvent) {
        var linha = tbAlunos.getSelectedRow();
        tfID.setText(tbAlunos.getValueAt(linha,0).toString());
        tfNome.setText(tbAlunos.getValueAt(linha,1).toString());
        tfCpf.setText(tbAlunos.getValueAt(linha,2).toString());
        tfEndereco.setText(tbAlunos.getValueAt(linha,3).toString());
    }

}
