package com.epam.service;

import com.epam.entity.History;
import com.epam.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface HistoryService {
    /**
     * Method for add the purchase to the story.
     *
     * @param historyList
     * @return true if the purchase is added to the history
     */
    boolean addPurchaseToHistory(List<History> historyList);
    /**
     * Method for clearing user history.
     *
     * @param user
     * @return true if cleared history
     */
    boolean clearHistory(User user);
    /**
     * Method getting the time at the moment.
     *
     * @return the time at the moment.
     */
    Timestamp currentTimeGeneration();
    /**
     * Method getting user history.
     *
     * @param user
     * @return history list
     */
    List<History> getHistory(User user);
}
