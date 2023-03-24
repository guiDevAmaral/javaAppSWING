package Screen;

import DadosDaPressao.DadosPressao;
import DadosDaPressao.ListaDadosPressao;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainScreen extends javax.swing.JFrame {

    private final String[] colunasTabela = {"Data", "Hora", "Pressão Sistólica", "Pressão Diastólica", "Situação de Estresse"};

    private static String diretorio = "C:\\Users\\Amaral\\Documents\\NetBeansProjects\\Atv4Uc09\\dadosTabela.txt";

    DefaultTableModel tabelaModelo = new DefaultTableModel(colunasTabela, 0);

    private List<DadosPressao> listaPaciente = ListaDadosPressao.RetornaDados();

    public MainScreen() {
        initComponents();
        geraAcessbilidade();
        carregarDadosPeloTXT();
    }

    public void carregarDadosPeloTXT() {
        try {
            File file = new File(this.diretorio);
            Scanner reader = new Scanner(file);

            if (reader.hasNextLine()) {
                String cabecalho = reader.nextLine();
            }

            while (reader.hasNextLine()) {
                String dados = reader.nextLine();
                List<String> linhaDados = Arrays.asList((dados.split(";")));

                String valor = String.valueOf(linhaDados.get(4));
                boolean estresse = false;

                if (valor.equalsIgnoreCase("Sim")) {
                    estresse = true;
                } else if (valor.equalsIgnoreCase("Não")) {
                    estresse = false;
                }

                DadosPressao dadosPressao = new DadosPressao();
                dadosPressao.setData(linhaDados.get(0));
                dadosPressao.setHora(linhaDados.get(1));
                dadosPressao.setpSistolica(Integer.parseInt(linhaDados.get(2)));
                dadosPressao.setpDiastolica(Integer.parseInt(linhaDados.get(3)));
                dadosPressao.setEstresse(estresse);

                ListaDadosPressao.Adicionar(dadosPressao);

                atualizarTabela();

            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + e.getMessage() + "\n Um novo arquivo será criado");
        }

    }

    public void atualizarTabela() {
        String dadosTXT = "";
        String cabecalho = "DATA;HORA;PRESSÃO SISTOLICA;PRESSÃO DIATOLICA;ESTRESSE\n";
        if (!listaPaciente.isEmpty()) {

            DadosPressao dadosPressao;
            tabelaModelo = new DefaultTableModel(colunasTabela, 0);
            for (int i = 0; i < listaPaciente.size(); i++) {

                dadosPressao = listaPaciente.get(i);

                String stringIsEstresse;
                if (dadosPressao.isEstresse()) {
                    stringIsEstresse = "Sim";
                } else {
                    stringIsEstresse = "Não";
                }
                String[] rowData = {dadosPressao.getData(), dadosPressao.getHora(), String.valueOf(dadosPressao.getpSistolica()), String.valueOf(dadosPressao.getpDiastolica()), stringIsEstresse};

                tabelaModelo.addRow(rowData);

                dadosTXT += dadosPressao.getData() + ";" + dadosPressao.getHora() + ";" + String.valueOf(dadosPressao.getpSistolica()) + ";" + String.valueOf(dadosPressao.getpDiastolica()) + ";" + stringIsEstresse + "\n";
            }
            jTabela.setModel(tabelaModelo);
        } else {

            tabelaModelo = new DefaultTableModel(colunasTabela, 0);
            jTabela.setModel(tabelaModelo);
        }

        try {
            FileWriter sobrescrever = new FileWriter(diretorio);

            sobrescrever.write(cabecalho + dadosTXT);
            sobrescrever.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro !\nNão foi possível sobrescrever o arquivo");
        }

    }

    public void geraAcessbilidade() {
        btnSalvar.setMnemonic(KeyEvent.VK_S);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPainel = new javax.swing.JPanel();
        jPainelDadosPaciente = new javax.swing.JLayeredPane();
        lblData = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        lblHora = new javax.swing.JLabel();
        txtHora = new javax.swing.JFormattedTextField();
        lblPressaoS = new javax.swing.JLabel();
        txtPressaoSistolica = new javax.swing.JTextField();
        lblPressaoD = new javax.swing.JLabel();
        txtPressaoDiastolica = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        cbSituacaoDeEstresse = new javax.swing.JCheckBox();
        jPainelRolamento = new javax.swing.JScrollPane();
        jTabela = new javax.swing.JTable();
        barraMenu = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        itMenuFechar = new javax.swing.JMenuItem();
        itMenuSalvar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("gui.dev.amaral@gmail.com");

        jPainelDadosPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Medição ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18))); // NOI18N
        jPainelDadosPaciente.setToolTipText("Informe aqui os dados da medição");

        lblData.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblData.setText("Data");

        try {
            txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtData.setToolTipText("Informe a data dd/mm/aaaa");
        txtData.setNextFocusableComponent(txtHora);

        lblHora.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblHora.setText("Hora");

        try {
            txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHora.setToolTipText("Informe a hora (HH:MM)");
        txtHora.setNextFocusableComponent(txtPressaoSistolica);

        lblPressaoS.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblPressaoS.setText("Pressão sistólica  ");

        txtPressaoSistolica.setToolTipText("Digite o valor da pressão sistólica");
        txtPressaoSistolica.setNextFocusableComponent(txtPressaoDiastolica);

        lblPressaoD.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblPressaoD.setText("Pressão diastólica ");

        txtPressaoDiastolica.setToolTipText("Digite o valor da pressão diastólica");
        txtPressaoDiastolica.setNextFocusableComponent(btnSalvar);

        btnSalvar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("Clique aqui para salvar");
        btnSalvar.setNextFocusableComponent(txtData);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        cbSituacaoDeEstresse.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        cbSituacaoDeEstresse.setText("Em situação de estresse");

        jPainelDadosPaciente.setLayer(lblData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(txtData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(lblHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(txtHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(lblPressaoS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(txtPressaoSistolica, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(lblPressaoD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(txtPressaoDiastolica, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(btnSalvar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPainelDadosPaciente.setLayer(cbSituacaoDeEstresse, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPainelDadosPacienteLayout = new javax.swing.GroupLayout(jPainelDadosPaciente);
        jPainelDadosPaciente.setLayout(jPainelDadosPacienteLayout);
        jPainelDadosPacienteLayout.setHorizontalGroup(
            jPainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPainelDadosPacienteLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPainelDadosPacienteLayout.createSequentialGroup()
                .addGroup(jPainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPressaoDiastolica)
                    .addGroup(jPainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPressaoSistolica, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(txtHora)
                            .addComponent(lblData, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtData)
                            .addComponent(lblHora, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPressaoS, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(lblPressaoD))
                    .addComponent(cbSituacaoDeEstresse, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPainelDadosPacienteLayout.setVerticalGroup(
            jPainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainelDadosPacienteLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblPressaoS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPressaoSistolica, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblPressaoD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPressaoDiastolica, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbSituacaoDeEstresse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Hora", "Pressão Sistólica", "Pressão Diastólica", "Situação de Estresse"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jPainelRolamento.setViewportView(jTabela);

        javax.swing.GroupLayout jPainelLayout = new javax.swing.GroupLayout(jPainel);
        jPainel.setLayout(jPainelLayout);
        jPainelLayout.setHorizontalGroup(
            jPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPainelDadosPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPainelRolamento, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPainelLayout.setVerticalGroup(
            jPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPainelRolamento)
                    .addComponent(jPainelDadosPaciente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu.setText("Menu");

        itMenuFechar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itMenuFechar.setText("Fechar");
        itMenuFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itMenuFecharActionPerformed(evt);
            }
        });
        jMenu.add(itMenuFechar);

        itMenuSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itMenuSalvar.setText("Salvar");
        itMenuSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itMenuSalvarActionPerformed(evt);
            }
        });
        jMenu.add(itMenuSalvar);

        barraMenu.add(jMenu);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itMenuFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itMenuFecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itMenuFecharActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        DadosPressao dados = new DadosPressao();

        if (txtData.getText().isEmpty() || txtHora.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            df.parse(txtData.getText());

            DateFormat tf = new SimpleDateFormat("HH:mm");
            tf.setLenient(false);
            tf.parse(txtHora.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira uma data e hora válidas (DD/MM/AAAA HH:MM).");
            return;
        }
        
        try {
            int psistolica = Integer.parseInt(txtPressaoSistolica.getText());
            int pdiastolica = Integer.parseInt(txtPressaoDiastolica.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido para a pressão sistólica e diastólica.");
            return;
        }

        dados.setData(txtData.getText());
        dados.setHora(txtHora.getText());
        dados.setpSistolica(Integer.parseInt(txtPressaoSistolica.getText()));
        dados.setpDiastolica(Integer.parseInt(txtPressaoDiastolica.getText()));

        if (cbSituacaoDeEstresse.isSelected()) {
            dados.setEstresse(true);
        } else {
            dados.setEstresse(false);
        }

        ListaDadosPressao.Adicionar(dados);

        atualizarTabela();


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void itMenuSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itMenuSalvarActionPerformed
        DadosPressao dados = new DadosPressao();

        dados.setData(txtData.getText());
        dados.setHora(txtHora.getText());
        dados.setpSistolica(Integer.parseInt(txtPressaoSistolica.getText()));
        dados.setpDiastolica(Integer.parseInt(txtPressaoDiastolica.getText()));

        if (cbSituacaoDeEstresse.isSelected()) {
            dados.setEstresse(true);
        } else {
            dados.setEstresse(false);
        }

        ListaDadosPressao.Adicionar(dados);
        atualizarTabela();

    }//GEN-LAST:event_itMenuSalvarActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox cbSituacaoDeEstresse;
    private javax.swing.JMenuItem itMenuFechar;
    private javax.swing.JMenuItem itMenuSalvar;
    private javax.swing.JMenu jMenu;
    private javax.swing.JPanel jPainel;
    private javax.swing.JLayeredPane jPainelDadosPaciente;
    private javax.swing.JScrollPane jPainelRolamento;
    private javax.swing.JTable jTabela;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblPressaoD;
    private javax.swing.JLabel lblPressaoS;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtPressaoDiastolica;
    private javax.swing.JTextField txtPressaoSistolica;
    // End of variables declaration//GEN-END:variables
}
