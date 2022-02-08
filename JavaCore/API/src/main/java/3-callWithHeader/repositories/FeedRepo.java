package repositories;

import models.Account;
import models.Feed;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedRepo {
    private String token = "Bearer eyJhbGciOiJQUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_31Uy47bMAz8lcLn5cJx_L711h_oB9ASlQiRJUOSd7so-u-VLTmO06C3zAwfQ4rO70w6l_UZThI4jebdebRK6suA-vbOzJi9ZW4eQkQuWtHkeQeiawooGzYA5jyH5syqgvMiZ6UIwfRryvpTXZ7b07lt67dMoo9EUzTlQiBjZtb-h1Gc7E_JQ-0TUSNYPkBDRQVlJwi6VggohqrtTp0oWuKhtjc30jEjr9oCu6GCojwvbpoWuqLMQdSc8XPd5KdyCBlhrO-MkXMxi2GoSANBLjCHcmhL6ErRQVPWoirrugrNloGZmWhZSnQKTBlHvLeE_NvGXVf7oHGkl4L_mp4EyUl7KSTZI6-k8wcmAc5tMN4Tl_4OouI9sutI98gdf1rp6RvO_mqsdOEZQWouPySfUcXgARVqlqwxtByY0d4aFRstTNKMFtKO6KXRYASIWXN3l9y9-wZiazY7b8ZtRBpRpsKKghF96XGa1NcdrVEjao6eek6KQokNJs3eyC-DTJYEWQre3f-kaCNqk0JGYQOeLnad4zHxXzGlkmVX3KYbyWNwgz0LcFUTXoea8ItokyJIQ0SwB4Ec8ZJmitr-E7xF7ZDtDgMNw6xu_faStFN7t4j3hhFvBZZ7CLc1Sr_XVIaFO3iosBJgloN4ZlOWNUKqzX6c50CtUZYYyckfgDtKcbkOP8KDObiY3ceBS9McuLXOIxMXFp78VYldfFFrF2NRdiU-K-KQFpZo8j4MOE8JTrh9MOHfcT1cMJY_tD-yW98j-yIfzKe-857WF2Pu45mauEjUPDhmwzaXQ9m6PHJr1OM1rQ_2fF7Zn7-FyfeC9gUAAA.PlF4VI-5IxRQYrEeYz0GYos-Nd8Sj0q71NPepwA1KsJMYUAtugTES2lH8imZe67uFcZuliEs_QkjUlEKbjWHaYznoi3-VPzbLrleu0ZAPte1mRU8aETHKdaWMd31sAE4pIMEUlOxUEkr9nq5ze9aMpv_oDHdUJdILoVFlXJUZckAKrp3LPdQqKsS8H-cx_NLTiIlvSzh07tTJfaAzxF82hHK13aE2bIBnk_vJlLB1lXEwvVgz37nQ6T71XZ3Nzflzunr0QCd5T-MI0pXVSBAAetBADwZsiX7DFNUsOG6RnWJ_zgJUQjaMN-EWoAUYd1ceRjSQAQ-4Y6GP-ECxz3nMbFwZQ1S_1FhZmEUBHtkIUvDKAeBmTtV4wkq_t4gP-ItKEl5MkDD0V4R_BUvId6u6HvwxhSiuB275t8PDebdZt5hCxTiXeD_B2CVNuSIK_ZRbwmaNWJ6ykNfPePsbn9oONbGdZjBsg13ZaFhrW3jVI6WsmXBo44tun6AFudlpBAkyQf0DUaaCnYf7Qyqqsn5vHzAS766tHKHeLUOXeTNAHHCIuGqFAG1rWsY0zKWsnD5V-fAgQ0N3nrsQrgkPW5Kt8A49KHcWUaB1kDmyT7l_63FbhyrLojHSdHobR697VhdwidCcf5fa6guvSpMKbe5MQbEoft6JOOvg0c4DM6OPYo";

    public List<Feed> fetch_feed(Account account) throws Exception {
        String feed_api = "https://api-sandbox.starlingbank.com/api/v2/feed/" +
                "account/"+account.getAccountUid() +
                "/category/"+account.getDefaultCategory() +
                "?changesSince="+account.getCreatedAt();

        URL url = new URL(feed_api);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/json");
        conn.addRequestProperty("User-Agent","TUAN CONG NGUYEN");
        conn.setRequestProperty("Authorization", token);
        conn.connect();

        int code = conn.getResponseCode();

        if (code != 200) {
            throw new RuntimeException("HttpResponseCode: " + code);
        } else {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inline;
            StringBuilder builder = new StringBuilder();

            while ((inline = bufferedReader.readLine()) != null) {
                builder.append(inline);
            }
            bufferedReader.close();

            return read_feed(builder.toString());
        }
    }

    public List<Feed> read_feed(String inline) throws Exception {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(inline);
        JSONArray jsonArray = (JSONArray) jsonObject.get("feedItems");

        List<Feed> feeds = new ArrayList<>();
        jsonArray.forEach(feed -> feeds.add(parseObject((JSONObject) feed)));

        List<Feed> feeds_out = new ArrayList<>();
        feeds.forEach(feed -> {
            if (feed.getDirection().equals("OUT")){
                feeds_out.add(feed);
            }
        });
        return feeds_out;
    }

    private Feed parseObject(JSONObject obj) {
        return new Feed().fromJson(obj);
    }
}