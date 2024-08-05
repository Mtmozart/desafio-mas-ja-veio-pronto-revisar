package br.com.alura.musicasBD.principal

import br.com.alura.musicasBD.dados.AlbumDAO
import br.com.alura.musicasBD.dados.Banco
import br.com.alura.musicasBD.dados.BandaDAO
import br.com.alura.musicasBD.modelos.Album
import br.com.alura.musicasBD.modelos.Banda

fun main() {


    val manager = Banco.getEntityManager()
    val albumDAO = AlbumDAO(manager)
    val bandaDao = BandaDAO(manager)
    val titas = bandaDao.recuperarPeloId(1)
    val goBack = titas?.let { Album("Go back", it) }
    val cabecaDeDinossauro = titas?.let { Album("Cabeça de dinossauro", it) }

    if (cabecaDeDinossauro != null) {
        albumDAO.adicionar(cabecaDeDinossauro)
    }
    if (goBack != null) {
        albumDAO.adicionar(goBack)
    }
    val listaAlbuns = albumDAO.getLista()
    println(listaAlbuns)


}