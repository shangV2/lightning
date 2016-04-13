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

public class DCConsistentTopic implements org.apache.thrift.TBase<DCConsistentTopic, DCConsistentTopic._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCConsistentTopic");

  private static final org.apache.thrift.protocol.TField TOPIC_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("topicId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField TOPIC_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("topicName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField WORDS_FIELD_DESC = new org.apache.thrift.protocol.TField("words", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField START_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("startDate", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField END_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("endDate", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField PERCENT_FIELD_DESC = new org.apache.thrift.protocol.TField("percent", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCConsistentTopicStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCConsistentTopicTupleSchemeFactory());
  }

  public long topicId; // optional
  public String topicName; // required
  public List<String> words; // required
  public String startDate; // required
  public String endDate; // required
  public int percent; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TOPIC_ID((short)1, "topicId"),
    TOPIC_NAME((short)2, "topicName"),
    WORDS((short)3, "words"),
    START_DATE((short)4, "startDate"),
    END_DATE((short)5, "endDate"),
    PERCENT((short)6, "percent");

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
        case 1: // TOPIC_ID
          return TOPIC_ID;
        case 2: // TOPIC_NAME
          return TOPIC_NAME;
        case 3: // WORDS
          return WORDS;
        case 4: // START_DATE
          return START_DATE;
        case 5: // END_DATE
          return END_DATE;
        case 6: // PERCENT
          return PERCENT;
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
  private static final int __PERCENT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.TOPIC_ID};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TOPIC_ID, new org.apache.thrift.meta_data.FieldMetaData("topicId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOPIC_NAME, new org.apache.thrift.meta_data.FieldMetaData("topicName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WORDS, new org.apache.thrift.meta_data.FieldMetaData("words", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.START_DATE, new org.apache.thrift.meta_data.FieldMetaData("startDate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.END_DATE, new org.apache.thrift.meta_data.FieldMetaData("endDate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PERCENT, new org.apache.thrift.meta_data.FieldMetaData("percent", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCConsistentTopic.class, metaDataMap);
  }

  public DCConsistentTopic() {
    this.percent = 20;

  }

  public DCConsistentTopic(
    String topicName,
    List<String> words,
    String startDate,
    String endDate,
    int percent)
  {
    this();
    this.topicName = topicName;
    this.words = words;
    this.startDate = startDate;
    this.endDate = endDate;
    this.percent = percent;
    setPercentIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCConsistentTopic(DCConsistentTopic other) {
    __isset_bitfield = other.__isset_bitfield;
    this.topicId = other.topicId;
    if (other.isSetTopicName()) {
      this.topicName = other.topicName;
    }
    if (other.isSetWords()) {
      List<String> __this__words = new ArrayList<String>();
      for (String other_element : other.words) {
        __this__words.add(other_element);
      }
      this.words = __this__words;
    }
    if (other.isSetStartDate()) {
      this.startDate = other.startDate;
    }
    if (other.isSetEndDate()) {
      this.endDate = other.endDate;
    }
    this.percent = other.percent;
  }

  public DCConsistentTopic deepCopy() {
    return new DCConsistentTopic(this);
  }

  @Override
  public void clear() {
    setTopicIdIsSet(false);
    this.topicId = 0;
    this.topicName = null;
    this.words = null;
    this.startDate = null;
    this.endDate = null;
    this.percent = 20;

  }

  public long getTopicId() {
    return this.topicId;
  }

  public DCConsistentTopic setTopicId(long topicId) {
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

  public String getTopicName() {
    return this.topicName;
  }

  public DCConsistentTopic setTopicName(String topicName) {
    this.topicName = topicName;
    return this;
  }

  public void unsetTopicName() {
    this.topicName = null;
  }

  /** Returns true if field topicName is set (has been assigned a value) and false otherwise */
  public boolean isSetTopicName() {
    return this.topicName != null;
  }

  public void setTopicNameIsSet(boolean value) {
    if (!value) {
      this.topicName = null;
    }
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

  public DCConsistentTopic setWords(List<String> words) {
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

  public String getStartDate() {
    return this.startDate;
  }

  public DCConsistentTopic setStartDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  public void unsetStartDate() {
    this.startDate = null;
  }

  /** Returns true if field startDate is set (has been assigned a value) and false otherwise */
  public boolean isSetStartDate() {
    return this.startDate != null;
  }

  public void setStartDateIsSet(boolean value) {
    if (!value) {
      this.startDate = null;
    }
  }

  public String getEndDate() {
    return this.endDate;
  }

  public DCConsistentTopic setEndDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  public void unsetEndDate() {
    this.endDate = null;
  }

  /** Returns true if field endDate is set (has been assigned a value) and false otherwise */
  public boolean isSetEndDate() {
    return this.endDate != null;
  }

  public void setEndDateIsSet(boolean value) {
    if (!value) {
      this.endDate = null;
    }
  }

  public int getPercent() {
    return this.percent;
  }

  public DCConsistentTopic setPercent(int percent) {
    this.percent = percent;
    setPercentIsSet(true);
    return this;
  }

  public void unsetPercent() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PERCENT_ISSET_ID);
  }

  /** Returns true if field percent is set (has been assigned a value) and false otherwise */
  public boolean isSetPercent() {
    return EncodingUtils.testBit(__isset_bitfield, __PERCENT_ISSET_ID);
  }

  public void setPercentIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PERCENT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TOPIC_ID:
      if (value == null) {
        unsetTopicId();
      } else {
        setTopicId((Long)value);
      }
      break;

    case TOPIC_NAME:
      if (value == null) {
        unsetTopicName();
      } else {
        setTopicName((String)value);
      }
      break;

    case WORDS:
      if (value == null) {
        unsetWords();
      } else {
        setWords((List<String>)value);
      }
      break;

    case START_DATE:
      if (value == null) {
        unsetStartDate();
      } else {
        setStartDate((String)value);
      }
      break;

    case END_DATE:
      if (value == null) {
        unsetEndDate();
      } else {
        setEndDate((String)value);
      }
      break;

    case PERCENT:
      if (value == null) {
        unsetPercent();
      } else {
        setPercent((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TOPIC_ID:
      return Long.valueOf(getTopicId());

    case TOPIC_NAME:
      return getTopicName();

    case WORDS:
      return getWords();

    case START_DATE:
      return getStartDate();

    case END_DATE:
      return getEndDate();

    case PERCENT:
      return Integer.valueOf(getPercent());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TOPIC_ID:
      return isSetTopicId();
    case TOPIC_NAME:
      return isSetTopicName();
    case WORDS:
      return isSetWords();
    case START_DATE:
      return isSetStartDate();
    case END_DATE:
      return isSetEndDate();
    case PERCENT:
      return isSetPercent();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCConsistentTopic)
      return this.equals((DCConsistentTopic)that);
    return false;
  }

  public boolean equals(DCConsistentTopic that) {
    if (that == null)
      return false;

    boolean this_present_topicId = true && this.isSetTopicId();
    boolean that_present_topicId = true && that.isSetTopicId();
    if (this_present_topicId || that_present_topicId) {
      if (!(this_present_topicId && that_present_topicId))
        return false;
      if (this.topicId != that.topicId)
        return false;
    }

    boolean this_present_topicName = true && this.isSetTopicName();
    boolean that_present_topicName = true && that.isSetTopicName();
    if (this_present_topicName || that_present_topicName) {
      if (!(this_present_topicName && that_present_topicName))
        return false;
      if (!this.topicName.equals(that.topicName))
        return false;
    }

    boolean this_present_words = true && this.isSetWords();
    boolean that_present_words = true && that.isSetWords();
    if (this_present_words || that_present_words) {
      if (!(this_present_words && that_present_words))
        return false;
      if (!this.words.equals(that.words))
        return false;
    }

    boolean this_present_startDate = true && this.isSetStartDate();
    boolean that_present_startDate = true && that.isSetStartDate();
    if (this_present_startDate || that_present_startDate) {
      if (!(this_present_startDate && that_present_startDate))
        return false;
      if (!this.startDate.equals(that.startDate))
        return false;
    }

    boolean this_present_endDate = true && this.isSetEndDate();
    boolean that_present_endDate = true && that.isSetEndDate();
    if (this_present_endDate || that_present_endDate) {
      if (!(this_present_endDate && that_present_endDate))
        return false;
      if (!this.endDate.equals(that.endDate))
        return false;
    }

    boolean this_present_percent = true;
    boolean that_present_percent = true;
    if (this_present_percent || that_present_percent) {
      if (!(this_present_percent && that_present_percent))
        return false;
      if (this.percent != that.percent)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCConsistentTopic other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCConsistentTopic typedOther = (DCConsistentTopic)other;

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
    lastComparison = Boolean.valueOf(isSetTopicName()).compareTo(typedOther.isSetTopicName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTopicName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.topicName, typedOther.topicName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
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
    lastComparison = Boolean.valueOf(isSetStartDate()).compareTo(typedOther.isSetStartDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startDate, typedOther.startDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEndDate()).compareTo(typedOther.isSetEndDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEndDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.endDate, typedOther.endDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPercent()).compareTo(typedOther.isSetPercent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPercent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.percent, typedOther.percent);
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
    StringBuilder sb = new StringBuilder("DCConsistentTopic(");
    boolean first = true;

    if (isSetTopicId()) {
      sb.append("topicId:");
      sb.append(this.topicId);
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("topicName:");
    if (this.topicName == null) {
      sb.append("null");
    } else {
      sb.append(this.topicName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("words:");
    if (this.words == null) {
      sb.append("null");
    } else {
      sb.append(this.words);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("startDate:");
    if (this.startDate == null) {
      sb.append("null");
    } else {
      sb.append(this.startDate);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("endDate:");
    if (this.endDate == null) {
      sb.append("null");
    } else {
      sb.append(this.endDate);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("percent:");
    sb.append(this.percent);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (topicName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'topicName' was not present! Struct: " + toString());
    }
    if (words == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'words' was not present! Struct: " + toString());
    }
    if (startDate == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'startDate' was not present! Struct: " + toString());
    }
    if (endDate == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'endDate' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'percent' because it's a primitive and you chose the non-beans generator.
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

  private static class DCConsistentTopicStandardSchemeFactory implements SchemeFactory {
    public DCConsistentTopicStandardScheme getScheme() {
      return new DCConsistentTopicStandardScheme();
    }
  }

  private static class DCConsistentTopicStandardScheme extends StandardScheme<DCConsistentTopic> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCConsistentTopic struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TOPIC_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.topicId = iprot.readI64();
              struct.setTopicIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOPIC_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.topicName = iprot.readString();
              struct.setTopicNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // WORDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list48 = iprot.readListBegin();
                struct.words = new ArrayList<String>(_list48.size);
                for (int _i49 = 0; _i49 < _list48.size; ++_i49)
                {
                  String _elem50; // required
                  _elem50 = iprot.readString();
                  struct.words.add(_elem50);
                }
                iprot.readListEnd();
              }
              struct.setWordsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // START_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.startDate = iprot.readString();
              struct.setStartDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // END_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.endDate = iprot.readString();
              struct.setEndDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // PERCENT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.percent = iprot.readI32();
              struct.setPercentIsSet(true);
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
      if (!struct.isSetPercent()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'percent' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCConsistentTopic struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetTopicId()) {
        oprot.writeFieldBegin(TOPIC_ID_FIELD_DESC);
        oprot.writeI64(struct.topicId);
        oprot.writeFieldEnd();
      }
      if (struct.topicName != null) {
        oprot.writeFieldBegin(TOPIC_NAME_FIELD_DESC);
        oprot.writeString(struct.topicName);
        oprot.writeFieldEnd();
      }
      if (struct.words != null) {
        oprot.writeFieldBegin(WORDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.words.size()));
          for (String _iter51 : struct.words)
          {
            oprot.writeString(_iter51);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.startDate != null) {
        oprot.writeFieldBegin(START_DATE_FIELD_DESC);
        oprot.writeString(struct.startDate);
        oprot.writeFieldEnd();
      }
      if (struct.endDate != null) {
        oprot.writeFieldBegin(END_DATE_FIELD_DESC);
        oprot.writeString(struct.endDate);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PERCENT_FIELD_DESC);
      oprot.writeI32(struct.percent);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCConsistentTopicTupleSchemeFactory implements SchemeFactory {
    public DCConsistentTopicTupleScheme getScheme() {
      return new DCConsistentTopicTupleScheme();
    }
  }

  private static class DCConsistentTopicTupleScheme extends TupleScheme<DCConsistentTopic> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCConsistentTopic struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.topicName);
      {
        oprot.writeI32(struct.words.size());
        for (String _iter52 : struct.words)
        {
          oprot.writeString(_iter52);
        }
      }
      oprot.writeString(struct.startDate);
      oprot.writeString(struct.endDate);
      oprot.writeI32(struct.percent);
      BitSet optionals = new BitSet();
      if (struct.isSetTopicId()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetTopicId()) {
        oprot.writeI64(struct.topicId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCConsistentTopic struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.topicName = iprot.readString();
      struct.setTopicNameIsSet(true);
      {
        org.apache.thrift.protocol.TList _list53 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.words = new ArrayList<String>(_list53.size);
        for (int _i54 = 0; _i54 < _list53.size; ++_i54)
        {
          String _elem55; // required
          _elem55 = iprot.readString();
          struct.words.add(_elem55);
        }
      }
      struct.setWordsIsSet(true);
      struct.startDate = iprot.readString();
      struct.setStartDateIsSet(true);
      struct.endDate = iprot.readString();
      struct.setEndDateIsSet(true);
      struct.percent = iprot.readI32();
      struct.setPercentIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.topicId = iprot.readI64();
        struct.setTopicIdIsSet(true);
      }
    }
  }

}
