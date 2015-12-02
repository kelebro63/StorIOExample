package me.bitfrom.storioexample.network;

import me.bitfrom.storioexample.network.model.Quote;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface UmoriliAPI {

    @GET(RequestKeeper.GET)
    Observable<Quote> getQuotes(@Query("site") String site, @Query("name") String name,
                                @Query("num") String num);

    @GET(RequestKeeper.RANDOM)
    Observable<Quote> getRandomQuotes(@Query("num") String num);

}
