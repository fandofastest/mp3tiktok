package com.example.swipevideo.fragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.swipevideo.MusicService;
import com.example.swipevideo.R;
import com.example.swipevideo.VideoAdapter;
import com.example.swipevideo.MusicItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    boolean scrolling=false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<String>listvideo = new ArrayList<>();
    VideoAdapter videoAdapter ;
    ProgressBar progressBar;

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
        progressBar=view.findViewById(R.id.videoProgressBar);
        ViewPager2 viewPager2 = view.findViewById(R.id.videosViewpager);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled", String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                scrolling=true;
                Log.e("onPageSelected", String.valueOf(scrolling));

            }

            @Override
            public void onPageSelected(int position) {

                if (scrolling){
                    playmusic(MusicService.listtopsong,position);
                    scrolling=false;
                }


            }
        });
        videoAdapter = new VideoAdapter(getContext(),MusicService.listtopsong,listvideo);
        viewPager2.setAdapter(videoAdapter);

        if (!MusicService.PLAYERSTATUS.equals("PLAYING")){
            getsongs("via vallen","search");
        }



    }
    public void playmusic (List<MusicItem> listsong,int position){
        progressBar.setVisibility(View.VISIBLE);

        MusicService.currentlist=listsong;

        Intent intent = new Intent(getContext(), MusicService.class);
        intent.putExtra("from","online");
        intent.putExtra("pos",position);
        getContext().startService(intent);


    }


    public void playerready(){
        progressBar.setVisibility(View.GONE);
    }


    public void getsongs(final String q, final String type){

        String url;
        if (type.equals("genre")){
            url="https://api-v2.soundcloud.com/charts?genre=soundcloud:genres:"+q+"&high_tier_only=false&kind=top&limit=100&client_id=iZIs9mchVcX5lhVRyQGGAYlNPVldzAoX";
        }
        else{
            url="https://api-v2.soundcloud.com/search/tracks?q="+q+"&client_id=iZIs9mchVcX5lhVRyQGGAYlNPVldzAoX&limit=100";

        }
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

//                linearLayout.setVisibility(View.GONE);

                if (type.equals("genre")){
                    try {
                        JSONArray jsonArray1=response.getJSONArray("collection");

                        for (int i = 0;i<jsonArray1.length();i++){
                            JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                            JSONObject jsonObject=jsonObject1.getJSONObject("track");
                            MusicItem listModalClass = new MusicItem();
                            listModalClass.setId(jsonObject.getInt("id"));
                            listModalClass.setTitle(jsonObject.getString("title"));
                            listModalClass.setImageurl(jsonObject.getString("artwork_url"));
                            listModalClass.setDuration(jsonObject.getString("full_duration"));
                            listModalClass.setType("online");
                            listModalClass.setArtist(q);



//                        System.out.println(jsonArray3);


                            MusicService.listtopsong.add(listModalClass);
                            MusicService.currentlist.add(listModalClass);
//



//                        Toast.makeText(getActivity(),id,Toast.LENGTH_LONG).show();


                        }





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }}









                else if (type.equals("search")){
                    try {
                        JSONArray jsonArray1=response.getJSONArray("collection");

                        for (int i = 0;i<jsonArray1.length();i++){
                            JSONObject jsonObject=jsonArray1.getJSONObject(i);
                            MusicItem listModalClass = new MusicItem();
                            listModalClass.setId(jsonObject.getInt("id"));
                            listModalClass.setTitle(jsonObject.getString("title"));
                            listModalClass.setImageurl(jsonObject.getString("artwork_url"));
                            listModalClass.setDuration(jsonObject.getString("full_duration"));
                            listModalClass.setType("online");


                            try {
                                JSONObject jsonArray3=jsonObject.getJSONObject("publisher_metadata");
                                listModalClass.setArtist(jsonArray3.getString("artist"));

                            }
                            catch (JSONException e){
                                listModalClass.setArtist("Artist");

                            }








                            MusicService.listtopsong.add(listModalClass);
//
//                        System.out.println(jsonArray1);


//                        Toast.makeText(getActivity(),id,Toast.LENGTH_LONG).show();


                        }





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



                videoAdapter.notifyDataSetChanged();
//                songAdapter.notifyDataSetChanged();
                //    System.out.println("update"+listsongModalSearch);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);


    }
    public void gettopchart(){
        String url="https://api-v2.soundcloud.com/charts?charts-top:all-music&&high_tier_only=false&kind=top&limit=100&client_id=iZIs9mchVcX5lhVRyQGGAYlNPVldzAoX";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

//                linearLayout.setVisibility(View.GONE);
//                System.out.println(response);


                try {
                    JSONArray jsonArray1=response.getJSONArray("collection");

                    for (int i = 0;i<jsonArray1.length();i++){
                        JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                        JSONObject jsonObject=jsonObject1.getJSONObject("track");
                        MusicItem musicSongOnline = new MusicItem();
                        musicSongOnline.setId(jsonObject.getInt("id"));
                        musicSongOnline.setTitle(jsonObject.getString("title"));
                        musicSongOnline.setImageurl(jsonObject.getString("artwork_url"));
                        musicSongOnline.setDuration(jsonObject.getString("full_duration"));
                        musicSongOnline.setType("online");


                        try {
                            JSONObject jsonArray3=jsonObject.getJSONObject("publisher_metadata");
                            musicSongOnline.setArtist(jsonArray3.getString("artist"));

                        }
                        catch (JSONException e){
                            musicSongOnline.setArtist("Artist");

                        }


//                        System.out.println(jsonArray3);


                        MusicService.listtopsong.add(musicSongOnline);
//



//                        Toast.makeText(getActivity(),id,Toast.LENGTH_LONG).show();


                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }
                videoAdapter.notifyDataSetChanged();
//                songAdapter.notifyDataSetChanged();
                //    System.out.println("update"+listsongModalSearch);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);


    }



    private void getVideo(){
        for (int i = 0; i < 6; i++) {
            listvideo.add("https://www.infinityandroid.com/videos/video"+i+".mp4");
        }
    }

}