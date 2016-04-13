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

public class DCTimeWordTrend implements org.apache.thrift.TBase<DCTimeWordTrend, DCTimeWordTrend._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCTimeWordTrend");

  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField WORDINFOS_FIELD_DESC = new org.apache.thrift.protocol.TField("wordinfos", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCTimeWordTrendStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCTimeWordTrendTupleSchemeFactory());
  }

  public String timestamp; // required
  public List<DCTimeWordInfo> wordinfos; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TIMESTAMP((short)1, "timestamp"),
    WORDINFOS((short)2, "wordinfos");

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
        case 1: // TIMESTAMP
          return TIMESTAMP;
        case 2: // WORDINFOS
          return WORDINFOS;
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
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WORDINFOS, new org.apache.thrift.meta_data.FieldMetaData("wordinfos", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DCTimeWordInfo.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCTimeWordTrend.class, metaDataMap);
  }

  public DCTimeWordTrend() {
  }

  public DCTimeWordTrend(
    String timestamp,
    List<DCTimeWordInfo> wordinfos)
  {
    this();
    this.timestamp = timestamp;
    this.wordinfos = wordinfos;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCTimeWordTrend(DCTimeWordTrend other) {
    if (other.isSetTimestamp()) {
      this.timestamp = other.timestamp;
    }
    if (other.isSetWordinfos()) {
      List<DCTimeWordInfo> __this__wordinfos = new ArrayList<DCTimeWordInfo>();
      for (DCTimeWordInfo other_element : other.wordinfos) {
        __this__wordinfos.add(new DCTimeWordInfo(other_element));
      }
      this.wordinfos = __this__wordinfos;
    }
  }

  public DCTimeWordTrend deepCopy() {
    return new DCTimeWordTrend(this);
  }

  @Override
  public void clear() {
    this.timestamp = null;
    this.wordinfos = null;
  }

  public String getTimestamp() {
    return this.timestamp;
  }

  public DCTimeWordTrend setTimestamp(String timestamp) {
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

  public int getWordinfosSize() {
    return (this.wordinfos == null) ? 0 : this.wordinfos.size();
  }

  public java.util.Iterator<DCTimeWordInfo> getWordinfosIterator() {
    return (this.wordinfos == null) ? null : this.wordinfos.iterator();
  }

  public void addToWordinfos(DCTimeWordInfo elem) {
    if (this.wordinfos == null) {
      this.wordinfos = new ArrayList<DCTimeWordInfo>();
    }
    this.wordinfos.add(elem);
  }

  public List<DCTimeWordInfo> getWordinfos() {
    return this.wordinfos;
  }

  public DCTimeWordTrend setWordinfos(List<DCTimeWordInfo> wordinfos) {
    this.wordinfos = wordinfos;
    return this;
  }

  public void unsetWordinfos() {
    this.wordinfos = null;
  }

  /** Returns true if field wordinfos is set (has been assigned a value) and false otherwise */
  public boolean isSetWordinfos() {
    return this.wordinfos != null;
  }

  public void setWordinfosIsSet(boolean value) {
    if (!value) {
      this.wordinfos = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((String)value);
      }
      break;

    case WORDINFOS:
      if (value == null) {
        unsetWordinfos();
      } else {
        setWordinfos((List<DCTimeWordInfo>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TIMESTAMP:
      return getTimestamp();

    case WORDINFOS:
      return getWordinfos();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TIMESTAMP:
      return isSetTimestamp();
    case WORDINFOS:
      return isSetWordinfos();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCTimeWordTrend)
      return this.equals((DCTimeWordTrend)that);
    return false;
  }

  public boolean equals(DCTimeWordTrend that) {
    if (that == null)
      return false;

    boolean this_present_timestamp = true && this.isSetTimestamp();
    boolean that_present_timestamp = true && that.isSetTimestamp();
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (!this.timestamp.equals(that.timestamp))
        return false;
    }

    boolean this_present_wordinfos = true && this.isSetWordinfos();
    boolean that_present_wordinfos = true && that.isSetWordinfos();
    if (this_present_wordinfos || that_present_wordinfos) {
      if (!(this_present_wordinfos && that_present_wordinfos))
        return false;
      if (!this.wordinfos.equals(that.wordinfos))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCTimeWordTrend other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCTimeWordTrend typedOther = (DCTimeWordTrend)other;

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
    lastComparison = Boolean.valueOf(isSetWordinfos()).compareTo(typedOther.isSetWordinfos());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWordinfos()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.wordinfos, typedOther.wordinfos);
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
    StringBuilder sb = new StringBuilder("DCTimeWordTrend(");
    boolean first = true;

    sb.append("timestamp:");
    if (this.timestamp == null) {
      sb.append("null");
    } else {
      sb.append(this.timestamp);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("wordinfos:");
    if (this.wordinfos == null) {
      sb.append("null");
    } else {
      sb.append(this.wordinfos);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (timestamp == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'timestamp' was not present! Struct: " + toString());
    }
    if (wordinfos == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'wordinfos' was not present! Struct: " + toString());
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

  private static class DCTimeWordTrendStandardSchemeFactory implements SchemeFactory {
    public DCTimeWordTrendStandardScheme getScheme() {
      return new DCTimeWordTrendStandardScheme();
    }
  }

  private static class DCTimeWordTrendStandardScheme extends StandardScheme<DCTimeWordTrend> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCTimeWordTrend struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.timestamp = iprot.readString();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WORDINFOS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list88 = iprot.readListBegin();
                struct.wordinfos = new ArrayList<DCTimeWordInfo>(_list88.size);
                for (int _i89 = 0; _i89 < _list88.size; ++_i89)
                {
                  DCTimeWordInfo _elem90; // required
                  _elem90 = new DCTimeWordInfo();
                  _elem90.read(iprot);
                  struct.wordinfos.add(_elem90);
                }
                iprot.readListEnd();
              }
              struct.setWordinfosIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCTimeWordTrend struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.timestamp != null) {
        oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
        oprot.writeString(struct.timestamp);
        oprot.writeFieldEnd();
      }
      if (struct.wordinfos != null) {
        oprot.writeFieldBegin(WORDINFOS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.wordinfos.size()));
          for (DCTimeWordInfo _iter91 : struct.wordinfos)
          {
            _iter91.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCTimeWordTrendTupleSchemeFactory implements SchemeFactory {
    public DCTimeWordTrendTupleScheme getScheme() {
      return new DCTimeWordTrendTupleScheme();
    }
  }

  private static class DCTimeWordTrendTupleScheme extends TupleScheme<DCTimeWordTrend> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCTimeWordTrend struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.timestamp);
      {
        oprot.writeI32(struct.wordinfos.size());
        for (DCTimeWordInfo _iter92 : struct.wordinfos)
        {
          _iter92.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCTimeWordTrend struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.timestamp = iprot.readString();
      struct.setTimestampIsSet(true);
      {
        org.apache.thrift.protocol.TList _list93 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.wordinfos = new ArrayList<DCTimeWordInfo>(_list93.size);
        for (int _i94 = 0; _i94 < _list93.size; ++_i94)
        {
          DCTimeWordInfo _elem95; // required
          _elem95 = new DCTimeWordInfo();
          _elem95.read(iprot);
          struct.wordinfos.add(_elem95);
        }
      }
      struct.setWordinfosIsSet(true);
    }
  }

}

