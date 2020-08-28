package com.example.swipevideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 videoViewpager = findViewById(R.id.videosViewpager);
        List<VideoItem> videoItems = new ArrayList<>();

        VideoItem videoItemOne = new VideoItem();
        videoItemOne.videoURL = "https://www.infinityandroid.com/videos/video1.mp4";
        videoItemOne.videoTitle = "Celebration";
        videoItemOne.videoDescription = "Celebrate who you are in your deepest heart. Love your self and the world will love you.";
        videoItems.add(videoItemOne);

        VideoItem videoItemTwo = new VideoItem();
        videoItemTwo.videoURL = "https://www.infinityandroid.com/videos/video2.mp4";
        videoItemTwo.videoTitle = "Party";
        videoItemTwo.videoDescription = "You gotta have life your way";
        videoItems.add(videoItemTwo);

        VideoItem videoItemThree = new VideoItem();
        videoItemThree.videoURL = "https://www.infinityandroid.com/videos/video3.mp4";
        videoItemThree.videoTitle = "Excercise";
        videoItemThree.videoDescription = "Whenever i feel the need to excercise, I lie down until it goes away";
        videoItems.add(videoItemThree);

        VideoItem videoItemFour = new VideoItem();
        videoItemFour.videoURL = "https://www.infinityandroid.com/videos/video4.mp4";
        videoItemFour.videoTitle = "Nature";
        videoItemFour.videoDescription = "In every walk in with nature one receives for more than seeks";
        videoItems.add(videoItemFour);

        VideoItem videoItemFive = new VideoItem();
        videoItemFive.videoURL = "https://www.infinityandroid.com/videos/video5.mp4";
        videoItemFive.videoTitle = "Travel";
        videoItemFive.videoDescription = "It is better to travel well than to arrive.";
        videoItems.add(videoItemFive);

        VideoItem videoItemSix = new VideoItem();
        videoItemSix.videoURL = "https://www.infinityandroid.com/videos/video6.mp4";
        videoItemSix.videoTitle = "Chill";
        videoItemSix.videoDescription = "Life is so much easier when you just chill out.";
        videoItems.add(videoItemSix);

        VideoItem videoItemSeven = new VideoItem();
        videoItemSeven.videoURL = "https://www.infinityandroid.com/videos/video7.mp4";
        videoItemSeven.videoTitle = "Love";
        videoItemSeven.videoDescription = "The best thing to hold onto in life is each other.";
        videoItems.add(videoItemSeven);

        videoViewpager.setAdapter(new VideoAdapter(videoItems));
    }
}