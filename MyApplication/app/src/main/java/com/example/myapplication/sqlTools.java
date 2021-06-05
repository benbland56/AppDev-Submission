package com.example.myapplication;

public class sqlTools {
    public static String[][] sqlTo2DArray(String s){
        s=s.replace("[","");//replacing all [ to ""
        s=s.substring(0,s.length()-2);//ignoring last two ]]
        String s1[]=s.split("],");//separating all by "],"

        String my_matrics[][] = new String[s1.length][2];//declaring two dimensional matrix for input

        for(int i=0;i<s1.length;i++) {
            s1[i] = s1[i].trim();//ignoring all extra space if the string s1[i] has
            String single_int[] = s1[i].split(", ");//separating integers by ", "

            for (int j = 0; j < single_int.length; j++) {
                my_matrics[i][j] = single_int[j];//adding single values
            }
        }
        return my_matrics;
    }


}
