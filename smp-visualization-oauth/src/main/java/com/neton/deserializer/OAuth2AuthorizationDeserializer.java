package com.neton.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;

import static org.springframework.security.oauth2.server.authorization.OAuth2Authorization.withRegisteredClient;

/**
 * @author TheSunshine
 * @date 2024-10-30 15:53:56
 */
public class OAuth2AuthorizationDeserializer extends JsonDeserializer<OAuth2Authorization> {

    private RegisteredClientRepository registeredClientRepository;

    private static final TypeReference<Set<String>> SET_TYPE_REFERENCE = new TypeReference<>() {
    };

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<>() {
    };

    private static final TypeReference<AuthorizationGrantType> GRANT_TYPE_TYPE_REFERENCE = new TypeReference<>() {
    };

    public OAuth2AuthorizationDeserializer(RegisteredClientRepository registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public OAuth2Authorization deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Set<String> authorizedScopes = mapper.convertValue(jsonNode.get("authorizedScopes"), SET_TYPE_REFERENCE);
        Map<String, Object> attributes = mapper.convertValue(jsonNode.get("attributes"), MAP_TYPE_REFERENCE);
        JsonNode newNode = removeKeyRecursively(jsonNode, "metadata");
        Map<String, Object> tokens = mapper.convertValue(newNode.get("tokens"), MAP_TYPE_REFERENCE);

        AuthorizationGrantType grantType = mapper.convertValue(jsonNode.get("authorizationGrantType"), GRANT_TYPE_TYPE_REFERENCE);

        String id = readJsonNode(jsonNode, "id").asText();
        String registeredClientId = readJsonNode(jsonNode, "registeredClientId").asText();
        String principalName = readJsonNode(jsonNode, "principalName").asText();

        RegisteredClient registeredClient = registeredClientRepository.findById(registeredClientId);
        Assert.notNull(registeredClient, "Registered client must not be null");

        OAuth2Authorization.Builder builder = withRegisteredClient(registeredClient)
                .id(id)
                .principalName(principalName)
                .authorizationGrantType(grantType)
                .authorizedScopes(authorizedScopes)
                .attributes(map -> map.putAll(attributes));

        Optional.ofNullable(tokens.get(OAuth2AuthorizationCode.class.getName())).ifPresent(
                token -> addToken((OAuth2Authorization.Token) token, builder));
        Optional.ofNullable(tokens.get(OAuth2AccessToken.class.getName())).ifPresent(
                token -> addToken((OAuth2Authorization.Token) token, builder));
        Optional.ofNullable(tokens.get(OAuth2RefreshToken.class.getName())).ifPresent(
                token -> addToken((OAuth2Authorization.Token) token, builder));
        Optional.ofNullable(tokens.get(OidcIdToken.class.getName())).ifPresent(
                token -> addToken((OAuth2Authorization.Token) token, builder));

        return builder.build();
    }

    public void addToken(OAuth2Authorization.Token<OAuth2Token> token, OAuth2Authorization.Builder builder) {
        builder.token(token.getToken(), map -> map.putAll(token.getMetadata()));
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

    private static JsonNode removeKeyRecursively(JsonNode node, String keyToRemove) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            objectNode.remove(keyToRemove);

            // 递归处理每个字段
            for (Iterator<String> fieldNames = objectNode.fieldNames(); fieldNames.hasNext();) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = objectNode.get(fieldName);
                objectNode.set(fieldName, removeKeyRecursively(fieldValue, keyToRemove));
            }
            return objectNode;
        } else if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode element = arrayNode.get(i);
                arrayNode.set(i, removeKeyRecursively(element, keyToRemove));
            }
            return arrayNode;
        } else {
            return node;
        }
    }
}
