package com.example.swipevideo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipevideo.R;
import com.example.swipevideo.VideoAdapter;
import com.example.swipevideo.VideoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 videoViewpager = view.findViewById(R.id.videosViewpager);
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