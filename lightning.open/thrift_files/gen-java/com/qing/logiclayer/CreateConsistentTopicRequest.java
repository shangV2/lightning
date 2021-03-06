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

public class CreateConsistentTopicRequest implements org.apache.thrift.TBase<CreateConsistentTopicRequest, CreateConsistentTopicRequest._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CreateConsistentTopicRequest");

  private static final org.apache.thrift.protocol.TField LANGUGE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("langugeType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TOPIC_FIELD_DESC = new org.apache.thrift.protocol.TField("topic", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CreateConsistentTopicRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CreateConsistentTopicRequestTupleSchemeFactory());
  }

  /**
   * 
   * @see OpenCommonLanguge
   */
  public OpenCommonLanguge langugeType; // required
  public ConsistentTopic topic; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see OpenCommonLanguge
     */
    LANGUGE_TYPE((short)1, "langugeType"),
    TOPIC((short)2, "topic");

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
        case 2: // TOPIC
          return TOPIC;
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
    tmpMap.put(_Fields.LANGUGE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("langugeType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, OpenCommonLanguge.class)));
    tmpMap.put(_Fields.TOPIC, new org.apache.thrift.meta_data.FieldMetaData("topic", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ConsistentTopic.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CreateConsistentTopicRequest.class, metaDataMap);
  }

  public CreateConsistentTopicRequest() {
    this.langugeType = com.qing.logiclayer.OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;

  }

  public CreateConsistentTopicRequest(
    OpenCommonLanguge langugeType,
    ConsistentTopic topic)
  {
    this();
    this.langugeType = langugeType;
    this.topic = topic;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CreateConsistentTopicRequest(CreateConsistentTopicRequest other) {
    if (other.isSetLangugeType()) {
      this.langugeType = other.langugeType;
    }
    if (other.isSetTopic()) {
      this.topic = new ConsistentTopic(other.topic);
    }
  }

  public CreateConsistentTopicRequest deepCopy() {
    return new CreateConsistentTopicRequest(this);
  }

  @Override
  public void clear() {
    this.langugeType = com.qing.logiclayer.OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;

    this.topic = null;
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
  public CreateConsistentTopicRequest setLangugeType(OpenCommonLanguge langugeType) {
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

  public ConsistentTopic getTopic() {
    return this.topic;
  }

  public CreateConsistentTopicRequest setTopic(ConsistentTopic topic) {
    this.topic = topic;
    return this;
  }

  public void unsetTopic() {
    this.topic = null;
  }

  /** Returns true if field topic is set (has been assigned a value) and false otherwise */
  public boolean isSetTopic() {
    return this.topic != null;
  }

  public void setTopicIsSet(boolean value) {
    if (!value) {
      this.topic = null;
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

    case TOPIC:
      if (value == null) {
        unsetTopic();
      } else {
        setTopic((ConsistentTopic)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LANGUGE_TYPE:
      return getLangugeType();

    case TOPIC:
      return getTopic();

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
    case TOPIC:
      return isSetTopic();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CreateConsistentTopicRequest)
      return this.equals((CreateConsistentTopicRequest)that);
    return false;
  }

  public boolean equals(CreateConsistentTopicRequest that) {
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

    boolean this_present_topic = true && this.isSetTopic();
    boolean that_present_topic = true && that.isSetTopic();
    if (this_present_topic || that_present_topic) {
      if (!(this_present_topic && that_present_topic))
        return false;
      if (!this.topic.equals(that.topic))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(CreateConsistentTopicRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    CreateConsistentTopicRequest typedOther = (CreateConsistentTopicRequest)other;

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
    lastComparison = Boolean.valueOf(isSetTopic()).compareTo(typedOther.isSetTopic());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTopic()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.topic, typedOther.topic);
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
    StringBuilder sb = new StringBuilder("CreateConsistentTopicRequest(");
    boolean first = true;

    sb.append("langugeType:");
    if (this.langugeType == null) {
      sb.append("null");
    } else {
      sb.append(this.langugeType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("topic:");
    if (this.topic == null) {
      sb.append("null");
    } else {
      sb.append(this.topic);
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
    if (topic == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'topic' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (topic != null) {
      topic.validate();
    }
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

  private static class CreateConsistentTopicRequestStandardSchemeFactory implements SchemeFactory {
    public CreateConsistentTopicRequestStandardScheme getScheme() {
      return new CreateConsistentTopicRequestStandardScheme();
    }
  }

  private static class CreateConsistentTopicRequestStandardScheme extends StandardScheme<CreateConsistentTopicRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CreateConsistentTopicRequest struct) throws org.apache.thrift.TException {
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
          case 2: // TOPIC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.topic = new ConsistentTopic();
              struct.topic.read(iprot);
              struct.setTopicIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CreateConsistentTopicRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.langugeType != null) {
        oprot.writeFieldBegin(LANGUGE_TYPE_FIELD_DESC);
        oprot.writeI32(struct.langugeType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.topic != null) {
        oprot.writeFieldBegin(TOPIC_FIELD_DESC);
        struct.topic.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CreateConsistentTopicRequestTupleSchemeFactory implements SchemeFactory {
    public CreateConsistentTopicRequestTupleScheme getScheme() {
      return new CreateConsistentTopicRequestTupleScheme();
    }
  }

  private static class CreateConsistentTopicRequestTupleScheme extends TupleScheme<CreateConsistentTopicRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CreateConsistentTopicRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.langugeType.getValue());
      struct.topic.write(oprot);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CreateConsistentTopicRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.langugeType = OpenCommonLanguge.findByValue(iprot.readI32());
      struct.setLangugeTypeIsSet(true);
      struct.topic = new ConsistentTopic();
      struct.topic.read(iprot);
      struct.setTopicIsSet(true);
    }
  }

}

