package com.dayrayaneh.automation.base;

import java.util.ArrayList;
import java.util.List;

public class ConstValue {

    public static String startDate = null;
    public static String startDatePersian = null;
    public static String endDate = null;
    public static String endDatePersian = null;
    public static String startTime = "00:00";
    public static String endTime = "23:59";


    /////save token
    public static String tokenContainer = null;

    public static int companyId = -1;


    ///boolean for show or hide item main list in main activity
    public static boolean menuIsOpen=false;


    ///check access item list
    public static List<Integer> accessItemIdList = new ArrayList<>();
    public static List<Integer> isAdminLis = new ArrayList<>();


    //// item main code for compare to accessItemList for Access or UnAccess
    public static int[] containAccessList = new int[]{ 1857 , 1858  };


    public static List<Integer> productCategoriesId = new ArrayList<>();


    public static String ip = "";
    public static String port = "";
    public static String ip_base = "192.168.0.16";
    public static String port_base = "7585";


    public static String uniqueIdVoice = "";








}
