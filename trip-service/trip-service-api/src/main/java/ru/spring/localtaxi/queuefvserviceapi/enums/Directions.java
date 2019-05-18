package ru.spring.localtaxi.queuefvserviceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Directions {

  FV("Поселок -> Город"),
  VF("Город -> Поселок");

  private final String name;
}
