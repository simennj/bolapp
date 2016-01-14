package no.polguide.bol;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;

public class ProductGenerator {

    public static final String API_URL = "http://polguide.no";
    private Retrofit retrofit;
    private Polguide polguide;

    public ProductGenerator() {

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        OkHttpClient httpClient = new OkHttpClient();

//        httpClient.interceptors().add(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient)
                .build();

        polguide = retrofit.create(Polguide.class);
    }

    public interface Polguide {

        @GET("/static/bol.json")
        Call<List<Product>> pages();
    }


    public void getPage(Callback<List<Product>> pageCallback) throws IOException{
        Call<List<Product>> call = polguide.pages();
        call.enqueue(pageCallback);
    }


    public List<Product> getPage() throws IOException {
        Call<List<Product>> call = polguide.pages();
        List<Product> page = null;
        page = call.execute().body();
        return page;
    }

    public static void main(String... args) throws IOException {
        ProductGenerator productGenerator = new ProductGenerator();
        Call<List<Product>> call = productGenerator.polguide.pages();
        List<Product> page = call.execute().body();
        for (Product product : page) {
            System.out.println(product);
        }

    }
}