/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoundTest;

import java.io.*;
    import java.net.URL;
    import javax.sound.sampled.*;
    import javax.swing.*;
       
    // To play sound using Clip, the process need to be alive.
    // Hence, we use a Swing application.
    public class JavaSoundTest extends JFrame {
       
       // Constructor
       public JavaSoundTest() {
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setTitle("Test Sound Clip");
          this.setSize(300, 200);
          this.setVisible(true);       
          try {
             // Open an audio input stream.       	  
        	  File soundFile = new File("H:\\Documents\\Downloads\\SwordClang.wav"); //you could also get the sound file with an URL
        	  AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);        	  
             // Get a sound clip resource.
             Clip clip = AudioSystem.getClip();
             // Open audio clip and load samples from the audio input stream.
             clip.open(audioIn);
             clip.start();
          } catch (UnsupportedAudioFileException e) {
             e.printStackTrace();
          } catch (IOException e) {
             e.printStackTrace();
          } catch (LineUnavailableException e) {
             e.printStackTrace();
          }
       }
       
       public static void main(String[] args) {
          new JavaSoundTest();
       }
    }