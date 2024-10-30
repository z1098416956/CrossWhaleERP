package com.neton.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.neton.entity.AccAccountDO;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author TheSunshine
 * @date 2024-10-30 16:19:19
 */
public class AccountDeserializer extends JsonDeserializer<AccAccountDO> {
    private static final TypeReference<Set<GrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<>() {};

    @Override
    public AccAccountDO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Set<GrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"), SIMPLE_GRANTED_AUTHORITY_SET);
        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        String accountNo = readJsonNode(jsonNode, "accountNo").asText();
        String accountName = readJsonNode(jsonNode, "accountName").asText();
        String accountPassword = passwordNode.asText("");
        Long id = readJsonNode(jsonNode, "id").asLong();
        Long createBy = readJsonNode(jsonNode, "createBy").asLong();
        String createByName = readJsonNode(jsonNode, "createByName").asText();
        LocalDateTime gmtCreate = mapper.convertValue(readJsonNode(jsonNode, "gmtCreate"), LocalDateTime.class);
        Long modifyBy = readJsonNode(jsonNode, "modifyBy").asLong();
        String modifyByName = readJsonNode(jsonNode, "modifyByName").asText();
        LocalDateTime gmtModify = mapper.convertValue(readJsonNode(jsonNode, "gmtModify"), LocalDateTime.class);
        int isDeleted = readJsonNode(jsonNode, "isDeleted").asInt();
        Long version = readJsonNode(jsonNode, "version").asLong();
        boolean enabled = readJsonNode(jsonNode, "enabled").asBoolean();
        AccAccountDO result = new AccAccountDO();
        result.setId(id);
        result.setAccountNo(accountNo);
        result.setAccountName(accountName);
        result.setAccountPassword(accountPassword);
        result.setEnabled(enabled);
        result.setAuthorities(authorities);
        result.setCreateBy(createBy);
        result.setCreateByName(createByName);
        result.setGmtCreate(gmtCreate);
        result.setModifyBy(modifyBy);
        result.setModifyByName(modifyByName);
        result.setGmtModify(gmtModify);
        result.setIsDeleted(isDeleted);
        result.setVersion(version);
        return result;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
