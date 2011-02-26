package slim3.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-02-27 03:01:40")
/** */
public final class PracticeMeta extends org.slim3.datastore.ModelMeta<slim3.model.Practice> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.util.Date> endDate = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.util.Date>(this, "endDate", "endDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.util.Date> gatheringDate = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.util.Date>(this, "gatheringDate", "gatheringDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Practice> gatheringPoint = new org.slim3.datastore.StringAttributeMeta<slim3.model.Practice>(this, "gatheringPoint", "gatheringPoint");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<slim3.model.Practice, java.util.List<java.lang.String>> music = new org.slim3.datastore.StringCollectionAttributeMeta<slim3.model.Practice, java.util.List<java.lang.String>>(this, "music", "music", java.util.List.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Practice> practicePlace = new org.slim3.datastore.StringAttributeMeta<slim3.model.Practice>(this, "practicePlace", "practicePlace");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<slim3.model.Practice> recital = new org.slim3.datastore.StringAttributeMeta<slim3.model.Practice>(this, "recital", "recital");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.util.Date> startDate = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.util.Date>(this, "startDate", "startDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<slim3.model.Practice, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final PracticeMeta slim3_singleton = new PracticeMeta();

    /**
     * @return the singleton
     */
    public static PracticeMeta get() {
       return slim3_singleton;
    }

    /** */
    public PracticeMeta() {
        super("Practice", slim3.model.Practice.class);
    }

    @Override
    public slim3.model.Practice entityToModel(com.google.appengine.api.datastore.Entity entity) {
        slim3.model.Practice model = new slim3.model.Practice();
        model.setEndDate((java.util.Date) entity.getProperty("endDate"));
        model.setGatheringDate((java.util.Date) entity.getProperty("gatheringDate"));
        model.setGatheringPoint((java.lang.String) entity.getProperty("gatheringPoint"));
        model.setKey(entity.getKey());
        model.setMusic(toList(java.lang.String.class, entity.getProperty("music")));
        model.setPracticePlace((java.lang.String) entity.getProperty("practicePlace"));
        model.setRecital((java.lang.String) entity.getProperty("recital"));
        model.setStartDate((java.util.Date) entity.getProperty("startDate"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        slim3.model.Practice m = (slim3.model.Practice) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("endDate", m.getEndDate());
        entity.setProperty("gatheringDate", m.getGatheringDate());
        entity.setProperty("gatheringPoint", m.getGatheringPoint());
        entity.setProperty("music", m.getMusic());
        entity.setProperty("practicePlace", m.getPracticePlace());
        entity.setProperty("recital", m.getRecital());
        entity.setProperty("startDate", m.getStartDate());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        slim3.model.Practice m = (slim3.model.Practice) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        slim3.model.Practice m = (slim3.model.Practice) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        slim3.model.Practice m = (slim3.model.Practice) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        slim3.model.Practice m = (slim3.model.Practice) model;
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
        slim3.model.Practice m = (slim3.model.Practice) model;
        writer.beginObject();
        org.slim3.datastore.json.JsonCoder encoder = null;
        if(m.getEndDate() != null){
            writer.setNextPropertyName("endDate");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getEndDate());
        }
        if(m.getGatheringDate() != null){
            writer.setNextPropertyName("gatheringDate");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getGatheringDate());
        }
        if(m.getGatheringPoint() != null){
            writer.setNextPropertyName("gatheringPoint");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getGatheringPoint());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getKey());
        }
        if(m.getMusic() != null){
            writer.setNextPropertyName("music");
            encoder = new org.slim3.datastore.json.Default();
            writer.beginArray();
            for(java.lang.String v : m.getMusic()){
                encoder.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getPracticePlace() != null){
            writer.setNextPropertyName("practicePlace");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getPracticePlace());
        }
        if(m.getRecital() != null){
            writer.setNextPropertyName("recital");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getRecital());
        }
        if(m.getStartDate() != null){
            writer.setNextPropertyName("startDate");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getStartDate());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    public slim3.model.Practice jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        slim3.model.Practice m = new slim3.model.Practice();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.JsonCoder decoder = null;
        reader = rootReader.newObjectReader("endDate");
        decoder = new org.slim3.datastore.json.Default();
        m.setEndDate(decoder.decode(reader, m.getEndDate()));
        reader = rootReader.newObjectReader("gatheringDate");
        decoder = new org.slim3.datastore.json.Default();
        m.setGatheringDate(decoder.decode(reader, m.getGatheringDate()));
        reader = rootReader.newObjectReader("gatheringPoint");
        decoder = new org.slim3.datastore.json.Default();
        m.setGatheringPoint(decoder.decode(reader, m.getGatheringPoint()));
        reader = rootReader.newObjectReader("key");
        decoder = new org.slim3.datastore.json.Default();
        m.setKey(decoder.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("music");
        decoder = new org.slim3.datastore.json.Default();
        {
            java.util.ArrayList<java.lang.String> elements = new java.util.ArrayList<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("music");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder.decode(reader, (java.lang.String)null);
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setMusic(elements);
            }
        }
        reader = rootReader.newObjectReader("practicePlace");
        decoder = new org.slim3.datastore.json.Default();
        m.setPracticePlace(decoder.decode(reader, m.getPracticePlace()));
        reader = rootReader.newObjectReader("recital");
        decoder = new org.slim3.datastore.json.Default();
        m.setRecital(decoder.decode(reader, m.getRecital()));
        reader = rootReader.newObjectReader("startDate");
        decoder = new org.slim3.datastore.json.Default();
        m.setStartDate(decoder.decode(reader, m.getStartDate()));
        reader = rootReader.newObjectReader("version");
        decoder = new org.slim3.datastore.json.Default();
        m.setVersion(decoder.decode(reader, m.getVersion()));
    return m;
}
}