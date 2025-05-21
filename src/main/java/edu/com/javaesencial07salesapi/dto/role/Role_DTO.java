package edu.com.javaesencial07salesapi.dto.role;

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
public class Role_DTO {

    @NotNull(message = "El id del rol no puede ser nulo")
    private Long idRole;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 20, message = "El nombre del rol no debe exceder los 20 caracteres")
    private String roleName;

    @NotNull(message = "El estado del rol (roleEnabled) no puede ser nulo")
    private Boolean roleEnabled;
}
