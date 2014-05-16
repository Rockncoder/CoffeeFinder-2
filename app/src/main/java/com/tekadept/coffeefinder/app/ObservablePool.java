package com.tekadept.coffeefinder.app;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Observable;

public class ObservablePool extends Observable {
    private ArrayList<AsyncTask<String, Void, String>> tasks = new ArrayList<AsyncTask<String, Void, String>>();
    private ArrayList<ArrayList<String>> params = new ArrayList<ArrayList<String>>();
    private int finishedTasks = 0;

    public void addTask(AsyncTask<String, Void, String> newTask, ArrayList<String> params){
        this.tasks.add(newTask);
        this.params.add(params);
    }

    public void executeTasks(){
        finishedTasks = 0;
        int ndx=0;
        for(AsyncTask<String, Void, String>t : tasks){
            t.execute((String[])params.get(ndx).toArray(new String[params.size()]));
        }
    }

    public void finishedTask(){
        finishedTasks++;
        if(finishedTasks == tasks.size()) {
            setChanged();
            this.notifyObservers(null);
        }
    }
}
