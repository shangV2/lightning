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

public class QueryShortMessageForTopicAtTimestampRequest implements org.apache.thrift.TBase<QueryShortMessageForTopicAtTimestampRequest, QueryShortMessageForTopicAtTimestampRequest._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryShortMessageForTopicAtTimestampRequest");

  private static final org.apache.thrift.protocol.TField LANGUGE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("langugeType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TOPIC_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("topicId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new QueryShortMessageForTopicAtTimestampRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new QueryShortMessageForTopicAtTimestampRequestTupleSchemeFactory());
  }

  /**
   * 
   * @see OpenCommonLanguge
   */
  public OpenCommonLanguge langugeType; // required
  public long topicId; // required
  public String timestamp; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see OpenCommonLanguge
     */
    LANGUGE_TYPE((short)1, "langugeType"),
    TOPIC_ID((short)2, "topicId"),
    TIMESTAMP((short)3, "timestamp");

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
        case 1: // LANGUGE_TYPE
          return LANGUGE_TYPE;
        case 2: // TOPIC_ID
          return TOPIC_ID;
        case 3: // TIMESTAMP
          return TIMESTAMP;
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
  private static final int __TOPICID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LANGUGE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("langugeType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, OpenCommonLanguge.class)));
    tmpMap.put(_Fields.TOPIC_ID, new org.apache.thrift.meta_data.FieldMetaData("topicId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryShortMessageForTopicAtTimestampRequest.class, metaDataMap);
  }

  public QueryShortMessageForTopicAtTimestampRequest() {
    this.langugeType = com.qing.logiclayer.OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;

  }

  public QueryShortMessageForTopicAtTimestampRequest(
    OpenCommonLanguge langugeType,
    long topicId,
    String timestamp)
  {
    this();
    this.langugeType = langugeType;
    this.topicId = topicId;
    setTopicIdIsSet(true);
    this.timestamp = timestamp;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryShortMessageForTopicAtTimestampRequest(QueryShortMessageForTopicAtTimestampRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetLangugeType()) {
      this.langugeType = other.langugeType;
    }
    this.topicId = other.topicId;
    if (other.isSetTimestamp()) {
      this.timestamp = other.timestamp;
    }
  }

  public QueryShortMessageForTopicAtTimestampRequest deepCopy() {
    return new QueryShortMessageForTopicAtTimestampRequest(this);
  }

  @Override
  public void clear() {
    this.langugeType = com.qing.logiclayer.OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;

    setTopicIdIsSet(false);
    this.topicId = 0;
    this.timestamp = null;
  }

  /**
   * 
   * @see OpenCommonLanguge
   */
  public OpenCommonLanguge getLangugeType() {
    return this.langugeType;
  }

  /**
   * 
   * @see OpenCommonLanguge
   */
  public QueryShortMessageForTopicAtTimestampRequest setLangugeType(OpenCommonLanguge langugeType) {
    this.langugeType = langugeType;
    return this;
  }

  public void unsetLangugeType() {
    this.langugeType = null;
  }

  /** Returns true if field langugeType is set (has been assigned a value) and false otherwise */
  public boolean isSetLangugeType() {
    return this.langugeType != null;
  }

  public void setLangugeTypeIsSet(boolean value) {
    if (!value) {
      this.langugeType = null;
    }
  }

  public long getTopicId() {
    return this.topicId;
  }

  public QueryShortMessageForTopicAtTimestampRequest setTopicId(long topicId) {
    this.topicId = topicId;
    setTopicIdIsSet(true);
    return this;
  }

  public void unsetTopicId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOPICID_ISSET_ID);
  }

  /** Returns true if field topicId is set (has been assigned a value) and false otherwise */
  public boolean isSetTopicId() {
    return EncodingUtils.testBit(__isset_bitfield, __TOPICID_ISSET_ID);
  }

  public void setTopicIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOPICID_ISSET_ID, value);
  }

  public String getTimestamp() {
    return this.timestamp;
  }

  public QueryShortMessageForTopicAtTimestampRequest setTimestamp(String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public void unsetTimestamp() {
    this.timestamp = null;
  }

  /** Returns true if field timestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return this.timestamp != null;
  }

  public void setTimestampIsSet(boolean value) {
    if (!value) {
      this.timestamp = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case LANGUGE_TYPE:
      if (value == null) {
        unsetLangugeType();
      } else {
        setLangugeType((OpenCommonLanguge)value);
      }
      break;

    case TOPIC_ID:
      if (value == null) {
        unsetTopicId();
      } else {
        setTopicId((Long)value);
      }
      break;

    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LANGUGE_TYPE:
      return getLangugeType();

    case TOPIC_ID:
      return Long.valueOf(getTopicId());

    case TIMESTAMP:
      return getTimestamp();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case LANGUGE_TYPE:
      return isSetLangugeType();
    case TOPIC_ID:
      return isSetTopicId();
    case TIMESTAMP:
      return isSetTimestamp();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryShortMessageForTopicAtTimestampRequest)
      return this.equals((QueryShortMessageForTopicAtTimestampRequest)that);
    return false;
  }

  public boolean equals(QueryShortMessageForTopicAtTimestampRequest that) {
    if (that == null)
      return false;

    boolean this_present_langugeType = true && this.isSetLangugeType();
    boolean that_present_langugeType = true && that.isSetLangugeType();
    if (this_present_langugeType || that_present_langugeType) {
      if (!(this_present_langugeType && that_present_langugeType))
        return false;
      if (!this.langugeType.equals(that.langugeType))
        return false;
    }

    boolean this_present_topicId = true;
    boolean that_present_topicId = true;
    if (this_present_topicId || that_present_topicId) {
      if (!(this_present_topicId && that_present_topicId))
        return false;
      if (this.topicId != that.topicId)
        return false;
    }

    boolean this_present_timestamp = true && this.isSetTimestamp();
    boolean that_present_timestamp = true && that.isSetTimestamp();
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (!this.timestamp.equals(that.timestamp))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(QueryShortMessageForTopicAtTimestampRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    QueryShortMessageForTopicAtTimestampRequest typedOther = (QueryShortMessageForTopicAtTimestampRequest)other;

    lastComparison = Boolean.valueOf(isSetLangugeType()).compareTo(typedOther.isSetLangugeType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLangugeType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.langugeType, typedOther.langugeType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTopicId()).compareTo(typedOther.isSetTopicId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTopicId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.topicId, typedOther.topicId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(typedOther.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, typedOther.timestamp);
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
    StringBuilder sb = new StringBuilder("QueryShortMessageForTopicAtTimestampRequest(");
    boolean first = true;

    sb.append("langugeType:");
    if (this.langugeType == null) {
      sb.append("null");
    } else {
      sb.append(this.langugeType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("topicId:");
    sb.append(this.topicId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("timestamp:");
    if (this.timestamp == null) {
      sb.append("null");
    } else {
      sb.append(this.timestamp);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (langugeType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'langugeType' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'topicId' because it's a primitive and you chose the non-beans generator.
    if (timestamp == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'timestamp' was not present! Struct: " + toString());
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

  private static class QueryShortMessageForTopicAtTimestampRequestStandardSchemeFactory implements SchemeFactory {
    public QueryShortMessageForTopicAtTimestampRequestStandardScheme getScheme() {
      return new QueryShortMessageForTopicAtTimestampRequestStandardScheme();
    }
  }

  private static class QueryShortMessageForTopicAtTimestampRequestStandardScheme extends StandardScheme<QueryShortMessageForTopicAtTimestampRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryShortMessageForTopicAtTimestampRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LANGUGE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.langugeType = OpenCommonLanguge.findByValue(iprot.readI32());
              struct.setLangugeTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOPIC_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.topicId = iprot.readI64();
              struct.setTopicIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.timestamp = iprot.readString();
              struct.setTimestampIsSet(true);
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
      if (!struct.isSetTopicId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'topicId' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryShortMessageForTopicAtTimestampRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.langugeType != null) {
        oprot.writeFieldBegin(LANGUGE_TYPE_FIELD_DESC);
        oprot.writeI32(struct.langugeType.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TOPIC_ID_FIELD_DESC);
      oprot.writeI64(struct.topicId);
      oprot.writeFieldEnd();
      if (struct.timestamp != null) {
        oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
        oprot.writeString(struct.timestamp);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryShortMessageForTopicAtTimestampRequestTupleSchemeFactory implements SchemeFactory {
    public QueryShortMessageForTopicAtTimestampRequestTupleScheme getScheme() {
      return new QueryShortMessageForTopicAtTimestampRequestTupleScheme();
    }
  }

  private static class QueryShortMessageForTopicAtTimestampRequestTupleScheme extends TupleScheme<QueryShortMessageForTopicAtTimestampRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryShortMessageForTopicAtTimestampRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.langugeType.getValue());
      oprot.writeI64(struct.topicId);
      oprot.writeString(struct.timestamp);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryShortMessageForTopicAtTimestampRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.langugeType = OpenCommonLanguge.findByValue(iprot.readI32());
      struct.setLangugeTypeIsSet(true);
      struct.topicId = iprot.readI64();
      struct.setTopicIdIsSet(true);
      struct.timestamp = iprot.readString();
      struct.setTimestampIsSet(true);
    }
  }

}
