package com.epam.service;

import com.epam.entity.History;
import com.epam.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface HistoryService {

    boolean addPurchaseToHistory(List<History> historyList);

    boolean clearHistory(User user);

    Timestamp currentTimeGeneration();

    List<History> getHistory(User user);
}
