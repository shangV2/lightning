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

public class DCQueryTopicWebpagesResponse implements org.apache.thrift.TBase<DCQueryTopicWebpagesResponse, DCQueryTopicWebpagesResponse._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DCQueryTopicWebpagesResponse");

  private static final org.apache.thrift.protocol.TField WEBPAGES_FIELD_DESC = new org.apache.thrift.protocol.TField("webpages", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DCQueryTopicWebpagesResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DCQueryTopicWebpagesResponseTupleSchemeFactory());
  }

  public List<DCWebPage> webpages; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    WEBPAGES((short)1, "webpages");

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
        case 1: // WEBPAGES
          return WEBPAGES;
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
    tmpMap.put(_Fields.WEBPAGES, new org.apache.thrift.meta_data.FieldMetaData("webpages", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DCWebPage.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DCQueryTopicWebpagesResponse.class, metaDataMap);
  }

  public DCQueryTopicWebpagesResponse() {
  }

  public DCQueryTopicWebpagesResponse(
    List<DCWebPage> webpages)
  {
    this();
    this.webpages = webpages;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DCQueryTopicWebpagesResponse(DCQueryTopicWebpagesResponse other) {
    if (other.isSetWebpages()) {
      List<DCWebPage> __this__webpages = new ArrayList<DCWebPage>();
      for (DCWebPage other_element : other.webpages) {
        __this__webpages.add(new DCWebPage(other_element));
      }
      this.webpages = __this__webpages;
    }
  }

  public DCQueryTopicWebpagesResponse deepCopy() {
    return new DCQueryTopicWebpagesResponse(this);
  }

  @Override
  public void clear() {
    this.webpages = null;
  }

  public int getWebpagesSize() {
    return (this.webpages == null) ? 0 : this.webpages.size();
  }

  public java.util.Iterator<DCWebPage> getWebpagesIterator() {
    return (this.webpages == null) ? null : this.webpages.iterator();
  }

  public void addToWebpages(DCWebPage elem) {
    if (this.webpages == null) {
      this.webpages = new ArrayList<DCWebPage>();
    }
    this.webpages.add(elem);
  }

  public List<DCWebPage> getWebpages() {
    return this.webpages;
  }

  public DCQueryTopicWebpagesResponse setWebpages(List<DCWebPage> webpages) {
    this.webpages = webpages;
    return this;
  }

  public void unsetWebpages() {
    this.webpages = null;
  }

  /** Returns true if field webpages is set (has been assigned a value) and false otherwise */
  public boolean isSetWebpages() {
    return this.webpages != null;
  }

  public void setWebpagesIsSet(boolean value) {
    if (!value) {
      this.webpages = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case WEBPAGES:
      if (value == null) {
        unsetWebpages();
      } else {
        setWebpages((List<DCWebPage>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case WEBPAGES:
      return getWebpages();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case WEBPAGES:
      return isSetWebpages();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DCQueryTopicWebpagesResponse)
      return this.equals((DCQueryTopicWebpagesResponse)that);
    return false;
  }

  public boolean equals(DCQueryTopicWebpagesResponse that) {
    if (that == null)
      return false;

    boolean this_present_webpages = true && this.isSetWebpages();
    boolean that_present_webpages = true && that.isSetWebpages();
    if (this_present_webpages || that_present_webpages) {
      if (!(this_present_webpages && that_present_webpages))
        return false;
      if (!this.webpages.equals(that.webpages))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(DCQueryTopicWebpagesResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DCQueryTopicWebpagesResponse typedOther = (DCQueryTopicWebpagesResponse)other;

    lastComparison = Boolean.valueOf(isSetWebpages()).compareTo(typedOther.isSetWebpages());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWebpages()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.webpages, typedOther.webpages);
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
    StringBuilder sb = new StringBuilder("DCQueryTopicWebpagesResponse(");
    boolean first = true;

    sb.append("webpages:");
    if (this.webpages == null) {
      sb.append("null");
    } else {
      sb.append(this.webpages);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (webpages == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'webpages' was not present! Struct: " + toString());
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

  private static class DCQueryTopicWebpagesResponseStandardSchemeFactory implements SchemeFactory {
    public DCQueryTopicWebpagesResponseStandardScheme getScheme() {
      return new DCQueryTopicWebpagesResponseStandardScheme();
    }
  }

  private static class DCQueryTopicWebpagesResponseStandardScheme extends StandardScheme<DCQueryTopicWebpagesResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DCQueryTopicWebpagesResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // WEBPAGES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list80 = iprot.readListBegin();
                struct.webpages = new ArrayList<DCWebPage>(_list80.size);
                for (int _i81 = 0; _i81 < _list80.size; ++_i81)
                {
                  DCWebPage _elem82; // required
                  _elem82 = new DCWebPage();
                  _elem82.read(iprot);
                  struct.webpages.add(_elem82);
                }
                iprot.readListEnd();
              }
              struct.setWebpagesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, DCQueryTopicWebpagesResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.webpages != null) {
        oprot.writeFieldBegin(WEBPAGES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.webpages.size()));
          for (DCWebPage _iter83 : struct.webpages)
          {
            _iter83.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DCQueryTopicWebpagesResponseTupleSchemeFactory implements SchemeFactory {
    public DCQueryTopicWebpagesResponseTupleScheme getScheme() {
      return new DCQueryTopicWebpagesResponseTupleScheme();
    }
  }

  private static class DCQueryTopicWebpagesResponseTupleScheme extends TupleScheme<DCQueryTopicWebpagesResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DCQueryTopicWebpagesResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.webpages.size());
        for (DCWebPage _iter84 : struct.webpages)
        {
          _iter84.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DCQueryTopicWebpagesResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list85 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.webpages = new ArrayList<DCWebPage>(_list85.size);
        for (int _i86 = 0; _i86 < _list85.size; ++_i86)
        {
          DCWebPage _elem87; // required
          _elem87 = new DCWebPage();
          _elem87.read(iprot);
          struct.webpages.add(_elem87);
        }
      }
      struct.setWebpagesIsSet(true);
    }
  }

}

