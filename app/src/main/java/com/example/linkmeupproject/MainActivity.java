package com.example.linkmeupproject;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


/*import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
*/import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends ActionBarActivity{

    // Declaring Your View and Variables
    public static String index = "INDEX_OF_LINK";
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter1;
    SlidingTabLayout tabs;
    String[] Titles = {"John","Bob"};
    int Numboftabs =2;
    ListView list;
    CustomAdapter adapter;
    public  MainActivity CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();

    /**
     * On Create method that sets up the toolbar and the pager adapter to create tabs.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creating The Toolbar and setting it as the Toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //  setSupportActionBar(toolbar);
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter1 =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter1);
        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

    }

    /**
     * This method is called when the query button is clicked. It will grab the ListView and assign an adapter to it.
     * This method calls the setListData method which populates the data tables;
     * @param v
     */
    public void getUserYoutubeFeed(View v){
        CustomListView = this;
        setListData();
        Resources res = getResources();
        list = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(CustomListView, CustomListViewValuesArr, res);
        list.setAdapter(adapter);
    }

    /**
     * This is a method that populates the data table. This is where my progress was incomplete
     */
    public void setListData(){
        String[] titles = {"Uncensored - Key & Peele - Negrotown","Key & Peele - Basketball Commentary","Uncensored - Key & Peele - Loco Gangsters", "Key & Peele - Al Qaeda Meeting"};
        String[] ids = {"Rg58d8opQKA","3d053pPV_AA","XDzpzYzp9HE","IHfiMoJUDVQ"};
        for(int i = 0; i < titles.length; i ++){
            ListModel listModel = new ListModel();
            listModel.setVideoName(titles[i]);
            listModel.setVideoID(ids[i]);
            CustomListViewValuesArr.add(listModel);
        }
        /*
        This is Code for getting results off of youtube dynamically. However, I was unable to resolve the Dex conflicts in time. In its place I have manually coded in the top 4 results for the search.
         */
      /*  try {
            YouTube youtube;
           final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            final JsonFactory JSON_FACTORY = new JacksonFactory();
          /*  youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
                }).setApplicationName("youtube-cmdline-search-sample").build();
            ;
            String queryTerm = "key and peele";
            YouTube.Search.List search = youtube.search().list("id,snippet");
            String apiKey = "AIzaSyBUFjpOLQa7hTU3ZEd-MFMIJATELyJeEAo";
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults((long)20);
            search.setType("video");
            search.setQ(queryTerm);
            search.setKey(apiKey);
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iteratorSearchResults = searchResultList.iterator();
            if(searchResultList!=null){
                while(iteratorSearchResults.hasNext()){
                    ListModel listModel = new ListModel();
                    SearchResult singleVideo = iteratorSearchResults.next();
                    ResourceId rId = singleVideo.getId();

                    if(rId.getKind().equals("youtube#video")){
                        Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                        listModel.setCompanyName(singleVideo.getSnippet().getTitle());
                        listModel.setUrl(rId.getVideoId());
                        listModel.setImage("drawable/ic_launcher");
                    }
                    CustomListViewValuesArr.add(listModel);
                    if(CustomListViewValuesArr.size()>20) break;
                }
            }

        }catch(Exception e){

        }*/
    }

    /**
     * This method reacts to the onClick status of each cell and launches the YouTubeActivity in response
     * @param mPosition: index of the cell clicked
     */
    public void onItemClick(int mPosition){
        Intent intent = new Intent(this, YouTubeActivity.class);
        intent.putExtra(index,CustomListViewValuesArr.get(mPosition).getVideoID());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
