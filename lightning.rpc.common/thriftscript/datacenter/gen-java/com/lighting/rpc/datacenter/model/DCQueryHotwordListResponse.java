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

public class DCQueryHotwordListResponse implements org.apache.thrift.TBase<DCQueryHotwordListResponse, DCQueryHotwordListResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCQueryHotwordListResponse");

  private static final org.apache.thrift.protocol.TField WORDS_FIELD_DESC = new org.apache.thrift.protocol.TField("words", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCQueryHotwordListResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCQueryHotwordListResponseTupleSchemeFactory());
  }

  public List<String> words; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    WORDS((short)1, "words");

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
        case 1: // WORDS
          return WORDS;
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
    tmpMap.put(_Fields.WORDS, new org.apache.thrift.meta_data.FieldMetaData("words", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCQueryHotwordListResponse.class, metaDataMap);
  }

  public DCQueryHotwordListResponse() {
  }

  public DCQueryHotwordListResponse(
    List<String> words)
  {
    this();
    this.words = words;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCQueryHotwordListResponse(DCQueryHotwordListResponse other) {
    if (other.isSetWords()) {
      List<String> __this__words = new ArrayList<String>();
      for (String other_element : other.words) {
        __this__words.add(other_element);
      }
      this.words = __this__words;
    }
  }

  public DCQueryHotwordListResponse deepCopy() {
    return new DCQueryHotwordListResponse(this);
  }

  @Override
  public void clear() {
    this.words = null;
  }

  public int getWordsSize() {
    return (this.words == null) ? 0 : this.words.size();
  }

  public java.util.Iterator<String> getWordsIterator() {
    return (this.words == null) ? null : this.words.iterator();
  }

  public void addToWords(String elem) {
    if (this.words == null) {
      this.words = new ArrayList<String>();
    }
    this.words.add(elem);
  }

  public List<String> getWords() {
    return this.words;
  }

  public DCQueryHotwordListResponse setWords(List<String> words) {
    this.words = words;
    return this;
  }

  public void unsetWords() {
    this.words = null;
  }

  /** Returns true if field words is set (has been assigned a value) and false otherwise */
  public boolean isSetWords() {
    return this.words != null;
  }

  public void setWordsIsSet(boolean value) {
    if (!value) {
      this.words = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case WORDS:
      if (value == null) {
        unsetWords();
      } else {
        setWords((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case WORDS:
      return getWords();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case WORDS:
      return isSetWords();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCQueryHotwordListResponse)
      return this.equals((DCQueryHotwordListResponse)that);
    return false;
  }

  public boolean equals(DCQueryHotwordListResponse that) {
    if (that == null)
      return false;

    boolean this_present_words = true && this.isSetWords();
    boolean that_present_words = true && that.isSetWords();
    if (this_present_words || that_present_words) {
      if (!(this_present_words && that_present_words))
        return false;
      if (!this.words.equals(that.words))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCQueryHotwordListResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCQueryHotwordListResponse typedOther = (DCQueryHotwordListResponse)other;

    lastComparison = Boolean.valueOf(isSetWords()).compareTo(typedOther.isSetWords());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWords()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.words, typedOther.words);
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
    StringBuilder sb = new StringBuilder("DCQueryHotwordListResponse(");
    boolean first = true;

    sb.append("words:");
    if (this.words == null) {
      sb.append("null");
    } else {
      sb.append(this.words);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (words == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'words' was not present! Struct: " + toString());
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

  private static class DCQueryHotwordListResponseStandardSchemeFactory implements SchemeFactory {
    public DCQueryHotwordListResponseStandardScheme getScheme() {
      return new DCQueryHotwordListResponseStandardScheme();
    }
  }

  private static class DCQueryHotwordListResponseStandardScheme extends StandardScheme<DCQueryHotwordListResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCQueryHotwordListResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // WORDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list96 = iprot.readListBegin();
                struct.words = new ArrayList<String>(_list96.size);
                for (int _i97 = 0; _i97 < _list96.size; ++_i97)
                {
                  String _elem98; // required
                  _elem98 = iprot.readString();
                  struct.words.add(_elem98);
                }
                iprot.readListEnd();
              }
              struct.setWordsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCQueryHotwordListResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.words != null) {
        oprot.writeFieldBegin(WORDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.words.size()));
          for (String _iter99 : struct.words)
          {
            oprot.writeString(_iter99);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCQueryHotwordListResponseTupleSchemeFactory implements SchemeFactory {
    public DCQueryHotwordListResponseTupleScheme getScheme() {
      return new DCQueryHotwordListResponseTupleScheme();
    }
  }

  private static class DCQueryHotwordListResponseTupleScheme extends TupleScheme<DCQueryHotwordListResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCQueryHotwordListResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.words.size());
        for (String _iter100 : struct.words)
        {
          oprot.writeString(_iter100);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCQueryHotwordListResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list101 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.words = new ArrayList<String>(_list101.size);
        for (int _i102 = 0; _i102 < _list101.size; ++_i102)
        {
          String _elem103; // required
          _elem103 = iprot.readString();
          struct.words.add(_elem103);
        }
      }
      struct.setWordsIsSet(true);
    }
  }

}

