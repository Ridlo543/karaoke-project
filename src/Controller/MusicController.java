/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import Model.Song;
import Model.SongDAO;
import View.MediaPlayer;
import View.MusicPlayer;

/**
 *
 * @author ysaac
 */


public class MusicController{
    
    private MusicPlayer musicPlayer;
    private List<Song> songList;
    private DefaultListModel defaultModel;
    public static int index;
    public static String playingState;

    public static int currentMusicPlaying;
    

    private static int progressBar, timeTrans, timeRem;
    
    // view components
    public static JLabel labelDetail, labelMusicTitle, labelTimeElapsed, labelTimeRemaining, lebelImageAlbum;
    public static JList jlistPanel;
    public static JProgressBar jprogressBar;
    
    private static Automatic automatic = null;
    
    private boolean isFinishedPlaying;
    
    private static ScheduledExecutorService scheduler = null;
    
    private Runnable dynamicDetails=null;
    
    private ScheduledFuture<?> dynamicDetailsHandle=null;
    
    private SongDAO songDAO;
            
    public MusicController(){
           songDAO = new SongDAO();
           musicPlayer = new MusicPlayer();
           songList = new LinkedList<>();
           defaultModel = new DefaultListModel();
           currentMusicPlaying=0;
           isFinishedPlaying = false;
           playingState = "";
           
           songList = songDAO.getSongs();
           if(songList!=null){
               if (songList.size() > 0) {
                   for (Song music : songList) {

                       defaultModel.addElement(music.getFileName());
                   }
               }else{
                   infoMesagge();
               }
              
               jlistPanel.setModel(defaultModel);
               
           }else{
               infoMesagge();
               songList = new LinkedList<>();
           }
    }
    
    
    
    public void play(int index){
        
        jlistPanel.setSelectedIndex(index);
        
         if(playingState.equals("PLAYING")){
            pauseDynamicDetails();
         }
        
 
        String path = songList.get(index).getPath();
        musicPlayer.Play(path);
        currentMusicPlaying=index;
        
        playingState = "PLAYING";
        setDynamicDetails(currentMusicPlaying);

        automatic = new Automatic();
    }
    
