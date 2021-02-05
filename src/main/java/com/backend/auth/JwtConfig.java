package com.backend.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "alguna.clave.secreta.123456789";
    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEogIBAAKCAQEA7z1PW5glXzURy7HfurHx3pkbkmcF2b5jCfypr0nM5tAaCkDt\n" +
            "P5yZ3HZpvwPXYCq2EjD7H7ld91woAT1jjmuxUDJ5WXFaiqB52LohJ226DCIoengS\n" +
            "1ogwNIOG+cFidf4NDl3GkEmF8Isd9wpXcS+DpD+jAd8t0IdQpo48JHJUIZUDN/19\n" +
            "uxMDhqAzLqbMRIXStINwRPEGzx8M0IuK48aCaQ+O+X7+qPezGK3bzfiqDZ2iVyec\n" +
            "rVmeN1e37Hm27X7Gp5qt/qqlLYCpbRJZailbMsZ9+bj6l/nqU66KLn16OjNmR+h0\n" +
            "RJBL6SO633rVY907uLQMK7yhvzPKAOnL9w2RNwIDAQABAoIBAFQu2b7cQcJHtVRc\n" +
            "6eIS/Y4biCUzFU7yfH0mKDtjF7PcZF1ZTWKF42o9TKaHXoEbZmfuTl9yZctF5VnS\n" +
            "VTbnVERpDmRXy8leAKIvlL0AEmh0mJPHrrFIyNWhtpwp3VCxrbxxaQ6aoIPtIAUu\n" +
            "4WgVlRf1wJFHDCMFim6hPC6DAHG0BHRcBuqhJGSMLgDGZRCAEU7RGP8oTNOZcAhJ\n" +
            "nxEJy7nj8+nWNW5BpvBl831BZ4AMe/8fZJsgN3pJ7NDzPHoJzXkTN/0Ta/wWInQ7\n" +
            "edSx0e1St1b7RYNIMJ3x3CbsCURIpKQa7GMXbaISE1DPfUkXcDiTIBrzxjgQiTkf\n" +
            "c90V2FECgYEA/DHoNZQO/Sx7DBC83cpG+KkHczZN/1qvlVhZAMYA5sQamoOSLZl+\n" +
            "Zbb7WVcnIOZzgWQcha74iX4AmtxldJk0k4ihBGBvlVCO+vUx+CR605VN+0voNNLr\n" +
            "K6HYSvo5hrSMU5hkgPAKWKAzCgjbHk/MWkYGVWeCQcmxmPVDisEyR28CgYEA8tlc\n" +
            "7LH0gWB0in5STgwREsiHnQIwd5CbV4YfNXETRe0M3l87uDM9hXDrAPrYAqX4rFwq\n" +
            "V5WniEHsJKhDFuYtDc0CSY7UPm/rfgT7Y1LwsiQ1G+XySsO9aLdLFXHUVvvB0O0W\n" +
            "HKAttisddPBNell48BnjlFpCYHMCDLRjXDw+LrkCgYArGliGt7xsFusc38t32LeS\n" +
            "jkkgrtpb12MFEml70VucFU46tFwKgR5yQfjphxS/8cMyDSs2yfXegfOWhC6u4/Rx\n" +
            "druWZOkNSwx05e1ZbNq1SOJgHdQdGyNofKvb/LJSTI9jRjt/MgeCqDaw81yH7LmP\n" +
            "gEJa3GsN1WwOKXUWxPhT7wKBgGAEgTE0yy+8K1f2yiQBsV5z2urBSth7XHAmn4BJ\n" +
            "zQ0AxO3HSZ1y5Qg1tTUV+QJTDRdY+YTXrv240t1Lifsr1vR18kmTJ2foaHvTDHIh\n" +
            "APnuRvFY4YueCO22SwGXmBUsRV2N4AhBbNFyCpuJx5hwd9i247xsU4I+9rh/Oinl\n" +
            "x9BBAoGAHkZr9YAUhWnP3xb1aMrgDPzm+dwyDAK7njyVL7oxO5zDeiRaGr03spUe\n" +
            "KmRTi7IzHrjrltp4WMaNDe5indbwL9lgvGIvU21lq6udVo2cTs//yRslYEsUm8sG\n" +
            "j7GjLI3LJ2zGyVGJL9F2jK+O3OW6KXRJD8ddgAhcTzof9U26bjU=\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7z1PW5glXzURy7HfurHx\n" +
            "3pkbkmcF2b5jCfypr0nM5tAaCkDtP5yZ3HZpvwPXYCq2EjD7H7ld91woAT1jjmux\n" +
            "UDJ5WXFaiqB52LohJ226DCIoengS1ogwNIOG+cFidf4NDl3GkEmF8Isd9wpXcS+D\n" +
            "pD+jAd8t0IdQpo48JHJUIZUDN/19uxMDhqAzLqbMRIXStINwRPEGzx8M0IuK48aC\n" +
            "aQ+O+X7+qPezGK3bzfiqDZ2iVyecrVmeN1e37Hm27X7Gp5qt/qqlLYCpbRJZailb\n" +
            "MsZ9+bj6l/nqU66KLn16OjNmR+h0RJBL6SO633rVY907uLQMK7yhvzPKAOnL9w2R\n" +
            "NwIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
