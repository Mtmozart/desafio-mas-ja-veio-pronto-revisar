package br.com.alura.musicasBD.dados

import javax.persistence.EntityManager
import javax.persistence.Persistence

object Banco {
    fun getEntityManager(): EntityManager {
        val factory = Persistence.createEntityManagerFactory("screen_sound")
        return factory.createEntityManager()
    }
}