package com.example.foodplaza_users.application.mapper.request;

import com.example.foodplaza_users.application.dto.resquest.RoleRequestDto;
import com.example.foodplaza_users.domain.model.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleRequestMapper {
    UserRole toRoleResponse(RoleRequestDto roleRequestDto);
}
