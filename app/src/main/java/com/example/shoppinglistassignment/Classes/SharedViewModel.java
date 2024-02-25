package com.example.shoppinglistassignment.Classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> nameList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<ArrayList<String>> passList = new MutableLiveData<>(new ArrayList<>());


    public LiveData<ArrayList<String>> getNameList() {
        return nameList;
    }

    public LiveData<ArrayList<String>> getPassList() {
        return passList;
    }

    public void setNameList(ArrayList<String> list) {
        nameList.setValue(list);
    }

    public void setPassList(ArrayList<String> list) {
        passList.setValue(list);
    }
}
