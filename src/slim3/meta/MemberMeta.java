package slim3.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-02-27 05:55:32")
/** */
public final class MemberMeta extends org.slim3.datastore.ModelMeta<slim3.model.Member> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> address = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "address", "address");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> address2 = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "address2", "address2");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, java.lang.Integer> authority = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, java.lang.Integer>(this, "authority", "authority", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, java.util.Date> birthDay = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, java.util.Date>(this, "birthDay", "birthDay", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> firstName = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "firstName", "firstName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> id = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "id", "id");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> lastName = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "lastName", "lastName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> mailAddress = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "mailAddress", "mailAddress");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> part = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "part", "part");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> password = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "password", "password");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> telNo = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "telNo", "telNo");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Member, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Member> workPlace = new org.slim3.datastore.StringAttributeMeta<slim3.model.Member>(this, "workPlace", "workPlace");

    private static final MemberMeta slim3_singleton = new MemberMeta();

    /**
     * @return the singleton
     */
    public static MemberMeta get() {
       return slim3_singleton;
    }

    /** */
    public MemberMeta() {
        super("Member", slim3.model.Member.class);
    }

    @Override
    public slim3.model.Member entityToModel(com.google.appengine.api.datastore.Entity entity) {
        slim3.model.Member model = new slim3.model.Member();
        model.setAddress((java.lang.String) entity.getProperty("address"));
        model.setAddress2((java.lang.String) entity.getProperty("address2"));
        model.setAuthority(longToPrimitiveInt((java.lang.Long) entity.getProperty("authority")));
        model.setBirthDay((java.util.Date) entity.getProperty("birthDay"));
        model.setFirstName((java.lang.String) entity.getProperty("firstName"));
        model.setId((java.lang.String) entity.getProperty("id"));
        model.setKey(entity.getKey());
        model.setLastName((java.lang.String) entity.getProperty("lastName"));
        model.setMailAddress((java.lang.String) entity.getProperty("mailAddress"));
        model.setPart((java.lang.String) entity.getProperty("part"));
        model.setPassword((java.lang.String) entity.getProperty("password"));
        model.setTelNo((java.lang.String) entity.getProperty("telNo"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setWorkPlace((java.lang.String) entity.getProperty("workPlace"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        slim3.model.Member m = (slim3.model.Member) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("address", m.getAddress());
        entity.setProperty("address2", m.getAddress2());
        entity.setProperty("authority", m.getAuthority());
        entity.setProperty("birthDay", m.getBirthDay());
        entity.setProperty("firstName", m.getFirstName());
        entity.setProperty("id", m.getId());
        entity.setProperty("lastName", m.getLastName());
        entity.setProperty("mailAddress", m.getMailAddress());
        entity.setProperty("part", m.getPart());
        entity.setProperty("password", m.getPassword());
        entity.setProperty("telNo", m.getTelNo());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("workPlace", m.getWorkPlace());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        slim3.model.Member m = (slim3.model.Member) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        slim3.model.Member m = (slim3.model.Member) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        slim3.model.Member m = (slim3.model.Member) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        slim3.model.Member m = (slim3.model.Member) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        slim3.model.Member m = (slim3.model.Member) model;
        writer.beginObject();
        org.slim3.datastore.json.JsonCoder encoder = null;
        if(m.getAddress() != null){
            writer.setNextPropertyName("address");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getAddress());
        }
        if(m.getAddress2() != null){
            writer.setNextPropertyName("address2");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getAddress2());
        }
        writer.setNextPropertyName("authority");
        encoder = new org.slim3.datastore.json.Default();
        encoder.encode(writer, m.getAuthority());
        if(m.getBirthDay() != null){
            writer.setNextPropertyName("birthDay");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getBirthDay());
        }
        if(m.getFirstName() != null){
            writer.setNextPropertyName("firstName");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getFirstName());
        }
        if(m.getId() != null){
            writer.setNextPropertyName("id");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getId());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getKey());
        }
        if(m.getLastName() != null){
            writer.setNextPropertyName("lastName");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getLastName());
        }
        if(m.getMailAddress() != null){
            writer.setNextPropertyName("mailAddress");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getMailAddress());
        }
        if(m.getPart() != null){
            writer.setNextPropertyName("part");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getPart());
        }
        if(m.getPassword() != null){
            writer.setNextPropertyName("password");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getPassword());
        }
        if(m.getTelNo() != null){
            writer.setNextPropertyName("telNo");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getTelNo());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getVersion());
        }
        if(m.getWorkPlace() != null){
            writer.setNextPropertyName("workPlace");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getWorkPlace());
        }
        writer.endObject();
    }

    @Override
    public slim3.model.Member jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        slim3.model.Member m = new slim3.model.Member();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.JsonCoder decoder = null;
        reader = rootReader.newObjectReader("address");
        decoder = new org.slim3.datastore.json.Default();
        m.setAddress(decoder.decode(reader, m.getAddress()));
        reader = rootReader.newObjectReader("address2");
        decoder = new org.slim3.datastore.json.Default();
        m.setAddress2(decoder.decode(reader, m.getAddress2()));
        reader = rootReader.newObjectReader("authority");
        decoder = new org.slim3.datastore.json.Default();
        m.setAuthority(decoder.decode(reader, m.getAuthority()));
        reader = rootReader.newObjectReader("birthDay");
        decoder = new org.slim3.datastore.json.Default();
        m.setBirthDay(decoder.decode(reader, m.getBirthDay()));
        reader = rootReader.newObjectReader("firstName");
        decoder = new org.slim3.datastore.json.Default();
        m.setFirstName(decoder.decode(reader, m.getFirstName()));
        reader = rootReader.newObjectReader("id");
        decoder = new org.slim3.datastore.json.Default();
        m.setId(decoder.decode(reader, m.getId()));
        reader = rootReader.newObjectReader("key");
        decoder = new org.slim3.datastore.json.Default();
        m.setKey(decoder.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("lastName");
        decoder = new org.slim3.datastore.json.Default();
        m.setLastName(decoder.decode(reader, m.getLastName()));
        reader = rootReader.newObjectReader("mailAddress");
        decoder = new org.slim3.datastore.json.Default();
        m.setMailAddress(decoder.decode(reader, m.getMailAddress()));
        reader = rootReader.newObjectReader("part");
        decoder = new org.slim3.datastore.json.Default();
        m.setPart(decoder.decode(reader, m.getPart()));
        reader = rootReader.newObjectReader("password");
        decoder = new org.slim3.datastore.json.Default();
        m.setPassword(decoder.decode(reader, m.getPassword()));
        reader = rootReader.newObjectReader("telNo");
        decoder = new org.slim3.datastore.json.Default();
        m.setTelNo(decoder.decode(reader, m.getTelNo()));
        reader = rootReader.newObjectReader("version");
        decoder = new org.slim3.datastore.json.Default();
        m.setVersion(decoder.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("workPlace");
        decoder = new org.slim3.datastore.json.Default();
        m.setWorkPlace(decoder.decode(reader, m.getWorkPlace()));
    return m;
}
}