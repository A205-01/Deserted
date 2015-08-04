package com.loonies.exercisemanager.data;

/**
 * Created by Õı∫∆÷€ on 2015/8/3.
 */
public class ListItemTextView {
    private int id;
    private int isDone;
    private String item;
    private String hoursOrWeights;
    private String minutesOrNumber;
    private String groups;
    private String wrTime;
    public ListItemTextView(int id,String item,String hoursOrWeights,String minutesOrNumber,String groups,String wrTime,int isDone){
        this.id=id;
        this.item=item;
        this.hoursOrWeights=hoursOrWeights;
        this.minutesOrNumber=minutesOrNumber;
        this.groups=groups;
        this.wrTime=wrTime;
        this.isDone=isDone;
    }
    public int getIsDone(){
        return this.isDone;
    }
    public void setIsDone(int isDone){
        this.isDone=isDone;
    }
    public String getItem(){
        return this.item;
    }
    public int getId(){ return this.id; }
    public String getHoursOrWeights(){
        return this.hoursOrWeights;
    }
    public String getMinutesOrNumber(){
        return this.minutesOrNumber;
    }
    public String getGroups(){
        return this.groups;
    }
    public String getWrTime(){
        return this.wrTime;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setItem(String item){
        this.item=item;
    }
    public void setHoursOrWeights(String hoursOrWeights){
        this.hoursOrWeights=hoursOrWeights;
    }
    public void setMinutesOrNumber(String minutesOrNumber){
        this.minutesOrNumber=minutesOrNumber;
    }
    public void setGroups(String groups){
        this.groups=groups;
    }
    public void setWrTime(String wrTime){
        this.wrTime=wrTime;
    }
}
