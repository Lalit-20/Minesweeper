import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.util.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
class Minesweeper extends JFrame implements ActionListener,ContainerListener{
    int fw, fh, blockr, blockc, var1, var2, num_of_mine, detectedmine = 0, savedlevel = 1,
            savedblockr, savedblockc, savednum_of_mine = 10;
     int[] r = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};
    JButton[][] blocks;
    int countmine[][];
    int [][] color;
    ImageIcon [] ic=new ImageIcon[14];
    JPanel panelb=new JPanel();
    JPanel panelmt=new JPanel();
    JTextField tf_mine,tf_time;
    JButton reset=new JButton("");
    Point p;
    boolean check=true;
   MouseHandler mh;
    Minesweeper()
    {
        super("Minesweeper");
        setLocation(450,20);
        setic();
        setmenu();
        setpanel(1,0,0,0);
       
        
        reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    
                    setpanel(savedlevel, savedblockr, savedblockc, savednum_of_mine);
                } catch (Exception ex) {
                    setpanel(savedlevel, savedblockr, savedblockc, savednum_of_mine);
                }
                reset();

            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
       
    }
    public void setpanel(int level,int setr,int setc,int setm)
    {
       if(level==1)
       {
           fw=200;
           fh=300;
           blockr=9;
           blockc=9;
           num_of_mine=10;
       }
       else if(level==2)
       {
           fw=300;
           fh=360;
           blockr=15;
           blockc=15;
           num_of_mine=40;
       }
       else if(level==3){
           fw=500;
           fh=650;
           blockr=30;
           blockc=20;
           num_of_mine=100;
       }
       else if(level==4){
           fw=(20*setc);
           fh=(25*setr);
           blockr=setr;
           blockc=setc;
           num_of_mine=setm;
       }
       savedblockr=blockr;
       savedblockc=blockc;
       savednum_of_mine=num_of_mine;
       setSize(fw,fh);
       setResizable(false);
       detectedmine=num_of_mine;
       p=this.getLocation();
       blocks=new JButton[blockr][blockc];
       countmine=new int[blockr][blockc];
       color=new int[blockr][blockc];
      mh=new MouseHandler();
       getContentPane().removeAll();
       panelb.removeAll();
       tf_mine=new JTextField(""+num_of_mine);
       tf_mine.setEditable(false);
       tf_mine.setBackground(Color.BLACK);
       tf_mine.setForeground(Color.RED);
       tf_mine.setFont(new Font("Times New Roman", Font.BOLD, 25));
       tf_mine.setBorder(BorderFactory.createLoweredBevelBorder());
       reset.setIcon(ic[11]);

       panelmt.removeAll();
       panelmt.setLayout(new BorderLayout());
       panelmt.add(tf_mine,BorderLayout.WEST);
       panelmt.add(reset,BorderLayout.CENTER);
       panelmt.setBorder(BorderFactory.createLoweredBevelBorder());
       panelb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        panelb.setPreferredSize(new Dimension(fw, fh));
        panelb.setLayout(new GridLayout(0, blockc));
        panelb.addContainerListener(this);

        for (int i = 0; i < blockr; i++) {
            for (int j = 0; j < blockc; j++) {
                blocks[i][j] = new JButton("");

                //blocks[i][j].addActionListener(this);
                blocks[i][j].addMouseListener(mh);

                panelb.add(blocks[i][j]);

            }
        }
        reset();

        panelb.revalidate();
        panelb.repaint();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().addContainerListener(this);
        getContentPane().repaint();
        getContentPane().add(panelb, BorderLayout.CENTER);
        getContentPane().add(panelmt, BorderLayout.NORTH);
        setVisible(true);
    
             

       setVisible(true);
       
       }
    public void reset(){
        check=true;
        for(int i=0;i<blockr;i++){
            for(int k=0;k<blockc;k++)
            {
                color[i][k]='w';
            }
        }
        
    }
    public void setmenu()
    {
        JMenuBar bar= new JMenuBar();
        JMenu game=new JMenu("Game");
        JMenuItem menuitem= new JMenuItem("New Game");
        final JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Beginner");
        final JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
        final JCheckBoxMenuItem expert = new JCheckBoxMenuItem("Expert");
        final JCheckBoxMenuItem custom = new JCheckBoxMenuItem("Custom");
        final JMenuItem exit=new JMenuItem("Exit");
        ButtonGroup status=new ButtonGroup();
        menuitem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setpanel(1,0,0,0);
            }
        });
        beginner.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(1, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        beginner.setSelected(true);
                        savedlevel = 1;
                    }
                });
        intermediate.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(2, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        intermediate.setSelected(true);
                        savedlevel = 2;
                    }
                });
        expert.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(3, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        expert.setSelected(true);
                        savedlevel = 3;
                    }
                });

        custom.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        //panelb.removeAll();
                        Customization cus = new Customization();
                        reset();
                        panelb.revalidate();
                        panelb.repaint();

                        //Minesweeper ob=new Minesweeper(4);
                        custom.setSelected(true);
                        savedlevel = 4;
                    }
                });
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setJMenuBar(bar);

        status.add(beginner);
        status.add(intermediate);
        status.add(expert);
        status.add(custom);
        game.add(menuitem);
        game.addSeparator();
        game.add(beginner);
        game.add(intermediate);
        game.add(expert);
        game.add(custom);
        game.addSeparator();
        game.add(exit);
        bar.add(game);
        

    }
    
    public void setic() {
        String name;

        for (int i = 0; i <= 8; i++) {
            name = i + ".gif";
            ic[i] = new ImageIcon(name);
        }
        ic[9] = new ImageIcon("mine.gif");
        ic[10] = new ImageIcon("flag.gif");
        ic[11] = new ImageIcon("new game.gif");
        ic[12] = new ImageIcon("crape.gif");
    }
    public void componentAdded(ContainerEvent ce) {
    }

    public void componentRemoved(ContainerEvent ce) {
    }

    public void actionPerformed(ActionEvent ae) {
    }
    class MouseHandler extends MouseAdapter{
        public void mouseClicked(MouseEvent me){
            if(check==true){
                for (int i = 0; i < blockr; i++) {
                    for (int j = 0; j < blockc; j++) {
                        if (me.getSource() == blocks[i][j]) {
                            var1 = i;
                            var2 = j;
                            i = blockr;
                            break;
                        }
                    }
                }
            
        
       plant();
        countmine();
        check=false;
       }
          showvalue(me);
          winner();
    }
    }
     public void winner() {
        int q = 0;
        for (int k = 0; k < blockr; k++) {
            for (int l = 0; l < blockc; l++) {
                if (color[k][l] == 'w') {
                    q = 1;
                }
            }
        }


        if (q == 0) {
            
            for (int k = 0; k < blockr; k++) {
                for (int l = 0; l < blockc; l++) {
                    blocks[k][l].removeMouseListener(mh);
                }
            }
         JOptionPane.showMessageDialog(this, "Congratulations! You Win :D ");
        }
    }
 
      class Customization extends JFrame implements ActionListener {

        JTextField t1, t2, t3;
        JLabel lb1, lb2, lb3;
        JButton b1, b2;
        int cr, cc, cm;

        Customization() {
            super("CUSTOMIZATION");
            setSize(180, 200);
            setResizable(false);
            setLocation(p);

            t1 = new JTextField();
            t2 = new JTextField();
            t3 = new JTextField();

            b1 = new JButton("OK");
            b2 = new JButton("Cancel");

            b1.addActionListener(this);
            b2.addActionListener(this);

            lb1 = new JLabel("Row");
            lb2 = new JLabel("Column");
            lb3 = new JLabel("Mine");

            getContentPane().setLayout(new GridLayout(0, 2));

            getContentPane().add(lb1);
            getContentPane().add(t1);
            getContentPane().add(lb2);
            getContentPane().add(t2);
            getContentPane().add(lb3);
            getContentPane().add(t3);

            getContentPane().add(b1);
            getContentPane().add(b2);
            setVisible(true);

           
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                try {
                    cr = Integer.parseInt(t1.getText());
                    cc = Integer.parseInt(t2.getText());
                    cm = Integer.parseInt(t3.getText());
                    setpanel(4, row(), column(), mine());
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Wrong Input");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                }
            }

            if (e.getSource() == b2) {
                dispose();
            }
        }

        public int row() {
            if (cr > 30) {
                return 30;
            } else if (cr < 9) {
                return 9;
            } else {
                return cr;
            }
        }

        public int column() {
            if (cc > 30) {
                return 30;
            } else if (cc < 9) {
                return 9;
            } else {
                return cc;
            }
        }

        public int mine() {
            if (cm > ((row() - 1) * (column() - 1))) {
                return ((row() - 1) * (column() - 1));
            } else if (cm < 10) {
                return 10;
            } else {
                return cm;
            }
        }
    }
      
         public void plant() {
        int row = 0, col = 0;
        Boolean[][] flag = new Boolean[blockr][blockc];
        Random rand=new Random();
        
        for (int i = 0; i < blockr; i++) {
            for (int j = 0; j < blockc; j++) {
                flag[i][j] = false;
                countmine[i][j] = 0;
            }
        }

        flag[var1][var2] = true;
        color[var1][var2] = 'b';

        for (int i = 0; i < num_of_mine; i++) {
            row = rand.nextInt(blockr);
            col = rand.nextInt(blockc);

            if (flag[row][col] == false) {

                countmine[row][col] = -1;
                color[row][col] = 'b';
                flag[row][col] = true;
            } else {
                i--;
            }
        }
    }
         public void showvalue(MouseEvent e) {
        for (int i = 0; i < blockr; i++) {
            for (int j = 0; j < blockc; j++) {

                if (e.getSource() == blocks[i][j]) {
                    if (e.isMetaDown() == false) {
                        if (blocks[i][j].getIcon() == ic[10]) {
                            if (detectedmine < num_of_mine) {
                                detectedmine++;
                            }
                            tf_mine.setText("" + detectedmine);
                        }

                        if (countmine[i][j] == -1) {
                            for (int k = 0; k < blockr; k++) {
                                for (int l = 0; l < blockc; l++) {
                                    if (countmine[k][l] == -1) {

                                        blocks[k][l].setIcon(ic[9]);
                                        blocks[k][l].removeMouseListener(mh);
                                    }
                                    blocks[k][l].removeMouseListener(mh);
                                }
                            }
                            
                            reset.setIcon(ic[12]);
                            JOptionPane.showMessageDialog(null, "Stepped on a Mine. You lost!");
                           
                        } else if (countmine[i][j] == 0) {
                            
                            uncover(i, j);
                        } else {
                            blocks[i][j].setIcon(ic[countmine[i][j]]);
                            color[i][j] = 'b';
                            break;
                        }
                    } else {
                        if (detectedmine != 0) {
                            if (blocks[i][j].getIcon() == null) {
                                detectedmine--;
                                blocks[i][j].setIcon(ic[10]);
                                color[i][j]='b';
                            }
                            tf_mine.setText("" + detectedmine);
                        }
                        

                      
                    }
                }

            }
        }

    }
         public void uncover(int row,int col)
         {
             int R,C;
             color[row][col] = 'b';

        blocks[row][col].setBackground(Color.ORANGE);
        blocks[row][col].setIcon(ic[countmine[row][col]]);
        for (int i = 0; i < 8; i++) {
            R = row + r[i];
            C = col + c[i];
            if (R >= 0 && R < blockr && C >= 0 && C < blockc && color[R][C] == 'w') {
                if (countmine[R][C] == 0) {
                    uncover(R, C);
                } else {
                    blocks[R][C].setIcon(ic[countmine[R][C]]);
                   
                    color[R][C] = 'b';
                    

                }
                
                
            }


        }
    }
         public void countmine() {
        int row, column;

        for (int i = 0; i < blockr; i++) {
            for (int j = 0; j < blockc; j++) {
                int value = 0;
                int R, C;
                row = i;
                column = j;
                if (countmine[row][column] != -1) {
                    for (int k = 0; k < 8; k++) {
                        R = row + r[k];
                        C = column + c[k];

                        if (R >= 0 && C >= 0 && R < blockr && C < blockc) {
                            if (countmine[R][C] == -1) {
                                value++;
                            }

                        }

                    }
                    countmine[row][column] = value;
                    

                }
            }
        }
    }
         
    public static void main(String[]args)
    {
        new Minesweeper();
    }
       }

