/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.lighting.rpc.datacenter.model;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DCAddHotWebEventsResponse implements org.apache.thrift.TBase<DCAddHotWebEventsResponse, DCAddHotWebEventsResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCAddHotWebEventsResponse");

  private static final org.apache.thrift.protocol.TField SUCCESS_AMOUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("successAmount", org.apache.thrift.protocol.TType.I32, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCAddHotWebEventsResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCAddHotWebEventsResponseTupleSchemeFactory());
  }

  public int successAmount; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SUCCESS_AMOUNT((short)1, "successAmount");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SUCCESS_AMOUNT
          return SUCCESS_AMOUNT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __SUCCESSAMOUNT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SUCCESS_AMOUNT, new org.apache.thrift.meta_data.FieldMetaData("successAmount", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCAddHotWebEventsResponse.class, metaDataMap);
  }

  public DCAddHotWebEventsResponse() {
  }

  public DCAddHotWebEventsResponse(
    int successAmount)
  {
    this();
    this.successAmount = successAmount;
    setSuccessAmountIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCAddHotWebEventsResponse(DCAddHotWebEventsResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.successAmount = other.successAmount;
  }

  public DCAddHotWebEventsResponse deepCopy() {
    return new DCAddHotWebEventsResponse(this);
  }

  @Override
  public void clear() {
    setSuccessAmountIsSet(false);
    this.successAmount = 0;
  }

  public int getSuccessAmount() {
    return this.successAmount;
  }

  public DCAddHotWebEventsResponse setSuccessAmount(int successAmount) {
    this.successAmount = successAmount;
    setSuccessAmountIsSet(true);
    return this;
  }

  public void unsetSuccessAmount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SUCCESSAMOUNT_ISSET_ID);
  }

  /** Returns true if field successAmount is set (has been assigned a value) and false otherwise */
  public boolean isSetSuccessAmount() {
    return EncodingUtils.testBit(__isset_bitfield, __SUCCESSAMOUNT_ISSET_ID);
  }

  public void setSuccessAmountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SUCCESSAMOUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SUCCESS_AMOUNT:
      if (value == null) {
        unsetSuccessAmount();
      } else {
        setSuccessAmount((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SUCCESS_AMOUNT:
      return Integer.valueOf(getSuccessAmount());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SUCCESS_AMOUNT:
      return isSetSuccessAmount();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCAddHotWebEventsResponse)
      return this.equals((DCAddHotWebEventsResponse)that);
    return false;
  }

  public boolean equals(DCAddHotWebEventsResponse that) {
    if (that == null)
      return false;

    boolean this_present_successAmount = true;
    boolean that_present_successAmount = true;
    if (this_present_successAmount || that_present_successAmount) {
      if (!(this_present_successAmount && that_present_successAmount))
        return false;
      if (this.successAmount != that.successAmount)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCAddHotWebEventsResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCAddHotWebEventsResponse typedOther = (DCAddHotWebEventsResponse)other;

    lastComparison = Boolean.valueOf(isSetSuccessAmount()).compareTo(typedOther.isSetSuccessAmount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSuccessAmount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.successAmount, typedOther.successAmount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DCAddHotWebEventsResponse(");
    boolean first = true;

    sb.append("successAmount:");
    sb.append(this.successAmount);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'successAmount' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DCAddHotWebEventsResponseStandardSchemeFactory implements SchemeFactory {
    public DCAddHotWebEventsResponseStandardScheme getScheme() {
      return new DCAddHotWebEventsResponseStandardScheme();
    }
  }

  private static class DCAddHotWebEventsResponseStandardScheme extends StandardScheme<DCAddHotWebEventsResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCAddHotWebEventsResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SUCCESS_AMOUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.successAmount = iprot.readI32();
              struct.setSuccessAmountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetSuccessAmount()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'successAmount' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCAddHotWebEventsResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SUCCESS_AMOUNT_FIELD_DESC);
      oprot.writeI32(struct.successAmount);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCAddHotWebEventsResponseTupleSchemeFactory implements SchemeFactory {
    public DCAddHotWebEventsResponseTupleScheme getScheme() {
      return new DCAddHotWebEventsResponseTupleScheme();
    }
  }

  private static class DCAddHotWebEventsResponseTupleScheme extends TupleScheme<DCAddHotWebEventsResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCAddHotWebEventsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.successAmount);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCAddHotWebEventsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.successAmount = iprot.readI32();
      struct.setSuccessAmountIsSet(true);
    }
  }

}

