
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class Buku extends javax.swing.JFrame {
private Statement st;
private Connection Con = new koneksi().connect();
private ResultSet Rs;
private String Tanggal="";
private String Sql="";
private String host;
public String Kodebuku,judulbuku,pengarang,tahunterbit,jumlahbuku,penerbit;
    /**
     * Creates new form Buku
     */
    public Buku() {
        initComponents();
        Tampilbuku(Sql);
        CBTAHUNTERBIT.addItem("Select");
        CBTAHUNTERBIT.setSelectedItem("Select");
        this.setTitle("Perpustakaan MTS SALFIYAH SAFI'IYAH");
    }
    public Connection koneksi(){
        Connection khusus = null;
         
        try {
            Class.forName("com.mysql.jdbc.Driver");    //Jika Tidak Terjadi Error
            khusus = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/db_perpustakaan","root","");
            return khusus;
        } catch (ClassNotFoundException ex) {
          return null;
        }catch (SQLException ex){
            return null;
        }
    }
         private void Tampilbuku(String Sql){
        DefaultTableModel  grid=new DefaultTableModel();
        grid.addColumn("No");
        grid.addColumn("Kode Buku");
        grid.addColumn("Judul Buku");
        grid.addColumn("Pengarang");
        grid.addColumn("Penerbit");
        grid.addColumn("Tanggal Beli");
        grid.addColumn("Tahun Terbit");
        grid.addColumn("Jumlah Buku");
       
        try {
            int i=1;
            st=Con.createStatement();
            Rs=st.executeQuery("SELECT*FROM buku");
            while (Rs.next()){
                grid.addRow(new Object[]{
                    (""+i++),Rs.getString(1),
                    Rs.getString(2),
                    Rs.getString(3),
                    Rs.getString(4),
                    Rs.getString(5),
                    Rs.getString(6),
                    Rs.getString(7)
            });
               TBUKU.setModel(grid);
               TBUKU.enable(false);
           
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Gagal Tampil: "+e);
        }
         }
     private void kosongkan (){
         txtkdbuku.setText("");
         txtjudulbuku.setText("");
         txtpengarang.setText("");
         txtpenerbit.setText("");
         JTANGGAL.setDate(null);
         CBTAHUNTERBIT.setSelectedItem("Select");
         txtjumlahbuku.setText("");
         txtkdbuku.requestFocus();
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtkdbuku = new javax.swing.JTextField();
        txtjudulbuku = new javax.swing.JTextField();
        txtpengarang = new javax.swing.JTextField();
        txtjumlahbuku = new javax.swing.JTextField();
        txtpenerbit = new javax.swing.JTextField();
        JTANGGAL = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBUKU = new javax.swing.JTable();
        CBTAHUNTERBIT = new javax.swing.JComboBox<>();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("KODE BUKU");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("JUDUL BUKU");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("TANGGAL BELI");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("PENGARANG");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("TAHUN TERBIT");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("STOK BUKU");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("PENERBIT");

        txtkdbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdbukuActionPerformed(evt);
            }
        });
        txtkdbuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtkdbukuKeyPressed(evt);
            }
        });

        txtjudulbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjudulbukuActionPerformed(evt);
            }
        });

        txtpengarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpengarangActionPerformed(evt);
            }
        });

        JTANGGAL.setDateFormatString("yyyy-MM-dd");
        JTANGGAL.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JTANGGALPropertyChange(evt);
            }
        });

        TBUKU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Buku", "Judul Buku", "Pengarang", "Penerbit", "Tanggal beli", "Tahun Terbit", "Jumlah Buku"
            }
        ));
        TBUKU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBUKUMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBUKU);

        CBTAHUNTERBIT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CBTAHUNTERBIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBTAHUNTERBITActionPerformed(evt);
            }
        });

        btnsimpan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button simpan.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setDisabledIcon(null);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnedit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Edit.png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnedit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnbatal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button batal.png"))); // NOI18N
        btnbatal.setText("BATAL");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnhapus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Hapus_1.png"))); // NOI18N
        btnhapus.setText("HAPUS");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnkeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Keluar.png"))); // NOI18N
        btnkeluar.setText("KELUAR");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        btncetak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Cetak.png"))); // NOI18N
        btncetak.setText("CETAK");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("KATA KUNCI PENCARIAN");

        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });

        bcari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bcari.setText("CARI");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnedit)
                                .addGap(14, 14, 14)
                                .addComponent(btnbatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnhapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncetak)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnkeluar)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtjudulbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkdbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtjumlahbuku)
                            .addComponent(CBTAHUNTERBIT, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JTANGGAL, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel9)
                .addGap(14, 14, 14)
                .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(bcari)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(JTANGGAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBTAHUNTERBIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjumlahbuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtkdbuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjudulbuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtpenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnkeluar)
                            .addComponent(btnhapus)
                            .addComponent(btnbatal)
                            .addComponent(btncetak))
                        .addComponent(btnedit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnsimpan))
                .addGap(28, 28, 28))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/buku1.png"))); // NOI18N
        jLabel1.setText("DATA BUKU");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
            for (int naik = 1990; naik <= 2030; naik++) {
            CBTAHUNTERBIT.addItem(String.valueOf(naik));
        }
    }//GEN-LAST:event_formWindowActivated

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        Kodebuku=txtkdbuku.getText();
        try { Sql="DELETE FROM buku "
            + "WHERE Kode_buku='" + Kodebuku+ "'";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            Tampilbuku("SELECT*FROM buku");
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
            btnsimpan.show();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Tidak Di Hapus\n"+e.getMessage());
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        Kodebuku=txtkdbuku.getText();
        judulbuku=txtjudulbuku.getText();
        pengarang=txtpengarang.getText();
        penerbit=txtpenerbit.getText();
        tahunterbit=CBTAHUNTERBIT.getItemAt(CBTAHUNTERBIT.getSelectedIndex());
        jumlahbuku=txtjumlahbuku.getText();
        try { Sql="UPDATE buku SET Judul_buku='"+judulbuku+"'," 
            + "Pengarang='"+pengarang+"',"
            + "Penerbit='"+penerbit+"'"
            + "Tanggal_beli='"+Tanggal+"',"
            + "Tahun_terbit='"+tahunterbit+"',"
            + "Jumlah_buku='"+jumlahbuku+"',"
            + "WHERE Kode_buku='"+Kodebuku+"'";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            Tampilbuku("SELECT*FROM buku");
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Edit");
            btnsimpan.show();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Tidak Di Perbaharui\n"+e.getMessage());
        }
    }//GEN-LAST:event_btneditActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        Kodebuku=txtkdbuku.getText();
        judulbuku=txtjudulbuku.getText();
        pengarang=txtpengarang.getText();
        penerbit=txtpenerbit.getText();
        tahunterbit=CBTAHUNTERBIT.getItemAt(CBTAHUNTERBIT.getSelectedIndex());
        jumlahbuku=txtjumlahbuku.getText();
        try {
            Sql="INSERT INTO buku"
            + "(Kode_buku,Judul_buku,Pengarang,Penerbit,Tanggal_beli,Tahun_terbit,Jumlah_buku)"
            + "VALUES('"+ Kodebuku+"',"
            + "'"+judulbuku+"',"
            + "'"+pengarang+"',"
            + "'"+penerbit+"',"
            + "'"+Tanggal+"',"
            + "'"+tahunterbit+"',"
            + "'"+jumlahbuku+"')";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            Tampilbuku("SELECT*FROM buku");
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            btnsimpan.enable(true);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Tidak Tersimpan\n"+e.getMessage());
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void CBTAHUNTERBITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBTAHUNTERBITActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBTAHUNTERBITActionPerformed

    private void JTANGGALPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JTANGGALPropertyChange
        // TODO add your handling code here:
        if(JTANGGAL.getDate()!=null){
            SimpleDateFormat format =new SimpleDateFormat ("yyyy-MM-dd");
            Tanggal=format.format(JTANGGAL.getDate());
        }
    }//GEN-LAST:event_JTANGGALPropertyChange

    private void txtkdbukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkdbukuKeyPressed
        // TODO add your handling code here:
        Kodebuku=txtkdbuku.getText();
        int A = evt.getKeyCode();
        if (A==10){
            try {
                Sql="select * from buku where Kode_buku='"+Kodebuku+"'";
                st=Con.createStatement();
                Rs=st.executeQuery(Sql);
                while (Rs.next()){
                    txtjudulbuku.setText(Rs.getString("Judul_buku"));
                    txtpenerbit.setText(Rs.getString("Penerbit"));
                    txtpengarang.setText(Rs.getString("Pengarang"));
                    JTANGGAL.setDate(Rs.getDate("Tanggal_beli"));
                    CBTAHUNTERBIT.setSelectedItem(Rs.getString("Tahun_terbit"));
                    txtjumlahbuku.setText(Rs.getString("Jumlah_buku"));
                    btnsimpan.hide();
                    btnedit.show();
                    btnhapus.show();
                    txtjudulbuku.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Di Temukan"+e.getMessage());
            }
        }
    }//GEN-LAST:event_txtkdbukuKeyPressed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
           try{
            String file = "src/lapbuku.jasper";
            JasperPrint jp = JasperFillManager.fillReport(file, null, koneksi());
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
        }catch (Exception e) {
        }
    }//GEN-LAST:event_btncetakActionPerformed

    private void TBUKUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBUKUMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TBUKUMouseClicked

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

    private void txtjudulbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjudulbukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjudulbukuActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        // TODO add your handling code here:
        try {
            Sql="select * from buku where Kode_buku='"+tcari.getText()+"'";
            st=Con.createStatement();
            Rs=st.executeQuery(Sql);
            while(Rs.next()){
                    txtjudulbuku.setText(Rs.getString("Judul_buku"));
                    txtpengarang.setText(Rs.getString("Pengarang"));
                    txtpenerbit.setText(Rs.getString("Penerbit"));
                    JTANGGAL.setDate(Rs.getDate("Tanggal_beli"));
                    CBTAHUNTERBIT.setSelectedItem(Rs.getString("Tahun_terbit"));
                    txtjumlahbuku.setText(Rs.getString("Jumlah_buku"));
            }  
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bcariActionPerformed

    private void txtkdbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdbukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkdbukuActionPerformed

    private void txtpengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpengarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpengarangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBTAHUNTERBIT;
    private com.toedter.calendar.JDateChooser JTANGGAL;
    private javax.swing.JTable TBUKU;
    private javax.swing.JButton bcari;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnsimpan;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField txtjudulbuku;
    private javax.swing.JTextField txtjumlahbuku;
    private javax.swing.JTextField txtkdbuku;
    private javax.swing.JTextField txtpenerbit;
    private javax.swing.JTextField txtpengarang;
    // End of variables declaration//GEN-END:variables
}
