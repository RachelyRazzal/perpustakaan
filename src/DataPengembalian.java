
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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
public class DataPengembalian extends javax.swing.JFrame {
private Statement st; //pilih yang java.sql
private Connection Con = new koneksi().connect();
private ResultSet RsUser;
private ResultSet Rs;
private ResultSet RsPinjam;
private String Tanggalkembali;
private String Tanggalpinjam;
private String Sql="";


public String Kode_kembali,Kode_pinjam,No_pinjam,Nama_anggota; //untuk deklrasi simpan penjualan
public int Keterlambatan,Jumlah_pinjam,Denda,Hasil;//untuk deklrasi simpan penjualan
    /**
     * Creates new form DataPengembalian
     */
    public DataPengembalian() {
        initComponents();
  
        tampilPengembalian();
        tampilpeminjaman();
        PilihKodePinjam();
        kosongkan(); 
        TgldiText();
        this.setTitle("Perpustakaan MTS SALFIYAH SAFI'IYAH");
//        Waktu();
        txtTanggalPinjam.enable(false);
        TXTKETERLAMBATAN.enable(false);
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
      private void PilihKodePinjam(){
        CBKODEPINJAM.removeAllItems();
        CBKODEPINJAM.addItem("Select");
        try {
            String Sql ="SELECT*FROM peminjam";
            Statement st= Con.createStatement();
            RsPinjam= st.executeQuery(Sql);
            while(RsPinjam.next()){
            String AliasKode= RsPinjam.getString("Kode_peminjam");
            CBKODEPINJAM.addItem(AliasKode);
        }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Data\n"+e.getMessage());
}
     }
       public void TgldiText(){
        Date tgl=new Date();
        SimpleDateFormat Kembali=new SimpleDateFormat("yyyy-MM-dd");
        txtTanggalKembali.setText(Kembali.format(tgl));
        txtTanggalPinjam.setText(Kembali.format(tgl));
       }
      private void kosongkan(){
        TXTKODEKEMBALI.setText("");
        CBKODEPINJAM.setSelectedItem("Select");
        txtTanggalPinjam.setText("");
        TXTNAMAANGGOTA.setText("");
        TXTJUMLAHPINJAM.setText("");
        TXTKETERLAMBATAN.setText("");
    }
        private void tampilPengembalian(){
        DefaultTableModel grid=new DefaultTableModel();
        grid.addColumn("No");
        grid.addColumn("Kode_pengembalian");
        grid.addColumn("Tanggal_pengembalian");
        grid.addColumn("Kode_pinjam");
        grid.addColumn("Tanggal_pinjam");
        grid.addColumn("Nama_anggota");
        grid.addColumn("Jumlah_pinjam");
        grid.addColumn("Keterlambatan");
        grid.addColumn("Denda");
        try {
           int i=1;
           st=Con.createStatement();
           Rs=st.executeQuery("SELECT*FROM pengembalian");
           while (Rs.next()){
               grid.addRow(new Object[]{
                   (""+i++),Rs.getString(1),Rs.getString(2),Rs.getString(3),
                    Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),
               });
               TPENGEMBALIAN.setModel(grid);
               TPENGEMBALIAN.enable(false);
              //BtnTambah.requestFocus();
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Tampil"+e);
        }
}
          private void tampilpeminjaman(){
        DefaultTableModel grid=new DefaultTableModel();
        grid.addColumn("No");
        grid.addColumn("Kode peminjam");
        grid.addColumn("Tanggal_Pinjam");
        grid.addColumn("Kode Petugas");
        grid.addColumn("Nama petugas");
        grid.addColumn("Kode Anggota");
        grid.addColumn("Nama Anggota");
        grid.addColumn("Jumlah Pinjam");
        try {
           int i=1;
           st=Con.createStatement();
           Rs=st.executeQuery("SELECT*FROM peminjam");
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
               TdetailPengembalian.setModel(grid);
               TdetailPengembalian.enable(false);
               Btnsimpan.requestFocus();
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Tampil"+e);
        }
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CBKODEPINJAM = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        TXTNAMAANGGOTA = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TXTJUMLAHPINJAM = new javax.swing.JTextField();
        txtTanggalPinjam = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TXTKODEKEMBALI = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTanggalKembali = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TXTKETERLAMBATAN = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdenda = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TPENGEMBALIAN = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TdetailPengembalian = new javax.swing.JTable();
        BtnHapus = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        Btnsimpan = new javax.swing.JButton();
        BtnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("NAMA ANGGOTA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("KODE PEMINJAM");

        CBKODEPINJAM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CBKODEPINJAM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Item 2", "Item 3", "Item 4" }));
        CBKODEPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBKODEPINJAMActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("TANGGAL PINJAM");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("JUMLAH PINJAM");

        TXTJUMLAHPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTJUMLAHPINJAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TXTNAMAANGGOTA)
                                .addComponent(CBKODEPINJAM, javax.swing.GroupLayout.Alignment.TRAILING, 0, 117, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBKODEPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("KODE PENGEMBALIAN");

        TXTKODEKEMBALI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTKODEKEMBALIActionPerformed(evt);
            }
        });
        TXTKODEKEMBALI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTKODEKEMBALIKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TXTKODEKEMBALI, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTKODEKEMBALI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/pengembalian1.png"))); // NOI18N
        jLabel1.setText("DATA PENGEMBALIAN ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tgl/ PENGEMBALIAN");

        txtTanggalKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalKembaliActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("KETERLAMBATAN");

        TXTKETERLAMBATAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTKETERLAMBATANActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("/Hari");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("DENDA");

        txtdenda.setBackground(new java.awt.Color(255, 255, 255));
        txtdenda.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtdenda.setText("0,");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Ketelambatan ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("2000/hari");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Keterangan :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(txtdenda, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTKETERLAMBATAN, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(txtTanggalKembali))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TXTKETERLAMBATAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdenda))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TPENGEMBALIAN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Pengembalian", "Tanggal Kembali", "Kode Pinjam", "Tanggal Pinjam", "Nama Anggota", "Jumlah Pinjam", "Keterlambatan", "Denda"
            }
        ));
        jScrollPane1.setViewportView(TPENGEMBALIAN);

        TdetailPengembalian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Pinjam", "Tanggal Pinjam", "Kode Petugas", "Nama Petugas", "Kode Anggota", "Nama Anggota", "Jumlah Pinjam"
            }
        ));
        jScrollPane2.setViewportView(TdetailPengembalian);

        BtnHapus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Hapus_1.png"))); // NOI18N
        BtnHapus.setText("HAPUS");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        BtnBatal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button batal.png"))); // NOI18N
        BtnBatal.setText("BATAL");
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Cetak.png"))); // NOI18N
        jButton1.setText("CETAK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        BtnKeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Keluar.png"))); // NOI18N
        BtnKeluar.setText("KELUAR");
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });

        Btnsimpan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button simpan.png"))); // NOI18N
        Btnsimpan.setText("SIMPAN");
        Btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnsimpanActionPerformed(evt);
            }
        });

        BtnEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Edit.png"))); // NOI18N
        BtnEdit.setText("EDIT");
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Btnsimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnKeluar))
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btnsimpan)
                    .addComponent(BtnEdit)
                    .addComponent(BtnHapus)
                    .addComponent(BtnBatal)
                    .addComponent(jButton1)
                    .addComponent(BtnKeluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTJUMLAHPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTJUMLAHPINJAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTJUMLAHPINJAMActionPerformed

    private void CBKODEPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBKODEPINJAMActionPerformed
        // TODO add your handling code here:
        try {
            Sql="select * from peminjam where Kode_peminjam='"+CBKODEPINJAM.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsPinjam=st.executeQuery(Sql);
            while(RsPinjam.next()){
                txtTanggalPinjam.setText(RsPinjam.getString("Tanggal_pinjam"));
                TXTNAMAANGGOTA.setText(RsPinjam.getString("Nama_anggota"));//sesuaikan di database, atau bisa di ubah menjadi("nama_pelanggan")
                TXTJUMLAHPINJAM.setText(RsPinjam.getString("Jumlah_pinjam"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CBKODEPINJAMActionPerformed

    private void TXTKODEKEMBALIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTKODEKEMBALIKeyPressed
        // TODO add your handling code here:
        Kode_kembali=TXTKODEKEMBALI.getText();
        int A = evt.getKeyCode();
        if (A==10){
            try {
                Sql="SELECT * FROM pengembalian WHERE Kode_pengembalian='"+Kode_kembali+"'";
                st=Con.createStatement();
                Rs=st.executeQuery(Sql);
                while(Rs.next()){
                    CBKODEPINJAM.setSelectedItem(Rs.getString(3));
                    txtTanggalKembali.setText(Rs.getString(2));
                    TXTNAMAANGGOTA.setText(Rs.getString(4));
                    TXTJUMLAHPINJAM.setText(Rs.getString(5));
                    txtTanggalKembali.setText(Rs.getString(6));
                    TXTKETERLAMBATAN.setText(Rs.getString(7));
                    txtdenda.setText(Rs.getString(8));
                    Btnsimpan.enable(false);
                    BtnEdit.enable(true);
                    BtnHapus.enable(true);
                    CBKODEPINJAM.enable(false);
                    TXTNAMAANGGOTA.enable(false);
                    TXTJUMLAHPINJAM.enable(false);
                    txtTanggalKembali.enable(false);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, "Data Tidak Ditemukan\n"+e.getMessage());
                TXTKODEKEMBALI.requestFocus();
            }
        }
    }//GEN-LAST:event_TXTKODEKEMBALIKeyPressed

    private void TXTKETERLAMBATANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTKETERLAMBATANActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTKETERLAMBATANActionPerformed

    private void txtTanggalKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalKembaliActionPerformed
        // TODO add your handling code here:
        int temptahun, tempbulan, temptanggal;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try{

            //Konversi dari string ke tanggal
            java.util.Date tanggalpinjam = df.parse(txtTanggalPinjam.getText());
            java.util.Date tanggalkembali = df.parse(txtTanggalKembali.getText());
            System.out.println("Pinjam : "+tanggalpinjam);
            System.out.println("Sekarang : "+tanggalkembali);
            //Tgl di konversi ke milidetik
            long Hari1 = tanggalpinjam.getTime();
            long Hari2 = tanggalkembali.getTime();
//            int bulan1s = tanggalpinjam.getMonth();
//            int bulan2s = tanggalkembali.getMonth();
//            int tahun1s = tanggalpinjam.getYear();
//            int tahun2s = tanggalkembali.getYear();
//            if(tahun1s == tahun2s) {
//                tempbulan = bulan2s - bulan1s;
//            } else {
//                temptahun = tahun2s - tahun1s;
//                tempbulan = bulan2s- bulan1s;
//                tempbulan += tempbulan + bulan1s;
//            }
//                System.out.println(tempbulan);
            long diff = Hari2 - Hari1;
            
            long Lama = diff / (24 * 60 * 60 * 1000);
//            long harifungsi = TimeUnit.MILLISECONDS.toDays(diff);
            LocalDate tgl1, tgl2;
            tgl1 = LocalDate.of(tanggalpinjam.getYear()+1900, tanggalpinjam.getMonth()+1, tanggalpinjam.getDate());
            tgl2 = LocalDate.of(tanggalkembali.getYear()+1900, tanggalkembali.getMonth()+1, tanggalkembali.getDate());
            long harifungsi = ChronoUnit.DAYS.between(tgl1, tgl2);
            TXTKETERLAMBATAN.setText(Long.toString(harifungsi));
            Keterlambatan=Integer.parseInt(TXTKETERLAMBATAN.getText());
            System.out.println(harifungsi);
            //denda=Integer.parseInt(TxtDenda.getText());
            if((harifungsi>=180)){
                Keterlambatan = (int)(harifungsi - 180);
            } else {
                Keterlambatan=0;
            }
//            if(Keterlambatan >=7){
//                Keterlambatan=Keterlambatan-7;
//            }
            TXTKETERLAMBATAN.setText(String.valueOf(Keterlambatan));
            Keterlambatan=Integer.parseInt(TXTKETERLAMBATAN.getText());
            Hasil=1000*Keterlambatan;
            txtdenda.setText(String.valueOf(Hasil));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }                       
    }//GEN-LAST:event_txtTanggalKembaliActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
           // TODO add your handling code here:
        Kode_kembali=TXTKODEKEMBALI.getText();
        try {
            Sql="delete from pengembalian "
                    + "where Kode_pengembalian='"+Kode_kembali+"'";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            tampilPengembalian();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Data Gagal Di Hapus"+e.getMessage());
        
    }                                        
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnsimpanActionPerformed
        // TODO add your handling code here:
        Kode_kembali=TXTKODEKEMBALI.getText();
        Tanggalkembali=txtTanggalKembali.getText();
        Kode_pinjam=CBKODEPINJAM.getItemAt(CBKODEPINJAM.getSelectedIndex());
        Tanggalpinjam=txtTanggalPinjam.getText();
        Nama_anggota=TXTNAMAANGGOTA.getText();
        Jumlah_pinjam=Integer.parseInt(TXTJUMLAHPINJAM.getText());
        Keterlambatan=Integer.parseInt(TXTKETERLAMBATAN.getText());
        Denda=Integer.parseInt(txtdenda.getText());
        try {
            Sql="INSERT INTO pengembalian"
            + "(Kode_pengembalian,Tanggal_kembali,Kode_pinjam,Tanggal_pinjam,Nama_anggota,Jumlah_pinjam,Keterlambatan,Denda)"
            + "VALUES('"+ Kode_kembali+"',"
            + "'"+Tanggalkembali+"',"
            + "'"+Kode_pinjam+"',"
            + "'"+Tanggalpinjam+"',"
            + "'"+Nama_anggota+"',"
            + "'"+Jumlah_pinjam+"',"
            + "'"+Keterlambatan+"',"
            + "'"+Denda+"')";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            tampilPengembalian();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            Btnsimpan.enable(true);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Gagal Tersimpan\n"+e.getMessage());
        }
    }//GEN-LAST:event_BtnsimpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
           try{
            String file = "src/lappengembalian.jasper";
            JasperPrint jp = JasperFillManager.fillReport(file, null, koneksi());
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
        }catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TXTKODEKEMBALIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTKODEKEMBALIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTKODEKEMBALIActionPerformed

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
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPengembalian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnEdit;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton Btnsimpan;
    private javax.swing.JComboBox<String> CBKODEPINJAM;
    private javax.swing.JTable TPENGEMBALIAN;
    private javax.swing.JTextField TXTJUMLAHPINJAM;
    private javax.swing.JTextField TXTKETERLAMBATAN;
    private javax.swing.JTextField TXTKODEKEMBALI;
    private javax.swing.JTextField TXTNAMAANGGOTA;
    private javax.swing.JTable TdetailPengembalian;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    private javax.swing.JLabel txtdenda;
    // End of variables declaration//GEN-END:variables
}
