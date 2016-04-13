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

public class WordInfo implements org.apache.thrift.TBase<WordInfo, WordInfo._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WordInfo");

  private static final org.apache.thrift.protocol.TField WORD_FIELD_DESC = new org.apache.thrift.protocol.TField("word", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField FREQ_FIELD_DESC = new org.apache.thrift.protocol.TField("freq", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new WordInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new WordInfoTupleSchemeFactory());
  }

  public String word; // required
  public int freq; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    WORD((short)1, "word"),
    FREQ((short)2, "freq");

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
        case 1: // WORD
          return WORD;
        case 2: // FREQ
          return FREQ;
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
  private static final int __FREQ_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.WORD, new org.apache.thrift.meta_data.FieldMetaData("word", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FREQ, new org.apache.thrift.meta_data.FieldMetaData("freq", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WordInfo.class, metaDataMap);
  }

  public WordInfo() {
  }

  public WordInfo(
    String word,
    int freq)
  {
    this();
    this.word = word;
    this.freq = freq;
    setFreqIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WordInfo(WordInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetWord()) {
      this.word = other.word;
    }
    this.freq = other.freq;
  }

  public WordInfo deepCopy() {
    return new WordInfo(this);
  }

  @Override
  public void clear() {
    this.word = null;
    setFreqIsSet(false);
    this.freq = 0;
  }

  public String getWord() {
    return this.word;
  }

  public WordInfo setWord(String word) {
    this.word = word;
    return this;
  }

  public void unsetWord() {
    this.word = null;
  }

  /** Returns true if field word is set (has been assigned a value) and false otherwise */
  public boolean isSetWord() {
    return this.word != null;
  }

  public void setWordIsSet(boolean value) {
    if (!value) {
      this.word = null;
    }
  }

  public int getFreq() {
    return this.freq;
  }

  public WordInfo setFreq(int freq) {
    this.freq = freq;
    setFreqIsSet(true);
    return this;
  }

  public void unsetFreq() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FREQ_ISSET_ID);
  }

  /** Returns true if field freq is set (has been assigned a value) and false otherwise */
  public boolean isSetFreq() {
    return EncodingUtils.testBit(__isset_bitfield, __FREQ_ISSET_ID);
  }

  public void setFreqIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FREQ_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case WORD:
      if (value == null) {
        unsetWord();
      } else {
        setWord((String)value);
      }
      break;

    case FREQ:
      if (value == null) {
        unsetFreq();
      } else {
        setFreq((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case WORD:
      return getWord();

    case FREQ:
      return Integer.valueOf(getFreq());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case WORD:
      return isSetWord();
    case FREQ:
      return isSetFreq();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WordInfo)
      return this.equals((WordInfo)that);
    return false;
  }

  public boolean equals(WordInfo that) {
    if (that == null)
      return false;

    boolean this_present_word = true && this.isSetWord();
    boolean that_present_word = true && that.isSetWord();
    if (this_present_word || that_present_word) {
      if (!(this_present_word && that_present_word))
        return false;
      if (!this.word.equals(that.word))
        return false;
    }

    boolean this_present_freq = true;
    boolean that_present_freq = true;
    if (this_present_freq || that_present_freq) {
      if (!(this_present_freq && that_present_freq))
        return false;
      if (this.freq != that.freq)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(WordInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    WordInfo typedOther = (WordInfo)other;

    lastComparison = Boolean.valueOf(isSetWord()).compareTo(typedOther.isSetWord());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWord()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.word, typedOther.word);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFreq()).compareTo(typedOther.isSetFreq());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFreq()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.freq, typedOther.freq);
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
    StringBuilder sb = new StringBuilder("WordInfo(");
    boolean first = true;

    sb.append("word:");
    if (this.word == null) {
      sb.append("null");
    } else {
      sb.append(this.word);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("freq:");
    sb.append(this.freq);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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

  private static class WordInfoStandardSchemeFactory implements SchemeFactory {
    public WordInfoStandardScheme getScheme() {
      return new WordInfoStandardScheme();
    }
  }

  private static class WordInfoStandardScheme extends StandardScheme<WordInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WordInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // WORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.word = iprot.readString();
              struct.setWordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FREQ
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.freq = iprot.readI32();
              struct.setFreqIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, WordInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.word != null) {
        oprot.writeFieldBegin(WORD_FIELD_DESC);
        oprot.writeString(struct.word);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(FREQ_FIELD_DESC);
      oprot.writeI32(struct.freq);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WordInfoTupleSchemeFactory implements SchemeFactory {
    public WordInfoTupleScheme getScheme() {
      return new WordInfoTupleScheme();
    }
  }

  private static class WordInfoTupleScheme extends TupleScheme<WordInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WordInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetWord()) {
        optionals.set(0);
      }
      if (struct.isSetFreq()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetWord()) {
        oprot.writeString(struct.word);
      }
      if (struct.isSetFreq()) {
        oprot.writeI32(struct.freq);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WordInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.word = iprot.readString();
        struct.setWordIsSet(true);
      }
      if (incoming.get(1)) {
        struct.freq = iprot.readI32();
        struct.setFreqIsSet(true);
      }
    }
  }

}
