package br.com.alura.musicasBD.dados

import javax.persistence.EntityManager
import javax.persistence.NoResultException

abstract class DAO<TModel, TEntity>(
    val manager: EntityManager,
    val entityType: Class<TEntity>
) {

    abstract fun toEntity(model: TModel): TEntity
    abstract fun toModel(entity: TEntity): TModel

    open fun getLista(): List<TModel> {
        val query = manager
            .createQuery("FROM ${entityType.simpleName}", entityType)
        return query.resultList.map { entity -> toModel(entity) }
    }

    open fun adicionar(model: TModel) {
        val entity = toEntity(model)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

    open fun recuperarEntityPeloId(id: Int): TEntity? {
        val query = manager.createQuery(
            "FROM ${entityType.simpleName} WHERE id = :id",
            entityType
        )
        query.setParameter("id", id)

        return try {
            query.singleResult as TEntity
        } catch (e: NoResultException) {
            // Log o erro ou faça algum tratamento adicional se necessário
            null
        }
    }

    open fun recuperarPeloId(id: Int): TModel? {
        val entity = recuperarEntityPeloId(id)
        return entity?.let { toModel(it) }
    }

    open fun apagar(id: Int) {
        val entity = recuperarEntityPeloId(id)
        manager.transaction.begin()
        manager.remove(entity)
        manager.transaction.commit()
    }
}
