import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.CosmosAsyncDatabase;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.implementation.InternalObjectNode;
import com.azure.cosmos.models.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Follow https://docs.microsoft.com/en-us/azure/azure-monitor/app/java-in-process-agent#enable-azure-monitor-application-insights In order to run this program
 * configure appinsightkeys in applicationinsights.json
 * configure cosmosdb keys below
 */
public class CosmosTracingSample {
    final static String ENDPOINT = <endpoint>;
    final static String KEY = <key>;

    private static final Logger LOGGER = getLogger("Sample");

    public static void main(String[] args) {
        runDemo();
        LOGGER.info("=== Completed  ===");
    }

    private static void runDemo() {
        try (CosmosAsyncClient client = new CosmosClientBuilder()
                .endpoint(ENDPOINT)
                .key(KEY)
                .buildAsyncClient()) {

            LOGGER.info("=== Start Operations  ===");
            CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists("passengers", ThroughputProperties.createManualThroughput(5000)).block();
            CosmosAsyncDatabase cosmosAsyncDatabase = client.getDatabase(databaseResponse.getProperties().getId());
            LOGGER.info("createDatabaseIfNotExists");

            String query = "select * from c where c.id = 'passengers'";
            client.queryDatabases(query, new CosmosQueryRequestOptions()).byPage().single().block();
            LOGGER.info("queryDatabases ");

            CosmosContainerProperties containerProperties = new CosmosContainerProperties("testCon", "/pk");
            CosmosContainerResponse containerResponse = cosmosAsyncDatabase.createContainerIfNotExists(containerProperties).block();
            LOGGER.info("createContainerIfNotExists completed ");

            CosmosAsyncContainer cosmosAsyncContainer = cosmosAsyncDatabase.getContainer(containerResponse.getProperties().getId());
            cosmosAsyncDatabase.readThroughput().block();
            LOGGER.info("readProvisionedThroughput");

            InternalObjectNode properties = new InternalObjectNode();
            properties.setId("testDoc");
            CosmosItemResponse itemResponse = cosmosAsyncContainer.createItem(properties).block();
            LOGGER.info("createItem completed ");

            cosmosAsyncContainer.upsertItem(properties, new CosmosItemRequestOptions()).block();
            LOGGER.info("upsertItem completed ");

            cosmosAsyncContainer.readItem("testDoc", PartitionKey.NONE, InternalObjectNode.class).block();
            cosmosAsyncDatabase.delete().block();
            LOGGER.info("deleteDatabase");

            LOGGER.info("=== End Operations  ===");
        }
    }
}
