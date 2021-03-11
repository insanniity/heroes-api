package com.insannity.heroes.config;

import static com.insannity.heroes.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;
import static com.insannity.heroes.constans.HeroesConstant.ENDPOINT_DYNAMO;
import static com.insannity.heroes.constans.HeroesConstant.REGION_DYNAMO;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.apache.commons.lang3.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;


public class HeroesData {
    public static void main(String[] args) throws Exception {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration( new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO)).build();
        DynamoDB dynamoDB = new DynamoDB( client );

        Table table = dynamoDB.getTable("heroes_table");

        Item hero = new Item()
                .withPrimaryKey("id", 1)
                .withString( "nome", "Mulher maravilha" )
                .withString( "universo", "DC" )
                .withNumber( "filmes", 3 );

        PutItemOutcome outcome = table.putItem( hero );

    }

}
