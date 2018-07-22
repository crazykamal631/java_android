package web.com.shoppingcart.server.api;


import retrofit2.Call;
import retrofit2.http.GET;
import web.com.shoppingcart.server.pojo.Response;

public interface APIInterfaceWeatherService {
    @GET("json")
    //Observable<Response> doGetWeatherInfoFiveDays();
    Call<Response> doGetWeatherInfoFiveDays();
}
