package com.example.ginger_alarm;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AlarmDispatchHandler {
    String musicFilePath;
    MediaPlayer mediaPlayer;
    MediaPlayer createMusicToPlay(){
        musicFilePath = String.valueOf((getClass().getResource("/assets/music/baba-yethu.m4a")));
        Media sound = new Media(musicFilePath);
        mediaPlayer = new MediaPlayer(sound);
       return mediaPlayer;
    }


}