    public void pause(){
        
        
        try {
            musicPlayer.Pause();
            
            playingState = "PAUSED";
            
            pauseDynamicDetails();
        } catch (IOException ex) {
            Logger.getLogger(MusicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void resume(){
        
        playingState = "RESUME";
        setDynamicDetails(currentMusicPlaying);
        musicPlayer.Resume();
        automatic = new Automatic();
    }
    
    
    public void next(){
        currentMusicPlaying++;
        
        if(songList.size()-1>=currentMusicPlaying){
            
            setDetail(currentMusicPlaying);
            playingState = "PLAYING";
            play(currentMusicPlaying);
            
        }else{
            currentMusicPlaying=0;
            setDetail(currentMusicPlaying);
            playingState = "PLAYING";
            play(currentMusicPlaying);            
        }
    }
    
    
    
    public void preview(){
        
        currentMusicPlaying--;
        if(currentMusicPlaying>=0){
            setDetail(currentMusicPlaying);
            playingState = "PLAYING";
            play(currentMusicPlaying);
        }else{
            currentMusicPlaying=0;
        }
        
    }
    
    
    
    //-----------------------------------------------------------------------
    
    public void playRandom(){
       
        int rnd = new Random().nextInt(songList.size());
        
        currentMusicPlaying = rnd;
        
        play(currentMusicPlaying);
    }
    
    public void stopMusic(){
        musicPlayer.Stop();
        pauseDynamicDetails();
    }
    
    
    
    //===========================================================================================
    
    private void pauseDynamicDetails(){
            automatic.pause();
            dynamicDetailsHandle.cancel(true);
            scheduler.shutdownNow();
    }
    
    
    public void addFiles(JList jlist){
        
        JFileChooser fc1 = new JFileChooser();
        
        fc1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos *MP3", "mp3");
        fc1.setFileFilter(filter);
        
        fc1.setMultiSelectionEnabled(true);   
        
        int returnVal = fc1.showOpenDialog(null); 
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            int length = fc1.getSelectedFiles().length;
            
            File[] files = new File[length];
            
            files = fc1.getSelectedFiles();

            for (File f : files) {

                if (f.isFile()) {

                    String path = f.getAbsolutePath().replace('\'', '/');

                    File ff = new File(path);

                    Song song = new Song();

                    musicPlayer.putMetaData(song, path, ff.getName());
                    
                    index+=1;
                    
                    song.setId(index);
                    
                    songList.add(song);
                    
                    defaultModel.addElement(ff.getName());

                }

            }
            jlist.setModel(defaultModel);
            jlistPanel=jlist;
            saveMusics();
        }    
    }
    
    
   
    
    
    
    
    public void setDynamicDetails(int indexMusic) {
        
        isFinishedPlaying= false;
        
        dynamicDetailsHandle=null;
        
        scheduler = null;
        
        dynamicDetails=null;
        
         dynamicDetails = new Runnable() {

                public void run() {

                    timeTrans++;
                    timeRem--;
                    progressBar++;

                    MusicController.jprogressBar.setValue(progressBar);

                    setTimeLabel(timeTrans, labelTimeElapsed);

                    setTimeLabel(timeRem, labelTimeRemaining);
                }
            };
         
    

        int musicLength = (int) songList.get(currentMusicPlaying).getLengthInSeconds();
        
        MusicController.jprogressBar.setMaximum(musicLength);
            
        int lapse=0;

            if(playingState.equals("PLAYING")){
                timeTrans = 0;
                timeRem = (int) musicLength;
                progressBar = 0;
                lapse = timeRem;
            }
            
            if(playingState.equals("RESUME")){
                lapse = timeRem;
            }
            
           
            
            scheduler = Executors.newScheduledThreadPool(1);
         dynamicDetailsHandle = scheduler.scheduleAtFixedRate(dynamicDetails, 1, 1, SECONDS);
            scheduler.schedule(new Runnable() {
                public void run() {
         
                    dynamicDetailsHandle.cancel(true);
                    isFinishedPlaying = true;
                    
                }
            }, lapse, SECONDS);
            
            playingState = "PLAYING";

    }
    
    
    
    
    private void setTimeLabel(int totalSecs, JLabel label){
        String timeString="";
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        label.setText(timeString);
    }
    
    
    public void setDetail(int selectedIndex){
        currentMusicPlaying=selectedIndex;
        Song music=songList.get(currentMusicPlaying);
        MusicController.labelDetail.setText(music.getSummary());
        MusicController.labelMusicTitle.setText(music.getTitle());
        MusicController.jlistPanel.setSelectedIndex(currentMusicPlaying);
        MusicController.jprogressBar.setValue(0);
        ImageIcon imageIcon=null;
        if(music.getImageData()!=null){
            imageIcon = new ImageIcon(new ImageIcon(music.getImageData()).getImage().getScaledInstance(257, 207, java.awt.Image.SCALE_DEFAULT));
            lebelImageAlbum.setIcon(imageIcon);
        }else{
            

            imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/view/images/defaultIcon.png")).getImage().getScaledInstance(227, 192, java.awt.Image.SCALE_DEFAULT));
            
            
            lebelImageAlbum.setIcon(imageIcon);
     
        }
    }
    
    
    
    
    public class Automatic extends Observable {

        Timer timer;
        public Automatic() {
            timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }
         TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(isFinishedPlaying){
                    if(MediaPlayer.random){
                        playRandom();
                    }
                    if(MediaPlayer.repeat){
                        play(currentMusicPlaying);
                    }
                    else{
                        next();
                    }
                }
            }

        };
        
        public void pause(){
            
             timerTask.cancel();
             timer.cancel();
        }

    }
    
    public void deleteMusic(int indexMusic){
     
        if(playingState.equals("PLAYING")){
            stopMusic();
        }
        else{
            musicPlayer.Stop();
        }
        
        playingState="";
        songDAO.deleteSong(songList, indexMusic);
        defaultModel.remove(indexMusic);  
        jlistPanel.setModel(defaultModel);
        currentMusicPlaying = 0;
        setDetail(currentMusicPlaying);
        labelTimeElapsed.setText("00:00:00");
        labelTimeRemaining.setText("00:00:00");
    }
    
    public void deleteAllMusics(){
        if(playingState.equals("PLAYING")){
            stopMusic();
        }
        musicPlayer.Stop();
        playingState="";
        songDAO.deleteAllSongs(songList);
        defaultModel.removeAllElements();
        jlistPanel.removeAll();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/view/images/nomusic.png")).getImage().getScaledInstance(227, 192, java.awt.Image.SCALE_DEFAULT));
        lebelImageAlbum.setIcon(imageIcon);
        labelTimeElapsed.setText("00:00:00");
        labelTimeRemaining.setText("00:00:00");
        labelDetail.setText("");
        labelMusicTitle.setText("");
        infoMesagge();
    }
    
    public List<Song> getMusics(){
        return songList;
    }
    
    public void saveMusics(){
        songDAO.insertSongs(songList);
    }
    
    private void infoMesagge(){
        JOptionPane.showMessageDialog(null, "There are no music to play. Add music", "Add music", JOptionPane.INFORMATION_MESSAGE);
    }

}
