
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
public class DataPeminjam extends javax.swing.JFrame {
private Statement st;
private Connection Con = new koneksi().connect();
private ResultSet RsBuku;
private ResultSet RsAnggota;
private ResultSet Rs;
private ResultSet RsPetugas;
private ResultSet RsJual;
private ResultSet RsDetail;
private ResultSet RsPeminjaman;
public String Kode_pinjam,Kode_petugas,Nama_petugas,Kode_anggota,Nama_anggota;
public int jumlahpinjam;
private String Tanggal="";
private String Sql="";
    /**
     * Creates new form DataPeminjam
     */
    public DataPeminjam() {
        initComponents();
        tampilpeminjaman();
        PilihPetugas();
        PilihBuku();
        PilihAnggota();
        Waktu();
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
    private void PilihPetugas(){
        CBPETUGAS.removeAllItems();
        CBPETUGAS.addItem("Select");
        try {
            String Sql ="SELECT*FROM petugas";
            Statement st= Con.createStatement();
            RsPetugas= st.executeQuery(Sql);
            while(RsPetugas.next()){
            String AliasKode= RsPetugas.getString("Kode_petugas");
            CBPETUGAS.addItem(AliasKode);
        }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Kode Peminjam\n"+e.getMessage());
}
  }
     private void PilihBuku(){
        CBBUKU.removeAllItems();
        CBBUKU.addItem("Select");
        try {
            String Sql ="SELECT*FROM buku";
            Statement st= Con.createStatement();
            RsBuku= st.executeQuery(Sql);
            while(RsBuku.next()){
            String AliasKode= RsBuku.getString("Kode_buku");
            CBBUKU.addItem(AliasKode);
        }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Kode Buku\n"+e.getMessage());
}
     }
     private void PilihAnggota(){
        CBANGGOTA.removeAllItems();
        CBANGGOTA.addItem("Select");
        try {
            String Sql ="SELECT*FROM anggota";
            Statement st= Con.createStatement();
            RsAnggota= st.executeQuery(Sql);
            while(RsAnggota.next()){
            String AliasKode= RsAnggota.getString("Kode_anggota");
            CBANGGOTA.addItem(AliasKode);
        }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Id Angota\n"+e.getMessage());
}
     }
    private void prosestambah(){
        try {
            DefaultTableModel tableModel =(DefaultTableModel)TPINJAM.getModel();
            String[]data = new String[4];
            data[0]= String.valueOf(CBBUKU.getSelectedItem());
            data[1]= TXTJUDULBUKU.getText();
            data[2]= TXTPENGARANG.getText();
            data[3]= TXTJUMLAHPINJAM.getText();
            tableModel.addRow(data);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error masukkan data \n"+e.getMessage());
        }
    }    
     private void Total(){
      int jumlahBaris = TPINJAM.getRowCount();
        int ttlpinjam = 0, jlhpinjam=0;
        int Jlhtotalpinjam;
        
        TableModel tblmodel;
        tblmodel = TPINJAM.getModel();
        for (int i=0; i<jumlahBaris; i++){
            Jlhtotalpinjam=Integer.parseInt(tblmodel.getValueAt(i, 3).toString());
                jlhpinjam=Jlhtotalpinjam+jlhpinjam;
                }
        TXTTOTALPINJAM.setText(String.valueOf(jlhpinjam));
     }
     public void Waktu(){
        Date tgl=new Date();
        JTANGGAL.setDate(tgl);
     }
      private void simpandetail(){
        int jumlah_baris=TPINJAM.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table is empty!!");
        } else {
            try {
                int i=0;
                while(i<jumlah_baris){
                    st.executeUpdate("insert into detailpinjam"
                            + "(Kode_pinjam,Kode_buku,Judul_buku,Pengarang,Jumlah_pinjam)"
                            + "values('"+TXTKODEPINJAM.getText()+"',"
                            + "'"+TPINJAM.getValueAt(i,0)+"',"
                            + "'"+TXTJUDULBUKU.getText()+"',"
                            + "'"+TXTPENGARANG.getText()+"',"
                            + "'"+TXTJUMLAHPINJAM.getText()+"')");
                    i++;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan..!!"+e);
            }
        }
    }
        
  private void tampilpeminjaman(){
        DefaultTableModel grid=new DefaultTableModel();
        grid.addColumn("No");
        grid.addColumn("Kode_peminjam");
        grid.addColumn("Tanggal_pinjam");
        grid.addColumn("Kode_petugas");
        grid.addColumn("Nama_petugas");
        grid.addColumn("Kode_anggota");
        grid.addColumn("Nama_anggota");
        grid.addColumn("Jumlah_pinjam");
     
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
               TPINJAM1.setModel(grid);
               TPINJAM1.enable(false);
               BTNSIMPAN.requestFocus();
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Tampil"+e);
        }
    }    
  
