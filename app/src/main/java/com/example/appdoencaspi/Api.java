package com.example.appdoencaspi;

public class Api {

    private static final String ROOT_URL = "http://192.168.0.200/DoencasApi/v1/Api.php?apicall=";

    public static final String URL_CREATE_DOENCAS = ROOT_URL + "createDoenca";
    public static final String URL_READ_DOENCAS = ROOT_URL + "getDoenca";
    public static final String URL_UPDATE_DOENCAS = ROOT_URL + "updateDoenca";
    public static final String URL_DELETE_DOENCAS = ROOT_URL + "deleteDoenca&id=";

    public static final String URL_CREATE_LOGIN = ROOT_URL + "createLogin";
    public static final String URL_READ_LOGIN = ROOT_URL + "getLogin";
    public static final String URL_UPDATE_LOGIN = ROOT_URL + "updateLogin";
    public static final String URL_DELETE_LOGIN = ROOT_URL + "deleteLogin&id=";
}
