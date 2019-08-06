package cn.lhl.web.dwk01.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.keycloak.representations.AccessToken;

/**
 * Created by Administrator on 2019/6/13.
 */
@Data
public class MyKeycloakToken extends AccessToken {
    @JsonProperty("employeeNumber")
    protected String employeeNumber;
    @JsonProperty("departmentName")
    protected String departmentName;
}
