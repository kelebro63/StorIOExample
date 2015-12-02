package me.bitfrom.storioexample.network;


import me.bitfrom.storioexample.network.model.Quote;
import rx.Observable;

public class UmoriliLoadService {

    private final UmoriliAPI umoriliAPI;

    public UmoriliLoadService(UmoriliAPI umoriliAPI) {
        this.umoriliAPI = umoriliAPI;
    }

    public Observable<Quote> getLast(String number) {
        return umoriliAPI.getQuotes(RequestKeeper.SITE, RequestKeeper.NAME, number);
    }

    public Observable<Quote> getRandom(String number) {
        return umoriliAPI.getRandomQuotes(number);
    }
}
