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

public class QueryTracePublicWordResponse implements org.apache.thrift.TBase<QueryTracePublicWordResponse, QueryTracePublicWordResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryTracePublicWordResponse");

  private static final org.apache.thrift.protocol.TField LOGID_FIELD_DESC = new org.apache.thrift.protocol.TField("logid", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField PUBLIC_WORDS_FIELD_DESC = new org.apache.thrift.protocol.TField("publicWords", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new QueryTracePublicWordResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new QueryTracePublicWordResponseTupleSchemeFactory());
  }

  public long logid; // required
  public List<TracePublicWord> publicWords; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LOGID((short)1, "logid"),
    PUBLIC_WORDS((short)2, "publicWords");

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
        case 2: // PUBLIC_WORDS
          return PUBLIC_WORDS;
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
    tmpMap.put(_Fields.PUBLIC_WORDS, new org.apache.thrift.meta_data.FieldMetaData("publicWords", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TracePublicWord.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryTracePublicWordResponse.class, metaDataMap);
  }

  public QueryTracePublicWordResponse() {
    this.logid = 1001L;

  }

  public QueryTracePublicWordResponse(
    long logid,
    List<TracePublicWord> publicWords)
  {
    this();
    this.logid = logid;
    setLogidIsSet(true);
    this.publicWords = publicWords;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryTracePublicWordResponse(QueryTracePublicWordResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.logid = other.logid;
    if (other.isSetPublicWords()) {
      List<TracePublicWord> __this__publicWords = new ArrayList<TracePublicWord>();
      for (TracePublicWord other_element : other.publicWords) {
        __this__publicWords.add(new TracePublicWord(other_element));
      }
      this.publicWords = __this__publicWords;
    }
  }

  public QueryTracePublicWordResponse deepCopy() {
    return new QueryTracePublicWordResponse(this);
  }

  @Override
  public void clear() {
    this.logid = 1001L;

    this.publicWords = null;
  }

  public long getLogid() {
    return this.logid;
  }

  public QueryTracePublicWordResponse setLogid(long logid) {
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

  public int getPublicWordsSize() {
    return (this.publicWords == null) ? 0 : this.publicWords.size();
  }

  public java.util.Iterator<TracePublicWord> getPublicWordsIterator() {
    return (this.publicWords == null) ? null : this.publicWords.iterator();
  }

  public void addToPublicWords(TracePublicWord elem) {
    if (this.publicWords == null) {
      this.publicWords = new ArrayList<TracePublicWord>();
    }
    this.publicWords.add(elem);
  }

  public List<TracePublicWord> getPublicWords() {
    return this.publicWords;
  }

  public QueryTracePublicWordResponse setPublicWords(List<TracePublicWord> publicWords) {
    this.publicWords = publicWords;
    return this;
  }

  public void unsetPublicWords() {
    this.publicWords = null;
  }

  /** Returns true if field publicWords is set (has been assigned a value) and false otherwise */
  public boolean isSetPublicWords() {
    return this.publicWords != null;
  }

  public void setPublicWordsIsSet(boolean value) {
    if (!value) {
      this.publicWords = null;
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

    case PUBLIC_WORDS:
      if (value == null) {
        unsetPublicWords();
      } else {
        setPublicWords((List<TracePublicWord>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LOGID:
      return Long.valueOf(getLogid());

    case PUBLIC_WORDS:
      return getPublicWords();

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
    case PUBLIC_WORDS:
      return isSetPublicWords();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryTracePublicWordResponse)
      return this.equals((QueryTracePublicWordResponse)that);
    return false;
  }

  public boolean equals(QueryTracePublicWordResponse that) {
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

    boolean this_present_publicWords = true && this.isSetPublicWords();
    boolean that_present_publicWords = true && that.isSetPublicWords();
    if (this_present_publicWords || that_present_publicWords) {
      if (!(this_present_publicWords && that_present_publicWords))
        return false;
      if (!this.publicWords.equals(that.publicWords))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(QueryTracePublicWordResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    QueryTracePublicWordResponse typedOther = (QueryTracePublicWordResponse)other;

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
    lastComparison = Boolean.valueOf(isSetPublicWords()).compareTo(typedOther.isSetPublicWords());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPublicWords()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.publicWords, typedOther.publicWords);
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
    StringBuilder sb = new StringBuilder("QueryTracePublicWordResponse(");
    boolean first = true;

    sb.append("logid:");
    sb.append(this.logid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("publicWords:");
    if (this.publicWords == null) {
      sb.append("null");
    } else {
      sb.append(this.publicWords);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'logid' because it's a primitive and you chose the non-beans generator.
    if (publicWords == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'publicWords' was not present! Struct: " + toString());
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

  private static class QueryTracePublicWordResponseStandardSchemeFactory implements SchemeFactory {
    public QueryTracePublicWordResponseStandardScheme getScheme() {
      return new QueryTracePublicWordResponseStandardScheme();
    }
  }

  private static class QueryTracePublicWordResponseStandardScheme extends StandardScheme<QueryTracePublicWordResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryTracePublicWordResponse struct) throws org.apache.thrift.TException {
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
          case 2: // PUBLIC_WORDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list48 = iprot.readListBegin();
                struct.publicWords = new ArrayList<TracePublicWord>(_list48.size);
                for (int _i49 = 0; _i49 < _list48.size; ++_i49)
                {
                  TracePublicWord _elem50; // required
                  _elem50 = new TracePublicWord();
                  _elem50.read(iprot);
                  struct.publicWords.add(_elem50);
                }
                iprot.readListEnd();
              }
              struct.setPublicWordsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryTracePublicWordResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(LOGID_FIELD_DESC);
      oprot.writeI64(struct.logid);
      oprot.writeFieldEnd();
      if (struct.publicWords != null) {
        oprot.writeFieldBegin(PUBLIC_WORDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.publicWords.size()));
          for (TracePublicWord _iter51 : struct.publicWords)
          {
            _iter51.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryTracePublicWordResponseTupleSchemeFactory implements SchemeFactory {
    public QueryTracePublicWordResponseTupleScheme getScheme() {
      return new QueryTracePublicWordResponseTupleScheme();
    }
  }

  private static class QueryTracePublicWordResponseTupleScheme extends TupleScheme<QueryTracePublicWordResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryTracePublicWordResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.logid);
      {
        oprot.writeI32(struct.publicWords.size());
        for (TracePublicWord _iter52 : struct.publicWords)
        {
          _iter52.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryTracePublicWordResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.logid = iprot.readI64();
      struct.setLogidIsSet(true);
      {
        org.apache.thrift.protocol.TList _list53 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.publicWords = new ArrayList<TracePublicWord>(_list53.size);
        for (int _i54 = 0; _i54 < _list53.size; ++_i54)
        {
          TracePublicWord _elem55; // required
          _elem55 = new TracePublicWord();
          _elem55.read(iprot);
          struct.publicWords.add(_elem55);
        }
      }
      struct.setPublicWordsIsSet(true);
    }
  }

}