  private void kosongkan(){
        TXTKODEPINJAM.setText("");
        JTANGGAL.setDate(null);
        CBANGGOTA.setSelectedItem(null);
        TXTNAMAANGGOTA.setText("");
        TXTALAMAT.setText("");
        TXTNOHP.setText("");
        CBPETUGAS.setSelectedItem(null);
        TXTNAMAPETUGAS.setText("");
        TXTJABATAN.setText("");
        CBBUKU.setSelectedItem(null);
        TXTJUDULBUKU.setText("");
        TXTPENGARANG.setText("");
        TXTJUMLAHPINJAM.setText("");
  }
  private void hapustable() {
    DefaultTableModel model = (DefaultTableModel)TPINJAM.getModel();

    while (model.getRowCount() > 0){
        for (int i = 0; i < model.getRowCount(); ++i){
            model.removeRow(i);
        }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TXTKODEPINJAM = new javax.swing.JTextField();
        JTANGGAL = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TXTNAMAANGGOTA = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CBANGGOTA = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TXTALAMAT = new javax.swing.JTextField();
        TXTNOHP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TPINJAM = new javax.swing.JTable();
        BTNSIMPAN = new javax.swing.JButton();
        BTNEDIT = new javax.swing.JButton();
        BTNHAPUS = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        CBPETUGAS = new javax.swing.JComboBox<>();
        TXTNAMAPETUGAS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TXTJABATAN = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TXTJUDULBUKU = new javax.swing.JTextField();
        CBBUKU = new javax.swing.JComboBox<>();
        TXTPENGARANG = new javax.swing.JTextField();
        TXTJUMLAHPINJAM = new javax.swing.JTextField();
        BTNTAMBAH = new javax.swing.JButton();
        BtnKurang = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TPINJAM1 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        TXTTOTALPINJAM = new javax.swing.JTextField();
        btncetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/peminjam1.png"))); // NOI18N
        jLabel7.setText("DATA PEMINJAMAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("KODE PINJAM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("TANGGAL PINJAM");

        TXTKODEPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTKODEPINJAMActionPerformed(evt);
            }
        });

