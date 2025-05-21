package edu.com.javaesencial07salesapi.dto.provider;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Provider_DTO {
    private Long idProvider;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 50, message = "El nombre del proveedor no debe exceder los 50 caracteres")
    private String providerName;

    @NotBlank(message = "La dirección del proveedor es obligatoria")
    @Size(max = 150, message = "La dirección del proveedor no debe exceder los 150 caracteres")
    private String providerAddress;

    @NotNull(message = "El estado del proveedor (providerEnabled) no puede ser nulo")
    private Boolean providerEnabled;
}
