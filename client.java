package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class client extends javax.swing.JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 1234;
    Boolean isConnected = false;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;    
     
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }    
       
    public void userAdd(String data) 
    {
         users.add(data);
    }    
   
    public void sendLeave() 
    {
        String a = (username + ":Leave");
        try
        {
            writer.println(a); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Leave message.\n");
        }
    }
    
    public void sendDisconnect() 
    {
        String bye = (username + ":Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }
   
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);
        grp.setEnabled(true);
    }
    
    public client() 
    {
        initComponents();
    }
    //--------------------------//
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat", leave = "Leave";
            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");
                     
                     if (data[2].equals(chat)) 
                     {
                         if(data[3].equals(grp.getText()) && !(data[4].equals(leave)))
                         {
                             if(data[1].equals("has connected"))
                                 ta_chat.append(data[0] + ":" + data[1] + " to Group "+grp.getText()+" \n");
                             else
                                 ta_chat.append(data[0] + ":" + data[1] + "\n");
                            ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         } 
                        
                     } 
                     else if (data[2].equals(connect))
                     {
                         userAdd(data[0]);
                     } 
                      
                     else if (data[2].equals(done)) 
                     {
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }
    //--------------------------//    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        lb_password = new javax.swing.JLabel();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        leave = new javax.swing.JButton();
        grp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_username.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lb_username.setText("Username :");

        tf_username.setBackground(new java.awt.Color(252, 204, 247));
        tf_username.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        lb_password.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lb_password.setText("Group:");

        b_connect.setBackground(new java.awt.Color(226, 87, 212));
        b_connect.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        b_connect.setForeground(java.awt.Color.white);
        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setBackground(new java.awt.Color(226, 87, 212));
        b_disconnect.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        b_disconnect.setForeground(java.awt.Color.white);
        b_disconnect.setText("Disconnect");
        b_disconnect.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_disconnect.setEnabled(false);
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        ta_chat.setBackground(new java.awt.Color(201, 199, 206));
        ta_chat.setColumns(20);
        ta_chat.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        ta_chat.setRows(5);
        ta_chat.setDisabledTextColor(java.awt.Color.black);
        ta_chat.setEnabled(false);
        jScrollPane1.setViewportView(ta_chat);

        tf_chat.setBackground(new java.awt.Color(169, 149, 235));
        tf_chat.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        tf_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_chatActionPerformed(evt);
            }
        });

        b_send.setBackground(new java.awt.Color(210, 73, 230));
        b_send.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        b_send.setForeground(java.awt.Color.white);
        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        leave.setBackground(new java.awt.Color(226, 87, 212));
        leave.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        leave.setForeground(java.awt.Color.white);
        leave.setText("Leave");
        leave.setEnabled(false);
        leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveActionPerformed(evt);
            }
        });

        grp.setBackground(new java.awt.Color(252, 204, 247));
        grp.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        grp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_connect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_disconnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leave))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_password)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(grp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_send)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_password)
                    .addComponent(grp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(b_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed

    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        
        if (isConnected == false && tf_username.getText().equals(""))
        {
            ta_chat.append("Please write your name\n");
        }
        else if (isConnected == false && !grp.getText().equals("")) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);
            grp.setEnabled(false);
            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected:Connect:" + grp.getText()+":"+" ");
                writer.flush(); 
        
                isConnected = true; 
                b_disconnect.setEnabled(true);
                leave.setEnabled(true);
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
                grp.setEnabled(true);
            }   
            ListenThread();
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
        else
        {
            ta_chat.append("Please write a group name\n");
        }
        
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat"+ ":" +grp.getText()+ ":" + " ");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }
        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    private void leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveActionPerformed
        // TODO add your handling code here:
        grp.setEnabled(true);
        isConnected = false;
        sendLeave();
        ta_chat.append(username+" left group "+grp.getText()+" \n");
        writer.flush(); // flushes the buffer
    }//GEN-LAST:event_leaveActionPerformed

    private void grpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grpActionPerformed

    private void tf_chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_chatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_chatActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client().setVisible(true);
            }           
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JTextField grp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_username;
    private javax.swing.JButton leave;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}