package slim3.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-02-20 14:58:22")
/** */
public final class AttendanceMeta extends org.slim3.datastore.ModelMeta<slim3.model.Attendance> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Integer> attendance = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Integer>(this, "attendance", "attendance", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Boolean> finished = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Boolean>(this, "finished", "finished", boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Boolean> inputFlg = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Boolean>(this, "inputFlg", "inputFlg", boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, com.google.appengine.api.datastore.Key> memberKey = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, com.google.appengine.api.datastore.Key>(this, "memberKey", "memberKey", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<slim3.model.Attendance, org.slim3.datastore.ModelRef<slim3.model.Member>, slim3.model.Member> memberRef = new org.slim3.datastore.ModelRefAttributeMeta<slim3.model.Attendance, org.slim3.datastore.ModelRef<slim3.model.Member>, slim3.model.Member>(this, "memberRef", "memberRef", org.slim3.datastore.ModelRef.class, slim3.model.Member.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, com.google.appengine.api.datastore.Key> practiceKey = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, com.google.appengine.api.datastore.Key>(this, "practiceKey", "practiceKey", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<slim3.model.Attendance, org.slim3.datastore.ModelRef<slim3.model.Practice>, slim3.model.Practice> practiceRef = new org.slim3.datastore.ModelRefAttributeMeta<slim3.model.Attendance, org.slim3.datastore.ModelRef<slim3.model.Practice>, slim3.model.Practice>(this, "practiceRef", "practiceRef", org.slim3.datastore.ModelRef.class, slim3.model.Practice.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Attendance> racital = new org.slim3.datastore.StringAttributeMeta<slim3.model.Attendance>(this, "racital", "racital");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Attendance, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final AttendanceMeta slim3_singleton = new AttendanceMeta();

    /**
     * @return the singleton
     */
    public static AttendanceMeta get() {
       return slim3_singleton;
    }

    /** */
    public AttendanceMeta() {
        super("Attendance", slim3.model.Attendance.class);
    }

    @Override
    public slim3.model.Attendance entityToModel(com.google.appengine.api.datastore.Entity entity) {
        slim3.model.Attendance model = new slim3.model.Attendance();
        model.setAttendance(longToPrimitiveInt((java.lang.Long) entity.getProperty("attendance")));
        model.setFinished(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("finished")));
        model.setInputFlg(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("inputFlg")));
        model.setKey(entity.getKey());
        model.setMemberKey((com.google.appengine.api.datastore.Key) entity.getProperty("memberKey"));
        if (model.getMemberRef() == null) {
            throw new NullPointerException("The property(memberRef) is null.");
        }
        model.getMemberRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("memberRef"));
        model.setPracticeKey((com.google.appengine.api.datastore.Key) entity.getProperty("practiceKey"));
        if (model.getPracticeRef() == null) {
            throw new NullPointerException("The property(practiceRef) is null.");
        }
        model.getPracticeRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("practiceRef"));
        model.setRacital((java.lang.String) entity.getProperty("racital"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        slim3.model.Attendance m = (slim3.model.Attendance) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("attendance", m.getAttendance());
        entity.setProperty("finished", m.getFinished());
        entity.setProperty("inputFlg", m.getInputFlg());
        entity.setProperty("memberKey", m.getMemberKey());
        if (m.getMemberRef() == null) {
            throw new NullPointerException("The property(memberRef) must not be null.");
        }
        entity.setProperty("memberRef", m.getMemberRef().getKey());
        entity.setProperty("practiceKey", m.getPracticeKey());
        if (m.getPracticeRef() == null) {
            throw new NullPointerException("The property(practiceRef) must not be null.");
        }
        entity.setProperty("practiceRef", m.getPracticeRef().getKey());
        entity.setProperty("racital", m.getRacital());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        slim3.model.Attendance m = (slim3.model.Attendance) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        slim3.model.Attendance m = (slim3.model.Attendance) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        slim3.model.Attendance m = (slim3.model.Attendance) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        slim3.model.Attendance m = (slim3.model.Attendance) model;
        if (m.getMemberRef() == null) {
            throw new NullPointerException("The property(memberRef) must not be null.");
        }
        m.getMemberRef().assignKeyIfNecessary(ds);
        if (m.getPracticeRef() == null) {
            throw new NullPointerException("The property(practiceRef) must not be null.");
        }
        m.getPracticeRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        slim3.model.Attendance m = (slim3.model.Attendance) model;
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
        slim3.model.Attendance m = (slim3.model.Attendance) model;
        writer.beginObject();
        org.slim3.datastore.json.JsonCoder encoder = null;
        writer.setNextPropertyName("attendance");
        encoder = new org.slim3.datastore.json.Default();
        encoder.encode(writer, m.getAttendance());
        writer.setNextPropertyName("finished");
        encoder = new org.slim3.datastore.json.Default();
        encoder.encode(writer, m.getFinished());
        writer.setNextPropertyName("inputFlg");
        encoder = new org.slim3.datastore.json.Default();
        encoder.encode(writer, m.getInputFlg());
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getKey());
        }
        if(m.getMemberKey() != null){
            writer.setNextPropertyName("memberKey");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getMemberKey());
        }
        if(m.getMemberRef() != null && m.getMemberRef().getKey() != null){
            writer.setNextPropertyName("memberRef");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getMemberRef(), maxDepth, currentDepth);
        }
        if(m.getPracticeKey() != null){
            writer.setNextPropertyName("practiceKey");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getPracticeKey());
        }
        if(m.getPracticeRef() != null && m.getPracticeRef().getKey() != null){
            writer.setNextPropertyName("practiceRef");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getPracticeRef(), maxDepth, currentDepth);
        }
        if(m.getRacital() != null){
            writer.setNextPropertyName("racital");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getRacital());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    public slim3.model.Attendance jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        slim3.model.Attendance m = new slim3.model.Attendance();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.JsonCoder decoder = null;
        reader = rootReader.newObjectReader("attendance");
        decoder = new org.slim3.datastore.json.Default();
        m.setAttendance(decoder.decode(reader, m.getAttendance()));
        reader = rootReader.newObjectReader("finished");
        decoder = new org.slim3.datastore.json.Default();
        m.setFinished(decoder.decode(reader, m.getFinished()));
        reader = rootReader.newObjectReader("inputFlg");
        decoder = new org.slim3.datastore.json.Default();
        m.setInputFlg(decoder.decode(reader, m.getInputFlg()));
        reader = rootReader.newObjectReader("key");
        decoder = new org.slim3.datastore.json.Default();
        m.setKey(decoder.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("memberKey");
        decoder = new org.slim3.datastore.json.Default();
        m.setMemberKey(decoder.decode(reader, m.getMemberKey()));
        reader = rootReader.newObjectReader("memberRef");
        decoder = new org.slim3.datastore.json.Default();
        decoder.decode(reader, m.getMemberRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("practiceKey");
        decoder = new org.slim3.datastore.json.Default();
        m.setPracticeKey(decoder.decode(reader, m.getPracticeKey()));
        reader = rootReader.newObjectReader("practiceRef");
        decoder = new org.slim3.datastore.json.Default();
        decoder.decode(reader, m.getPracticeRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("racital");
        decoder = new org.slim3.datastore.json.Default();
        m.setRacital(decoder.decode(reader, m.getRacital()));
        reader = rootReader.newObjectReader("version");
        decoder = new org.slim3.datastore.json.Default();
        m.setVersion(decoder.decode(reader, m.getVersion()));
    return m;
}
}