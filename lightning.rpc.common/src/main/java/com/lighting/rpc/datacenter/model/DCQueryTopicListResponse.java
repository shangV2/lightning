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

public class DCQueryTopicListResponse implements org.apache.thrift.TBase<DCQueryTopicListResponse, DCQueryTopicListResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCQueryTopicListResponse");

  private static final org.apache.thrift.protocol.TField TOTAL_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("totalNum", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TOPICS_FIELD_DESC = new org.apache.thrift.protocol.TField("topics", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCQueryTopicListResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCQueryTopicListResponseTupleSchemeFactory());
  }

  public int totalNum; // required
  public List<DCConsistentTopic> topics; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TOTAL_NUM((short)1, "totalNum"),
    TOPICS((short)2, "topics");

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
        case 1: // TOTAL_NUM
          return TOTAL_NUM;
        case 2: // TOPICS
          return TOPICS;
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
  private static final int __TOTALNUM_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TOTAL_NUM, new org.apache.thrift.meta_data.FieldMetaData("totalNum", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TOPICS, new org.apache.thrift.meta_data.FieldMetaData("topics", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DCConsistentTopic.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCQueryTopicListResponse.class, metaDataMap);
  }

  public DCQueryTopicListResponse() {
  }

  public DCQueryTopicListResponse(
    int totalNum,
    List<DCConsistentTopic> topics)
  {
    this();
    this.totalNum = totalNum;
    setTotalNumIsSet(true);
    this.topics = topics;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCQueryTopicListResponse(DCQueryTopicListResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.totalNum = other.totalNum;
    if (other.isSetTopics()) {
      List<DCConsistentTopic> __this__topics = new ArrayList<DCConsistentTopic>();
      for (DCConsistentTopic other_element : other.topics) {
        __this__topics.add(new DCConsistentTopic(other_element));
      }
      this.topics = __this__topics;
    }
  }

  public DCQueryTopicListResponse deepCopy() {
    return new DCQueryTopicListResponse(this);
  }

  @Override
  public void clear() {
    setTotalNumIsSet(false);
    this.totalNum = 0;
    this.topics = null;
  }

  public int getTotalNum() {
    return this.totalNum;
  }

  public DCQueryTopicListResponse setTotalNum(int totalNum) {
    this.totalNum = totalNum;
    setTotalNumIsSet(true);
    return this;
  }

  public void unsetTotalNum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTALNUM_ISSET_ID);
  }

  /** Returns true if field totalNum is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalNum() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTALNUM_ISSET_ID);
  }

  public void setTotalNumIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTALNUM_ISSET_ID, value);
  }

  public int getTopicsSize() {
    return (this.topics == null) ? 0 : this.topics.size();
  }

  public java.util.Iterator<DCConsistentTopic> getTopicsIterator() {
    return (this.topics == null) ? null : this.topics.iterator();
  }

  public void addToTopics(DCConsistentTopic elem) {
    if (this.topics == null) {
      this.topics = new ArrayList<DCConsistentTopic>();
    }
    this.topics.add(elem);
  }

  public List<DCConsistentTopic> getTopics() {
    return this.topics;
  }

  public DCQueryTopicListResponse setTopics(List<DCConsistentTopic> topics) {
    this.topics = topics;
    return this;
  }

  public void unsetTopics() {
    this.topics = null;
  }

  /** Returns true if field topics is set (has been assigned a value) and false otherwise */
  public boolean isSetTopics() {
    return this.topics != null;
  }

  public void setTopicsIsSet(boolean value) {
    if (!value) {
      this.topics = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TOTAL_NUM:
      if (value == null) {
        unsetTotalNum();
      } else {
        setTotalNum((Integer)value);
      }
      break;

    case TOPICS:
      if (value == null) {
        unsetTopics();
      } else {
        setTopics((List<DCConsistentTopic>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TOTAL_NUM:
      return Integer.valueOf(getTotalNum());

    case TOPICS:
      return getTopics();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TOTAL_NUM:
      return isSetTotalNum();
    case TOPICS:
      return isSetTopics();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCQueryTopicListResponse)
      return this.equals((DCQueryTopicListResponse)that);
    return false;
  }

  public boolean equals(DCQueryTopicListResponse that) {
    if (that == null)
      return false;

    boolean this_present_totalNum = true;
    boolean that_present_totalNum = true;
    if (this_present_totalNum || that_present_totalNum) {
      if (!(this_present_totalNum && that_present_totalNum))
        return false;
      if (this.totalNum != that.totalNum)
        return false;
    }

    boolean this_present_topics = true && this.isSetTopics();
    boolean that_present_topics = true && that.isSetTopics();
    if (this_present_topics || that_present_topics) {
      if (!(this_present_topics && that_present_topics))
        return false;
      if (!this.topics.equals(that.topics))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCQueryTopicListResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCQueryTopicListResponse typedOther = (DCQueryTopicListResponse)other;

    lastComparison = Boolean.valueOf(isSetTotalNum()).compareTo(typedOther.isSetTotalNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalNum, typedOther.totalNum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTopics()).compareTo(typedOther.isSetTopics());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTopics()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.topics, typedOther.topics);
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
    StringBuilder sb = new StringBuilder("DCQueryTopicListResponse(");
    boolean first = true;

    sb.append("totalNum:");
    sb.append(this.totalNum);
    first = false;
    if (!first) sb.append(", ");
    sb.append("topics:");
    if (this.topics == null) {
      sb.append("null");
    } else {
      sb.append(this.topics);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'totalNum' because it's a primitive and you chose the non-beans generator.
    if (topics == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'topics' was not present! Struct: " + toString());
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

  private static class DCQueryTopicListResponseStandardSchemeFactory implements SchemeFactory {
    public DCQueryTopicListResponseStandardScheme getScheme() {
      return new DCQueryTopicListResponseStandardScheme();
    }
  }

  private static class DCQueryTopicListResponseStandardScheme extends StandardScheme<DCQueryTopicListResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCQueryTopicListResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TOTAL_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.totalNum = iprot.readI32();
              struct.setTotalNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOPICS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list56 = iprot.readListBegin();
                struct.topics = new ArrayList<DCConsistentTopic>(_list56.size);
                for (int _i57 = 0; _i57 < _list56.size; ++_i57)
                {
                  DCConsistentTopic _elem58; // required
                  _elem58 = new DCConsistentTopic();
                  _elem58.read(iprot);
                  struct.topics.add(_elem58);
                }
                iprot.readListEnd();
              }
              struct.setTopicsIsSet(true);
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
      if (!struct.isSetTotalNum()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'totalNum' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCQueryTopicListResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TOTAL_NUM_FIELD_DESC);
      oprot.writeI32(struct.totalNum);
      oprot.writeFieldEnd();
      if (struct.topics != null) {
        oprot.writeFieldBegin(TOPICS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.topics.size()));
          for (DCConsistentTopic _iter59 : struct.topics)
          {
            _iter59.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCQueryTopicListResponseTupleSchemeFactory implements SchemeFactory {
    public DCQueryTopicListResponseTupleScheme getScheme() {
      return new DCQueryTopicListResponseTupleScheme();
    }
  }

  private static class DCQueryTopicListResponseTupleScheme extends TupleScheme<DCQueryTopicListResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCQueryTopicListResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.totalNum);
      {
        oprot.writeI32(struct.topics.size());
        for (DCConsistentTopic _iter60 : struct.topics)
        {
          _iter60.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCQueryTopicListResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.totalNum = iprot.readI32();
      struct.setTotalNumIsSet(true);
      {
        org.apache.thrift.protocol.TList _list61 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.topics = new ArrayList<DCConsistentTopic>(_list61.size);
        for (int _i62 = 0; _i62 < _list61.size; ++_i62)
        {
          DCConsistentTopic _elem63; // required
          _elem63 = new DCConsistentTopic();
          _elem63.read(iprot);
          struct.topics.add(_elem63);
        }
      }
      struct.setTopicsIsSet(true);
    }
  }

}
