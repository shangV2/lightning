/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.qing.logiclayer;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum TermCategoryType implements org.apache.thrift.TEnum {
  TCT_HOTWORD_TYPE(0),
  TCT_SENSWORD_TYPE(1),
  TCT_UNUSWORD_TYPE(2);

  private final int value;

  private TermCategoryType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TermCategoryType findByValue(int value) { 
    switch (value) {
      case 0:
        return TCT_HOTWORD_TYPE;
      case 1:
        return TCT_SENSWORD_TYPE;
      case 2:
        return TCT_UNUSWORD_TYPE;
      default:
        return null;
    }
  }
}