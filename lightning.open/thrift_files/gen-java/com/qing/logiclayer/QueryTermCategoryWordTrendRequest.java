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

public class QueryTermCategoryWordTrendRequest implements org.apache.thrift.TBase<QueryTermCategoryWordTrendRequest, QueryTermCategoryWordTrendRequest._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryTermCategoryWordTrendRequest");

  private static final org.apache.thrift.protocol.TField LANGUGE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("langugeType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TCTYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("tctype", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField STARTDATE_FIELD_DESC = new org.apache.thrift.protocol.TField("startdate", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField ENDDATE_FIELD_DESC = new org.apache.thrift.protocol.TField("enddate", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new QueryTermCategoryWordTrendRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new QueryTermCategoryWordTrendRequestTupleSchemeFactory());
  }

  /**
   * 
   * @see OpenCommonLanguge
   */
  public OpenCommonLanguge langugeType; // required
  /**
   * 
   * @see TermCategoryType
   */
  public TermCategoryType tctype; // required
  public String startdate; // required
  public String enddate; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see OpenCommonLanguge
     */
    LANGUGE_TYPE((short)1, "langugeType"),
    /**
     * 
     * @see TermCategoryType
     */
    TCTYPE((short)2, "tctype"),
    STARTDATE((short)3, "startdate"),
    ENDDATE((short)4, "enddate");

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
        case 2: // TCTYPE
          return TCTYPE;
        case 3: // STARTDATE
          return STARTDATE;
        case 4: // ENDDATE
          return ENDDATE;
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
    tmpMap.put(_Fields.TCTYPE, new org.apache.thrift.meta_data.FieldMetaData("tctype", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TermCategoryType.class)));
    tmpMap.put(_Fields.STARTDATE, new org.apache.thrift.meta_data.FieldMetaData("startdate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENDDATE, new org.apache.thrift.meta_data.FieldMetaData("enddate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryTermCategoryWordTrendRequest.class, metaDataMap);
  }

  public QueryTermCategoryWordTrendRequest() {
    this.langugeType = com.qing.logiclayer.OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;

    this.tctype = com.qing.logiclayer.TermCategoryType.TCT_HOTWORD_TYPE;

  }

  public QueryTermCategoryWordTrendRequest(
    OpenCommonLanguge langugeType,
    TermCategoryType tctype,
    String startdate,
    String enddate)
  {
    this();
    this.langugeType = langugeType;
    this.tctype = tctype;
    this.startdate = startdate;
    this.enddate = enddate;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryTermCategoryWordTrendRequest(QueryTermCategoryWordTrendRequest other) {
    if (other.isSetLangugeType()) {
      this.langugeType = other.langugeType;
    }
    if (other.isSetTctype()) {
      this.tctype = other.tctype;
    }
    if (other.isSetStartdate()) {
      this.startdate = other.startdate;
    }
    if (other.isSetEnddate()) {
      this.enddate = other.enddate;
    }
  }

  public QueryTermCategoryWordTrendRequest deepCopy() {
    return new QueryTermCategoryWordTrendRequest(this);
  }

  @Override
  public void clear() {
    this.langugeType = com.qing.logiclayer.OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;

    this.tctype = com.qing.logiclayer.TermCategoryType.TCT_HOTWORD_TYPE;

    this.startdate = null;
    this.enddate = null;
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
  public QueryTermCategoryWordTrendRequest setLangugeType(OpenCommonLanguge langugeType) {
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

  /**
   * 
   * @see TermCategoryType
   */
  public TermCategoryType getTctype() {
    return this.tctype;
  }

  /**
   * 
   * @see TermCategoryType
   */
  public QueryTermCategoryWordTrendRequest setTctype(TermCategoryType tctype) {
    this.tctype = tctype;
    return this;
  }

  public void unsetTctype() {
    this.tctype = null;
  }

  /** Returns true if field tctype is set (has been assigned a value) and false otherwise */
  public boolean isSetTctype() {
    return this.tctype != null;
  }

  public void setTctypeIsSet(boolean value) {
    if (!value) {
      this.tctype = null;
    }
  }

  public String getStartdate() {
    return this.startdate;
  }

  public QueryTermCategoryWordTrendRequest setStartdate(String startdate) {
    this.startdate = startdate;
    return this;
  }

  public void unsetStartdate() {
    this.startdate = null;
  }

  /** Returns true if field startdate is set (has been assigned a value) and false otherwise */
  public boolean isSetStartdate() {
    return this.startdate != null;
  }

  public void setStartdateIsSet(boolean value) {
    if (!value) {
      this.startdate = null;
    }
  }

  public String getEnddate() {
    return this.enddate;
  }

  public QueryTermCategoryWordTrendRequest setEnddate(String enddate) {
    this.enddate = enddate;
    return this;
  }

  public void unsetEnddate() {
    this.enddate = null;
  }

  /** Returns true if field enddate is set (has been assigned a value) and false otherwise */
  public boolean isSetEnddate() {
    return this.enddate != null;
  }

  public void setEnddateIsSet(boolean value) {
    if (!value) {
      this.enddate = null;
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

    case TCTYPE:
      if (value == null) {
        unsetTctype();
      } else {
        setTctype((TermCategoryType)value);
      }
      break;

    case STARTDATE:
      if (value == null) {
        unsetStartdate();
      } else {
        setStartdate((String)value);
      }
      break;

    case ENDDATE:
      if (value == null) {
        unsetEnddate();
      } else {
        setEnddate((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LANGUGE_TYPE:
      return getLangugeType();

    case TCTYPE:
      return getTctype();

    case STARTDATE:
      return getStartdate();

    case ENDDATE:
      return getEnddate();

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
    case TCTYPE:
      return isSetTctype();
    case STARTDATE:
      return isSetStartdate();
    case ENDDATE:
      return isSetEnddate();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryTermCategoryWordTrendRequest)
      return this.equals((QueryTermCategoryWordTrendRequest)that);
    return false;
  }

  public boolean equals(QueryTermCategoryWordTrendRequest that) {
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

    boolean this_present_tctype = true && this.isSetTctype();
    boolean that_present_tctype = true && that.isSetTctype();
    if (this_present_tctype || that_present_tctype) {
      if (!(this_present_tctype && that_present_tctype))
        return false;
      if (!this.tctype.equals(that.tctype))
        return false;
    }

    boolean this_present_startdate = true && this.isSetStartdate();
    boolean that_present_startdate = true && that.isSetStartdate();
    if (this_present_startdate || that_present_startdate) {
      if (!(this_present_startdate && that_present_startdate))
        return false;
      if (!this.startdate.equals(that.startdate))
        return false;
    }

    boolean this_present_enddate = true && this.isSetEnddate();
    boolean that_present_enddate = true && that.isSetEnddate();
    if (this_present_enddate || that_present_enddate) {
      if (!(this_present_enddate && that_present_enddate))
        return false;
      if (!this.enddate.equals(that.enddate))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(QueryTermCategoryWordTrendRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    QueryTermCategoryWordTrendRequest typedOther = (QueryTermCategoryWordTrendRequest)other;

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
    lastComparison = Boolean.valueOf(isSetTctype()).compareTo(typedOther.isSetTctype());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTctype()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tctype, typedOther.tctype);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStartdate()).compareTo(typedOther.isSetStartdate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartdate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startdate, typedOther.startdate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEnddate()).compareTo(typedOther.isSetEnddate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnddate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.enddate, typedOther.enddate);
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
    StringBuilder sb = new StringBuilder("QueryTermCategoryWordTrendRequest(");
    boolean first = true;

    sb.append("langugeType:");
    if (this.langugeType == null) {
      sb.append("null");
    } else {
      sb.append(this.langugeType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tctype:");
    if (this.tctype == null) {
      sb.append("null");
    } else {
      sb.append(this.tctype);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("startdate:");
    if (this.startdate == null) {
      sb.append("null");
    } else {
      sb.append(this.startdate);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("enddate:");
    if (this.enddate == null) {
      sb.append("null");
    } else {
      sb.append(this.enddate);
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
    if (tctype == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'tctype' was not present! Struct: " + toString());
    }
    if (startdate == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'startdate' was not present! Struct: " + toString());
    }
    if (enddate == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'enddate' was not present! Struct: " + toString());
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

  private static class QueryTermCategoryWordTrendRequestStandardSchemeFactory implements SchemeFactory {
    public QueryTermCategoryWordTrendRequestStandardScheme getScheme() {
      return new QueryTermCategoryWordTrendRequestStandardScheme();
    }
  }

  private static class QueryTermCategoryWordTrendRequestStandardScheme extends StandardScheme<QueryTermCategoryWordTrendRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryTermCategoryWordTrendRequest struct) throws org.apache.thrift.TException {
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
          case 2: // TCTYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tctype = TermCategoryType.findByValue(iprot.readI32());
              struct.setTctypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // STARTDATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.startdate = iprot.readString();
              struct.setStartdateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ENDDATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.enddate = iprot.readString();
              struct.setEnddateIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryTermCategoryWordTrendRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.langugeType != null) {
        oprot.writeFieldBegin(LANGUGE_TYPE_FIELD_DESC);
        oprot.writeI32(struct.langugeType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.tctype != null) {
        oprot.writeFieldBegin(TCTYPE_FIELD_DESC);
        oprot.writeI32(struct.tctype.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.startdate != null) {
        oprot.writeFieldBegin(STARTDATE_FIELD_DESC);
        oprot.writeString(struct.startdate);
        oprot.writeFieldEnd();
      }
      if (struct.enddate != null) {
        oprot.writeFieldBegin(ENDDATE_FIELD_DESC);
        oprot.writeString(struct.enddate);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryTermCategoryWordTrendRequestTupleSchemeFactory implements SchemeFactory {
    public QueryTermCategoryWordTrendRequestTupleScheme getScheme() {
      return new QueryTermCategoryWordTrendRequestTupleScheme();
    }
  }

  private static class QueryTermCategoryWordTrendRequestTupleScheme extends TupleScheme<QueryTermCategoryWordTrendRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryTermCategoryWordTrendRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.langugeType.getValue());
      oprot.writeI32(struct.tctype.getValue());
      oprot.writeString(struct.startdate);
      oprot.writeString(struct.enddate);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryTermCategoryWordTrendRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.langugeType = OpenCommonLanguge.findByValue(iprot.readI32());
      struct.setLangugeTypeIsSet(true);
      struct.tctype = TermCategoryType.findByValue(iprot.readI32());
      struct.setTctypeIsSet(true);
      struct.startdate = iprot.readString();
      struct.setStartdateIsSet(true);
      struct.enddate = iprot.readString();
      struct.setEnddateIsSet(true);
    }
  }

}

