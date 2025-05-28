package com.slowcloud.copypaste.mapper;

import org.mapstruct.Mapper;

import com.slowcloud.copypaste.dto.PasteResponseDto;
import com.slowcloud.copypaste.entity.Paste;

@Mapper(componentModel = "spring")
public interface PasteResponseMapper extends AbstractMapper<Paste, PasteResponseDto> {
}
