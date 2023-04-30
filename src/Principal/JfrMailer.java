package Principal;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dvchinx_
 */
public class JfrMailer extends javax.swing.JFrame {

    private static String emailFrom;
    private static String passwordFrom;
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    private BodyPart adjunto;
    private BodyPart texto;

    private JFileChooser fc = new JFileChooser();
    private String rutaFile;
    private String nameFile;
    private Boolean Anexo = false;

    private int xMouse, yMouse;

    public JfrMailer() {
        initComponents();
        mProperties = new Properties();
        txtEmail.setText("davidflorez0111@gmail.com");
        txtPass.setText("vogawbawfjzhzufh");
    }

    private void createEmail() throws MessagingException {

        emailFrom = txtEmail.getText().trim();
        passwordFrom = new String(txtPass.getPassword());

        emailTo = txtEmailTo.getText().trim();
        subject = txtAsunto.getText().trim();
        content = txtContent.getText().trim();

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        if (Anexo) {
            texto = new MimeBodyPart();
            texto.setText(content);

            adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaFile)));
            adjunto.setFileName(nameFile);

            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            m.addBodyPart(adjunto);

            try {
                mCorreo = new MimeMessage(mSession);
                mCorreo.setFrom(new InternetAddress(emailFrom));
                mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                mCorreo.setSubject(subject);
                mCorreo.setContent(m);

            } catch (AddressException ex) {
                Logger.getLogger(JfrMailer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(JfrMailer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                mCorreo = new MimeMessage(mSession);
                mCorreo.setFrom(new InternetAddress(emailFrom));
                mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                mCorreo.setSubject(subject);
                mCorreo.setText(content, "ISO-8859-1", "html");

            } catch (AddressException ex) {
                Logger.getLogger(JfrMailer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(JfrMailer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JfrMailer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JfrMailer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void GetRouteFile() {
        int seleccion = fc.showOpenDialog(pnlMain);
        //Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Seleccionamos el fichero
            File fichero = fc.getSelectedFile();
            //Ecribe la ruta del fichero seleccionado en el campo de texto
            rutaFile = fichero.getAbsolutePath();
            nameFile = fichero.getName();

            System.out.println("rutaFile = " + rutaFile);
            System.out.println("nameFile = " + nameFile);

            Anexo = true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        pnlTop = new javax.swing.JPanel();
        lblBtnClose = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        lblBtnClose1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtEmailTo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        btnAnexar = new javax.swing.JButton();
        btnSendEmail = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEmail.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email:");
        pnlMain.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        txtEmail.setText("Email");
        txtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEmailMouseClicked(evt);
            }
        });
        pnlMain.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 200, -1));

        lblPass.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblPass.setForeground(new java.awt.Color(0, 0, 0));
        lblPass.setText("Pass:");
        pnlMain.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        txtPass.setText("jPasswordField1");
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        pnlMain.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 200, -1));

        pnlTop.setBackground(new java.awt.Color(211, 241, 248));
        pnlTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTopMouseDragged(evt);
            }
        });
        pnlTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTopMousePressed(evt);
            }
        });
        pnlTop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBtnClose.setFont(new java.awt.Font("Corbel", 0, 21)); // NOI18N
        lblBtnClose.setForeground(new java.awt.Color(0, 0, 0));
        lblBtnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBtnClose.setText("X");
        lblBtnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBtnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtnCloseMouseClicked(evt);
            }
        });
        pnlTop.add(lblBtnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 40, 40));

        lblTop.setBackground(new java.awt.Color(0, 0, 0));
        lblTop.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblTop.setForeground(new java.awt.Color(0, 0, 0));
        lblTop.setText("Mailer");
        pnlTop.add(lblTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, 447, 28));

        lblBtnClose1.setFont(new java.awt.Font("Corbel", 0, 21)); // NOI18N
        lblBtnClose1.setForeground(new java.awt.Color(0, 0, 0));
        lblBtnClose1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBtnClose1.setText("-");
        lblBtnClose1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBtnClose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtnClose1MouseClicked(evt);
            }
        });
        pnlTop.add(lblBtnClose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 40, 40));

        pnlMain.add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 40));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Para:");
        pnlMain.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));
        pnlMain.add(txtEmailTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 330, 20));

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Contenido:");
        pnlMain.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, -1));
        pnlMain.add(txtAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 330, 20));

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Asunto:");
        pnlMain.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        txtContent.setColumns(20);
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);

        pnlMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 330, 120));

        btnAnexar.setBackground(new java.awt.Color(211, 241, 248));
        btnAnexar.setText("Anexar");
        btnAnexar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarActionPerformed(evt);
            }
        });
        pnlMain.add(btnAnexar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 90, -1));

        btnSendEmail.setBackground(new java.awt.Color(211, 241, 248));
        btnSendEmail.setText("Enviar");
        btnSendEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSendEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendEmailActionPerformed(evt);
            }
        });
        pnlMain.add(btnSendEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 80, -1));

        btnRefresh.setBackground(new java.awt.Color(211, 241, 248));
        btnRefresh.setText("Refrescar");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        pnlMain.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 100, -1));

        getContentPane().add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblBtnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtnCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblBtnCloseMouseClicked

    private void pnlTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnlTopMousePressed

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void txtEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailMouseClicked
        txtEmail.setText("");
    }//GEN-LAST:event_txtEmailMouseClicked

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked
        txtPass.setText("");
    }//GEN-LAST:event_txtPassMouseClicked

    private void btnAnexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarActionPerformed
        GetRouteFile();
    }//GEN-LAST:event_btnAnexarActionPerformed

    private void btnSendEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendEmailActionPerformed
        try {
            createEmail();
        } catch (MessagingException ex) {
            Logger.getLogger(JfrMailer.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendEmail();
    }//GEN-LAST:event_btnSendEmailActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        rutaFile = "";
        nameFile = "";
        txtContent.setText("");
        txtAsunto.setText("");
        txtEmailTo.setText("");
        Anexo = false;
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void lblBtnClose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtnClose1MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_lblBtnClose1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnexar;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSendEmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBtnClose;
    private javax.swing.JLabel lblBtnClose1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailTo;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
