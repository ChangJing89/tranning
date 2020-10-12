package com.example.common.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * dto转换model工具类
 */
public abstract class ConvertToModel extends ModelMapper {

  public ConvertToModel() {
    this.getConfiguration().setPropertyCondition(Conditions.isNotNull()).setMatchingStrategy(
        MatchingStrategies.STRICT);
  }

  public final <D, M> M toModel(D dto, Class<M> destinationType) {
    return this.map(dto, destinationType);
  }

  public final <M, D> D toDTO(M model, Class<D> destinationType) {
    return this.map(model, destinationType);
  }

  public final <D, M> List<M> toListModel(@NotNull List<D> dtoList, Class<M> destinationType) {
    return dtoList.stream().map(dto -> this.toModel(dto, destinationType)).collect(Collectors.toList());
  }

  public final <M, D> List<D> toListDTO(@NotNull List<M> list, Class<D> destinationType) {
    return list.stream().map(model -> this.toDTO(model, destinationType)).collect(Collectors.toList());
  }

}
