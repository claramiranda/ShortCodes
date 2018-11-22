import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OuvintesAnonimos
   {
   public static void main(String[] args)
      {
      (new JanelaOuvintesAnonimos("Ouvintes com Classes Anônimas")).setVisible(true);
      }
   }

class JanelaOuvintesAnonimos extends JFrame
   {
   private static final long      serialVersionUID = 1L;
   private JButton                unicoBotao       = new JButton("Aperte Aqui!");
   private JPanel                 painelFundo      = new JPanel();
   private JLabel                 rotulo           = new JLabel("Ouvintes com Classes Anônimas");

   public JanelaOuvintesAnonimos(String title) throws HeadlessException
      {
      super(title);
      configuraJanela();
      configuraPainelERotulos();
      adicionaComponentes();
      adicionaOuvintes();
      }

   void adicionaComponentes()
      {
      this.add(painelFundo, BorderLayout.CENTER);
      this.add(unicoBotao, BorderLayout.SOUTH);
      }

   void adicionaOuvintes()
      {
      // codigo original, dependente das respectivas classes Ouvintes (1-3)
      
      // this.unicoBotao.addActionListener(new Ouvintes_Ouvinte1(this));
      // this.unicoBotao.addActionListener(new Ouvintes_Ouvinte2(this));
      // this.unicoBotao.addActionListener(new Ouvintes_Ouvinte3(this));

      this.unicoBotao.addActionListener(new ActionListener()
      {
      public void actionPerformed(ActionEvent arg0)
         {
         JanelaOuvintesAnonimos.this.setRotulo("Mensagem do Ouvinte Anônimo 1");
         }
      });

      this.unicoBotao.addActionListener(new ActionListener()
      {
      public void actionPerformed(ActionEvent arg0)
         {
         JanelaOuvintesAnonimos.this.setCorPainel(Color.yellow);
         }
      });

      this.unicoBotao.addActionListener(new ActionListener()
      {
      public void actionPerformed(ActionEvent arg0)
         {
         JOptionPane.showMessageDialog(JanelaOuvintesAnonimos.this, "Ouvinte Anônimo 3", "Ouvinte Anônimo atuando", JOptionPane.WARNING_MESSAGE);
         }
      });
      }

   void configuraJanela()
      {
      this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5 / 4),
                   (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.15));
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(new BorderLayout(50, 50));
      }

   void configuraPainelERotulos()
      {
      this.painelFundo.setLayout(new BoxLayout(painelFundo, BoxLayout.Y_AXIS));

      this.rotulo.setAlignmentX(CENTER_ALIGNMENT);

      this.painelFundo.add(Box.createRigidArea(new Dimension(10, 10)));
      this.painelFundo.add(rotulo);
      this.painelFundo.add(Box.createRigidArea(new Dimension(10, 10)));

      this.painelFundo.setBackground(Color.lightGray);
      }

   void setCorPainel(Color cor)
      {
      this.painelFundo.setBackground(cor);
      }

   void setRotulo(String texto)
      {
      this.rotulo.setText(texto);
      }
   }
