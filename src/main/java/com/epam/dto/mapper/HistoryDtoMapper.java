package com.epam.dto.mapper;

import com.epam.dto.HistoryDto;
import com.epam.entity.Entity;
import com.epam.entity.History;

public class HistoryDtoMapper implements DtoMapper<HistoryDto> {

    @Override
    public HistoryDto mapFromEntityToDto(Entity entity) {
        History history = (History) entity;
        return new HistoryDto(history.getId(), history.getNameProduct(),
                history.getPrice(), history.getCategory(), history.getGuarantee(),
                history.getUserID(), history.getDate());
    }
}
