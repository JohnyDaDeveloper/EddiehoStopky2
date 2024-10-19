package cz.johnyapps.eddiehostopky.migration.domain

interface MigrationRepository {

    suspend fun getMigratedToVersion(): Int

    suspend fun setMigratedToVersion(version: Int)
}
