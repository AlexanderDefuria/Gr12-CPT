/*
 * The MIT License
 *
 * Copyright 2018 Alexander.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package grade12cpt;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;



public class Main extends JFrame implements ActionListener{

    private static final double aspectRatio = 16.0/9.0;
    public static final int HEIGHT = 720;
    public static final int WIDTH = (int)(HEIGHT * aspectRatio);
    public static MainMenu mainmenu;
    public static JButton button;
    
    public Main() {
        
        init();
    }
    
    private void init() {
        mainmenu = new MainMenu(WIDTH, HEIGHT);
        add(mainmenu);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        
        button = new JButton();
        button.setSize(200, 50);
        mainmenu.add(button);
        
        button.addActionListener(this);
        

        
        setTitle("CPT");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public void startBoard() {
        add(new Board(WIDTH,HEIGHT));
        this.transferFocusUpCycle();
        pack();

    }
    
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
            
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.removeAll();
        this.remove(mainmenu);
        this.revalidate();        
        startBoard();
    }
    

}
