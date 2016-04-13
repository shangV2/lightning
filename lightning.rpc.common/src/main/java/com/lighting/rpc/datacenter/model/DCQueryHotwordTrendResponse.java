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

public class DCQueryHotwordTrendResponse implements org.apache.thrift.TBase<DCQueryHotwordTrendResponse, DCQueryHotwordTrendResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCQueryHotwordTrendResponse");

  private static final org.apache.thrift.protocol.TField TRENDS_FIELD_DESC = new org.apache.thrift.protocol.TField("trends", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCQueryHotwordTrendResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCQueryHotwordTrendResponseTupleSchemeFactory());
  }

  public List<DCTimeTrend> trends; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TRENDS((short)1, "trends");

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
        case 1: // TRENDS
          return TRENDS;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TRENDS, new org.apache.thrift.meta_data.FieldMetaData("trends", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DCTimeTrend.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCQueryHotwordTrendResponse.class, metaDataMap);
  }

  public DCQueryHotwordTrendResponse() {
  }

  public DCQueryHotwordTrendResponse(
    List<DCTimeTrend> trends)
  {
    this();
    this.trends = trends;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCQueryHotwordTrendResponse(DCQueryHotwordTrendResponse other) {
    if (other.isSetTrends()) {
      List<DCTimeTrend> __this__trends = new ArrayList<DCTimeTrend>();
      for (DCTimeTrend other_element : other.trends) {
        __this__trends.add(new DCTimeTrend(other_element));
      }
      this.trends = __this__trends;
    }
  }

  public DCQueryHotwordTrendResponse deepCopy() {
    return new DCQueryHotwordTrendResponse(this);
  }

  @Override
  public void clear() {
    this.trends = null;
  }

  public int getTrendsSize() {
    return (this.trends == null) ? 0 : this.trends.size();
  }

  public java.util.Iterator<DCTimeTrend> getTrendsIterator() {
    return (this.trends == null) ? null : this.trends.iterator();
  }

  public void addToTrends(DCTimeTrend elem) {
    if (this.trends == null) {
      this.trends = new ArrayList<DCTimeTrend>();
    }
    this.trends.add(elem);
  }

  public List<DCTimeTrend> getTrends() {
    return this.trends;
  }

  public DCQueryHotwordTrendResponse setTrends(List<DCTimeTrend> trends) {
    this.trends = trends;
    return this;
  }

  public void unsetTrends() {
    this.trends = null;
  }

  /** Returns true if field trends is set (has been assigned a value) and false otherwise */
  public boolean isSetTrends() {
    return this.trends != null;
  }

  public void setTrendsIsSet(boolean value) {
    if (!value) {
      this.trends = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TRENDS:
      if (value == null) {
        unsetTrends();
      } else {
        setTrends((List<DCTimeTrend>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TRENDS:
      return getTrends();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TRENDS:
      return isSetTrends();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCQueryHotwordTrendResponse)
      return this.equals((DCQueryHotwordTrendResponse)that);
    return false;
  }

  public boolean equals(DCQueryHotwordTrendResponse that) {
    if (that == null)
      return false;

    boolean this_present_trends = true && this.isSetTrends();
    boolean that_present_trends = true && that.isSetTrends();
    if (this_present_trends || that_present_trends) {
      if (!(this_present_trends && that_present_trends))
        return false;
      if (!this.trends.equals(that.trends))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCQueryHotwordTrendResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCQueryHotwordTrendResponse typedOther = (DCQueryHotwordTrendResponse)other;

    lastComparison = Boolean.valueOf(isSetTrends()).compareTo(typedOther.isSetTrends());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTrends()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.trends, typedOther.trends);
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
    StringBuilder sb = new StringBuilder("DCQueryHotwordTrendResponse(");
    boolean first = true;

    sb.append("trends:");
    if (this.trends == null) {
      sb.append("null");
    } else {
      sb.append(this.trends);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (trends == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'trends' was not present! Struct: " + toString());
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DCQueryHotwordTrendResponseStandardSchemeFactory implements SchemeFactory {
    public DCQueryHotwordTrendResponseStandardScheme getScheme() {
      return new DCQueryHotwordTrendResponseStandardScheme();
    }
  }

  private static class DCQueryHotwordTrendResponseStandardScheme extends StandardScheme<DCQueryHotwordTrendResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCQueryHotwordTrendResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TRENDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list104 = iprot.readListBegin();
                struct.trends = new ArrayList<DCTimeTrend>(_list104.size);
                for (int _i105 = 0; _i105 < _list104.size; ++_i105)
                {
                  DCTimeTrend _elem106; // required
                  _elem106 = new DCTimeTrend();
                  _elem106.read(iprot);
                  struct.trends.add(_elem106);
                }
                iprot.readListEnd();
              }
              struct.setTrendsIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCQueryHotwordTrendResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.trends != null) {
        oprot.writeFieldBegin(TRENDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.trends.size()));
          for (DCTimeTrend _iter107 : struct.trends)
          {
            _iter107.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCQueryHotwordTrendResponseTupleSchemeFactory implements SchemeFactory {
    public DCQueryHotwordTrendResponseTupleScheme getScheme() {
      return new DCQueryHotwordTrendResponseTupleScheme();
    }
  }

  private static class DCQueryHotwordTrendResponseTupleScheme extends TupleScheme<DCQueryHotwordTrendResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCQueryHotwordTrendResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.trends.size());
        for (DCTimeTrend _iter108 : struct.trends)
        {
          _iter108.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCQueryHotwordTrendResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list109 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.trends = new ArrayList<DCTimeTrend>(_list109.size);
        for (int _i110 = 0; _i110 < _list109.size; ++_i110)
        {
          DCTimeTrend _elem111; // required
          _elem111 = new DCTimeTrend();
          _elem111.read(iprot);
          struct.trends.add(_elem111);
        }
      }
      struct.setTrendsIsSet(true);
    }
  }

}