        JTANGGAL.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JTANGGALPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TXTKODEPINJAM, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(JTANGGAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TXTKODEPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(JTANGGAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA ANGGOTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("KODE ANGGOTA");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("NAMA ANGGOTA");

        CBANGGOTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CBANGGOTA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Item 2", "Item 3", "Item 4" }));
        CBANGGOTA.setToolTipText("");
        CBANGGOTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBANGGOTAActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("ALAMAT");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("NO.HP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTNOHP)
                            .addComponent(TXTALAMAT, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBANGGOTA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CBANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(TXTALAMAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TXTNOHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        TPINJAM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Buku", "Judul Buku", "Pengarang", "Jumlah Pinjam"
            }
        ));
        TPINJAM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TPINJAMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TPINJAM);

        BTNSIMPAN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BTNSIMPAN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button simpan.png"))); // NOI18N
        BTNSIMPAN.setText("SIMPAN");
        BTNSIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSIMPANActionPerformed(evt);
            }
        });

        BTNEDIT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BTNEDIT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Edit.png"))); // NOI18N
        BTNEDIT.setText("EDIT");
        BTNEDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEDITActionPerformed(evt);
            }
        });

        BTNHAPUS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BTNHAPUS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Hapus_1.png"))); // NOI18N
        BTNHAPUS.setText("HAPUS");
        BTNHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNHAPUSActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button batal.png"))); // NOI18N
        jButton4.setText("BATAL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Keluar.png"))); // NOI18N
        jButton5.setText("KELUAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 51, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT PETUGAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        CBPETUGAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CBPETUGAS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Item 2", "Item 3", "Item 4" }));
        CBPETUGAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBPETUGASActionPerformed(evt);
            }
        });

        TXTNAMAPETUGAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTNAMAPETUGASActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("KODE PETUGAS");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("NAMA PETUGAS");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("JABATAN ");

        TXTJABATAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTJABATANActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CBPETUGAS, 0, 116, Short.MAX_VALUE)
                    .addComponent(TXTNAMAPETUGAS, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(TXTJABATAN))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBPETUGAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTNAMAPETUGAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TXTJABATAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 51, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA BUKU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("KODE BUKU");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("JUDUL BUKU");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("PENGARANG");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("JUMLAH PINJAM");

        CBBUKU.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CBBUKU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Item 2", "Item 3", "Item 4" }));
        CBBUKU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBBUKUActionPerformed(evt);
            }
        });

        BTNTAMBAH.setText("+");
        BTNTAMBAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNTAMBAHActionPerformed(evt);
            }
        });

        BtnKurang.setText("-");
        BtnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKurangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CBBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(BTNTAMBAH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnKurang, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TXTJUDULBUKU, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TXTPENGARANG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(CBBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TXTJUDULBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TXTPENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNTAMBAH)
                    .addComponent(BtnKurang)))
        );

        TPINJAM1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Peminjam", "Tanggal Pinjam", "Kode Petugas", "Nama Petugas", "Kode Anggota", "Nama Anggota", "Jumlah Pinjam"
            }
        ));
        jScrollPane2.setViewportView(TPINJAM1);
        if (TPINJAM1.getColumnModel().getColumnCount() > 0) {
            TPINJAM1.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel15.setText("TOTAL PINJAM");

        btncetak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button Cetak.png"))); // NOI18N
        btncetak.setText("CETAK");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(41, 41, 41)
                                .addComponent(TXTTOTALPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTNSIMPAN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNEDIT)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(BTNHAPUS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncetak)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)))
                        .addContainerGap(112, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(TXTTOTALPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTNSIMPAN)
                            .addComponent(BTNEDIT)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTNHAPUS)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btncetak)
                                .addComponent(jButton5))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TXTJABATANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTJABATANActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTJABATANActionPerformed

    private void CBPETUGASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBPETUGASActionPerformed
        // TODO add your handling code here:
          try {
            Sql="select * from petugas where Kode_petugas='"+CBPETUGAS.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsPetugas=st.executeQuery(Sql);
            while(RsPetugas.next()){
                TXTNAMAPETUGAS.setText(RsPetugas.getString("Nama_petugas"));//sesuaikan di database
                TXTJABATAN.setText(RsPetugas.getString("Jabatan"));
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_CBPETUGASActionPerformed

    private void CBBUKUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBBUKUActionPerformed
        // TODO add your handling code here:
         try {
            Sql="select * from buku where Kode_buku='"+CBBUKU.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsBuku=st.executeQuery(Sql);
            while(RsBuku.next()){
                TXTJUDULBUKU.setText(RsBuku.getString("Judul_buku"));//sesuaikan di database, atau bisa di ubah menjadi("nama_pelanggan")
                TXTPENGARANG.setText(RsBuku.getString("Pengarang"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CBBUKUActionPerformed

    private void CBANGGOTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBANGGOTAActionPerformed
        // TODO add your handling code here:
            try {
            Sql="select * from anggota where Kode_anggota='"+CBANGGOTA.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsAnggota=st.executeQuery(Sql);
            while(RsAnggota.next()){
                TXTNAMAANGGOTA.setText(RsAnggota.getString("Nama_anggota"));//sesuaikan di database, atau bisa di ubah menjadi("nama_pelanggan")
                TXTALAMAT.setText(RsAnggota.getString("Alamat"));
                TXTNOHP.setText(RsAnggota.getString("No_hp"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CBANGGOTAActionPerformed

    private void BTNTAMBAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNTAMBAHActionPerformed
        // TODO add your handling code here:
        prosestambah();
        Total();
    }//GEN-LAST:event_BTNTAMBAHActionPerformed

    private void BtnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKurangActionPerformed
        // TODO add your handling code here:
              DefaultTableModel model = (DefaultTableModel)TPINJAM.getModel();
        int row = TPINJAM.getSelectedRow();
            if (row>=0) {
                int ok = JOptionPane.showConfirmDialog(null, "You sure you want to Delete","Message",JOptionPane.YES_NO_OPTION);
            
                if (ok==0){
                    model.removeRow(row);
                }
                }
            Total();
    }//GEN-LAST:event_BtnKurangActionPerformed

    private void BTNSIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSIMPANActionPerformed
        // TODO add your handling code here:
        Kode_pinjam=TXTKODEPINJAM.getText();
        Kode_petugas=CBPETUGAS.getItemAt(CBPETUGAS.getSelectedIndex()).toString();
        Nama_petugas=TXTNAMAPETUGAS.getText();
        Kode_anggota=CBANGGOTA.getItemAt(CBANGGOTA.getSelectedIndex()).toString();
        Nama_anggota=TXTNAMAANGGOTA.getText();
        jumlahpinjam=Integer.parseInt(TXTTOTALPINJAM.getText());
        simpandetail();
        try {
            Sql="insert into peminjam"
                    +"(Kode_peminjam,Tanggal_pinjam,Kode_petugas,Nama_petugas,Kode_anggota,Nama_anggota,Jumlah_pinjam)"
                    +"values('"+Kode_pinjam+"',"
                    + "'"+Tanggal+"',"
                    + "'"+Kode_petugas+"',"
                    + "'"+Nama_petugas+"',"
                    + "'"+Kode_anggota+"',"
                    + "'"+Nama_anggota+"',"
                    + "'"+jumlahpinjam+"')";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            
            tampilpeminjaman();
            JOptionPane.showMessageDialog(null, "Data successfully saved");
            hapustable();
            BTNTAMBAH.show();
            BTNSIMPAN.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data is not successfully saved, Data that you entered is incorrect"+e.getMessage());
        }
    }//GEN-LAST:event_BTNSIMPANActionPerformed

    private void JTANGGALPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JTANGGALPropertyChange
        // TODO add your handling code here:
        if(JTANGGAL.getDate()!=null){
            SimpleDateFormat format =new SimpleDateFormat ("yyyy-MM-dd");
            Tanggal=format.format(JTANGGAL.getDate());
        }
    }//GEN-LAST:event_JTANGGALPropertyChange

    private void BTNHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNHAPUSActionPerformed
        // TODO add your handling code here:
        Kode_pinjam=TXTKODEPINJAM.getText();
        try {
            Sql="delete from peminjam "
                    + "where Kode_peminjam='"+Kode_pinjam+"'";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            tampilpeminjaman();
            JOptionPane.showMessageDialog(null, "Data has been Removed");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Data is not deleted !!"+e.getMessage());
        
    }                                        

    }//GEN-LAST:event_BTNHAPUSActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       new MenuUtama().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void TXTKODEPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTKODEPINJAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTKODEPINJAMActionPerformed

    private void TXTNAMAPETUGASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTNAMAPETUGASActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTNAMAPETUGASActionPerformed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
           try{
            String file = "src/laporan/lappeminjaman.jasper";
            JasperPrint jp = JasperFillManager.fillReport(file, null, koneksi());
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
        }catch (Exception e) {
        }
    }//GEN-LAST:event_btncetakActionPerformed

    private void BTNEDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEDITActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNEDITActionPerformed

    private void TPINJAMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TPINJAMMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TPINJAMMouseClicked

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
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPeminjam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNEDIT;
    private javax.swing.JButton BTNHAPUS;
    private javax.swing.JButton BTNSIMPAN;
    private javax.swing.JButton BTNTAMBAH;
    private javax.swing.JButton BtnKurang;
    private javax.swing.JComboBox<String> CBANGGOTA;
    private javax.swing.JComboBox<String> CBBUKU;
    private javax.swing.JComboBox<String> CBPETUGAS;
    private com.toedter.calendar.JDateChooser JTANGGAL;
    private javax.swing.JTable TPINJAM;
    private javax.swing.JTable TPINJAM1;
    private javax.swing.JTextField TXTALAMAT;
    private javax.swing.JTextField TXTJABATAN;
    private javax.swing.JTextField TXTJUDULBUKU;
    private javax.swing.JTextField TXTJUMLAHPINJAM;
    private javax.swing.JTextField TXTKODEPINJAM;
    private javax.swing.JTextField TXTNAMAANGGOTA;
    private javax.swing.JTextField TXTNAMAPETUGAS;
    private javax.swing.JTextField TXTNOHP;
    private javax.swing.JTextField TXTPENGARANG;
    private javax.swing.JTextField TXTTOTALPINJAM;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
