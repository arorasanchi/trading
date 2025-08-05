package com.sanchi.service;

import com.sanchi.model.Coin;
import com.sanchi.model.User;
import com.sanchi.model.Watchlist;

public interface WatchlistService {
    Watchlist findUserWatchlist(Long userId) throws Exception;

    Watchlist createWatchlist(User user);

    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin, User user) throws Exception;



}
