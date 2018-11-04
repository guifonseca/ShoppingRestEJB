
package br.com.shopping.test.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "exception",
    "fieldViolations",
    "propertyViolations",
    "classViolations",
    "parameterViolations",
    "returnValueViolations"
})
public class BeanValidationError {

    @JsonProperty("exception")
    private Object exception;
    @JsonProperty("fieldViolations")
    private List<Object> fieldViolations = null;
    @JsonProperty("propertyViolations")
    private List<Object> propertyViolations = null;
    @JsonProperty("classViolations")
    private List<Object> classViolations = null;
    @JsonProperty("parameterViolations")
    private List<ParameterViolation> parameterViolations = null;
    @JsonProperty("returnValueViolations")
    private List<Object> returnValueViolations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("exception")
    public Object getException() {
        return exception;
    }

    @JsonProperty("exception")
    public void setException(Object exception) {
        this.exception = exception;
    }

    @JsonProperty("fieldViolations")
    public List<Object> getFieldViolations() {
        return fieldViolations;
    }

    @JsonProperty("fieldViolations")
    public void setFieldViolations(List<Object> fieldViolations) {
        this.fieldViolations = fieldViolations;
    }

    @JsonProperty("propertyViolations")
    public List<Object> getPropertyViolations() {
        return propertyViolations;
    }

    @JsonProperty("propertyViolations")
    public void setPropertyViolations(List<Object> propertyViolations) {
        this.propertyViolations = propertyViolations;
    }

    @JsonProperty("classViolations")
    public List<Object> getClassViolations() {
        return classViolations;
    }

    @JsonProperty("classViolations")
    public void setClassViolations(List<Object> classViolations) {
        this.classViolations = classViolations;
    }

    @JsonProperty("parameterViolations")
    public List<ParameterViolation> getParameterViolations() {
        return parameterViolations;
    }

    @JsonProperty("parameterViolations")
    public void setParameterViolations(List<ParameterViolation> parameterViolations) {
        this.parameterViolations = parameterViolations;
    }

    @JsonProperty("returnValueViolations")
    public List<Object> getReturnValueViolations() {
        return returnValueViolations;
    }

    @JsonProperty("returnValueViolations")
    public void setReturnValueViolations(List<Object> returnValueViolations) {
        this.returnValueViolations = returnValueViolations;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
