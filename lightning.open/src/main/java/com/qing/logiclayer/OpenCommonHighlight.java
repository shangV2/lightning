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

public enum OpenCommonHighlight implements org.apache.thrift.TEnum {
  OC_HIGHLIGHT_OFF(0),
  OC_HIGHLIGHT_ON(1);

  private final int value;

  private OpenCommonHighlight(int value) {
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
  public static OpenCommonHighlight findByValue(int value) { 
    switch (value) {
      case 0:
        return OC_HIGHLIGHT_OFF;
      case 1:
        return OC_HIGHLIGHT_ON;
      default:
        return null;
    }
  }
}
