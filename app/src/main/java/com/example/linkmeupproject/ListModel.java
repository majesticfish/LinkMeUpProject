package com.example.linkmeupproject;

/**
 * Created by Sean on 6/18/2015.
 * Java Class to store the information in each cell of the list
 */
public class ListModel {

    private  String VideoName ="";
    private  String videoID ="";

    /*********** Set Methods ******************/

    public void setVideoName(String CompanyName){
        this.VideoName = CompanyName;
    }


    public void setVideoID(String Url){
        this.videoID = Url;
    }

    /*********** Get Methods ****************/

    public String getVideoName(){
        return this.VideoName;
    }


    public String getVideoID(){
        return this.videoID;
    }
}