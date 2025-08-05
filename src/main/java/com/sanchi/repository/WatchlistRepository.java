package com.sanchi.repository;

import com.sanchi.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WatchlistRepository extends JpaRepository<Watchlist,Long> {

    Watchlist findByUserId(Long userId);
}
