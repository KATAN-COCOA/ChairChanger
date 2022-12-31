package com.mycompany.main;

public class StoreData {
    public static int dataNumbersOfNameFile;
    public static int dataNumbersOfChairPlaceFile;
    
    public void setDataOfNameFile(int catchData)
    {
        dataNumbersOfNameFile = catchData;
    }
    
    public void setDataOfChairPlaceFile(int catchData)
    {
        dataNumbersOfChairPlaceFile = catchData;
    }
    
    public int getDataOfNameFile()
    {
        return dataNumbersOfNameFile;
    }
    
    public int getDataOfChairPlaceFile()
    {
        return dataNumbersOfChairPlaceFile;
    }
}