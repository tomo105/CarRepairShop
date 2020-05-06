package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.history.History;
import java.util.Collection;

public interface HistoryDao {

    Collection<History> getAllHistory();

}
