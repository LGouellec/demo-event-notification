/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.example.demo.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Notification extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3528149056191755530L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Notification\",\"namespace\":\"org.example.demo.avro\",\"fields\":[{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"ruleId\",\"type\":\"int\"},{\"name\":\"device\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"descriptionRule\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"destination\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Notification> ENCODER =
      new BinaryMessageEncoder<Notification>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Notification> DECODER =
      new BinaryMessageDecoder<Notification>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Notification> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Notification> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Notification> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Notification>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Notification to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Notification from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Notification instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Notification fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String name;
  private java.lang.String type;
  private int ruleId;
  private java.lang.String device;
  private java.lang.String descriptionRule;
  private java.lang.String destination;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Notification() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   * @param type The new value for type
   * @param ruleId The new value for ruleId
   * @param device The new value for device
   * @param descriptionRule The new value for descriptionRule
   * @param destination The new value for destination
   */
  public Notification(java.lang.String name, java.lang.String type, java.lang.Integer ruleId, java.lang.String device, java.lang.String descriptionRule, java.lang.String destination) {
    this.name = name;
    this.type = type;
    this.ruleId = ruleId;
    this.device = device;
    this.descriptionRule = descriptionRule;
    this.destination = destination;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return type;
    case 2: return ruleId;
    case 3: return device;
    case 4: return descriptionRule;
    case 5: return destination;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = value$ != null ? value$.toString() : null; break;
    case 1: type = value$ != null ? value$.toString() : null; break;
    case 2: ruleId = (java.lang.Integer)value$; break;
    case 3: device = value$ != null ? value$.toString() : null; break;
    case 4: descriptionRule = value$ != null ? value$.toString() : null; break;
    case 5: destination = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.String getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.String getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.String value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'ruleId' field.
   * @return The value of the 'ruleId' field.
   */
  public int getRuleId() {
    return ruleId;
  }


  /**
   * Sets the value of the 'ruleId' field.
   * @param value the value to set.
   */
  public void setRuleId(int value) {
    this.ruleId = value;
  }

  /**
   * Gets the value of the 'device' field.
   * @return The value of the 'device' field.
   */
  public java.lang.String getDevice() {
    return device;
  }


  /**
   * Sets the value of the 'device' field.
   * @param value the value to set.
   */
  public void setDevice(java.lang.String value) {
    this.device = value;
  }

  /**
   * Gets the value of the 'descriptionRule' field.
   * @return The value of the 'descriptionRule' field.
   */
  public java.lang.String getDescriptionRule() {
    return descriptionRule;
  }


  /**
   * Sets the value of the 'descriptionRule' field.
   * @param value the value to set.
   */
  public void setDescriptionRule(java.lang.String value) {
    this.descriptionRule = value;
  }

  /**
   * Gets the value of the 'destination' field.
   * @return The value of the 'destination' field.
   */
  public java.lang.String getDestination() {
    return destination;
  }


  /**
   * Sets the value of the 'destination' field.
   * @param value the value to set.
   */
  public void setDestination(java.lang.String value) {
    this.destination = value;
  }

  /**
   * Creates a new Notification RecordBuilder.
   * @return A new Notification RecordBuilder
   */
  public static org.example.demo.avro.Notification.Builder newBuilder() {
    return new org.example.demo.avro.Notification.Builder();
  }

  /**
   * Creates a new Notification RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Notification RecordBuilder
   */
  public static org.example.demo.avro.Notification.Builder newBuilder(org.example.demo.avro.Notification.Builder other) {
    if (other == null) {
      return new org.example.demo.avro.Notification.Builder();
    } else {
      return new org.example.demo.avro.Notification.Builder(other);
    }
  }

  /**
   * Creates a new Notification RecordBuilder by copying an existing Notification instance.
   * @param other The existing instance to copy.
   * @return A new Notification RecordBuilder
   */
  public static org.example.demo.avro.Notification.Builder newBuilder(org.example.demo.avro.Notification other) {
    if (other == null) {
      return new org.example.demo.avro.Notification.Builder();
    } else {
      return new org.example.demo.avro.Notification.Builder(other);
    }
  }

  /**
   * RecordBuilder for Notification instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Notification>
    implements org.apache.avro.data.RecordBuilder<Notification> {

    private java.lang.String name;
    private java.lang.String type;
    private int ruleId;
    private java.lang.String device;
    private java.lang.String descriptionRule;
    private java.lang.String destination;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.example.demo.avro.Notification.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.ruleId)) {
        this.ruleId = data().deepCopy(fields()[2].schema(), other.ruleId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.device)) {
        this.device = data().deepCopy(fields()[3].schema(), other.device);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.descriptionRule)) {
        this.descriptionRule = data().deepCopy(fields()[4].schema(), other.descriptionRule);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.destination)) {
        this.destination = data().deepCopy(fields()[5].schema(), other.destination);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing Notification instance
     * @param other The existing instance to copy.
     */
    private Builder(org.example.demo.avro.Notification other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ruleId)) {
        this.ruleId = data().deepCopy(fields()[2].schema(), other.ruleId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.device)) {
        this.device = data().deepCopy(fields()[3].schema(), other.device);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.descriptionRule)) {
        this.descriptionRule = data().deepCopy(fields()[4].schema(), other.descriptionRule);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.destination)) {
        this.destination = data().deepCopy(fields()[5].schema(), other.destination);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder setName(java.lang.String value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.String getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder setType(java.lang.String value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'ruleId' field.
      * @return The value.
      */
    public int getRuleId() {
      return ruleId;
    }


    /**
      * Sets the value of the 'ruleId' field.
      * @param value The value of 'ruleId'.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder setRuleId(int value) {
      validate(fields()[2], value);
      this.ruleId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'ruleId' field has been set.
      * @return True if the 'ruleId' field has been set, false otherwise.
      */
    public boolean hasRuleId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'ruleId' field.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder clearRuleId() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'device' field.
      * @return The value.
      */
    public java.lang.String getDevice() {
      return device;
    }


    /**
      * Sets the value of the 'device' field.
      * @param value The value of 'device'.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder setDevice(java.lang.String value) {
      validate(fields()[3], value);
      this.device = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'device' field has been set.
      * @return True if the 'device' field has been set, false otherwise.
      */
    public boolean hasDevice() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'device' field.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder clearDevice() {
      device = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'descriptionRule' field.
      * @return The value.
      */
    public java.lang.String getDescriptionRule() {
      return descriptionRule;
    }


    /**
      * Sets the value of the 'descriptionRule' field.
      * @param value The value of 'descriptionRule'.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder setDescriptionRule(java.lang.String value) {
      validate(fields()[4], value);
      this.descriptionRule = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'descriptionRule' field has been set.
      * @return True if the 'descriptionRule' field has been set, false otherwise.
      */
    public boolean hasDescriptionRule() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'descriptionRule' field.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder clearDescriptionRule() {
      descriptionRule = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'destination' field.
      * @return The value.
      */
    public java.lang.String getDestination() {
      return destination;
    }


    /**
      * Sets the value of the 'destination' field.
      * @param value The value of 'destination'.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder setDestination(java.lang.String value) {
      validate(fields()[5], value);
      this.destination = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'destination' field has been set.
      * @return True if the 'destination' field has been set, false otherwise.
      */
    public boolean hasDestination() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'destination' field.
      * @return This builder.
      */
    public org.example.demo.avro.Notification.Builder clearDestination() {
      destination = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Notification build() {
      try {
        Notification record = new Notification();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.String) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (java.lang.String) defaultValue(fields()[1]);
        record.ruleId = fieldSetFlags()[2] ? this.ruleId : (java.lang.Integer) defaultValue(fields()[2]);
        record.device = fieldSetFlags()[3] ? this.device : (java.lang.String) defaultValue(fields()[3]);
        record.descriptionRule = fieldSetFlags()[4] ? this.descriptionRule : (java.lang.String) defaultValue(fields()[4]);
        record.destination = fieldSetFlags()[5] ? this.destination : (java.lang.String) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Notification>
    WRITER$ = (org.apache.avro.io.DatumWriter<Notification>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Notification>
    READER$ = (org.apache.avro.io.DatumReader<Notification>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.name);

    out.writeString(this.type);

    out.writeInt(this.ruleId);

    out.writeString(this.device);

    out.writeString(this.descriptionRule);

    out.writeString(this.destination);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.name = in.readString();

      this.type = in.readString();

      this.ruleId = in.readInt();

      this.device = in.readString();

      this.descriptionRule = in.readString();

      this.destination = in.readString();

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.name = in.readString();
          break;

        case 1:
          this.type = in.readString();
          break;

        case 2:
          this.ruleId = in.readInt();
          break;

        case 3:
          this.device = in.readString();
          break;

        case 4:
          this.descriptionRule = in.readString();
          break;

        case 5:
          this.destination = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









