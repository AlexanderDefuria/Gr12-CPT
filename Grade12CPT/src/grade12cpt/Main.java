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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;




public class Main extends JFrame implements ActionListener{

    private static final double aspectRatio = 16.0/9.0;
    public static final int HEIGHT = 720;
    public static final int WIDTH = (int)(HEIGHT * aspectRatio);
    public static MainMenu mainmenu;
    public static JButton button;
    public static Boolean alive = true;
    public static Board board;
    
    public Main() {
        
        init();
    }
    
    private void init() {
        
        //Setup mainmenu
        mainmenu = new MainMenu(WIDTH, HEIGHT);
        add(mainmenu);
        
        // Setup the window
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setTitle("CPT");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Create invisiable button in line with image start button
        button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(500, 480, 320, 150);
        button.addActionListener(this);
        mainmenu.add(button);
        mainmenu.setLayout(null);
        
        
        // --------- Start of Kamren's Code --------------
        try {
             // Open an audio input stream.       	  
             File soundFile = new File("H:\\Documents\\Downloads\\Sound.wav"); //you could also get the sound file with an URL
             AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);        	  
             // Get a sound clip resource.
             Clip clip = AudioSystem.getClip();
             // Open audio clip and load samples from the audio input stream.
             clip.open(audioIn);
             clip.start();
        } catch (LineUnavailableException ex) { 
        } catch (IOException ex) {
        } catch (UnsupportedAudioFileException ex) { } 
        // ------------ End of Kemren's Code ----------------
        

        
    }
    
    
    // Begin the new board and wait for the player to die
    public void startBoard() {
        alive = true;
        board = new Board(WIDTH,HEIGHT);
        add(board);
        this.transferFocusUpCycle();
        pack();     
        

    }
    
    

    // Initially begins this program
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
            
        });

    }

    
    // When the button has an action performed on it switch to the playing board
    @Override
    public void actionPerformed(ActionEvent e) {
        button.removeAll();
        this.remove(mainmenu);
        this.revalidate();        
        startBoard();
    }
    

}
