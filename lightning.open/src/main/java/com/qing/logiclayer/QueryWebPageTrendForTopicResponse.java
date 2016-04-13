/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.qing.logiclayer;

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

public class QueryWebPageTrendForTopicResponse implements org.apache.thrift.TBase<QueryWebPageTrendForTopicResponse, QueryWebPageTrendForTopicResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryWebPageTrendForTopicResponse");

  private static final org.apache.thrift.protocol.TField LOGID_FIELD_DESC = new org.apache.thrift.protocol.TField("logid", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField TRENDS_FIELD_DESC = new org.apache.thrift.protocol.TField("trends", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new QueryWebPageTrendForTopicResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new QueryWebPageTrendForTopicResponseTupleSchemeFactory());
  }

  public long logid; // required
  public List<DateNumberTopicTrend> trends; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LOGID((short)1, "logid"),
    TRENDS((short)2, "trends");

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
        case 1: // LOGID
          return LOGID;
        case 2: // TRENDS
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
  private static final int __LOGID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LOGID, new org.apache.thrift.meta_data.FieldMetaData("logid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TRENDS, new org.apache.thrift.meta_data.FieldMetaData("trends", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DateNumberTopicTrend.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryWebPageTrendForTopicResponse.class, metaDataMap);
  }

  public QueryWebPageTrendForTopicResponse() {
    this.logid = 1001L;

  }

  public QueryWebPageTrendForTopicResponse(
    long logid,
    List<DateNumberTopicTrend> trends)
  {
    this();
    this.logid = logid;
    setLogidIsSet(true);
    this.trends = trends;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryWebPageTrendForTopicResponse(QueryWebPageTrendForTopicResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.logid = other.logid;
    if (other.isSetTrends()) {
      List<DateNumberTopicTrend> __this__trends = new ArrayList<DateNumberTopicTrend>();
      for (DateNumberTopicTrend other_element : other.trends) {
        __this__trends.add(new DateNumberTopicTrend(other_element));
      }
      this.trends = __this__trends;
    }
  }

  public QueryWebPageTrendForTopicResponse deepCopy() {
    return new QueryWebPageTrendForTopicResponse(this);
  }

  @Override
  public void clear() {
    this.logid = 1001L;

    this.trends = null;
  }

  public long getLogid() {
    return this.logid;
  }

  public QueryWebPageTrendForTopicResponse setLogid(long logid) {
    this.logid = logid;
    setLogidIsSet(true);
    return this;
  }

  public void unsetLogid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LOGID_ISSET_ID);
  }

  /** Returns true if field logid is set (has been assigned a value) and false otherwise */
  public boolean isSetLogid() {
    return EncodingUtils.testBit(__isset_bitfield, __LOGID_ISSET_ID);
  }

  public void setLogidIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LOGID_ISSET_ID, value);
  }

  public int getTrendsSize() {
    return (this.trends == null) ? 0 : this.trends.size();
  }

  public java.util.Iterator<DateNumberTopicTrend> getTrendsIterator() {
    return (this.trends == null) ? null : this.trends.iterator();
  }

  public void addToTrends(DateNumberTopicTrend elem) {
    if (this.trends == null) {
      this.trends = new ArrayList<DateNumberTopicTrend>();
    }
    this.trends.add(elem);
  }

  public List<DateNumberTopicTrend> getTrends() {
    return this.trends;
  }

  public QueryWebPageTrendForTopicResponse setTrends(List<DateNumberTopicTrend> trends) {
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
    case LOGID:
      if (value == null) {
        unsetLogid();
      } else {
        setLogid((Long)value);
      }
      break;

    case TRENDS:
      if (value == null) {
        unsetTrends();
      } else {
        setTrends((List<DateNumberTopicTrend>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LOGID:
      return Long.valueOf(getLogid());

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
    case LOGID:
      return isSetLogid();
    case TRENDS:
      return isSetTrends();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryWebPageTrendForTopicResponse)
      return this.equals((QueryWebPageTrendForTopicResponse)that);
    return false;
  }

  public boolean equals(QueryWebPageTrendForTopicResponse that) {
    if (that == null)
      return false;

    boolean this_present_logid = true;
    boolean that_present_logid = true;
    if (this_present_logid || that_present_logid) {
      if (!(this_present_logid && that_present_logid))
        return false;
      if (this.logid != that.logid)
        return false;
    }

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

  public int compareTo(QueryWebPageTrendForTopicResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    QueryWebPageTrendForTopicResponse typedOther = (QueryWebPageTrendForTopicResponse)other;

    lastComparison = Boolean.valueOf(isSetLogid()).compareTo(typedOther.isSetLogid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLogid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.logid, typedOther.logid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
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
    StringBuilder sb = new StringBuilder("QueryWebPageTrendForTopicResponse(");
    boolean first = true;

    sb.append("logid:");
    sb.append(this.logid);
    first = false;
    if (!first) sb.append(", ");
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
    // alas, we cannot check 'logid' because it's a primitive and you chose the non-beans generator.
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class QueryWebPageTrendForTopicResponseStandardSchemeFactory implements SchemeFactory {
    public QueryWebPageTrendForTopicResponseStandardScheme getScheme() {
      return new QueryWebPageTrendForTopicResponseStandardScheme();
    }
  }

  private static class QueryWebPageTrendForTopicResponseStandardScheme extends StandardScheme<QueryWebPageTrendForTopicResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryWebPageTrendForTopicResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LOGID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.logid = iprot.readI64();
              struct.setLogidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TRENDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list80 = iprot.readListBegin();
                struct.trends = new ArrayList<DateNumberTopicTrend>(_list80.size);
                for (int _i81 = 0; _i81 < _list80.size; ++_i81)
                {
                  DateNumberTopicTrend _elem82; // required
                  _elem82 = new DateNumberTopicTrend();
                  _elem82.read(iprot);
                  struct.trends.add(_elem82);
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
      if (!struct.isSetLogid()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'logid' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryWebPageTrendForTopicResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(LOGID_FIELD_DESC);
      oprot.writeI64(struct.logid);
      oprot.writeFieldEnd();
      if (struct.trends != null) {
        oprot.writeFieldBegin(TRENDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.trends.size()));
          for (DateNumberTopicTrend _iter83 : struct.trends)
          {
            _iter83.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryWebPageTrendForTopicResponseTupleSchemeFactory implements SchemeFactory {
    public QueryWebPageTrendForTopicResponseTupleScheme getScheme() {
      return new QueryWebPageTrendForTopicResponseTupleScheme();
    }
  }

  private static class QueryWebPageTrendForTopicResponseTupleScheme extends TupleScheme<QueryWebPageTrendForTopicResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryWebPageTrendForTopicResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.logid);
      {
        oprot.writeI32(struct.trends.size());
        for (DateNumberTopicTrend _iter84 : struct.trends)
        {
          _iter84.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryWebPageTrendForTopicResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.logid = iprot.readI64();
      struct.setLogidIsSet(true);
      {
        org.apache.thrift.protocol.TList _list85 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.trends = new ArrayList<DateNumberTopicTrend>(_list85.size);
        for (int _i86 = 0; _i86 < _list85.size; ++_i86)
        {
          DateNumberTopicTrend _elem87; // required
          _elem87 = new DateNumberTopicTrend();
          _elem87.read(iprot);
          struct.trends.add(_elem87);
        }
      }
      struct.setTrendsIsSet(true);
    }
  }

}

