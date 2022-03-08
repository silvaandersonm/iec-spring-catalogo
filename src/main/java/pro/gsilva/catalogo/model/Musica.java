package pro.gsilva.catalogo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="musica")
@Data
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_musica")
    private Long id;

    @Column(name="ds_titulo", length=60, nullable=false)
    @NotBlank
    private String titulo;

    @Column(name="nm_autor", length=60, nullable=false)
    @NotBlank
    private String autor;

    @Column(name="dt_lancamento", nullable=false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", locale = "UTC-03")
    private LocalDate data;

    @Column(name="ds_letra", nullable=false)
    @NotBlank
    @Lob
    private String letra;

    @ManyToOne
	@JoinColumn(name="id_categoria", nullable=false)
	private Categoria categoria;

}
