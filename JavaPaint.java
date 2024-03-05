import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.MenuItemUI;

import java.awt.geom.Line2D;
import java.awt.image.*;

 class DrawArea extends JComponent  {
 
  // Image in which we're going to draw
   Image image;
  // Graphics2D object ==> used to draw on
  private Graphics2D g2;
  // Mouse coordinates
  private int currentX, currentY, oldX, oldY;

  Point point1;
  Point point2;
  Line2D line2d;
 
  DrawArea() {
    setDoubleBuffered(false);
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        // save coord x,y when mouse is pressed
        oldX = e.getX();
        oldY = e.getY();
      }
    });
 
    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        // coord x,y when drag mouse
        currentX = e.getX();
        currentY = e.getY();
 
        if (g2 != null) {
          // draw line if g2 context not null
          g2.drawLine(oldX, oldY, currentX, currentY);
          // refresh draw area to repaint
          repaint();
          // store current coords x,y as olds x,y
          oldX = currentX;
          oldY = currentY;
        }
      }
    });
  }
   
 
  protected void paintComponent(Graphics g) {
    if (image == null) {
      // image to draw null ==> we create
      image = createImage(getSize().width, getSize().height);
      g2 = (Graphics2D) image.getGraphics();
      // enable antialiasing
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      // clear draw area
      clear();
    }
 
    g.drawImage(image, 0, 0, null);
  }
 
  // now we create exposed methods
  public void clear() {
    g2.setPaint(Color.white);
    // draw white on entire draw area to clear
    g2.fillRect(0, 0, getSize().width, getSize().height);
    g2.setPaint(Color.black);
    repaint();
  }   

  public void white() {
    g2.setPaint(Color.white);
  } 
  public void anycol() {
    Color initialcolor=Color.black;    
    Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);
    g2.setPaint(color);
  } 

  public void stroke1() {
    g2.setStroke(new BasicStroke(5));
  }

  public void stroke2() {
    g2.setStroke(new BasicStroke(10));
  }

  public void stroke3() {
    g2.setStroke(new BasicStroke(15));
  }

  public void stroke4() {
    g2.setStroke(new BasicStroke(20));
  }
}
 
public class JavaPaint {
 
  JButton clearBtn , b, s1, s2, s3, s4, eraseBtn;
  DrawArea drawArea;  

  ActionListener actionListener = new ActionListener() {
    
  public void actionPerformed(ActionEvent e) {
   
      if (e.getSource() == clearBtn) {
        drawArea.clear();
      } else if (e.getSource() == eraseBtn) {
        drawArea.white();
      } else if (e.getSource() == b) {
        drawArea.anycol();
      } else if (e.getSource() == s1) {
        drawArea.stroke1();
      } else if (e.getSource() == s2) {
        drawArea.stroke2();
      } else if (e.getSource() == s3) {
        drawArea.stroke3();
      } else if (e.getSource() == s4) {
        drawArea.stroke4();
      }
    }
                
};
 
  public static void main(String[] args) {
    new JavaPaint().show();
  }
 
  public void show() {
    // create main frame
    JFrame frame = new JFrame("JAGRITI PAINT");
    Container content = frame.getContentPane();

    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
    drawArea = new DrawArea();
    // add to content pane
    content.add(drawArea, BorderLayout.CENTER);
    // create controls to apply colors and call clear feature
    JPanel panel = new JPanel();

    b = new JButton("Color");
    b.addActionListener(actionListener);
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    eraseBtn = new JButton("Erase");
    eraseBtn.addActionListener(actionListener);
    s1 = new JButton("Brush 1x");
    s1.addActionListener(actionListener);
    s2 = new JButton("Brush 2x");
    s2.addActionListener(actionListener);
    s3 = new JButton("Brush 3x");
    s3.addActionListener(actionListener);
    s4 = new JButton("Brush 4x");
    s4.addActionListener(actionListener);

    JMenuBar mb=new JMenuBar();  
    JMenu menu1=new JMenu("File");   
    mb.add(menu1); 
    JMenu mn1= new JMenu("New");
    menu1.add(mn1);
    JMenu mn2 = new JMenu("Open");
    menu1.add(mn2);
    JMenu mn3 = new JMenu("Save");
    menu1.add(mn3);

    JMenu menu2 = new JMenu("Edit");
    mb.add(menu2);

    JMenu menu3=new JMenu("Draw");   
    mb.add(menu3); 
    JMenu m1= new JMenu("New");
    menu3.add(m1);
    JMenu m2= new JMenu("New");
    menu3.add(m2);
    JMenu m3= new JMenu("New");
    menu3.add(m3);
    
    
    JMenu menu4 = new JMenu("Help");
    mb.add(menu4);
    frame.setJMenuBar(mb);    
 
    // add to panel
    panel.add(b);
    panel.add(eraseBtn);
    panel.add(clearBtn);
    panel.add(s1);
    panel.add(s2);
    panel.add(s3);
    panel.add(s4);
 
    // add to content pane
    content.add(panel, BorderLayout.NORTH);
 
    frame.setSize(700, 700);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // show the swing paint result
    frame.setVisible(true);
   
  }
 
  }
