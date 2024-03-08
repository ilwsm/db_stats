package org.kinocat.db_stats.service;

import com.mongodb.MongoBulkWriteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ImportJsonService {
    private final Logger log = LogManager.getLogger(this.getClass());

    private final MongoTemplate mongo;

    public ImportJsonService(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public int importTo(String jsonText) {

        Document document = Document.parse(jsonText);
        int counter = 0;
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            List<?> list;
            if (value instanceof Document) {
                list = List.of((Document) value);
            } else if (value instanceof List<?>) {
                list = (List<?>) value;
            } else throw new RuntimeException("Wrong document");
            mongo.dropCollection(key);
            //mongo.remove(new Query(), key);
            counter += insertInto(key, list);
        }
        return counter;
    }

    private int insertInto(String collection, List<?> mongoDocs) {
        try {
            Collection<?> inserts = mongo.insert(mongoDocs, collection);
            return inserts.size();
        } catch (DataIntegrityViolationException e) {
            log.error("importing docs", e);
            if (e.getCause() instanceof MongoBulkWriteException) {
                return ((MongoBulkWriteException) e.getCause()).getWriteResult()
                        .getInsertedCount();
            }
            return 0;
        }
    }
}
