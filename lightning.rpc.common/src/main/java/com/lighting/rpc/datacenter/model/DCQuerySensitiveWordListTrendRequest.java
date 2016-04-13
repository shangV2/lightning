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

public class DCQuerySensitiveWordListTrendRequest implements org.apache.thrift.TBase<DCQuerySensitiveWordListTrendRequest, DCQuerySensitiveWordListTrendRequest._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCQuerySensitiveWordListTrendRequest");

  private static final org.apache.thrift.protocol.TField LOGID_FIELD_DESC = new org.apache.thrift.protocol.TField("logid", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField LANGUGE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("langugeType", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField START_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("startDate", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField END_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("endDate", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PAGENO_FIELD_DESC = new org.apache.thrift.protocol.TField("pageno", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField PAGESIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("pagesize", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCQuerySensitiveWordListTrendRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCQuerySensitiveWordListTrendRequestTupleSchemeFactory());
  }

  public long logid; // required
  /**
   * 
   * @see DCCommonLanguge
   */
  public DCCommonLanguge langugeType; // required
  public String startDate; // required
  public String endDate; // required
  public int pageno; // required
  public int pagesize; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LOGID((short)1, "logid"),
    /**
     * 
     * @see DCCommonLanguge
     */
    LANGUGE_TYPE((short)2, "langugeType"),
    START_DATE((short)3, "startDate"),
    END_DATE((short)4, "endDate"),
    PAGENO((short)5, "pageno"),
    PAGESIZE((short)6, "pagesize");

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
        case 2: // LANGUGE_TYPE
          return LANGUGE_TYPE;
        case 3: // START_DATE
          return START_DATE;
        case 4: // END_DATE
          return END_DATE;
        case 5: // PAGENO
          return PAGENO;
        case 6: // PAGESIZE
          return PAGESIZE;
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
  private static final int __PAGENO_ISSET_ID = 1;
  private static final int __PAGESIZE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LOGID, new org.apache.thrift.meta_data.FieldMetaData("logid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LANGUGE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("langugeType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, DCCommonLanguge.class)));
    tmpMap.put(_Fields.START_DATE, new org.apache.thrift.meta_data.FieldMetaData("startDate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.END_DATE, new org.apache.thrift.meta_data.FieldMetaData("endDate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PAGENO, new org.apache.thrift.meta_data.FieldMetaData("pageno", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PAGESIZE, new org.apache.thrift.meta_data.FieldMetaData("pagesize", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCQuerySensitiveWordListTrendRequest.class, metaDataMap);
  }

  public DCQuerySensitiveWordListTrendRequest() {
    this.langugeType = com.lighting.rpc.datacenter.model.DCCommonLanguge.DC_LAN_CHINESE_ZH_CN;

    this.pageno = 0;

    this.pagesize = 10;

  }

  public DCQuerySensitiveWordListTrendRequest(
    long logid,
    DCCommonLanguge langugeType,
    String startDate,
    String endDate,
    int pageno,
    int pagesize)
  {
    this();
    this.logid = logid;
    setLogidIsSet(true);
    this.langugeType = langugeType;
    this.startDate = startDate;
    this.endDate = endDate;
    this.pageno = pageno;
    setPagenoIsSet(true);
    this.pagesize = pagesize;
    setPagesizeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCQuerySensitiveWordListTrendRequest(DCQuerySensitiveWordListTrendRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.logid = other.logid;
    if (other.isSetLangugeType()) {
      this.langugeType = other.langugeType;
    }
    if (other.isSetStartDate()) {
      this.startDate = other.startDate;
    }
    if (other.isSetEndDate()) {
      this.endDate = other.endDate;
    }
    this.pageno = other.pageno;
    this.pagesize = other.pagesize;
  }

  public DCQuerySensitiveWordListTrendRequest deepCopy() {
    return new DCQuerySensitiveWordListTrendRequest(this);
  }

  @Override
  public void clear() {
    setLogidIsSet(false);
    this.logid = 0;
    this.langugeType = com.lighting.rpc.datacenter.model.DCCommonLanguge.DC_LAN_CHINESE_ZH_CN;

    this.startDate = null;
    this.endDate = null;
    this.pageno = 0;

    this.pagesize = 10;

  }

  public long getLogid() {
    return this.logid;
  }

  public DCQuerySensitiveWordListTrendRequest setLogid(long logid) {
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

  /**
   * 
   * @see DCCommonLanguge
   */
  public DCCommonLanguge getLangugeType() {
    return this.langugeType;
  }

  /**
   * 
   * @see DCCommonLanguge
   */
  public DCQuerySensitiveWordListTrendRequest setLangugeType(DCCommonLanguge langugeType) {
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

  public String getStartDate() {
    return this.startDate;
  }

  public DCQuerySensitiveWordListTrendRequest setStartDate(String startDate) {
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

  public DCQuerySensitiveWordListTrendRequest setEndDate(String endDate) {
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

  public int getPageno() {
    return this.pageno;
  }

  public DCQuerySensitiveWordListTrendRequest setPageno(int pageno) {
    this.pageno = pageno;
    setPagenoIsSet(true);
    return this;
  }

  public void unsetPageno() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PAGENO_ISSET_ID);
  }

  /** Returns true if field pageno is set (has been assigned a value) and false otherwise */
  public boolean isSetPageno() {
    return EncodingUtils.testBit(__isset_bitfield, __PAGENO_ISSET_ID);
  }

  public void setPagenoIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PAGENO_ISSET_ID, value);
  }

  public int getPagesize() {
    return this.pagesize;
  }

  public DCQuerySensitiveWordListTrendRequest setPagesize(int pagesize) {
    this.pagesize = pagesize;
    setPagesizeIsSet(true);
    return this;
  }

  public void unsetPagesize() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PAGESIZE_ISSET_ID);
  }

  /** Returns true if field pagesize is set (has been assigned a value) and false otherwise */
  public boolean isSetPagesize() {
    return EncodingUtils.testBit(__isset_bitfield, __PAGESIZE_ISSET_ID);
  }

  public void setPagesizeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PAGESIZE_ISSET_ID, value);
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

    case LANGUGE_TYPE:
      if (value == null) {
        unsetLangugeType();
      } else {
        setLangugeType((DCCommonLanguge)value);
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

    case PAGENO:
      if (value == null) {
        unsetPageno();
      } else {
        setPageno((Integer)value);
      }
      break;

    case PAGESIZE:
      if (value == null) {
        unsetPagesize();
      } else {
        setPagesize((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LOGID:
      return Long.valueOf(getLogid());

    case LANGUGE_TYPE:
      return getLangugeType();

    case START_DATE:
      return getStartDate();

    case END_DATE:
      return getEndDate();

    case PAGENO:
      return Integer.valueOf(getPageno());

    case PAGESIZE:
      return Integer.valueOf(getPagesize());

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
    case LANGUGE_TYPE:
      return isSetLangugeType();
    case START_DATE:
      return isSetStartDate();
    case END_DATE:
      return isSetEndDate();
    case PAGENO:
      return isSetPageno();
    case PAGESIZE:
      return isSetPagesize();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCQuerySensitiveWordListTrendRequest)
      return this.equals((DCQuerySensitiveWordListTrendRequest)that);
    return false;
  }

  public boolean equals(DCQuerySensitiveWordListTrendRequest that) {
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

    boolean this_present_langugeType = true && this.isSetLangugeType();
    boolean that_present_langugeType = true && that.isSetLangugeType();
    if (this_present_langugeType || that_present_langugeType) {
      if (!(this_present_langugeType && that_present_langugeType))
        return false;
      if (!this.langugeType.equals(that.langugeType))
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

    boolean this_present_pageno = true;
    boolean that_present_pageno = true;
    if (this_present_pageno || that_present_pageno) {
      if (!(this_present_pageno && that_present_pageno))
        return false;
      if (this.pageno != that.pageno)
        return false;
    }

    boolean this_present_pagesize = true;
    boolean that_present_pagesize = true;
    if (this_present_pagesize || that_present_pagesize) {
      if (!(this_present_pagesize && that_present_pagesize))
        return false;
      if (this.pagesize != that.pagesize)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCQuerySensitiveWordListTrendRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCQuerySensitiveWordListTrendRequest typedOther = (DCQuerySensitiveWordListTrendRequest)other;

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
    lastComparison = Boolean.valueOf(isSetPageno()).compareTo(typedOther.isSetPageno());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPageno()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pageno, typedOther.pageno);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPagesize()).compareTo(typedOther.isSetPagesize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPagesize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pagesize, typedOther.pagesize);
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
    StringBuilder sb = new StringBuilder("DCQuerySensitiveWordListTrendRequest(");
    boolean first = true;

    sb.append("logid:");
    sb.append(this.logid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("langugeType:");
    if (this.langugeType == null) {
      sb.append("null");
    } else {
      sb.append(this.langugeType);
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
    sb.append("pageno:");
    sb.append(this.pageno);
    first = false;
    if (!first) sb.append(", ");
    sb.append("pagesize:");
    sb.append(this.pagesize);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'logid' because it's a primitive and you chose the non-beans generator.
    if (langugeType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'langugeType' was not present! Struct: " + toString());
    }
    if (startDate == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'startDate' was not present! Struct: " + toString());
    }
    if (endDate == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'endDate' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'pageno' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'pagesize' because it's a primitive and you chose the non-beans generator.
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

  private static class DCQuerySensitiveWordListTrendRequestStandardSchemeFactory implements SchemeFactory {
    public DCQuerySensitiveWordListTrendRequestStandardScheme getScheme() {
      return new DCQuerySensitiveWordListTrendRequestStandardScheme();
    }
  }

  private static class DCQuerySensitiveWordListTrendRequestStandardScheme extends StandardScheme<DCQuerySensitiveWordListTrendRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCQuerySensitiveWordListTrendRequest struct) throws org.apache.thrift.TException {
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
          case 2: // LANGUGE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.langugeType = DCCommonLanguge.findByValue(iprot.readI32());
              struct.setLangugeTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // START_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.startDate = iprot.readString();
              struct.setStartDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // END_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.endDate = iprot.readString();
              struct.setEndDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PAGENO
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pageno = iprot.readI32();
              struct.setPagenoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // PAGESIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pagesize = iprot.readI32();
              struct.setPagesizeIsSet(true);
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
      if (!struct.isSetPageno()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'pageno' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetPagesize()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'pagesize' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCQuerySensitiveWordListTrendRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(LOGID_FIELD_DESC);
      oprot.writeI64(struct.logid);
      oprot.writeFieldEnd();
      if (struct.langugeType != null) {
        oprot.writeFieldBegin(LANGUGE_TYPE_FIELD_DESC);
        oprot.writeI32(struct.langugeType.getValue());
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
      oprot.writeFieldBegin(PAGENO_FIELD_DESC);
      oprot.writeI32(struct.pageno);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PAGESIZE_FIELD_DESC);
      oprot.writeI32(struct.pagesize);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCQuerySensitiveWordListTrendRequestTupleSchemeFactory implements SchemeFactory {
    public DCQuerySensitiveWordListTrendRequestTupleScheme getScheme() {
      return new DCQuerySensitiveWordListTrendRequestTupleScheme();
    }
  }

  private static class DCQuerySensitiveWordListTrendRequestTupleScheme extends TupleScheme<DCQuerySensitiveWordListTrendRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCQuerySensitiveWordListTrendRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.logid);
      oprot.writeI32(struct.langugeType.getValue());
      oprot.writeString(struct.startDate);
      oprot.writeString(struct.endDate);
      oprot.writeI32(struct.pageno);
      oprot.writeI32(struct.pagesize);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCQuerySensitiveWordListTrendRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.logid = iprot.readI64();
      struct.setLogidIsSet(true);
      struct.langugeType = DCCommonLanguge.findByValue(iprot.readI32());
      struct.setLangugeTypeIsSet(true);
      struct.startDate = iprot.readString();
      struct.setStartDateIsSet(true);
      struct.endDate = iprot.readString();
      struct.setEndDateIsSet(true);
      struct.pageno = iprot.readI32();
      struct.setPagenoIsSet(true);
      struct.pagesize = iprot.readI32();
      struct.setPagesizeIsSet(true);
    }
  }

}

