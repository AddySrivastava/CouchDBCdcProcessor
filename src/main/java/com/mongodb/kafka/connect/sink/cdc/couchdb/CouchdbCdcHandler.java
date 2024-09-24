package com.mongodb.kafka.connect.sink.cdc.couchdb;

import com.mongodb.client.model.WriteModel;
import com.mongodb.kafka.connect.sink.cdc.CdcOperation;
import com.mongodb.kafka.connect.sink.MongoSinkTopicConfig;
import com.mongodb.kafka.connect.sink.converter.SinkDocument;
import org.bson.BsonDocument;
import com.mongodb.kafka.connect.sink.cdc.CdcHandler;
import com.mongodb.kafka.connect.sink.cdc.couchdb.operations.Delete;
import com.mongodb.kafka.connect.sink.cdc.couchdb.operations.Insert;
import com.mongodb.kafka.connect.sink.cdc.couchdb.operations.Replace;
import com.mongodb.kafka.connect.sink.cdc.couchdb.operations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CouchdbCdcHandler extends CdcHandler {
    private static final String OPERATION_TYPE = "operationType";
    private static final Map<OperationType, CdcOperation> OPERATIONS =
            Collections.unmodifiableMap(
                    new HashMap<OperationType, CdcOperation>() {
                        {
                            put(OperationType.INSERT, new Insert());
                            put(OperationType.REPLACE, new Replace());
                            put(OperationType.UPDATE, new Update());
                            put(OperationType.DELETE, new Delete());
                        }
                    });
    private static final Logger LOGGER = LoggerFactory.getLogger(CouchdbCdcHandler.class);

    public CouchdbCdcHandler(final MongoSinkTopicConfig config) {
        super(config);
    }

    @Override
    public Optional<WriteModel<BsonDocument>> handle(SinkDocument doc) {
        BsonDocument couchdbChangeDocumentValue = OperationHelper.getDocumentValue(doc);

        if (couchdbChangeDocumentValue.isEmpty()) return Optional.of(OPERATIONS.get(OperationType.DELETE).perform(doc));

        return Optional.of(OPERATIONS.get(OperationType.INSERT).perform(doc));

    }
}
