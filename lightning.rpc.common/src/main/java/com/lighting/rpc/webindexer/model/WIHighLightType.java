/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.lighting.rpc.webindexer.model;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum WIHighLightType implements org.apache.thrift.TEnum {
  WI_HIGHLIGHT_OFF(0),
  WI_HIGHLIGHT_ON(1);

  private final int value;

  private WIHighLightType(int value) {
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
  public static WIHighLightType findByValue(int value) { 
    switch (value) {
      case 0:
        return WI_HIGHLIGHT_OFF;
      case 1:
        return WI_HIGHLIGHT_ON;
      default:
        return null;
    }
  }
}
