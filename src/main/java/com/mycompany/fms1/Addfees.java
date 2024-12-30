/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fms1;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author KRISH GUPTA
 */
public class Addfees extends javax.swing.JFrame {

    /**
     * Creates new form Addfees
     */
//    public void displayCaShFirst() {
//       if (lbl_cheque_num != null) lbl_cheque_num.setVisible(false);
//       if (txt_cheque_num != null) txt_cheque_num.setVisible(false);
//        if (lbl_dd_num != null) lbl_dd_num.setVisible(false);
//        if (txt_dd_num != null) txt_dd_num.setVisible(false);
//        if (lbl_bank_name != null) lbl_bank_name.setVisible(false);
//        if (txt_bank_name != null) txt_bank_name.setVisible(false);
//       if (lbl_rec_name != null) lbl_rec_name.setVisible(true);
//        if (txt_rec_name != null) txt_rec_name.setVisible(true);
//
//    }
    public String insertData() {
        int receipt_no = Integer.parseInt(txt_receipt_num.getText());
        String sname = txt_rec_name.getText();
        String rollno = txt_roll_num.getText();
        String paymentmode = combo_mode_payment.getSelectedItem().toString();
        System.out.println("cheque no  "+txt_cheque_num.getText());
        
        //checking th e check no
        int chequeno = 0; // Default value if not applicable
        if (!txt_cheque_num.getText().trim().isEmpty()) {
            try {
                chequeno = Integer.parseInt(txt_cheque_num.getText().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid cheque number: " + txt_cheque_num.getText());
                chequeno = 0; // Set default or handle error appropriately
            }
        }
//        int chequeno = Integer.parseInt(txt_cheque_num.getText());
        String bankname = txt_bank_name.getText();
        
        
        System.out.println("cheque no  "+txt_bank_name.getText());

        String ddno = txt_dd_num.getText();
        String coursename = course_combo.getSelectedItem().toString();
        String gst = jLabel4.getText();
        float total = Float.parseFloat(total_num.getText());
        String date = date_c.getText();
        float amount = Float.parseFloat(txt_amount.getText());
        float cgst = Float.parseFloat(cgst_num.getText());
        float sgst = Float.parseFloat(sgst_num.getText());
        String totalinwords = ttxt_total_in_word.getText();
        String remark = jTextArea1.getText();
        int year1 = Integer.parseInt(from_year.getText());
        int to = Integer.parseInt(to_num.getText());
        String status = " ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fmsdatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "root");
            String sql = "insert into fees_details(receipt_no,student_name,roll_no,payment_mode,cheque_no,bank_name,dd_no"
                    + ",course_name ,gstin, total_amount, date ,amount, cgst, sgst ,total_in_words ,remark ,year1 ,year2) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, receipt_no);
            st.setString(2, sname);
            st.setString(3, rollno);
            st.setString(4, paymentmode);
            st.setInt(5, chequeno);
            st.setString(6, bankname);
            st.setString(7, ddno);
            st.setString(8, coursename);
            st.setString(9, gst);
            st.setFloat(10, total);
            st.setString(11, date);
            st.setFloat(12, amount);
            st.setFloat(13, cgst);
            st.setFloat(14, sgst);
            st.setString(15, totalinwords);
            st.setString(16, remark);
            st.setInt(17, year1);
            st.setInt(18, to);

            int c = st.executeUpdate();

            if (c == 1) {
                status = "success";
            } else {
                status = "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }

    public Addfees() {

//        displayCaShFirst();
        initComponents();
        feelComboBox();
        int r = getRno();
        r++;
        txt_receipt_num.setText(Integer.toString(r));
    }

    public class NumberToWordsConverter {

        public static final String[] units = {"", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};

        public static final String[] tens = {
            "", // 0
            "", // 1
            "Twenty", // 2
            "Thirty", // 3
            "Forty", // 4
            "Fifty", // 5
            "Sixty", // 6
            "Seventy", // 7
            "Eighty", // 8
            "Ninety" // 9
        };

        public static String convert(final int n) {
            if (n < 0) {
                return "Minus " + convert(-n);
            }

            if (n < 20) {
                return units[n];
            }

            if (n < 100) {
                return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
            }

            if (n < 1000) {
                return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
            }

            if (n < 100000) {
                return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
            }

            if (n < 10000000) {
                return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
            }

            return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
        }

        public static void main(final String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Amount : ");
            int n = sc.nextInt();

            System.out.println(convert(n) + " Only");

        }
    }

    boolean validation() {
        if (txt_rec_name.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Receiver Name ");
            return false;
        }

        if (date_c.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Date ");
            return false;
        }

        if (txt_amount.getText().equals("") || txt_amount.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount (in numbers) ");
            return false;
        }
        if (combo_mode_payment.getSelectedItem().toString().equalsIgnoreCase("cheque")) {
            if (txt_cheque_num.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Cheque No. ");
                return false;
            }
            if (txt_bank_name.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name ");
                return false;
            }
        }
        if (combo_mode_payment.getSelectedItem().toString().equalsIgnoreCase("dd")) {
            if (txt_dd_num.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter DD No. ");
                return false;
            }
            if (txt_bank_name.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name ");
                return false;
            }

        }

        return true;
    }

    public void feelComboBox() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fmsdatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "root");
            String sql = "select cname from course ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                course_combo.addItem(rs.getString("cname"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    public int getRno() {
        int rno = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/fmsdatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con = DriverManager.getConnection(url, "root", "root");
            PreparedStatement st = con.prepareStatement("select max(receipt_no) from fees_details ");
            ResultSet rs = st.executeQuery("select max(receipt_no) from fees_details ");

            if (rs.next() == true) {
                rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return rno;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbl_receipt_num = new javax.swing.JPanel();
        receipt_num = new javax.swing.JLabel();
        lbl_mode_payment = new javax.swing.JLabel();
        lbl_cheque_num = new javax.swing.JLabel();
        combo_mode_payment = new javax.swing.JComboBox<>();
        lbl_dd_num = new javax.swing.JLabel();
        txt_receipt_num = new javax.swing.JTextField();
        txt_dd_num = new javax.swing.JTextField();
        txt_bank_name = new javax.swing.JTextField();
        lbl_date = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbl_rec_name = new javax.swing.JLabel();
        txt_rec_name = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField9 = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        cgst_num = new javax.swing.JTextField();
        sgst_num = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        total_num = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ttxt_total_in_word = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txt_roll_num = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        to_num = new javax.swing.JTextField();
        from_year = new javax.swing.JTextField();
        txt_cheque_num = new javax.swing.JTextField();
        txt_date = new javax.swing.JLabel();
        txt_gstin = new javax.swing.JLabel();
        date_c = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbl_mode_payment1 = new javax.swing.JLabel();
        lbl_mode_payment2 = new javax.swing.JLabel();
        lbl_bank_name = new javax.swing.JLabel();
        txt_date1 = new javax.swing.JLabel();
        txt_date2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        combo_mode_payment1 = new javax.swing.JComboBox<>();
        combo_mode_payment2 = new javax.swing.JComboBox<>();
        combo_mode_payment3 = new javax.swing.JComboBox<>();
        combo_mode_payment4 = new javax.swing.JComboBox<>();
        combo_mode_payment5 = new javax.swing.JComboBox<>();
        course_combo = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        lbl_receipt_num.setBackground(new java.awt.Color(204, 204, 255));
        lbl_receipt_num.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        receipt_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        receipt_num.setText("Receipt No");
        lbl_receipt_num.add(receipt_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 52, 83, -1));

        lbl_mode_payment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_mode_payment.setText("Mode of Payment");
        lbl_receipt_num.add(lbl_mode_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 124, -1));

        lbl_cheque_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_cheque_num.setText("Cheque No");
        lbl_receipt_num.add(lbl_cheque_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 83, -1));

