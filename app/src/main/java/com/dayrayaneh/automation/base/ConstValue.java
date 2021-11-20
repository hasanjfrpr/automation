package com.dayrayaneh.automation.base;

import java.util.ArrayList;
import java.util.List;

public class ConstValue {

    public static String startDate = null;
    public static String startDatePersian = null;
    public static String endDate = null;
    public static String endDatePersian = null;


    /////save token
    public static String tokenContainer = null;


    ///boolean for show or hide item main list in main activity
    public static boolean menuIsOpen=false;


    ///check access item list
    public static List<Integer> accessItemIdList = new ArrayList<>();
    public static List<Integer> isAdminLis = new ArrayList<>();


    //// item main code for compare to accessItemList for Access or UnAccess
    public static int[] containAccessList = new int[]{ 1857 , 1858  };


    public static List<Integer> productCategoriesId = new ArrayList<>();


}
