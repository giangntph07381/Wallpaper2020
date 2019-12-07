package com.developer.wallpaper;

import android.os.AsyncTask;
import android.util.Log;

import com.developer.wallpaper.mvp.model.Post;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DowloadUrlAsyncTask  extends AsyncTask<String,Void, List<Post>> {

    private static final String TAG = "DownloadTask";

    private Listen listen;
    private int i=0;


    public DowloadUrlAsyncTask(Listen listen) {
        this.listen = listen;
    }

    @Override
    protected List<Post> doInBackground(String... strings) {
        Document document = null;
        List<Post> list=new ArrayList<>();
        list.clear();
        try {
            document = (Document) Jsoup.connect(strings[0]).get();
            Elements sub = document.select("div.flex_grid.credits.search_results > div.item > a");
            for (Element element : sub) {
                Log.e("I",i+++"");
            Element urls=element.getElementsByTag("img").first();
            if (i<17){
                String img[]=urls.attr("src").split("__");
                list.add(new Post(img[0]+"_640.jpg"));
            }else {
                String img[]=urls.attr("data-lazy").split("__");
                list.add(new Post(img[0]+"_640.jpg"));
            }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("Listssss",list.size()+"");
        return list;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Bat dau
    }

    @Override
    protected void onPostExecute(List<Post> strings) {
        super.onPostExecute(strings);
        if (strings==null){
            listen.ThatBai();
        }else {
            listen.ThanhCong(strings);
        }
        //du lieu tra be
    }
}
