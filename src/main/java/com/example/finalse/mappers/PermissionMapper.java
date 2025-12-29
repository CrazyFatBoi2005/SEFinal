package com.example.finalse.mappers;

import com.example.finalse.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    default String toString(Permission p) { return p == null ? null : p.getPermission(); }
}