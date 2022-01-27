package Transformations;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.bson.Document;

import java.util.Objects;

public class GetLatestDocumentInPCollectionTransformation extends DoFn<KV<String, Iterable<Document>>, Document> {

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<Document>> input, OutputReceiver<Document> outputReceiver) {
        Document document = new Document();

        for (Document inputDocument : Objects.requireNonNull(input.getValue())) {
            document = inputDocument;
        }

        outputReceiver.output(document);
    }

}
