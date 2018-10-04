/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactermovement;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Alexander
 */
public class Board extends JPanel implements Runnable{
    

    public Board() {
        init();
    }
    
    private void init() {
        setPreferredSize(new Dimension(700,500));
        setDoubleBuffered(true);
        setBackground(Color.black);
    }
    
    private void cycle() {
        
    }

    @Override
    public void run() {
        
    }
}
