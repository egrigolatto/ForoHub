package forohub.api.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        String mensaje,
        String titulo
) {
}
