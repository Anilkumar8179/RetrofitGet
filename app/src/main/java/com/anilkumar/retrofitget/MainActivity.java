package com.anilkumar.retrofitget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.net.URI;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.Text_view);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyInterface myInterface=retrofit.create(MyInterface.class);
        Call<List<PostModal>>call=myInterface.getModal();
        call.enqueue(new Callback<List<PostModal>>() {
            @Override
            public void onResponse(Call<List<PostModal>> call, Response<List<PostModal>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:"+response.code());
                    return;
                }
                List<PostModal>postModals=response.body();
                 for (PostModal postModal:postModals){
                     String content="";
                     content+="ID:"+postModal.getId()+"\n";
                     content+="userId:"+postModal.getUserId()+"\n";
                     content+="body:"+postModal.getBody()+"\n";
                     content+="title:"+postModal.getTitle()+"\n\n";
                     textView.append(content);





                 }

            }

            @Override
            public void onFailure(Call<List<PostModal>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });

    }
}