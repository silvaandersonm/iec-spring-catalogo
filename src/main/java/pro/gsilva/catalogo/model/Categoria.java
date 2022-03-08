package pro.gsilva.catalogo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="categoria")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_categoria")
    private Long id;

    @Column(name="nm_categoria", length=30, nullable=false)
    @NotBlank
    @Size(max=30, message="{javax.validation.constraints.Size.categoria.nome.message}")
    private String nome;

}
