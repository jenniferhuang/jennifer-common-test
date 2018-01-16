package com.jennifer.test;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * RC platform request related
 */


/**
 * developer
 * https://ai-xia01-c01-dpw01.lab.rcch.ringcentral.com/index.html  dpw.admin/Test!123
 * search APIs, search Apps
 */


public class TestToken {

    public static void main(String[] args) throws UnsupportedEncodingException {


        String s = "WjA2eUpuYzhUTU85MkRWY1VvaHJGZzpXUnlzQ2hnZVM0R3pndGozWi1FRkFRdDFkQjhVcEdTWGUtY0tPOWVkdEhxdw";  //Internal APP, redirect lrw, PAS return access for lrw application
        System.out.println(Base64.base64Decode(s));  //Z06yJnc8TMO92DVcUohrFg:WRysChgeS4Gzgtj3Z-EFAQt1dB8UpGSXe-cKO9edtHqw


        String dpw_key = "ZHFsT3M5R0hRRXl5Yjl6QktPcmVUdzo0dHhMNndCZlREQ0RuZ3J2SmQ0TVVBUmk2NmpEWW9UbFN3TG9qUEVfMVViUQ";  //Dpw app,    used in fre webphone
        System.out.println(Base64.base64Decode(dpw_key));  //dqlOs9GHQEyyb9zBKOreTw:4txL6wBfTDCDngrvJd4MUARi66jDYoTlSwLojPE_1UbQ


        String xiaup_key = "TWtDZGxTVnFRMDZINmk3S1ljdjliZzo1X3RGQlhCUVRMV2FWY1BGNjFMVUdnbmdCZmM4S0dRQ2FaMF9VVHc4MHZzdw==";
        System.out.println(Base64.base64Decode(xiaup_key));  //MkCdlSVqQ06H6i7KYcv9bg:5_tFBXBQTLWaVcPF61LUGgngBfc8KGQCaZ0_UTw80vsw  //Glip_Api_Client use this key


        String xiaUpAppKey = "eac8797af1b3502F2CEAAEECAC3Ed378AA7858A386656f28A008b0c638A754B1";       //used in webphone
        String xiaUpAppSecret = "c082702E4ea4DA18c4b1377917778a8aafabCA3Be579B78B66d17C36874b27F4";

        String basic_result = new String(java.util.Base64.getEncoder().encode((xiaUpAppKey + ":" + xiaUpAppSecret).getBytes()), "UTF-8");
        System.out.println(basic_result);


        String d3key = "SjYmlnCwR6-MzXn19snORg";
        String d3keySecret = "9ZAwJ4sQRVSxU_VXgrr3WAb51SK1GYQVWIKL1pSsrLAg";


        String d3_ta = "";


        //JWT  - JSON Web Token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJEYXNoIiwidWlkIjoiMWo0NTBiMzRqYWZqZ2k3Y3JxOGVuNmFxcDAiLCJzdnAiOnsiNzI3IjoiMiJ9LCJzdWIiOiIxNDYxMjMwMDMiLCJleHQiOiIxNDYxMjMwMDMifQ.SbZipAU9LWsOH-M_bHO2-DEX2-BQJlCiaqcRQLF9Dsk";
        System.out.println(Base64.base64Decode(token));

        new Date();

        System.out.println(Math.round(new Date().getTime()));


    }
}
