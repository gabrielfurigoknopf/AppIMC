package br.upf.ccc.appimc.view;

import br.upf.ccc.appimc.model.Pessoa;
import br.upf.ccc.appimc.utilities.IMCCalculadora;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AppIMCUI extends JFrame {
    private final ArrayList<Pessoa> testes;  // armazena os testes realizados
    private JTextArea resultArea; // área para mostrar os resultados

    public AppIMCUI() {
        testes = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        setTitle("Cálculo de IMC");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // Botões de funcionalidade
        JButton btnIncluir = new JButton("Incluir Teste");
        buttonPanel.add(btnIncluir);
        JButton btnListar = new JButton("Listar Testes");
        buttonPanel.add(btnListar);
        JButton btnExcluir = new JButton("Excluir Teste");
        buttonPanel.add(btnExcluir);
        JButton btnEstatisticas = new JButton("Estatísticas");
        buttonPanel.add(btnEstatisticas);

        // Área de resultados
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        getContentPane().add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Ações dos botões
        btnIncluir.addActionListener((ActionEvent e) -> incluirTeste());
        btnListar.addActionListener((ActionEvent e) -> listarTestes());
        btnExcluir.addActionListener((ActionEvent e) -> excluirTeste());
        btnEstatisticas.addActionListener((ActionEvent e) -> exibirEstatisticas());
    }

    private void incluirTeste() {
        JTextField cpfField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField pesoField = new JTextField();
        JTextField alturaField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Peso (kg):"));
        panel.add(pesoField);
        panel.add(new JLabel("Altura (m):"));
        panel.add(alturaField);

        int option = JOptionPane.showConfirmDialog(this, panel, "Incluir Teste", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String cpf = cpfField.getText();
            String nome = nomeField.getText();
            double peso = Double.parseDouble(pesoField.getText());
            double altura = Double.parseDouble(alturaField.getText());

            if (peso > 0 && altura > 0) {
                Pessoa pessoa = new Pessoa(cpf, nome, peso, altura);
                testes.add(pessoa);
                JOptionPane.showMessageDialog(this, "Teste incluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Peso e altura devem ser positivos.");
            }
        }
    }

    private void listarTestes() {
        resultArea.setText("");
        for (Pessoa p : testes) {
            double imc = IMCCalculadora.calcularIMC(p);
            String interpretacao = IMCCalculadora.interpretarIMC(imc);
            resultArea.append(String.format("%s (CPF: %s) - Peso: %.2f kg, Altura: %.2f m, IMC: %.2f - %s\n",
                    p.getNome(), p.getCpf(), p.getPeso(), p.getAltura(), imc, interpretacao));
        }
    }

    private void excluirTeste() {
        String cpfExcluir = JOptionPane.showInputDialog(this, "Digite o CPF do teste a excluir:");
        testes.removeIf(p -> p.getCpf().equals(cpfExcluir));
        JOptionPane.showMessageDialog(this, "Teste removido, se existia.");
    }

    private void exibirEstatisticas() {
        if (testes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum teste realizado ainda.");
            return;
        }

        int[] categorias = new int[6];
        for (Pessoa p : testes) {
            double imc = IMCCalculadora.calcularIMC(p);
            if (imc < 18.5) categorias[0]++;
            else if (imc < 24.9) categorias[1]++;
            else if (imc < 29.9) categorias[2]++;
            else if (imc < 34.9) categorias[3]++;
            else if (imc < 39.9) categorias[4]++;
            else categorias[5]++;
        }

        String[] nomesCategorias = {"Magreza", "Normal", "Sobrepeso", "Obesidade Grau I", "Obesidade Grau II", "Obesidade Grau III"};
        int total = testes.size();
        StringBuilder stats = new StringBuilder();
        stats.append(String.format("Total: %d testes realizados.\n", total));

        for (int i = 0; i < categorias.length; i++) {
            double percentual = (categorias[i] / (double) total) * 100;
            stats.append(String.format("%s: %d (%.2f%%)\n", nomesCategorias[i], categorias[i], percentual));
        }

        resultArea.setText(stats.toString());
    }
}