        combo_mode_payment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_mode_payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PHONEPAY", "CHEQUE" }));
        combo_mode_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_paymentActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(combo_mode_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 93, 91, -1));

        lbl_dd_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_dd_num.setText("DD no");
        lbl_receipt_num.add(lbl_dd_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 83, -1));

        txt_receipt_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_receipt_num.add(txt_receipt_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 49, 90, -1));

        txt_dd_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_dd_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dd_numActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(txt_dd_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 92, -1));

        txt_bank_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_receipt_num.add(txt_bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 202, 92, -1));

        lbl_date.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_date.setText("Date");
        lbl_receipt_num.add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(838, 21, 53, -1));
        lbl_receipt_num.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 21, 129, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("GSTIN");
        lbl_receipt_num.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(838, 52, 53, -1));
        lbl_receipt_num.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 52, 129, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        lbl_rec_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_rec_name.setText("Receiver Name");

        txt_rec_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rec_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rec_nameActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Roll no");

        jSeparator1.setForeground(new java.awt.Color(51, 0, 102));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Sr No.");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("head");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Amount");

        jSeparator2.setForeground(new java.awt.Color(51, 0, 102));

        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });

        jLabel15.setText("CGST 7%");

        jLabel16.setText("SGST 7%");

        jSeparator3.setForeground(new java.awt.Color(51, 0, 102));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Total");

        total_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_numActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Total in Words");

        ttxt_total_in_word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttxt_total_in_wordActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Remark");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Receiver signature");

        jSeparator4.setForeground(new java.awt.Color(51, 0, 102));

        jButton9.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton9.setText("SUBMIT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Roll No");

        txt_roll_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_roll_numActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("From Year");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("to");

        to_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_numActionPerformed(evt);
            }
        });

        from_year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                from_yearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(183, 183, 183)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lbl_rec_name)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_rec_name, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(378, 378, 378))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(416, 416, 416)
                                .addComponent(jLabel25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_roll_num, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(from_year, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(to_num, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(601, 601, 601)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cgst_num, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sgst_num, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(ttxt_total_in_word, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(total_num, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_rec_name)
                    .addComponent(txt_rec_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txt_roll_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(from_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(to_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cgst_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sgst_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(total_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ttxt_total_in_word, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );

        lbl_receipt_num.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        txt_cheque_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_receipt_num.add(txt_cheque_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 92, -1));

        txt_date.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_date.setText("Date:");
        lbl_receipt_num.add(txt_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 48, 56, -1));

        txt_gstin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_gstin.setText("GSTIN");
        lbl_receipt_num.add(txt_gstin, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 87, 65, -1));

        date_c.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        date_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_cActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(date_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(623, 45, 120, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("MCS6489BG0ZIQD");
        lbl_receipt_num.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 87, -1, -1));

        lbl_mode_payment1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_mode_payment1.setText("Mode of Payment");
        lbl_receipt_num.add(lbl_mode_payment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 124, -1));

        lbl_mode_payment2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_mode_payment2.setText("Mode of Payment");
        lbl_receipt_num.add(lbl_mode_payment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 124, -1));

        lbl_bank_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bank_name.setText("Bank Name");
        lbl_receipt_num.add(lbl_bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 83, -1));

        txt_date1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_date1.setText("Date:");
        lbl_receipt_num.add(txt_date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 48, 56, -1));

        txt_date2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_date2.setText("Date:");
        lbl_receipt_num.add(txt_date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 48, 56, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Course");
        lbl_receipt_num.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, -1, -1));

        combo_mode_payment1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_mode_payment1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PHONEPAY", "CHEQUE" }));
        combo_mode_payment1.setSelectedIndex(1);
        combo_mode_payment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_payment1ActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(combo_mode_payment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 93, 91, -1));

        combo_mode_payment2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_mode_payment2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PHONEPAY", "CHEQUE" }));
        combo_mode_payment2.setSelectedIndex(1);
        combo_mode_payment2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_payment2ActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(combo_mode_payment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 93, 91, -1));

        combo_mode_payment3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_mode_payment3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PHONEPAY", "CHEQUE" }));
        combo_mode_payment3.setSelectedIndex(1);
        combo_mode_payment3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_payment3ActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(combo_mode_payment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 93, 91, -1));

        combo_mode_payment4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_mode_payment4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PHONEPAY", "CHEQUE" }));
        combo_mode_payment4.setSelectedIndex(1);
        combo_mode_payment4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_payment4ActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(combo_mode_payment4, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 93, 91, -1));

        combo_mode_payment5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_mode_payment5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PHONEPAY", "CHEQUE" }));
        combo_mode_payment5.setSelectedIndex(1);
        combo_mode_payment5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_payment5ActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(combo_mode_payment5, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 93, 91, -1));

        course_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                course_comboActionPerformed(evt);
            }
        });
        lbl_receipt_num.add(course_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Course");
        lbl_receipt_num.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Course");
        lbl_receipt_num.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 204));
        jButton2.setText("HOME");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 204));
        jButton3.setText("SEARCH RECORD");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 204));
        jButton4.setText("EDIT COURSE");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 204));
        jButton5.setText("COURSE LIST");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 204));
        jButton6.setText("VIEW ALL RECORD");

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 204));
        jButton7.setText("BACK");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 204));
        jButton8.setText("LOGOUT");

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 204));
        jButton10.setText("ADD COURSE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_receipt_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addGap(13, 13, 13)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbl_receipt_num, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_mode_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_paymentActionPerformed
        if (combo_mode_payment.getSelectedIndex() == 0) {
            lbl_dd_num.setVisible(true);
            txt_dd_num.setVisible(true);
            lbl_bank_name.setVisible(true);
            txt_bank_name.setVisible(true);
            lbl_rec_name.setVisible(true);
            txt_rec_name.setVisible(true);

            lbl_cheque_num.setVisible(false);
            txt_cheque_num.setVisible(false);
        }
        if (combo_mode_payment.getSelectedIndex() == 1) {

            lbl_rec_name.setVisible(true);
            txt_rec_name.setVisible(true);
            lbl_cheque_num.setVisible(false);
            txt_cheque_num.setVisible(false);
            lbl_dd_num.setVisible(false);
            txt_dd_num.setVisible(false);
            lbl_bank_name.setVisible(false);
            txt_bank_name.setVisible(false);
        }
        if (combo_mode_payment.getSelectedIndex() == 3) {

            lbl_rec_name.setVisible(true);
            txt_rec_name.setVisible(true);
            lbl_cheque_num.setVisible(true);
            txt_cheque_num.setVisible(true);
            lbl_dd_num.setVisible(false);
            txt_dd_num.setVisible(false);
            lbl_bank_name.setVisible(true);
            txt_bank_name.setVisible(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_paymentActionPerformed

    private void txt_rec_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rec_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rec_nameActionPerformed

    private void total_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_numActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (validation() == true) {
            String s = insertData();
            if (s.equals("success")) {
                JOptionPane.showMessageDialog(this, "Record Inserted Successfully ");
            } else {
                JOptionPane.showMessageDialog(this, "Record  Not Inserted  ");
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void date_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date_cActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        String s1 = txt_amount.getText();
        float amt = Float.parseFloat(s1);

        float cgst = amt * 0.07f;
        float sgst = amt * 0.07f;

        cgst_num.setText(Float.toString(cgst));
        sgst_num.setText(Float.toString(sgst));
        float t = amt + cgst + sgst;
        total_num.setText(Float.toString(t));

        ttxt_total_in_word.setText(NumberToWordsConverter.convert((int) t));

// TODO add your handling code here:
    }//GEN-LAST:event_txt_amountActionPerformed

    private void combo_mode_payment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_payment1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_payment1ActionPerformed

    private void combo_mode_payment2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_payment2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_payment2ActionPerformed

    private void combo_mode_payment3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_payment3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_payment3ActionPerformed

    private void combo_mode_payment4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_payment4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_payment4ActionPerformed

    private void combo_mode_payment5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_payment5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_payment5ActionPerformed

    private void ttxt_total_in_wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttxt_total_in_wordActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_ttxt_total_in_wordActionPerformed

    private void course_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_comboActionPerformed

        String s1 = course_combo.getSelectedItem().toString();
        jTextField9.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_course_comboActionPerformed

    private void txt_roll_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_roll_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_roll_numActionPerformed

    private void txt_dd_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dd_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dd_numActionPerformed

    private void to_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_to_numActionPerformed

    private void from_yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_from_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_from_yearActionPerformed

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
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Addfees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cgst_num;
    private javax.swing.JComboBox<String> combo_mode_payment;
    private javax.swing.JComboBox<String> combo_mode_payment1;
    private javax.swing.JComboBox<String> combo_mode_payment2;
    private javax.swing.JComboBox<String> combo_mode_payment3;
    private javax.swing.JComboBox<String> combo_mode_payment4;
    private javax.swing.JComboBox<String> combo_mode_payment5;
    private javax.swing.JComboBox<String> course_combo;
    private javax.swing.JTextField date_c;
    private javax.swing.JTextField from_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbl_bank_name;
    private javax.swing.JLabel lbl_cheque_num;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_dd_num;
    private javax.swing.JLabel lbl_mode_payment;
    private javax.swing.JLabel lbl_mode_payment1;
    private javax.swing.JLabel lbl_mode_payment2;
    private javax.swing.JLabel lbl_rec_name;
    private javax.swing.JPanel lbl_receipt_num;
    private javax.swing.JLabel receipt_num;
    private javax.swing.JTextField sgst_num;
    private javax.swing.JTextField to_num;
    private javax.swing.JTextField total_num;
    private javax.swing.JTextField ttxt_total_in_word;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bank_name;
    private javax.swing.JTextField txt_cheque_num;
    private javax.swing.JLabel txt_date;
    private javax.swing.JLabel txt_date1;
    private javax.swing.JLabel txt_date2;
    private javax.swing.JTextField txt_dd_num;
    private javax.swing.JLabel txt_gstin;
    private javax.swing.JTextField txt_rec_name;
    private javax.swing.JTextField txt_receipt_num;
    private javax.swing.JTextField txt_roll_num;
    // End of variables declaration//GEN-END:variables
}
