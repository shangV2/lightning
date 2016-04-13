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

public class ListShortMessageForTopicWithPhoneResponse implements org.apache.thrift.TBase<ListShortMessageForTopicWithPhoneResponse, ListShortMessageForTopicWithPhoneResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ListShortMessageForTopicWithPhoneResponse");

  private static final org.apache.thrift.protocol.TField LOGID_FIELD_DESC = new org.apache.thrift.protocol.TField("logid", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField TOTAL_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("totalNum", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField SHORTMESSAGES_FIELD_DESC = new org.apache.thrift.protocol.TField("shortmessages", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ListShortMessageForTopicWithPhoneResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ListShortMessageForTopicWithPhoneResponseTupleSchemeFactory());
  }

  public long logid; // required
  public int totalNum; // required
  public List<OpenShortMessageContent> shortmessages; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LOGID((short)1, "logid"),
    TOTAL_NUM((short)2, "totalNum"),
    SHORTMESSAGES((short)3, "shortmessages");

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
        case 2: // TOTAL_NUM
          return TOTAL_NUM;
        case 3: // SHORTMESSAGES
          return SHORTMESSAGES;
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
  private static final int __TOTALNUM_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LOGID, new org.apache.thrift.meta_data.FieldMetaData("logid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL_NUM, new org.apache.thrift.meta_data.FieldMetaData("totalNum", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SHORTMESSAGES, new org.apache.thrift.meta_data.FieldMetaData("shortmessages", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, OpenShortMessageContent.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ListShortMessageForTopicWithPhoneResponse.class, metaDataMap);
  }

  public ListShortMessageForTopicWithPhoneResponse() {
    this.logid = 1001L;

  }

  public ListShortMessageForTopicWithPhoneResponse(
    long logid,
    int totalNum,
    List<OpenShortMessageContent> shortmessages)
  {
    this();
    this.logid = logid;
    setLogidIsSet(true);
    this.totalNum = totalNum;
    setTotalNumIsSet(true);
    this.shortmessages = shortmessages;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ListShortMessageForTopicWithPhoneResponse(ListShortMessageForTopicWithPhoneResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.logid = other.logid;
    this.totalNum = other.totalNum;
    if (other.isSetShortmessages()) {
      List<OpenShortMessageContent> __this__shortmessages = new ArrayList<OpenShortMessageContent>();
      for (OpenShortMessageContent other_element : other.shortmessages) {
        __this__shortmessages.add(new OpenShortMessageContent(other_element));
      }
      this.shortmessages = __this__shortmessages;
    }
  }

  public ListShortMessageForTopicWithPhoneResponse deepCopy() {
    return new ListShortMessageForTopicWithPhoneResponse(this);
  }

  @Override
  public void clear() {
    this.logid = 1001L;

    setTotalNumIsSet(false);
    this.totalNum = 0;
    this.shortmessages = null;
  }

  public long getLogid() {
    return this.logid;
  }

  public ListShortMessageForTopicWithPhoneResponse setLogid(long logid) {
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

  public int getTotalNum() {
    return this.totalNum;
  }

  public ListShortMessageForTopicWithPhoneResponse setTotalNum(int totalNum) {
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

  public int getShortmessagesSize() {
    return (this.shortmessages == null) ? 0 : this.shortmessages.size();
  }

  public java.util.Iterator<OpenShortMessageContent> getShortmessagesIterator() {
    return (this.shortmessages == null) ? null : this.shortmessages.iterator();
  }

  public void addToShortmessages(OpenShortMessageContent elem) {
    if (this.shortmessages == null) {
      this.shortmessages = new ArrayList<OpenShortMessageContent>();
    }
    this.shortmessages.add(elem);
  }

  public List<OpenShortMessageContent> getShortmessages() {
    return this.shortmessages;
  }

  public ListShortMessageForTopicWithPhoneResponse setShortmessages(List<OpenShortMessageContent> shortmessages) {
    this.shortmessages = shortmessages;
    return this;
  }

  public void unsetShortmessages() {
    this.shortmessages = null;
  }

  /** Returns true if field shortmessages is set (has been assigned a value) and false otherwise */
  public boolean isSetShortmessages() {
    return this.shortmessages != null;
  }

  public void setShortmessagesIsSet(boolean value) {
    if (!value) {
      this.shortmessages = null;
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

    case TOTAL_NUM:
      if (value == null) {
        unsetTotalNum();
      } else {
        setTotalNum((Integer)value);
      }
      break;

    case SHORTMESSAGES:
      if (value == null) {
        unsetShortmessages();
      } else {
        setShortmessages((List<OpenShortMessageContent>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LOGID:
      return Long.valueOf(getLogid());

    case TOTAL_NUM:
      return Integer.valueOf(getTotalNum());

    case SHORTMESSAGES:
      return getShortmessages();

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
    case TOTAL_NUM:
      return isSetTotalNum();
    case SHORTMESSAGES:
      return isSetShortmessages();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ListShortMessageForTopicWithPhoneResponse)
      return this.equals((ListShortMessageForTopicWithPhoneResponse)that);
    return false;
  }

  public boolean equals(ListShortMessageForTopicWithPhoneResponse that) {
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

    boolean this_present_totalNum = true;
    boolean that_present_totalNum = true;
    if (this_present_totalNum || that_present_totalNum) {
      if (!(this_present_totalNum && that_present_totalNum))
        return false;
      if (this.totalNum != that.totalNum)
        return false;
    }

    boolean this_present_shortmessages = true && this.isSetShortmessages();
    boolean that_present_shortmessages = true && that.isSetShortmessages();
    if (this_present_shortmessages || that_present_shortmessages) {
      if (!(this_present_shortmessages && that_present_shortmessages))
        return false;
      if (!this.shortmessages.equals(that.shortmessages))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(ListShortMessageForTopicWithPhoneResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ListShortMessageForTopicWithPhoneResponse typedOther = (ListShortMessageForTopicWithPhoneResponse)other;

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
    lastComparison = Boolean.valueOf(isSetShortmessages()).compareTo(typedOther.isSetShortmessages());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetShortmessages()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.shortmessages, typedOther.shortmessages);
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
    StringBuilder sb = new StringBuilder("ListShortMessageForTopicWithPhoneResponse(");
    boolean first = true;

    sb.append("logid:");
    sb.append(this.logid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("totalNum:");
    sb.append(this.totalNum);
    first = false;
    if (!first) sb.append(", ");
    sb.append("shortmessages:");
    if (this.shortmessages == null) {
      sb.append("null");
    } else {
      sb.append(this.shortmessages);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'logid' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'totalNum' because it's a primitive and you chose the non-beans generator.
    if (shortmessages == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'shortmessages' was not present! Struct: " + toString());
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

  private static class ListShortMessageForTopicWithPhoneResponseStandardSchemeFactory implements SchemeFactory {
    public ListShortMessageForTopicWithPhoneResponseStandardScheme getScheme() {
      return new ListShortMessageForTopicWithPhoneResponseStandardScheme();
    }
  }

  private static class ListShortMessageForTopicWithPhoneResponseStandardScheme extends StandardScheme<ListShortMessageForTopicWithPhoneResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ListShortMessageForTopicWithPhoneResponse struct) throws org.apache.thrift.TException {
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
          case 2: // TOTAL_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.totalNum = iprot.readI32();
              struct.setTotalNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SHORTMESSAGES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list104 = iprot.readListBegin();
                struct.shortmessages = new ArrayList<OpenShortMessageContent>(_list104.size);
                for (int _i105 = 0; _i105 < _list104.size; ++_i105)
                {
                  OpenShortMessageContent _elem106; // required
                  _elem106 = new OpenShortMessageContent();
                  _elem106.read(iprot);
                  struct.shortmessages.add(_elem106);
                }
                iprot.readListEnd();
              }
              struct.setShortmessagesIsSet(true);
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
      if (!struct.isSetTotalNum()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'totalNum' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ListShortMessageForTopicWithPhoneResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(LOGID_FIELD_DESC);
      oprot.writeI64(struct.logid);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_NUM_FIELD_DESC);
      oprot.writeI32(struct.totalNum);
      oprot.writeFieldEnd();
      if (struct.shortmessages != null) {
        oprot.writeFieldBegin(SHORTMESSAGES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.shortmessages.size()));
          for (OpenShortMessageContent _iter107 : struct.shortmessages)
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

  private static class ListShortMessageForTopicWithPhoneResponseTupleSchemeFactory implements SchemeFactory {
    public ListShortMessageForTopicWithPhoneResponseTupleScheme getScheme() {
      return new ListShortMessageForTopicWithPhoneResponseTupleScheme();
    }
  }

  private static class ListShortMessageForTopicWithPhoneResponseTupleScheme extends TupleScheme<ListShortMessageForTopicWithPhoneResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ListShortMessageForTopicWithPhoneResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.logid);
      oprot.writeI32(struct.totalNum);
      {
        oprot.writeI32(struct.shortmessages.size());
        for (OpenShortMessageContent _iter108 : struct.shortmessages)
        {
          _iter108.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ListShortMessageForTopicWithPhoneResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.logid = iprot.readI64();
      struct.setLogidIsSet(true);
      struct.totalNum = iprot.readI32();
      struct.setTotalNumIsSet(true);
      {
        org.apache.thrift.protocol.TList _list109 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.shortmessages = new ArrayList<OpenShortMessageContent>(_list109.size);
        for (int _i110 = 0; _i110 < _list109.size; ++_i110)
        {
          OpenShortMessageContent _elem111; // required
          _elem111 = new OpenShortMessageContent();
          _elem111.read(iprot);
          struct.shortmessages.add(_elem111);
        }
      }
      struct.setShortmessagesIsSet(true);
    }
  }

}

