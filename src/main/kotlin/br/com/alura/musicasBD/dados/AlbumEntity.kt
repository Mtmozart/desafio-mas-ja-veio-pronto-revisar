package br.com.alura.musicasBD.dados

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "albuns")
class AlbumEntity(
    val titulo: String = "Título do álbum",
    @ManyToOne
    val banda: BandaEntity = BandaEntity(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
){
    override fun toString(): String {
        return "AlbumEntity(titulo='$titulo', banda=$banda, id=$id)"
    }
}